import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;


class DirectionsLineParser{

    public static void main(String[] args){
        String oneLine = "Walk, 10 hours 20 minutes, N";

        String[] parsedSegments = oneLine.split(",");

        if (parsedSegments[1].contains("hour")){
            if (parsedSegments[1].contains("minute")){
                //case where both hour and minutes exist
            }
            else {
                //case where only hour exists
            }
        }
        else{
            //Must contain minutes
        }

        parsedSegments[2] = parsedSegments[2].trim();

        System.out.println(parsedSegments[0]);
        System.out.println(parsedSegments[1]);
        System.out.println(parsedSegments[2]);

    }
}