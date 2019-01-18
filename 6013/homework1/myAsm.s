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
extern _myGTOD

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

_myGTOD:
;rbp and rsp dont need to be pushed
    push rbp
    mov rbp, rsp
;needs to be in hex, table was in decimal
    mov rax, 0x2000074

    sub rsp, 16
    mov rdi, rsp
    mov rsi, 0

    syscall

    mov rax, [rsp]
    mov rdx, [rsp+8]
    add rsp, 16

    pop rbp
    ret

section .rodata
helloString:    db  "hello",0
