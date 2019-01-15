
//included libraries via header files
#include <string.h>
#include <stdint.h>


//checks to see if __GNUC__ (GNU Compiler) is defined, if so specifies information
#ifdef __GNUC__
    //forward declaration of WT as size_t (size of unsigned int - not consistent between architectures) for furture use
    typedef __attribute__((__may_alias__)) size_t WT;
    //defines future use of WS to the sizeof(WT) as a shorthand
    #define WS (sizeof(WT))
#endif

//copies n bytes from src to dest - unlike memcpy allows for overlap of src and dest
//takes generic pointer to destination, constant generic pointer to source, and a size based on an unsigned int (not consistent between architectures)
//returns a generic pointer to the location the destination pointer points to
void *memmove(void *dest, const void *src, size_t n)
{
    //creates pointer to destination pointer argument
    char *d = dest;
    //create constant pointer to source pointer argument
    const char *s = src;
    
    //if the pointers point to the same location, no need to copy - return destination pointer
    if (d==s) return d;
    
    //if the pointer to the source minus the pointer to the destination minus the length is less than the length times -2
    //then there isn't overlap and memcpy can be called instead
    //e.g pointer to source is 0, pointer to destination is 10, size is 10:
    //evaluates to true (0-10-10 <= -2*10) == (-20 <= -20)
    //note that pointers are cast to uintptr_t, a size based on computer architecture
    //not sure why the qualification is s-d-n <= -2*n instead of s-d <= -n ???
    // Only handles case where source is before destination in memory ???
    if ((uintptr_t)s-(uintptr_t)d-n <= -2*n) return memcpy(d, s, n);
    
    //if destination is before source in memory start at the front of the section to be copied to prevent overlap
    if (d<s) {
        //checks for presence of GNU compiler and handles optimally for that case
        #ifdef __GNUC__
            //if the pointers to s and d are aligned in relation to the size of one unsized int (again, varies based on architecture)
            if ((uintptr_t)s % WS == (uintptr_t)d % WS) {
                //while the uintptr_t cast is not a multiple of sizeof(size_t) copy by one byte at a time
                while ((uintptr_t)d % WS) {
                    //in each loop, n is decremented and if n == 0, returns destination pointer since copy is complete
                    if (!n--) return dest;
                    //in each loop, pointer value at destination is set equal to the pointer value from the source
                    //and both pointers are incremented
                    *d++ = *s++;
                }
                //once destination is properly aligned with WS you can copy by larger segments
                //until n > sizeof(size_T) n is decrimented and both destination and source pointers are incremented
                for (; n>=WS; n-=WS, d+=WS, s+=WS)
                    //casts and dereferences pointers and then copies value at source to destination
                    //note that the cast here allows for larger segments to be copied at a time
                    *(WT *)d = *(WT *)s;
            }
            #endif
        //if not using the GNU conpiler - dererences both pointers and copies value at source to destination, then increments both
        for (; n; n--) *d++ = *s++;
    //if destination is after source in memory, will perform a similar operation except that the back of the section to be copied will be copied first to prevent overlap
    } else {
        //checks for presence of GNU compiler and handles optimally for that case
        #ifdef __GNUC__
            //if the pointers to s and d are aligned in relation to the size of one unsized int (again, varies based on architecture)
            if ((uintptr_t)s % WS == (uintptr_t)d % WS) {
                //while the uintptr_t cast of the destination + n (because we're starting at the end) is not a multiple of sizeof(size_t)
                while ((uintptr_t)(d+n) % WS) {
                    if (!n--) return dest;
                    //size is decrimented in each if statement and then bytes are copied
                    d[n] = s[n];
                }
                while (n>=WS) n-=WS, *(WT *)(d+n) = *(WT *)(s+n);
            }
        #endif
        //again, going from back to front
        while (n) n--, d[n] = s[n];
    }
    
    return dest;
}



