import java.util.HashMap;

public class MapDirectionsData{

    HashMap<String, Double> MapDirectionSums = new HashMap<String, Double>();

    final double cartesianOriginCoordinateX = 0;
    final double cartesianOriginCoordinateY = 0;

    double cartesianCurrentCoordinateX;
    double cartesianCurrentCoordinateY;

    double cartesianTargetCoordinateX;
    double cartesianTargetCoordinateY;

    double directionsPathTotalDistance;
    double directPathTotalDistance;

    double directPathBearing;

    public MapDirectionsData(){
        MapDirectionSums.put("N", 0.0);
        MapDirectionSums.put("NE", 0.0);
        MapDirectionSums.put("E", 0.0);
        MapDirectionSums.put("SE", 0.0);
        MapDirectionSums.put("S", 0.0);
        MapDirectionSums.put("SW", 0.0);
        MapDirectionSums.put("W", 0.0);
        MapDirectionSums.put("NW", 0.0);

        cartesianCurrentCoordinateX = 0;
        cartesianCurrentCoordinateY = 0;
    
        cartesianTargetCoordinateX = 0;
        cartesianTargetCoordinateY = 0;
    
        directionsPathTotalDistance = 0;
        directPathTotalDistance = 0;

        directPathBearing = 0;
    }

    public void setDirectionMiles(String direction, double miles){
        MapDirectionSums.put(direction, MapDirectionSums.get(direction) + miles);
    }

    public double getDirectionMiles(String direction){
        return MapDirectionSums.get(direction);
    }

    public String getTargetCoordinateInfo(){
        //System.out.println("X Coord: " + cartesianTargetCoordinateX);
        //System.out.println("Y Coord: " + cartesianTargetCoordinateY);
        //System.out.println("distance of straight path (Miles): " + directPathTotalDistance);
        //System.out.println("distance in Original Instructions (Miles): " + directionsPathTotalDistance);
        //System.out.println("bearing from Origin (in Degrees): " + directPathBearing);

        return  "X Coordinate: " + cartesianTargetCoordinateX + "\n" +
                "Y Coordinate: " + cartesianTargetCoordinateY + "\n" +
                "Distance Of Direct Path: " + directPathTotalDistance + " Miles\n" +
                "Distance Of Original Instructions: " + directionsPathTotalDistance + " Miles\n" +
                "Bearing from Origin Point: " + directPathBearing + " Degrees\n";
    }
    
    public void calculateNextCoordinates(){

    }

    public void calculateTotalDistance(){
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("N");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("NE");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("E");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("SE");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("S");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("SW");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("W");
        directionsPathTotalDistance = directionsPathTotalDistance + MapDirectionSums.get("NW");
    }

    //Finds direct path distance with pythagorean theorem (a^2 + b^2 = c^2)
    public void calculateDirectPathDistance(){
        double pythagoreanSideA = cartesianTargetCoordinateX * cartesianTargetCoordinateX;
        double pythagoreanSideB = cartesianTargetCoordinateY * cartesianTargetCoordinateY;
        double pythagoreanSideC = Math.sqrt(pythagoreanSideA + pythagoreanSideB);

        directPathTotalDistance = pythagoreanSideC;
    }

    //Calculates bearing for travelling from origin
    public void calculateDirectPathBearing(){
        
        //directPathBearing = 360.00 + Math.toDegrees(Math.atan(cartesianTargetCoordinateX / cartesianTargetCoordinateY));

        //directPathBearing = Math.toDegrees(Math.atan(5 / 3));

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

    //calculates final target coordinates with aggregated directional distance information
    public void calculateTargetVector(){

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
}