
/*
    Written By: Justin Senia
    Written: 11/02/2019
    Last Updated: 11/02/2019
    TreasureMapCondenser.java (class): 
*/

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
        mapDirectionsDataObject.calculateBearingPolarDirection();

        return mapDirectionsDataObject.getTargetCoordinateInfo();
    }

}