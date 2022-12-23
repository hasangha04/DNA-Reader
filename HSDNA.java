// Programmer: Harpreet Sangha 
// Date: 11/24/2021
// Purpose: This program will take a DNA sequence and will then print out the name of the sequence 
// and the sequence itself. The it will count how many nucleotides are in the sequence and will also 
// calculate the mass of the sequence and it will split that up amongst the respective nucleotides.  

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;    

public class HSDNA
{
   public static void main(String[] args)
   throws FileNotFoundException // If the file is not found it will throw this exception 
   {  
      System.out.println("This program reports information about DNA");
      System.out.println("nucleotide sequences that may encode proteins.");
      
      System.out.print("Input file name? (Include the .txt): ");

      Scanner keyboard = new Scanner(System.in);
       
      String fileName = keyboard.nextLine();
  
      File file = new File(fileName);
    
      Scanner inputFile = new Scanner(file);
            
      System.out.print("Output file name? (Include the .txt): ");
      
      Scanner userInput = new Scanner(System.in);
      
      String inputName = userInput.nextLine();
       
      File outFile = new File(inputName);
      
      Scanner outputFile = new Scanner(outFile);
      
      PrintStream output = new PrintStream (outFile);
      
      System.out.println(); 
      
      double total = 0;
              
      while (inputFile.hasNextLine())
      {
         int[] nucCount = new int[4];
         
         String line1 = inputFile.nextLine();
         String line2 = inputFile.nextLine();
          
         line2 = line2.toUpperCase();
         
         double[] masses = new double[5];
         masses[0] = 135.128; 
         masses[1] = 111.103;
         masses[2] = 151.128;
         masses[3] = 125.107;
         masses[4] = 100.000;
         
         // Creates a new array for the masses of the nucleotides once they have been multiplied
         // and rounded 
         double[] mass = new double[5];
         
         // Initializes the nucleotides variable so that they can be incremented in the for loop                   
         int a1 = 0;
         int c1 = 0;
         int g1 = 0;
         int t1 = 0;
         int d1 = 0;
         
         // The for loop increments the amount of nucleotides in a certain sequence and puts that 
         // number in the nucCount array   
         for (int i = 0; i < line2.length(); i++)
         {
         	char value = line2.charAt(i);
            if (value == 'A')
            {
               a1++;
               nucCount [0] = a1;
            }
            if (value == 'C')
            {
               c1++;
               nucCount [1] = c1;
            }
            if (value == 'G')
            {
               g1++;
               nucCount [2] = g1;
            }
            if (value == 'T')
            {
               t1++;
               nucCount [3] = t1;
            }
            if (value == '-')
            {
               d1++;
            }
            
            double mass1 = nucCount[0] * masses[0];
            double mass2 = nucCount[1] * masses[1];
            double mass3 = nucCount[2] * masses[2];
            double mass4 = nucCount[3] * masses[3];
            double mass5 = d1 * masses[4];
            
            total = mass1 + mass2 + mass3 + mass4 + mass5; 
            
            double percent1 = mass1/total;
            double percent2 = mass2/total;
            double percent3 = mass3/total;
            double percent4 = mass4/total;
            double percent5 = mass5/total;
            
            double roundPercent1 = Math.round(percent1 * 1000.0) / 10.0;
            double roundPercent2 = Math.round(percent2 * 1000.0) / 10.0;
            double roundPercent3 = Math.round(percent3 * 1000.0) / 10.0;
            double roundPercent4 = Math.round(percent4 * 1000.0) / 10.0;
            double roundPercent5 = Math.round(percent5 * 1000.0) / 10.0;
            
            mass[0] = roundPercent1;
            mass[1] = roundPercent2;
            mass[2] = roundPercent3;
            mass[3] = roundPercent4;
            mass[4] = roundPercent5;
         }
         
         output.println("Region Name: " + line1);
         output.println("Nucleotides: " + line2);  
         output.println("Nuc. Counts: " + Arrays.toString(nucCount));
         output.print("Total Mass%: " + Arrays.toString(mass) + " of " );
         output.printf("%.1f" , total);
         output.println();
         output.println("Codons List: " + Arrays.toString(line2.split("(?<=\\G...)")));
         output.println();
         
         if (line2.startsWith("ATG"))
         {  // Checks the ending of the sequence to see if it fits the requirements
            if (line2.endsWith("TAA") || line2.endsWith("TAG") || line2.endsWith("TGA"))
            {
               // These two check the length and if the sequence has at least 30% of C and G
               // nucleotides 
               if ((line2.length()/3) >= 5)
               { 
                  if ((mass[1] + mass[2]) >= 30.0)
                  {
                     output.println("Is Protein?: Yes");
                     output.println();
                  } 
               }         
            }
         }
         else
         {
            output.println("Is Protein?: No");
            output.println();
         } 
      } 
       
      while (outputFile.hasNextLine())
      {
         String text = outputFile.nextLine();
         System.out.println (text);
      } 
   } 
}