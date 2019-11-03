import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
    Written By: Justin Senia
    Written: 11/01/2019
    Last Updated: 11/03/2019
    TresureMapProcessor.java (class): 
*/

class TreasureMapProcessor{

    public static void processTreasureMap(String treasureMapFile, MapDirectionsData mapDirectionsDataObject){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("treasureMap.txt"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                DirectionsLineParser.parseLine(line, mapDirectionsDataObject);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}