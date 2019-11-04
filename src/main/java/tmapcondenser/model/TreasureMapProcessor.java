package src.main.java.tmapcondenser.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
    Written By: Justin Senia
    Written: 11/01/2019
    Last Updated: 11/03/2019
    TreasureMapProcessor.java (class): takes in a filename (the name of the treasure
    map file) to be processed, and a MapDirectionsData Object, to open, and parse
    the treasure map file, line by line, passing each individual line (and reference
    to the "MapDirectionsData" Object) to the "DirectionsLineParser"'s "parseLine" 
    method for processing, interpreting and storing the Maps's Directions. 
*/

class TreasureMapProcessor{

    // "treasureMapFile" Ex: "treasureMap.txt"
    // "mapDirectionsDataObject" Passed from "TreasureMapCondenser" -> TreasureMapProcessor
    static boolean processTreasureMap(String treasureMapFile, MapDirectionsData mapDirectionsDataObject){
        
        // Create buffered file reader
        BufferedReader reader;

        // Try/Catch to deal with File I/O
        try{

            // Attaches filename to FileReader
            reader = new BufferedReader(new FileReader(treasureMapFile));

            // Reads and stores a single line
            String line = reader.readLine();

            // Continue until the document reaches EOF (end of file)
            while (line != null) {

                // Passing line and Data object to DirectionsLineParser for processing
                DirectionsLineParser.parseLine(line, mapDirectionsDataObject);
                
                // Read next line, run next loop iteration
                line = reader.readLine();
            }

            // Closes reader after it's finished, to avoid potential file corruption (and to save memory)
            reader.close();

            // Map was processed successfully, return true
            return true;
        } 

        // Catches IOExeption in the case that there is a problem is Reading/writing to the file
        catch (IOException e) {
            e.printStackTrace();

            // Map was not processed successfully, return false
            return false;
        }
    }
}