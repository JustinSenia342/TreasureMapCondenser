package src.main.java.tmapcondenser.model;

import java.util.HashMap;
import java.lang.Math;
import java.text.DecimalFormat;

/*
    Written By: Justin Senia
    Written: 11/01/2019
    Last Updated: 11/03/2019
    MapDirectionsData.java (class): Used as the Data Model Object for storing
    information that can be viewed by the user and controlled via the
    GUI from "TrasureMapCondenserGui.java"
*/

class MapDirectionsData{

    // Used to format final output
    private DecimalFormat decimalFormatter = new DecimalFormat("####0.00");

    // Contains Accumulated direction based travel distances
    private HashMap<String, Double> MapDirectionSums = new HashMap<String, Double>();

    // Ensures Origin is always set to '0'
    private final double cartesianOriginCoordinateX = 0;
    private final double cartesianOriginCoordinateY = 0;

    // Act as X,Y coordinate markers for determining Target
    // location based on parsed map directional information
    private double cartesianTargetCoordinateX;
    private double cartesianTargetCoordinateY;

    // Stores total distance from Map directions
    private double directionsPathTotalDistance;

    // Stores total distance from parsed/direct path
    private double directPathTotalDistance;     

    // Stores bearing (in degrees, starting from North (0° or 360°), going clockwise)
    // to determine which direction needed to travel from origin to arrive at target destination
    private double directPathBearing;

    // Stores mnemonic direction of "directPathBearing" angle from origin
    // Used to output directions for travelling to target destination 
    private String bearingPolarDirection;


    // Constructor for creating objects of this class
    MapDirectionsData(){

        // Creating hashmap key/value pairs, and initializing them to 0.00
        MapDirectionSums.put("N", 0.0);
        MapDirectionSums.put("NE", 0.0);
        MapDirectionSums.put("E", 0.0);
        MapDirectionSums.put("SE", 0.0);
        MapDirectionSums.put("S", 0.0);
        MapDirectionSums.put("SW", 0.0);
        MapDirectionSums.put("W", 0.0);
        MapDirectionSums.put("NW", 0.0);
    
        // Initializing cartesian X and Y values to Origin (0, 0)
        cartesianTargetCoordinateX = cartesianOriginCoordinateX;
        cartesianTargetCoordinateY = cartesianOriginCoordinateY;
    
        // Zeroing out calculated distance metric variables
        directionsPathTotalDistance = 0;
        directPathTotalDistance = 0;

        // Initializing "bearing" and "bearing direction" with default values
        directPathBearing = 0;
        bearingPolarDirection = "";
    }


    // Setter method that accumulates miles by adding newly calculated miles
    // to the previous sum of miles (in a specified direction) and updating
    // the value in the "MapDirectionSums" hashmap
    // "direction" Ex: "N", "SW", "E", etc. | "miles" Ex: 34.00, 23.34843, etc.
    void setDirectionMiles(String direction, double miles){
        MapDirectionSums.put(direction, MapDirectionSums.get(direction) + miles);
    }

    // Getter method to retrieve miles accumulated from the "MapDirectionSums" hashmap
    // "direction" Ex: "N", "SW", "E", etc...
    double getDirectionMiles(String direction){
        return MapDirectionSums.get(direction);
    }


    // Getter method for returning summary of calculated information
    // for display in GUI View for the User 
    String getTargetCoordinateInfo(){
        return  "  " + "\n" +
                "  *************************************************************************************************" + "\n" +
                "  *                                     PARSED TREASURE MAP RESULTS                                 " + "\n" +
                "  *************************************************************************************************" + "\n\n" +
                "    Cartesian X Destination Coordinate (Miles) :  " + decimalFormatter.format(cartesianTargetCoordinateX) + "\n" +
                "    Cartesian Y Destination Coordinate (Miles) :  " + decimalFormatter.format(cartesianTargetCoordinateY) + "\n\n" +
                "    ---------------------------------------------------------------------------" + "\n\n" +
                "    Distance Of Direct Path :  " + decimalFormatter.format(directPathTotalDistance) + " Miles\n" +
                "    Distance Of Original Instructions :  " + decimalFormatter.format(directionsPathTotalDistance) + " Miles\n\n" +
                "      -  Total Travel Distance Reduction From Taking Direct Route :  " + decimalFormatter.format(directionsPathTotalDistance - directPathTotalDistance) + " Miles\n\n" +
                "    ---------------------------------------------------------------------------" + "\n\n" +
                "    Directions to Destination :   From Origin Point, Travel " + decimalFormatter.format(directPathTotalDistance) + " Miles,  " + decimalFormatter.format(directPathBearing) + '°' + "  " + bearingPolarDirection + "\n\n" +
                "      -  Estimated Travel Time By Walking  [(AVG) 3 MPH] :  " + decimalFormatter.format(directPathTotalDistance / 3.00) + " Hour(s)\n" +
                "      -  Estimated Travel Time By Running  [(AVG) 6 MPH] :  " + decimalFormatter.format(directPathTotalDistance / 6.00) + " Hour(s)\n" +
                "      -  Estimated Travel Time By Horse (Trotting)  [(AVG) 4 MPH] :  " + decimalFormatter.format(directPathTotalDistance / 4.00) + " Hour(s)\n" +
                "      -  Estimated Travel Time By Horse (Galloping)  [(AVG) 15 MPH] :  " + decimalFormatter.format(directPathTotalDistance / 15.00) + " Hour(s)\n" +
                "      -  Estimated Travel Time By Elephant (Ride)  [(AVG) 6 MPH] :  " + decimalFormatter.format(directPathTotalDistance / 6.00) + " Hour(s)\n\n" +
                "  *************************************************************************************************" + "\n\n";
    }


    // Calculates total distance (in Miles) of all aggregated treasure map direction data
    // Used to calculate how this program's optimization has influenced potential travel time
    void calculateTotalDistance(){
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("N");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("NE");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("E");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("SE");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("S");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("SW");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("W");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("NW");
    }


    // Calculates direct path distance with pythagorean theorem (a^2 + b^2 = c^2)
    // Determines Direct Path Length (Hypotenuse) for use in Trigonometric "calculateDirectPathBearing" Function
    void calculateDirectPathDistance(){
        double pythagoreanSideA = cartesianTargetCoordinateX * cartesianTargetCoordinateX;
        double pythagoreanSideB = cartesianTargetCoordinateY * cartesianTargetCoordinateY;
        double pythagoreanSideC = Math.sqrt(pythagoreanSideA + pythagoreanSideB);

        directPathTotalDistance = pythagoreanSideC;
    }


    // Calculates bearing (in degrees) for travelling from origin straight to destination
    // Uses concepts from Trigonometry to approximate total angle based on a re-orientated
    // Unit-Circle To Base starting point from North and calculate clockwise
    void calculateDirectPathBearing(){
        
        // Temp variables, renamed to make calculations easier to follow
        double xCoord = cartesianTargetCoordinateX;
        double yCoord = cartesianTargetCoordinateY;
        double hypot = directPathTotalDistance;

        //Case: Destination is at Origin
        if (xCoord == 0.00 && yCoord == 0.00){
            directPathBearing = 0.00;
        }
        //Case: N Axis
        else if ((xCoord == 0.00 || xCoord == 360.00) && yCoord > 0.00){
            directPathBearing = 0.00;
        }
        //Case: Q1 (Uses arctan)
        else if (xCoord > 0.00 && yCoord > 0.00){
            directPathBearing = Math.atan(xCoord / yCoord);
            directPathBearing = Math.toDegrees(directPathBearing);
        }
        //Case: E Axis
        else if (xCoord > 0.00 && yCoord == 0.00){
            directPathBearing = 90.00;
        }
        //Case: Q4 (Must Use arccos)
        else if (xCoord > 0.00 && yCoord < 0.00){
            directPathBearing = Math.acos(xCoord / hypot);
            directPathBearing = Math.toDegrees(directPathBearing);
            directPathBearing = directPathBearing + 90.00;
        }
        //Case: S Axis
        else if (xCoord == 0.00 && yCoord < 0.00){
            directPathBearing = 180.00;
        }
        //Case: Q3 (Must Use arctan)
        else if (xCoord < 0.00 && yCoord < 0.00){
            directPathBearing = Math.atan(xCoord / yCoord);
            directPathBearing = Math.toDegrees(directPathBearing);
            directPathBearing = directPathBearing + 180.00;
        }
        //Case: W Axis
        else if (xCoord < 0.00 && yCoord == 0.00){
            directPathBearing = 270.00;
        }
        //Case: Q2 (Must Use arcsin)
        else if (xCoord < 0.00 && yCoord > 0.00){
            directPathBearing = Math.asin(yCoord / hypot);
            directPathBearing = Math.toDegrees(directPathBearing);
            directPathBearing = directPathBearing + 270.00;
        }

    }


    // Previously calculated bearing from "calculateDirectPathBearing" and determines
    // which mnemonic should be used to describe the direction in navigation terminology.
    // Value determined by comparing bearing angle (starting at North) clockwise
    // and determining which range it falls under 
    void calculateBearingPolarDirection(){
        if ((directPathBearing >= 348.75 && directPathBearing <= 360.00) ||
            (directPathBearing >= 0.00 && directPathBearing < 11.25))
            {bearingPolarDirection = "North";}
        else if (directPathBearing >= 11.25 && directPathBearing < 33.75)
            {bearingPolarDirection = "North-North-East";}
        else if (directPathBearing >= 33.75 && directPathBearing < 56.25)
            {bearingPolarDirection = "North-East";}
        else if (directPathBearing >= 56.25 && directPathBearing < 78.75)
            {bearingPolarDirection = "East-North-East";}
        else if (directPathBearing >= 78.75 && directPathBearing < 101.25)
            {bearingPolarDirection = "East";}
        else if (directPathBearing >= 101.25 && directPathBearing < 123.75)
            {bearingPolarDirection = "East-South-East";}
        else if (directPathBearing >= 123.75 && directPathBearing < 146.25)
            {bearingPolarDirection = "South-East";}
        else if (directPathBearing >= 146.25 && directPathBearing < 168.75)
            {bearingPolarDirection = "South-South-East";}
        else if (directPathBearing >= 168.75 && directPathBearing < 191.25)
            {bearingPolarDirection = "South";}
        else if (directPathBearing >= 191.25 && directPathBearing < 213.75)
            {bearingPolarDirection = "South-South-West";}
        else if (directPathBearing >= 213.75 && directPathBearing < 236.25)
            {bearingPolarDirection = "South-West";}
        else if (directPathBearing >= 236.25 && directPathBearing < 258.75)
            {bearingPolarDirection = "West-South-West";}
        else if (directPathBearing >= 258.75 && directPathBearing < 281.25)
            {bearingPolarDirection = "West";}
        else if (directPathBearing >= 281.25 && directPathBearing < 303.75)
            {bearingPolarDirection = "West-North-West";}
        else if (directPathBearing >= 303.75 && directPathBearing < 326.25)
            {bearingPolarDirection = "North-West";}
        else if (directPathBearing >= 326.25 && directPathBearing < 348.75)
            {bearingPolarDirection = "North-North-West";}
        else
            {bearingPolarDirection = "Error: Out of Range";}
    }


    // Calculates final target coordinates with aggregated directional distance information.
    // Applies accumulated directional distances to coordinates beginning at origin.
    // N E S W Directions are simple addition/subtraction, but Diagonal directions require 
    // Trig Calc (45 45 90 Right Triangle) then added to each final cartesian coordinate variable.
    void calculateTargetVector(){

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "N"
        cartesianTargetCoordinateX = cartesianTargetCoordinateX + MapDirectionSums.get("N");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "NE"
        //Assume direction is perfect, forming a 45 45 90 right triangle with 
        //path travelled being the hypotenuse so (LegX = LegY = Hypotenuse / Math.sqrt(2))
        cartesianTargetCoordinateX = cartesianTargetCoordinateX + (MapDirectionSums.get("NE") / Math.sqrt(2));
        cartesianTargetCoordinateY = cartesianTargetCoordinateY + (MapDirectionSums.get("NE") / Math.sqrt(2));

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "E"
        cartesianTargetCoordinateY = cartesianTargetCoordinateY + MapDirectionSums.get("E");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "SE"
        //Assume direction is perfect, forming a 45 45 90 right triangle with 
        //path travelled being the hypotenuse so (LegX = LegY = Hypotenuse / Math.sqrt(2))
        cartesianTargetCoordinateX = cartesianTargetCoordinateX + (MapDirectionSums.get("SE") / Math.sqrt(2));
        cartesianTargetCoordinateY = cartesianTargetCoordinateY - (MapDirectionSums.get("SE") / Math.sqrt(2));

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "S"
        cartesianTargetCoordinateX = cartesianTargetCoordinateX - MapDirectionSums.get("S");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "SW"
        //Assume direction is perfect, forming a 45 45 90 right triangle with 
        //path travelled being the hypotenuse so (LegX = LegY = Hypotenuse / Math.sqrt(2))
        cartesianTargetCoordinateX = cartesianTargetCoordinateX - (MapDirectionSums.get("SW") / Math.sqrt(2));
        cartesianTargetCoordinateY = cartesianTargetCoordinateY - (MapDirectionSums.get("SW") / Math.sqrt(2));

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "W"
        cartesianTargetCoordinateY = cartesianTargetCoordinateY - MapDirectionSums.get("W");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "NW"
        //Assume direction is perfect, forming a 45 45 90 right triangle with 
        //path travelled being the hypotenuse so (LegX = LegY = Hypotenuse / Math.sqrt(2))
        cartesianTargetCoordinateX = cartesianTargetCoordinateX - (MapDirectionSums.get("NW") / Math.sqrt(2));
        cartesianTargetCoordinateY = cartesianTargetCoordinateY + (MapDirectionSums.get("NW") / Math.sqrt(2));
    }

    void resetObjectData(){
        // Resetting hashmap key/value pairs, and initializing them to 0.00
        MapDirectionSums.put("N", 0.0);
        MapDirectionSums.put("NE", 0.0);
        MapDirectionSums.put("E", 0.0);
        MapDirectionSums.put("SE", 0.0);
        MapDirectionSums.put("S", 0.0);
        MapDirectionSums.put("SW", 0.0);
        MapDirectionSums.put("W", 0.0);
        MapDirectionSums.put("NW", 0.0);
    
        // Resetting cartesian X and Y values to Origin (0, 0)
        cartesianTargetCoordinateX = cartesianOriginCoordinateX;
        cartesianTargetCoordinateY = cartesianOriginCoordinateY;
    
        // Resetting calculated distance metric variables
        directionsPathTotalDistance = 0;
        directPathTotalDistance = 0;

        // Resetting "bearing" and "bearing direction" with default values
        directPathBearing = 0;
        bearingPolarDirection = "";
    }
}