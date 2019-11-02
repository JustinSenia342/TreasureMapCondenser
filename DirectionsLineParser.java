import java.util.regex.Matcher;
import java.util.regex.Pattern;


class DirectionsLineParser{

    public static void parseLine(String unparsedLine, MapDirectionsData mapDirectionsDataObject){
        //String oneLine = "Walk, 10 hours 20 minutes, N";
        //String oneLine = "Elephant ride, 4 hours, E";
        //String oneLine = "Run, 11 minutes, W";
        

        String[] parsedSegments = unparsedLine.split(",");

        String movementType = parsedSegments[0];
        String movementDirection = parsedSegments[2];

        int parsedHourValue = 0;
        int parsedMinuteValue = 0;

        int totalMinutes = 0;
        double totalMiles = 0.0;

        if (parsedSegments[1].contains("hour")){
            if (parsedSegments[1].contains("minute")){
                //case where both hour(s) and minute(s) exist
                //Pattern pattern = Pattern.compile("(^\\s+)(?>\\d+)(?>\\s+)(?>\\d+)");
                ////System.out.println(parsedSegments[1]);

                Pattern pattern = Pattern.compile(" (\\d+) .* (\\d+) .*");
                Matcher matcher = pattern.matcher(parsedSegments[1]);
                if (matcher.find()) {
                    parsedHourValue = Integer.parseInt(matcher.group(1));
                    parsedMinuteValue = Integer.parseInt(matcher.group(2));
                }

                totalMinutes = parsedMinuteValue + (parsedHourValue * 60);
                //System.out.println(totalMinutes);
            }
            else {
                //case where only hour(s) exists, no minute(s)
                Pattern pattern = Pattern.compile(" (\\d+) .*");
                Matcher matcher = pattern.matcher(parsedSegments[1]);
                if (matcher.find()) {
                    parsedHourValue = Integer.parseInt(matcher.group(1));
                }

                totalMinutes = parsedHourValue * 60;
                //System.out.println(totalMinutes);
            }
        }
        else{
            //Only contains minute(s)
            Pattern pattern = Pattern.compile(" (\\d+) .*");
            Matcher matcher = pattern.matcher(parsedSegments[1]);
            if (matcher.find()) {
                parsedMinuteValue = Integer.parseInt(matcher.group(1));
            }

            totalMinutes = parsedMinuteValue;
            //System.out.println(totalMinutes);
        }

        movementDirection = movementDirection.trim();

        //System.out.println(parsedSegments[0]);
        //System.out.println(totalMinutes);
        //System.out.println(parsedSegments[2]);

        System.out.print(movementType);
        System.out.print(" " + totalMinutes + " ");
        System.out.println(movementDirection);

        //Walk          - 3 mph
        //Run           - 6 mph
        //Horse trot    - 4 mph
        //Horse gallop  - 15 mph
        //Elephant ride - 6 mph

        //Calculate miles traveled in specific direction (Miles = hours * MilesPerHour)
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

        mapDirectionsDataObject.setDirectionMiles(movementDirection, totalMiles);

        //resetting values for variable re-use
        parsedHourValue = 0;
        parsedMinuteValue = 0;
        totalMinutes = 0;
        totalMiles = 0.0;
        movementType = "";
        movementDirection = "";
    }

}