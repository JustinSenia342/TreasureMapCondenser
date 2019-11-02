import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;


class DirectionsLineParser{

    public static void main(String[] args){
        //String oneLine = "Walk, 10 hours 20 minutes, N";
        //String oneLine = "Elephant ride, 4 hours, E";
        String oneLine = "Run, 11 minutes, W";
        

        String[] parsedSegments = oneLine.split(",");

        int parsedHourValue = 0;
        int parsedMinuteValue = 0;
        int totalMinutes = 0;

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

        parsedSegments[2] = parsedSegments[2].trim();

        System.out.println(parsedSegments[0]);
        System.out.println(totalMinutes);
        System.out.println(parsedSegments[2]);

        //resetting values for variable re-use
        parsedHourValue = 0;
        parsedMinuteValue = 0;
        totalMinutes = 0;
    }
}