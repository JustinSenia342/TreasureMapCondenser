import java.util.HashMap;

public class MapDirectionsData{

    HashMap<String, Integer> MapDirectionSums = new HashMap<String, Integer>();

    public MapDirectionsData(){
        MapDirectionSums.put("N", 0);
        MapDirectionSums.put("NE", 0);
        MapDirectionSums.put("E", 0);
        MapDirectionSums.put("SE", 0);
        MapDirectionSums.put("S", 0);
        MapDirectionSums.put("SW", 0);
        MapDirectionSums.put("W", 0);
        MapDirectionSums.put("NW", 0);
    }

    public void setDirectionMiles(String direction, int miles){
        MapDirectionSums.put(direction, MapDirectionSums.get(direction) + miles);
    }

    public int getDirectionMiles(String direction){
        return MapDirectionSums.get(direction);
    }

}