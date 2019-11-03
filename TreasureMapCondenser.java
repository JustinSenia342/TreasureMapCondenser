
class TreasureMapCondenser{

    MapDirectionsData mapDirectionsDataObject;

    public TreasureMapCondenser(){
        mapDirectionsDataObject = new MapDirectionsData();
    }

    public String condenseTreasureMap(){

        TreasureMapProcessor.processTreasureMap("treasureMap.txt", mapDirectionsDataObject);

        mapDirectionsDataObject.calculateTotalDistance();
        mapDirectionsDataObject.calculateTargetVector();
        mapDirectionsDataObject.calculateDirectPathDistance();
        mapDirectionsDataObject.calculateDirectPathBearing();

        return mapDirectionsDataObject.getTargetCoordinateInfo();
    }

}