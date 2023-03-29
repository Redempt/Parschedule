package parschedule;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeOffsetParser {

    public static Duration parseTimeOffset(String input) {
        Pattern pattern = Pattern.compile("(\\d+)([smhd])");
        Matcher matcher = pattern.matcher(input);
        LocalTime time = LocalTime.parse(timeString.toUpperCase(), DateTimeFormatter.ofPattern("h:mma"));
        return Duration.between(LocalTime.NOON, time);

        long seconds = 0;
        while (matcher.find()) {
            String value = matcher.group(1);
            String unit = matcher.group(2);

            switch (unit) {
                case "s":
                    seconds += Long.parseLong(value);
                    break;
                case "m":
                    seconds += Long.parseLong(value) * 60;
                    break;
                case "h":
                    seconds += Long.parseLong(value) * 60 * 60;
                    break;
                case "d":
                    seconds += Long.parseLong(value) * 24 * 60 * 60;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid time unit: " + unit);
            }
        }

        return Duration.ofSeconds(seconds);
    }
    
    public static Duration diff(Calendar cal1, Calendar cal2) {
        return Duration.between(cal1.toInstant(), cal2.toInstant());
   
}

