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

    public void getTargetCoordinateInfo(){
        System.out.println("X Coord: " + cartesianTargetCoordinateX);
        System.out.println("Y Coord: " + cartesianTargetCoordinateY);
        System.out.println("distance of straight path (Miles): " + directPathTotalDistance);
        System.out.println("distance in Original Instructions (Miles): " + directionsPathTotalDistance);
        System.out.println("bearing from Origin (in Degrees): " + directPathBearing);
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

        double xCoord = -3.00;
        double yCoord = 1.00;
        double hypot = Math.sqrt(10.00);

        //Q1
        if (xCoord >= 0 && yCoord >= 0){
            directPathBearing = Math.atan(xCoord / yCoord);
            directPathBearing = Math.toDegrees(directPathBearing);
        }
        //Q4
        else if (xCoord >= 0 && yCoord < 0){
            directPathBearing = Math.acos(xCoord / hypot);
            directPathBearing = Math.toDegrees(directPathBearing);
            directPathBearing = directPathBearing + 90.00;
        }
        //Q3
        else if (xCoord < 0 && yCoord < 0){
            directPathBearing = Math.atan(xCoord / yCoord);
            directPathBearing = Math.toDegrees(directPathBearing);
            directPathBearing = directPathBearing + 180.00;
        }
        //Q2
        else if (xCoord < 0 && yCoord >= 0){
            directPathBearing = Math.asin(yCoord / hypot);
            directPathBearing = Math.toDegrees(directPathBearing);
            directPathBearing = directPathBearing + 270.00;
        }

        //directPathBearing = Math.tan(xCoord / yCoord);
        //directPathBearing = Math.toDegrees(directPathBearing);

        //all Q1
        //directPathBearing = Math.tan(5.00 / 5.00);

        //Cos Q4
        //directPathBearing = Math.cos(5.00 / -5.00);

        //Tan Q3
        //directPathBearing = Math.tan(-5.00 / -5.00);

        //Sin Q2
        //directPathBearing = Math.sin(-5.00 / -5.00);

        //directPathBearing = Math.toDegrees(directPathBearing);

        //if (directPathBearing == 360){
        //    directPathBearing = 0;
        //}

        //directPathBearing = directPathBearing * (180.00 / Math.PI);
        //directPathBearing = directPathBearing;
        /*
        //handles case where both coordinates are equal to origin
        if(cartesianTargetCoordinateX == 0 && cartesianTargetCoordinateY == 0){
            System.out.println("Target Is Origin, No Travel Required");
        }
        //handles 0 (or) 360 degree and 180 degree cases 
        else if (cartesianTargetCoordinateX >= 0 &&){
            
        }
        //handles 90 or 270 degree cases

        //handles 1 through 89 degree cases

        //handles 91 through 179 degree cases
        */
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