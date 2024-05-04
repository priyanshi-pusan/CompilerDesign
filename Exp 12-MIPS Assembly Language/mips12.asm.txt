.data 
int1 : .word 23

int2 : .word 3

result : .word 0

.globl main

.text

main:

lw $t0, int1

lw $t1, int2

add $t2,$t0,$t1

sw $t2, result

li $v0, 1 #these instructions will be used to print

lw $a0, result

syscall