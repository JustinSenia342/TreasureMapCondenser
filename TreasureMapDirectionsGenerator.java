import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class TreasureMapDirectionsGenerator{
   
   public static void main(String[] args){


      /*
      Walk, 20 mins, N
      Run, 1 hour, E
      Horse trot, 3 hours, NW
      Elephant ride, 1 hour 30 mins, SE
      Horse gallop, 20 mins, SE
      Walk, 30 mins, SW
      Horse trot, 1 hour 1 min, W

      Multiple teams of treasure hunters have departed to follow the map's
      instructions.

      Your team decided, based on the fact that there are more than 1000 instructions,
      that you are better off parsing the map with a computer and then just following
      a straight line towards the treasure.

      You made some generalizations on the speed of the modes of transportation and
      came up with the following approximations:
         Walk          - 3 mph
         Run           - 6 mph
         Horse trot    - 4 mph
         Horse gallop  - 15 mph
         Elephant ride - 6 mph
         */

      String[] movementTypeArray = new String[]{"Walk", "Run", "Horse trot", "Horse gallop", "Elephant ride"};
      String[] movementDirectionArray = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

      Calendar calendarForSeed = Calendar.getInstance();
      long randomNumGenSeed = calendarForSeed.getTimeInMillis();

      Random randomGen = new Random(randomNumGenSeed);

      int timeTempStorage = 0;
      boolean hourFlag = false;

      try {

         FileWriter treasureMapWriter = new FileWriter("treasureMap.txt");

         for (int i = 0; i < 1000; i++){
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

      } catch (IOException e) {
         System.out.println("An error occurred");
         e.printStackTrace();
      }

   }
}