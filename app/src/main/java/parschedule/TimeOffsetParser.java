package parschedule;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeOffsetParser {
    private static final Pattern OFFSET_PATTERN = Pattern.compile("(\\d+)([smhd])");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d\\d) ?([aApP][mM])");

    public static Duration parseTimeOffset(String input) {
        Matcher matcher = OFFSET_PATTERN.matcher(input);
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

        Calendar timeOfDay = timeOfDayParser(input, seconds);
        return diff(timeOfDay, Calendar.getInstance());
    }
    public static Duration diff(Calendar cal1, Calendar cal2) {
        return Duration.between(cal1.toInstant(), cal2.toInstant());
    }
    public static Calendar timeOfDayParser(String input, long offSet){
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.SECOND, (int) offSet);
        Matcher matcher = TIME_PATTERN.matcher(input);

        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = Integer.parseInt(matcher.group(2));
            String unit = matcher.group(3);
            

            switch(unit.toLowerCase()){
                case "am":
                    if(hour == 12){
                        hour = 0;
                    }
                    break;
                case "pm":
                    if (hour != 12){
                        hour += 12;
                    }
                    break;
                default:
                throw new IllegalArgumentException("Invalid time of day: " + unit);

            }
            calender.set(Calendar.HOUR, hour);
            calender.set(Calendar.MINUTE, minute);
            calender.set(Calendar.SECOND, 0);
        }
        return calender;
    }


}

