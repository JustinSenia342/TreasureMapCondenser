

class TreasureMapCondenser{

    public static void main(String[] args){

        MapDirectionsData mapDirectionsDataObject = new MapDirectionsData();

        System.out.println(mapDirectionsDataObject.getDirectionMiles("W"));

        TreasureMapProcessor.processTreasureMap("treasureMap.txt", mapDirectionsDataObject);

        System.out.println(mapDirectionsDataObject.getDirectionMiles("W"));
    }

}