import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

/*
    Written By: Justin Senia
    Written: 11/01/2019
    Last Updated: 11/01/2019
    TreasureMapDirectionsGenerator.java (class): 
*/

class TreasureMapDirectionsGenerator{
   
   public static String GenerateNewMap(int numberOfSteps){

      if (numberOfSteps <= 0){
         return "ERROR: Please Enter Valid Int Number For Number Of Steps Value.";
      }

      String[] movementTypeArray = new String[]{"Walk", "Run", "Horse trot", "Horse gallop", "Elephant ride"};
      String[] movementDirectionArray = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

      Calendar calendarForSeed = Calendar.getInstance();
      long randomNumGenSeed = calendarForSeed.getTimeInMillis();

      Random randomGen = new Random(randomNumGenSeed);

      int timeTempStorage = 0;
      boolean hourFlag = false;

      try {

         FileWriter treasureMapWriter = new FileWriter("treasureMap.txt");

         for (int i = 0; i < numberOfSteps; i++){
            treasureMapWriter.write(movementTypeArray[randomGen.nextInt(5)] + ",");

            if (randomGen.nextInt(2) == 1){
               timeTempStorage = randomGen.nextInt(10) + 1;
               treasureMapWriter.write(" " + timeTempStorage);
               if(timeTempStorage == 1){
                  treasureMapWriter.write(" hour");
               }
               else{
                  treasureMapWriter.write(" hours");
               }
               hourFlag = true;
            }

            if (hourFlag == true){
               timeTempStorage = randomGen.nextInt(61);
               if (timeTempStorage != 0 && timeTempStorage != 60)
               {
                  treasureMapWriter.write(" " + timeTempStorage);
                  if(timeTempStorage == 1){
                     treasureMapWriter.write(" minute");
                  }
                  else{
                     treasureMapWriter.write(" minutes");
                  }
               }
            }
            else{
               timeTempStorage = randomGen.nextInt(58) + 1;
               treasureMapWriter.write(" " + timeTempStorage);
               if(timeTempStorage == 1){
                  treasureMapWriter.write(" minute");
               }
               else{
                  treasureMapWriter.write(" minutes");
               }
            }
            
            treasureMapWriter.write(", " + movementDirectionArray[randomGen.nextInt(8)] + "\n");

            //resetting variables for re-use
            timeTempStorage = 0;
            hourFlag = false;
         }

         treasureMapWriter.close();

         return "New Treasure Map Generated Successfully.";

      } catch (IOException e) {
         System.out.println("An error occurred");
         e.printStackTrace();
         return "Error Occured During New Treasure Map Generation, See Logs for Details.";
      }

   }
}