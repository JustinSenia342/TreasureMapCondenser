package src.main.java.tmapcondenser.model;

/*
    Written By: Justin Senia
    Written: 11/02/2019
    Last Updated: 11/02/2019
    TreasureMapCondenser.java (class): Functions as the main hub for 
    creating the mapDirectionsDataObject (to function as the data model
    for this particular solution), and handling the sequential set of steps
    required to process the generated Treasure Map File after the user
    requests it via the GUI that's acting as a Viewer/Controller in this
    model. 
*/

public class TreasureMapCondenser{

    // Used to determine success of processing the requested treasure map
    boolean mapProcessSuccess;

    // Used to store results of the processed treasure map data
    String processedMapResults;

    // mapDirectionsDataObject will store/process parsed map data
    src.main.java.tmapcondenser.model.MapDirectionsData mapDirectionsDataObject;

    // Constructor for TreasureMapCondenser Class
    public TreasureMapCondenser(){

        // Initializing as a new MapDirectionsData object
        mapDirectionsDataObject = new src.main.java.tmapcondenser.model.MapDirectionsData();

        // Setting to false, will use as a flag for determining if
        // map data processing should proceed or get terminated early
        mapProcessSuccess = false;

        // Initializing to empty string
        processedMapResults = "";
    }

    // Runs sequence of treasure map methods, parsing, processing and calculating
    // the information from the pre-generated "treasureMap.txt" to find a direct
    // route and related information, it will then provide the user that requested it
    public String condenseTreasureMap(){

        // Gets current working root directory
        String currWorkingDir = System.getProperty("user.dir");
        currWorkingDir = currWorkingDir + "\\src\\main\\java\\tmapcondenser\\treasuremaps\\treasureMap.txt";

        // Process treasure map, and aggregate data in mapDirectionsDataObject
        mapProcessSuccess = src.main.java.tmapcondenser.model.TreasureMapProcessor.processTreasureMap(currWorkingDir, mapDirectionsDataObject);

        // Map processing was successful, continue calculations
        if (mapProcessSuccess == true){

            // Calculates total distance of original map directions with aggregated data
            // Stores calculated results in data model object "mapDirectionsDataObject"
            mapDirectionsDataObject.calculateTotalDistance();

            // Calculates X and Y value (polar coordinates) using processed data
            // Stores calculated results in data model object "mapDirectionsDataObject"
            mapDirectionsDataObject.calculateTargetVector();

            // Calculates distance from Polar Origin (0, 0) to Target Destination
            // Stores calculated results in data model object "mapDirectionsDataObject"
            mapDirectionsDataObject.calculateDirectPathDistance();

            // Calculates polar bearings (in degrees, Starting N, Clockwise)
            // From Polar Origin (0, 0) to Target Destination using Trigonometry
            // Stores calculated results in data model object "mapDirectionsDataObject"
            mapDirectionsDataObject.calculateDirectPathBearing();

            // Calculates which directional mnemonic is appropriate for the calculated polar bearing
            // Stores calculated results in data model object "mapDirectionsDataObject" 
            mapDirectionsDataObject.calculateBearingPolarDirection();

            // Retrieves a summary of all related calculated inferrences from the Map Data
            // Stores this collection of summary data to display to the user in the GUI
            processedMapResults = mapDirectionsDataObject.getTargetCoordinateInfo();

            // Resets variables in Object Data, so user can re-run the program with a
            // new map and there won't be any conflicts. (Output would normally be saved
            // for later to avoid needing to recalculate the same data, in this case I'm
            // just using this as a shortcut for being able to re-run the program with
            // new data sets
            mapDirectionsDataObject.resetObjectData();

            // Returns the Summary of calculated results to the GUI so the user can View them
            return processedMapResults;
        }

        // Returns an error to the GUI if there was an error processing the treasure map
        else {
            return "Error processing treasure map: please see log for details.";
        }
    }

}