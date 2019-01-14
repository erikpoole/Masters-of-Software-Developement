
//included libraries via header files
#include <string.h>
#include <stdint.h>


//checks to see if __GNUC__ (GNU Compiler) is defined, if so specifies information
#ifdef __GNUC__
    //forward declaration of size_t WT for furture use
    typedef __attribute__((__may_alias__)) size_t WT;
    //defines future use of WS to the sizeof(WT) as a shorthand
    #define WS (sizeof(WT))
#endif

//copies n bytes from src to dest - unlike memcpy allows for overlap of src and dest
//takes generic pointer to destination, constant generic pointer to source, and a size based on an unsigned int (not consistent between OSes)
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
    //not sure why the qualification is s-d-n <= -2*n instead of s-d <= -n ???
    if ((uintptr_t)s-(uintptr_t)d-n <= -2*n) return memcpy(d, s, n);
    
    //if destination is before source in memory
    if (d<s) {
        //checks for presence of GNU compiler and handles optimally for that case
        #ifdef __GNUC__
            //if the pointers to s and d are aligned in relation to the size of one unsized int (again, varies based on OS)
            if ((uintptr_t)s % WS == (uintptr_t)d % WS) {
                //evaluates to true if non-zero
                
                
                
                while ((uintptr_t)d % WS) {
                    //in each loop, n is decremented and if n == 0, returns destination pointer since copy is complete
                    if (!n--) return dest;
                    //in each loop, pointer value at destination is set equal to the pointer value from the source
                    //and both pointers are incremented
                    *d++ = *s++;
                }
                //until n > sizeof(WT) n is decrimented by one unsigned int and both destination and source pointers are incremented
                for (; n>=WS; n-=WS, d+=WS, s+=WS)
                    //casts and derefernces pointers and then copies value at source to destination
                    *(WT *)d = *(WT *)s;
            }
            #endif
        //if not using the GNU conpiler - dererences both pointers and copies value at source to destination, then increments both
        for (; n; n--) *d++ = *s++;
    } else {
        #ifdef __GNUC__
            if ((uintptr_t)s % WS == (uintptr_t)d % WS) {
                while ((uintptr_t)(d+n) % WS) {
                    if (!n--) return dest;
                    d[n] = s[n];
                }
                while (n>=WS) n-=WS, *(WT *)(d+n) = *(WT *)(s+n);
            }
        #endif
        while (n) n--, d[n] = s[n];
    }
    
    return dest;
}



