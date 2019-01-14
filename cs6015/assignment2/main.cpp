
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
//takes generic pointer to destination, constant generic pointer to source, and a size in bytes
//returns a generic pointer to the location the destination pointer points to
void *memmove(void *dest, const void *src, size_t n)
{
    //creates pointer to destination pointer argument
    char *d = dest;
    //create constant pointer to source pointer argument
    const char *s = src;
    
    //if the pointers point to the same location, no need to copy - return destination pointer
    if (d==s) return d;
    
    if ((uintptr_t)s-(uintptr_t)d-n <= -2*n) return memcpy(d, s, n);
    
    if (d<s) {
#ifdef __GNUC__
        if ((uintptr_t)s % WS == (uintptr_t)d % WS) {
            while ((uintptr_t)d % WS) {
                if (!n--) return dest;
                *d++ = *s++;
            }
            for (; n>=WS; n-=WS, d+=WS, s+=WS) *(WT *)d = *(WT *)s;
        }
#endif
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
