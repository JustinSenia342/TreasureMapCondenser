package src.main.java.tmapcondenser.treasuremaps;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

/*
    Written By: Justin Senia
    Written: 11/01/2019
    Last Updated: 11/01/2019
    TreasureMapDirectionsGenerator.java (class): Creates new Treasure map
    In the exact format specified in the project documentation. The number
    of lines/steps generated is controlled by the passed int parameter
    "numberOfSteps". Generated Map is written to a file named "treasureMap.txt"
*/

public class TreasureMapDirectionsGenerator{
   
   // "numberOfSteps" Ex: Any int value (controls number of map steps generated)
   // Assumptions: User doesn't try pass non int type into method 
   public static String GenerateNewMap(int numberOfSteps){

      // Checks if int Value is an invalid number, returns Error message if true
      if (numberOfSteps <= 0){
         return "ERROR: Please Enter Valid Int Number For Number Of Steps Value.";
      }

      // Arrays to contain movement types, and polar directions
      String[] movementTypeArray = new String[]{"Walk", "Run", "Horse trot", "Horse gallop", "Elephant ride"};
      String[] movementDirectionArray = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

      // Obtaining number based on current time, to act as random seed value 
      Calendar calendarForSeed = Calendar.getInstance();
      long randomNumGenSeed = calendarForSeed.getTimeInMillis();

      // Random object created to determine how map instructions are populated
      Random randomGen = new Random(randomNumGenSeed);

      // Stores generated time value to perform further calculations/evaluations
      int timeTempStorage = 0;

      // Boolean flag to determine if "hour(s)" have been added to directions (based on Random chance)
      // (helps establish complete set of use-cases for generating the "Time" instructions) 
      boolean hourFlag = false;

      // Try/Catch blocks to deal with potential for File IO errors
      try {

         String currWorkingDir = System.getProperty("user.dir");
         currWorkingDir = currWorkingDir + "\\src\\main\\java\\tmapcondenser\\treasuremaps\\treasureMap.txt";

         // Creating new file based on filename
         FileWriter treasureMapWriter = new FileWriter(currWorkingDir);

         // Creates number of lines of Map instructions based on supplied "numberOfSteps" value
         for (int i = 0; i < numberOfSteps; i++){

            // writes random movement Type to file in the format "movementType," 
            treasureMapWriter.write(movementTypeArray[randomGen.nextInt(5)] + ",");

            // randomly chooses to include a "int value"/"hour" pairing when writing "time" to file
            // UseCase: add "hour" to time written to file
            if (randomGen.nextInt(2) == 1){

               // Determines number of hours to write to file (int value between 1 and 10)
               timeTempStorage = randomGen.nextInt(10) + 1;

               // Writes hour value to file preceded by a space
               treasureMapWriter.write(" " + timeTempStorage);

               // Writes " hour" to file (not plural as it's only 1 hour)
               if(timeTempStorage == 1){
                  treasureMapWriter.write(" hour");
               }
               // Writes " hours" to file (plural as it's > 1 hour)
               else{
                  treasureMapWriter.write(" hours");
               }

               // Flag set to true (enables minutes to be an optional addition (based on chance))
               hourFlag = true;
            }

            // UseCase: "hour" was already written, minutes are now optional (determined by random)
            if (hourFlag == true){

               // Generate # between 0 and 60
               timeTempStorage = randomGen.nextInt(61);

               // If # generated wasn't equal to 0 or 60, write minute(s) to file 
               if (timeTempStorage != 0 && timeTempStorage != 60)
               {

                  // Write # of seconds to file preceded by a space
                  treasureMapWriter.write(" " + timeTempStorage);

                  // If number is equal to 1, write " minute" (non-plural) 
                  if(timeTempStorage == 1){
                     treasureMapWriter.write(" minute");
                  }

                  // If number is > 1, write " minutes" (plural)
                  else{
                     treasureMapWriter.write(" minutes");
                  }
               }
            }

            // UseCase: "hour" wasn't written to file, "minutes" must exist
            // the value of "minutes" must not be 0, or 60 (60min == 1 hr) 
            else{

               // Generate random number between 1 and 59
               timeTempStorage = randomGen.nextInt(58) + 1;

               // Write number to file preceded by a space
               treasureMapWriter.write(" " + timeTempStorage);

               // If number is equal to 1, write " minute" (non-plural)
               if(timeTempStorage == 1){
                  treasureMapWriter.write(" minute");
               }
               // If number is > 1, write " minutes" (plural)
               else{
                  treasureMapWriter.write(" minutes");
               }
            }
            
            // Writes random movement Direction to file in the format ", movementDirection\n"
            // Includes newline character to move to next line in agreed upon project format 
            treasureMapWriter.write(", " + movementDirectionArray[randomGen.nextInt(8)] + "\n");

            // Resetting variables for re-use
            timeTempStorage = 0;
            hourFlag = false;
         }

         // Closes writer to avoid potentially corrupting file and free up memory
         treasureMapWriter.close();

         // Return String message indicating file creation was a success
         return "New Treasure Map Generated Successfully.";

      }
      
      // Catch any File IO errors, and return a String message detailing the error
      catch (IOException e) {
         System.out.println("An error occurred");
         e.printStackTrace();
         return "Error Occured During New Treasure Map Generation, See Logs for Details.";
      }
   }
}