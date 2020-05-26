package mipsArchitecture;

import java.util.ArrayList;

/**
 * Class that supports the MIPS Architecture Simulator.
 * The register file, memory, and the 5 stages of the MIPS pipeline are
 * simulated in this class.
 */
class Simulator {
   /**
    * Constructs Simulator object
    *
    * @param instructions formatted instructions from input file
    * @param mode debug flag variable
    */
   public Simulator(ArrayList<String> instructions, boolean mode) {
      this.instructions = instructions;
      this.debug = mode;
   }

   /**
    * Begins simulation
    */
   public void begin() { fetch(); }

   /**
    * Determines which instruction is being called, and sets control signals
    * accordingly. Then passes appropriate instruction arguments to the
    * register file.
    */
   private void fetch() {
      boolean finalOutput = false;

      for (pc = 0; pc < instructions.size(); pc += 4) {
         switch (instructions.get(pc)) {
            case "j":
               aluControl = 1;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), "0", "0");
               break;

            case "beq":
               aluControl = 2;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "add":
            case "addi":
               aluControl = 3;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "sub":
               aluControl = 4;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "sw":
               aluControl = 5;
               writeToMem = 1;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "lw":
               aluControl = 6;
               writeToMem = 0;
               readMem = 1;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "sll":
               aluControl = 7;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "srl":
               aluControl = 8;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "mult":
               aluControl = 9;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "and":
               aluControl = 10;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "or":
               aluControl = 11;
               writeToMem = 0;
               readMem = 0;
               readRegisters(instructions.get(pc + 1), instructions.get(pc + 2),
                       instructions.get(pc + 3));
               break;

            case "nop":
               aluControl = 7; //sll
               writeToMem = 0;
               readMem = 0;
               pc -= 3;
               readRegisters("0", "0", "0");
               break;

            default: break;
         }

         if (debug)
            printInfo(finalOutput);
      }

      finalOutput = true;
      printInfo(finalOutput);
   }

   /**
    * Reads contents of registers specified in instruction, passes values to
    * the ALU for computation.
    *
    * @param rd The destination register of the operation
    * @param rs The 1st source register of the operation
    * @param rt The 2nd source register of the operation
    */
   private void readRegisters(String rd, String rs, String rt) {
      int rdValue,
          rsValue,
          rtValue;

      switch (rs) {
         case "$t0":
            rsValue = this.$t0;
            break;
         case "$t1":
            rsValue = this.$t1;
            break;
         case "$t2":
            rsValue = this.$t2;
            break;
         case "$t3":
            rsValue = this.$t3;
            break;
         case "$t4":
            rsValue = this.$t4;
            break;
         case "$t5":
            rsValue = this.$t5;
            break;
         case "$t6":
            rsValue = this.$t6;
            break;
         case "$t7":
            rsValue = this.$t7;
            break;
         case "$t8":
            rsValue = this.$t8;
            break;
         case "$t9":
            rsValue = this.$t9;
            break;
         case "$s0":
            rsValue = this.$s0;
            break;
         case "$s1":
            rsValue = this.$s1;
            break;
         case "$s2":
            rsValue = this.$s2;
            break;
         case "$s3":
            rsValue = this.$s3;
            break;
         case "$s4":
            rsValue = this.$s4;
            break;
         case "$s5":
            rsValue = this.$s5;
            break;
         case "$s6":
            rsValue = this.$s6;
            break;
         case "$s7":
            rsValue = this.$s7;
            break;
         case "$v0":
            rsValue = this.$v0;
            break;
         case "$v1":
            rsValue = this.$v1;
            break;
         case "$a0":
            rsValue = this.$a0;
            break;
         case "$a1":
            rsValue = this.$a1;
            break;
         case "$a2":
            rsValue = this.$a2;
            break;
         case "$a3":
            rsValue = this.$a3;
            break;
         case "$zero":
            rsValue = this.$zero;
            break;
         default:
            rsValue = Integer.parseInt(rs);
            break;
      }

      switch (rt) {
         case "$t0":
            rtValue = this.$t0;
            break;
         case "$t1":
            rtValue = this.$t1;
            break;
         case "$t2":
            rtValue = this.$t2;
            break;
         case "$t3":
            rtValue = this.$t3;
            break;
         case "$t4":
            rtValue = this.$t4;
            break;
         case "$t5":
            rtValue = this.$t5;
            break;
         case "$t6":
            rtValue = this.$t6;
            break;
         case "$t7":
            rtValue = this.$t7;
            break;
         case "$t8":
            rtValue = this.$t8;
            break;
         case "$t9":
            rtValue = this.$t9;
            break;
         case "$s0":
            rtValue = this.$s0;
            break;
         case "$s1":
            rtValue = this.$s1;
            break;
         case "$s2":
            rtValue = this.$s2;
            break;
         case "$s3":
            rtValue = this.$s3;
            break;
         case "$s4":
            rtValue = this.$s4;
            break;
         case "$s5":
            rtValue = this.$s5;
            break;
         case "$s6":
            rtValue = this.$s6;
            break;
         case "$s7":
            rtValue = this.$s7;
            break;
         case "$v0":
            rtValue = this.$v0;
            break;
         case "$v1":
            rtValue = this.$v1;
            break;
         case "$a0":
            rtValue = this.$a0;
            break;
         case "$a1":
            rtValue = this.$a1;
            break;
         case "$a2":
            rtValue = this.$a2;
            break;
         case "$a3":
            rtValue = this.$a3;
            break;
         case "$zero":
            rtValue = this.$zero;
            break;
         default:
            rtValue = Integer.parseInt(rt);
            break;
      }

      switch (rd) {
         case "$t0":
            rdValue = this.$t0;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t1":
            rdValue = this.$t1;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t2":
            rdValue = this.$t2;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t3":
            rdValue = this.$t3;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t4":
            rdValue = this.$t4;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t5":
            rdValue = this.$t5;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t6":
            rdValue = this.$t6;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t7":
            rdValue = this.$t7;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t8":
            rdValue = this.$t8;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$t9":
            rdValue = this.$t9;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s0":
            rdValue = this.$s0;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s1":
            rdValue = this.$s1;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s2":
            rdValue = this.$s2;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s3":
            rdValue = this.$s3;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s4":
            rdValue = this.$s4;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s5":
            rdValue = this.$s5;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s6":
            rdValue = this.$s6;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$s7":
            rdValue = this.$s7;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$v0":
            rdValue = this.$v0;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$v1":
            rdValue = this.$v1;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$a0":
            rdValue = this.$a0;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$a1":
            rdValue = this.$a1;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$a2":
            rdValue = this.$a2;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$a3":
            rdValue = this.$a3;
            ALU(rd, rdValue, rsValue, rtValue);
            break;
         case "$zero":
            rdValue = 0;
            ALU(rd, rdValue, rsValue, rtValue); 
            break;
         default:
            rdValue = Integer.parseInt(rd);
            ALU(rd, rdValue, rsValue, rtValue);
            break;
      }
   }

   /**
    * Performs operation based on control signal set in the decode phase.
    * Passes results onto the memory stage
    *
    * @param regDest Name of the destination register
    * @param rd Destination register value
    * @param rs 1st source register value
    * @param rt 2nd source register value
    */
   private void ALU(String regDest, int rd, int rs, int rt) {
      switch (aluControl) {
         //j
         case 1:
            pc = rd - 4;
            break;

         //beq
         case 2:
            if (rd == rs)
               pc += rt;
            break;

         //add, add immediate
         case 3:
            rd = rs + rt;
            break;

         //subtract
         case 4:
            rd = rs - rt;
            break;

         //store word
         case 5:
            memIndex = rs + rt;
            break;

         //load word
         case 6:
            rd = rs + rt;
            break;

         //sll
         case 7:
            rd = rs << rt;
            break;

         //srl
         case 8:
            rd = rs >> rt;
            break;

         //multiply
         case 9:
            rd = rs * rt;
            break;

         //and
         case 10:
            rd = rs & rt;
            break;

         //or
         case 11:
            rd = rs | rt;
            break;

         default: break;
      }

      writeMemory(regDest, rd);
   }

   /**
    * Performs a memory read/write/nop based on control signals set in decode
    * phase. Passes destination register name and value onto the register
    * write-back stage.
    *
    * @param rdName Name of the destination register
    * @param rdValue Value to be potentially stored in memory
    */
   private void writeMemory(String rdName, int rdValue) {
      if (writeToMem == 0) {
         if (readMem == 1)
            rdValue = memory[rdValue];

         writeRegister(rdName, rdValue);
      }
      else if (writeToMem == 1) {
         memory[memIndex] = rdValue;
         writeRegister("$null", 0);
      }
   }

   /**
    * Performs a register write-back based on control signals set in decode
    * phase. Does not allow write-back to register $zero.
    *
    * @param rdName Name of the destination register
    * @param rdValue Value to be stored in the destination register
    */
   private void writeRegister(String rdName, int rdValue) {
      switch (rdName) {
         case "$t0":
            this.$t0 = rdValue;
            break;
         case "$t1":
            this.$t1 = rdValue;
            break;
         case "$t2":
            this.$t2 = rdValue;
            break;
         case "$t3":
            this.$t3 = rdValue;
            break;
         case "$t4":
            this.$t4 = rdValue;
            break;
         case "$t5":
            this.$t5 = rdValue;
            break;
         case "$t6":
            this.$t6 = rdValue;
            break;
         case "$t7":
            this.$t7 = rdValue;
            break;
         case "$t8":
            this.$t8 = rdValue;
            break;
         case "$t9":
            this.$t9 = rdValue;
            break;
         case "$s0":
            this.$s0 = rdValue;
            break;
         case "$s1":
            this.$s1 = rdValue;
            break;
         case "$s2":
            this.$s2 = rdValue;
            break;
         case "$s3":
            this.$s3 = rdValue;
            break;
         case "$s4":
            this.$s4 = rdValue;
            break;
         case "$s5":
            this.$s5 = rdValue;
            break;
         case "$s6":
            this.$s6 = rdValue;
            break;
         case "$s7":
            this.$s7 = rdValue;
            break;
         case "$v0":
            this.$v0 = rdValue;
            break;
         case "$v1":
            this.$v1 = rdValue;
            break;
         case "$a0":
            this.$a0 = rdValue;
            break;
         case "$a1":
            this.$a1 = rdValue;
            break;
         case "$a2":
            this.$a2 = rdValue;
            break;
         case "$a3":
            this.$a3 = rdValue;
            break;
         default: break;
      }
   }

   /**
    * Prints final state of registers and memory after execution.
    * If debug mode is enabled, registers and control signals are printed after
    * each instruction is executed.
    *
    * @param finalOutput Flag variable that indicates state of program
    */
   private void printInfo(boolean finalOutput) {
      System.out.println();
      if (finalOutput)
         System.out.println("\nFinal Output:");
      else
         System.out.println("Instruction #" + ((pc / 4) + 1));
      System.out.println("Temporary Registers: ");
      System.out.printf("$t0: %-5s $t1: %-5s $t2: %-5s $t3: %-5s $t4: %-5s\n",
              $t0, $t1, $t2, $t3, $t4);
      System.out.printf("$t5: %-5s $t6: %-5s $t7: %-5s $t8: %-5s $t9: %-5s\n",
              $t5, $t6, $t7, $t8, $t9);
      System.out.println("Saved Registers: ");
      System.out.printf("$s0: %-5s $s1: %-5s $s2: %-5s $s3: %-5s\n",
              $s0, $s1, $s2, $s3);
      System.out.printf("$s4: %-5s $s5: %-5s $s6: %-5s $s7: %-5s\n",
              $s4, $s5, $s6, $s7);
      System.out.println("Argument Registers: ");
      System.out.printf("$a0: %-5s $a1: %-5s $a2: %-5s $a3: %-5s\n",
              $a0, $a1, $a2, $a3);
      System.out.println("Return Value Registers: ");
      System.out.printf("$v0: %-5s $v1: %-5s\n", $v0, $v1);

      if (finalOutput) {
         System.out.println("\nMemory: ");
         for (int i = 0; i < memory.length; ++i) {
            if (i < 10)
               System.out.printf("Memory[" + i + "]:  %-5s", memory[i]);
            else
               System.out.printf("Memory[" + i + "]: %-5s", memory[i]);
            if ((i + 1) % 4 == 0)
               System.out.println();
         }
         System.out.println();
      }

      if (debug) {
         System.out.println();
         System.out.println("Control Signals: ");
         System.out.println("aluControl: " + aluControl);
         System.out.println("writeToMem: " + writeToMem);
         System.out.println("readMem: " + readMem + "\n");
      }
   }

   /**
    * Program Counter
    */
   private int pc;

   /**
    * Formatted instructions from input file
    */
   private ArrayList<String> instructions;

   /**
    * Control signal for ALU operation
    */
   private int aluControl;

   /**
    * Control signal for memory write
    */
   private int writeToMem;

   /**
    * Control signal for memory read
    */
   private int readMem;

   /**
    * Memory index to be calculated by the store word instruction
    */
   private int memIndex;

   /**
    * Temporary registers $t0 through $t9
    */
   private int $t0 = 0;
   private int $t1 = 0;
   private int $t2 = 0;
   private int $t3 = 0;
   private int $t4 = 0;
   private int $t5 = 0;
   private int $t6 = 0;
   private int $t7 = 0;
   private int $t8 = 0;
   private int $t9 = 0;

   /**
    * Saved registers $s0 through $s7
    */
   private int $s0 = 0;
   private int $s1 = 0;
   private int $s2 = 0;
   private int $s3 = 0;
   private int $s4 = 0;
   private int $s5 = 0;
   private int $s6 = 0;
   private int $s7 = 0;

   /**
    * Argument registers $a0 through $a3
    */
   private int $a0 = 0;
   private int $a1 = 0;
   private int $a2 = 0;
   private int $a3 = 0;

   /**
    * Return value registers $v0, and $v1
    */
   private int $v0 = 0;
   private int $v1 = 0;

   /**
    * Constant value register $zero
    */
   private static final int $zero = 0;

   /**
    * 8kB addressable memory
    */
   private int[] memory = new int[2000];

   /**
    * Debug flag variable
    */
   private boolean debug;
}
