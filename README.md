# MIPS-Simulator
## Description
This simulator began as a final project submission for my Computer Architecture class. The goal of this project was to simulate the operation of a MIPS CPU using a high level coding language. The first iteration of this program focused on the most basic MIPS instructions:
* J, BEQ
* ADD, ADDI, SUB
* MULT, AND, OR
* SRL, SLL, NOP
* SW, LW

The program takes valid assembly `.asm` files as input, executes the instructions within, and outputs the values stored in the registers and memory at the end of execution. An option to run the program in debug mode causes the program to print the contents of the register file and the control signals of the system after the execution of each instruction. The program is comprised of the files `MainApp.java` and `Simulator.java`. 

`MainApp.java` contains the main method of the program, which is responsible for:
1. Determining the mode of operation
2. Locating the input file provided by the user 
3. Reading in and formatting the instructions
4. Creation and execution of the simulator

The aptly named file `Simulator.java` contains the code that is responsible for the simulation of the input instructions. The 5 primary functions of the `Simulator` class are each named after their respective stage of the MIPS pipeline:
1. Instruction Fetch - `fetch()`
2. Instruction Decode - `readRegisters()`
3. Execute - `ALU()`
4. Memory Access - `writeMemory()`
5. Write Back - `writeRegister()`

The primitive integer type was chosen to represent each of the registers, and an int array of size 2000 (8kB) was used to represent memory.

## Input File Guidelines
This simulator accepts input files that contain any combination of the instructions listed above, providing they do not introduce control or data hazards. The syntax of the MIPS ISA should be utilized. The program recognizes register references in the format of `$t0`. Registers `$t0` through `$t9`, `$s0` through `$s7`, `$a0` through `$a3`, and `$v0` and `$v1` are recognized. Constants are also recognized in accordance with the MIPS ISA syntax. Three sample input files are given in the "test_files" directory.

## Build Instructions
Since this program is written in Java, a Java Development Kit (JDK) must be installed on your system in order to run it. 
* To test if your system has a previously installed JDK, open a command line/terminal window and type: `javac -version`
* If you see something along the lines of `javac xx.x.x` (where the x's represent numbers) you're good to go
* If you see something else then you'll need to install a JDK, [this](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html) can help you with that 

Now that you have a JDK installed you can run the simulator: 
1. Start by downloading the repository onto your computer. 
2. Unzip the files and store them somewhere that is easy to access, since this program is executed by command line. 
3. Next you should open your system's native command line/terminal program. 
4. Navigate inside the "src" folder using the cd command. 
5. Once inside the "src" folder type the command: `javac mipsArchitecture/MainApp.java` to compile the program. If compilation is successful then your current file path will be reprinted
6. To run the program, type the command: `java mipsArchitecture.MainApp /your/file/path/to/input.asm`, where "/your/file/path/to/input.asm" is the file path to the instructions that you would like to simulate. The input file name is not restricted to "input.asm," if you correctly type in the file path to the instructions the program will locate it.

#### Optional Debug Mode
* To run the program in debug mode use the flag "-d"
* Ex. `java mipsArchitecture.MainApp /your/file/path/to/input.asm -d`

## Limitations
Currently, the program does not support the following elements of the MIPS ISA:
1. Function calls of any kind
2. Registers `$at`, `$gp`, `$sp`, `$fp`, `$ra`
3. Floating point registers
4. Inactive # comments
5. Any instruction not listed in the description section
6. Pipelining

Only the aforementioned instructions can be utilized in the input file. This limits the simulator to only very basic MIPS assembly files. The simulator executes instructions sequentially. This means there is no need for storing or forwarding, as it is not possible for data or control hazards to exist.

## Future Updates
* v1.1 will add registers `$at`, `$gp`, `$sp`, `$fp`, `$ra` and function calls
* v1.2 will add # comments
* v1.3 will add floating point registers
* v2.0 will add the remainder of the MIPS instructions
* v3.0 will implement concurrent execution of instructions
* v3.1 will handle control and data hazards with store and forward
* v4.0 will implement a GUI to better demonstrate the MIPS ISA
