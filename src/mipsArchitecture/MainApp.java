package mipsArchitecture;

import java.util.*;
import java.io.*;

/**
 * By Micah Snell
 * Texas State University
 * Dr. Apan Qasem - CS 3339
 * 05/11/2020
 *
 * This program simulates a computer architecture that implements a MIPs
 * instruction set. This program takes input from a .asm file that may contain
 * any sequence of the following MIPs instructions:
 *
 * - J, BEQ
 * - ADD, ADDI, SUB
 * - SW, LW
 * - SLL, SRL
 * - MUL, AND, OR
 * - NOP
 *
 * At the end of the program the values of the register file and memory will
 * be displayed.
 */
public class  MainApp {
   /**
    * Main method accepts file path of .asm file as a command line argument, as
    * well as an optional debug mode using the flag variable '-d'
    * Main:
    *    1. reads instructions in from the provided .asm file
    *    2. formats them
    *    3. creates new Simulator object 'mips' with formatted instructions
    *    4. begins simulation.
    *
    * @param args the command line arguments
    */
   public static void main(String[] args) {
     if (args.length == 0 || args.length > 2) {
         System.err.println("\nWrong number of args, please try again.");
         System.err.println("Example: java mipsArchitecture.MainApp " +
               "<d:/path/to/assembly/instructions> <-d> (for optional debug)");

         return;
      }

      boolean mode = false;
      if (args.length == 2)
         if (args[1].equals("-d")) {
            System.out.println("\nDebug mode enabled.");
            mode = true;
         }

      try {
         String filePath = args[0];
         FileReader file = new FileReader(filePath);
         Scanner inFile = new Scanner(file);
         String fileContents = null,
                        temp = null;
         int temp2;

         while (inFile.hasNextLine()) {
            temp = inFile.nextLine();
            
            if (temp.contains("#")) {
               temp2 = temp.indexOf("#");
               if (temp2 == 0)
                  continue;
               
               temp = temp.substring(0, temp2);
            }
            
            fileContents += temp;
            fileContents += " ";
         }
         inFile.close();
         file.close();

         fileContents = fileContents.substring(4); //cuts off null
         fileContents = fileContents.replaceAll(",", "");
         fileContents = fileContents.replaceAll("[()]", " ");
         fileContents = fileContents.toLowerCase();

         ArrayList<String> instructions =
                    new ArrayList<>(Arrays.asList(fileContents.split("\\s+")));
         Simulator mips = new Simulator(instructions, mode);
         mips.begin();
         
      } catch (NullPointerException npe) {
         System.err.println("\nFile contents are null, please try again and " +
                 "ensure the file path is typed correctly.\n");
      } catch (FileNotFoundException fnfe) {
         System.err.println("\nFile not found, please try again and ensure " +
                 "the file path is typed correctly.\n");
      } catch (IOException ioe) {
         System.err.println("\nInput file error, please try again and " +
                 "ensure the file path is typed correctly.\n");
      }
   }
}
