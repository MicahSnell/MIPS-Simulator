addi $t1, $zero, 5
addi $t2, $zero, 7
addi $t7, $zero, 10
addi $t8, $zero, 2
sll $t3, $t1, $t2
srl $t4, $t8, $t7
addi $s0, $zero, 0
addi $s1, $zero, 1
and $s2, $s0, $s1
or $s3, $s0, $s1
and $s4, $s1, $s1
nop
beq $zero, $zero, 4
sub $t5, $t2, $t1
mult $t5, $t2, $t1
sw $t1, 0($t1)
sw $t2, 1($t1)
sw $t3, 2($t1)
sw $t4, 3($t1)
sw $t5, 4($t1)
sw $s2, 5($t1)
sw $s3, 6($t1)
