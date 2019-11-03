import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    Written By: Justin Senia
    Written: 11/01/2019
    Last Updated: 11/03/2019
    DirectionsLineParser.java (class): This class takes in individual
    lines from the treasure map, breaks apart the data into three components:
    "movementType", "Time", and "MovementDirection". "Time" can include multiple
    possible combinations of hours and minutes, so it's broken apart via Regular
    Expressions and converted into only minutes and used to calculate "Distance"
    based on "movementType" (pre-established MPH values). "movementDirection" and 
    "Distance" are used as a key/value pairing to add to accumulated total in the
    Data Model Object's hashmap (that data model object is passed in as a parameter).
*/

class DirectionsLineParser{

    // "unparsedLine" Ex: "Run, 9 hours 5 minutes, E", "Walk, 14 minutes, E", etc.
    // "mapDirectionsDataObject" Passed from "TreasureMapCondenser" -> TreasureMapProcessor -> DirectionsLineParser 
    public static void parseLine(String unparsedLine, MapDirectionsData mapDirectionsDataObject){

        // Splitting unparsedLine parameter into 3 pieces (based on location of ',')
        String[] parsedSegments = unparsedLine.split(",");

        // Assigning data from array index '0' to movementType variable (for clarity)
        String movementType = parsedSegments[0];

        // Assigning data from array index '2' to movementDirection variable (for clarity)
        String movementDirection = parsedSegments[2];

        // Variables to store minute & hour values
        int parsedHourValue = 0;
        int parsedMinuteValue = 0;

        // Variables to store calculated totalMinutes and totalMiles
        int totalMinutes = 0;
        double totalMiles = 0.0;

        // "If statements" to deal with 3 different possible Hour/minute scenarios
        // (1) hour and minute exists, (2) only hour exists, (3) only minutes exist
        // Assumptions: time cannot be empty, time cannot be 0, 
        // format/capitalization (spacing / all lowercase) is consistent
        if (parsedSegments[1].contains("hour")){
            
            // Case where both hour(s) and minute(s) exist
            if (parsedSegments[1].contains("minute")){
                
                // Creating Regular Expression Capture Patterns for hours and minutes
                Pattern pattern = Pattern.compile(" (\\d+) .* (\\d+) .*");
                Matcher matcher = pattern.matcher(parsedSegments[1]);
                
                // If statement results in value of "0" denoting an error has occured
                // Adds values to appropriately named variables (for calculation clarity)
                if (matcher.find()) {
                    parsedHourValue = Integer.parseInt(matcher.group(1));
                    parsedMinuteValue = Integer.parseInt(matcher.group(2));
                }

                // Calculates total minutes by combining both hour(converted to minutes) and minute values
                totalMinutes = parsedMinuteValue + (parsedHourValue * 60);

            }

            // Case where only hour(s) exists, no minute(s)
            else {
                
                // Creating Regular Expression Capture Patterns for hours
                Pattern pattern = Pattern.compile(" (\\d+) .*");
                Matcher matcher = pattern.matcher(parsedSegments[1]);

                // If statement results in value of "0" denoting an error has occured
                // Adds values to appropriately named variable (for calculation clarity)
                if (matcher.find()) {
                    parsedHourValue = Integer.parseInt(matcher.group(1));
                }

                // Calculates total minutes by converting hour value into minutes
                totalMinutes = parsedHourValue * 60;
            }
        }

        //Case where only minute(s) exists, no hours(s)
        else{
            
            // Creating Regular Expression Capture Patterns for minutes
            Pattern pattern = Pattern.compile(" (\\d+) .*");
            Matcher matcher = pattern.matcher(parsedSegments[1]);

            // If statement results in value of "0" denoting an error has occured
            // Adds values to appropriately named variable (for calculation clarity)
            if (matcher.find()) {
                parsedMinuteValue = Integer.parseInt(matcher.group(1));
            }

            // No calculation required, just add minute value to totalMinutes variable
            totalMinutes = parsedMinuteValue;
        }

        // Trims whitespace off the movementDirection String
        movementDirection = movementDirection.trim();

        // Switch statement based on movementType.
        // Each movement type has a set movement rate (MPH).
        // "Walk": 3MPH, "Run": 6MPH, Horse trot: 4MPH
        // "Horse gallop": 15MPH, "Elephant ride": 6MPH .
        // total Minutes are cast to "double" and divided by 60.00 to get "hours".
        // "hours" are mutliplied by the rate of the specified movement type to get "miles".
        // "miles" are assigned to the "totalMiles" variable.
        switch(movementType){
            case "Walk" :
                totalMiles = ((double)(totalMinutes) / 60.00) * 3.00;
                break;
            case "Run" :
                totalMiles = ((double)(totalMinutes) / 60.00) * 6.00;
                break;
            case "Horse trot" :
                totalMiles = ((double)(totalMinutes) / 60.00) * 4.00;
                break;
            case "Horse gallop" :
                totalMiles = ((double)(totalMinutes) / 60.00) * 15.00;
                break;
            case "Elephant ride" :
                totalMiles = ((double)(totalMinutes) / 60.00) * 6.00;
                break;
        }

        // movementDirection/totalMiles key/value pairings are passed to the "setDirectionMiles"
        // method of the mapDirectionsDataObject, which accumulates that information in
        // the mapDirectionsDataObject's hashmap 
        mapDirectionsDataObject.setDirectionMiles(movementDirection, totalMiles);

        //************************* MAY BE Unecessary, leftovers*/
        //resetting values for variable re-use
        parsedHourValue = 0;
        parsedMinuteValue = 0;
        totalMinutes = 0;
        totalMiles = 0.0;
        movementType = "";
        movementDirection = "";
    }

}