;
;  File.s
;  homework1
;
;  Created by Erik Poole on 1/15/19.
;  Copyright © 2019 ErikPoole. All rights reserved.
;

    global  _sayHello
    extern _puts

    section .text
_sayHello:
    push rbp
    mov rsp, rbp
    mov rdi, helloString
    call _puts
    leave
    ret

section .rodata
helloString:    db  "hello",0
