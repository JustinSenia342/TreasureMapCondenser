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

    //calculates final target coordinates with aggregated directional distance information
    public void calculateTargetVector(){

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "N"
        cartesianTargetCoordinateX = cartesianTargetCoordinateX + MapDirectionSums.get("N");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "NE"
        //cartesianTargetCoordinateX = cartesianTargetCoordinateX + MapDirectionSums.get("NE");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "E"
        cartesianTargetCoordinateY = cartesianTargetCoordinateY + MapDirectionSums.get("E");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "SE"
        //cartesianTargetCoordinateX = cartesianTargetCoordinateX + MapDirectionSums.get("SE");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "S"
        cartesianTargetCoordinateX = cartesianTargetCoordinateX - MapDirectionSums.get("S");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "SW"
        //cartesianTargetCoordinateX = cartesianTargetCoordinateX + MapDirectionSums.get("SW");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "W"
        cartesianTargetCoordinateY = cartesianTargetCoordinateY - MapDirectionSums.get("W");

        //Calculates Target Coordinate Changes for Aggregated Miles Travelled "NW"
        //cartesianTargetCoordinateX = cartesianTargetCoordinateX + MapDirectionSums.get("NW");

    }
}