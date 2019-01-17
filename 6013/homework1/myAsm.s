;
;  File.s
;  homework1
;
;  Created by Erik Poole on 1/15/19.
;  Copyright © 2019 ErikPoole. All rights reserved.
;

;first argument - rdi,
;second argument - rsi,
;third argument - rdx,


global  _sayHello
extern _puts
extern _myPuts

section .text
_sayHello:
    push rbp
    mov rbp, rsp
    mov rdi, helloString
    call _puts
    pop rbp
    ret


_myPuts:
    mov rax, 0x2000004
    mov rdx, rsi
    mov rsi, rdi
    mov rdi, 1
    syscall
    ret

section .rodata
helloString:    db  "hello",0
