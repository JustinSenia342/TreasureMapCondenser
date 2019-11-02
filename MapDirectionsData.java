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
        System.out.println("distance (in Miles): " + directPathTotalDistance);
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