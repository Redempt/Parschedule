/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package parschedule;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    public static Duration diff(Calendar first, Calendar second) {
        return Duration.ofMillis(second.getTimeInMillis() - first.getTimeInMillis());
    }

    @Test
    public void simpleTimeOffsetTest() {
        assertEquals(TimeOffsetParser.parseTimeOffset("10s"), Duration.ofSeconds(10));
        assertEquals(TimeOffsetParser.parseTimeOffset("5m10s"), Duration.ofSeconds(310));
        assertEquals(TimeOffsetParser.parseTimeOffset("1d"), Duration.ofDays(1));
    }

    @Test
    public void simpleTimeSetTest() {
        Calendar threePM = Calendar.getInstance();
        threePM.set(Calendar.HOUR_OF_DAY, 15);
        threePM.set(Calendar.MINUTE, 0);
        threePM.set(Calendar.SECOND, 0);
        assertEquals(TimeOffsetParser.parseTimeOffset("3:00pm").getSeconds(), diff(Calendar.getInstance(), threePM).getSeconds());
    }

    @Test
    public void scheduleTask() {
        List<Task> tasks = new ArrayList<>();
        if (tasks[0] == "Task 1", "Do Something"){
            assertTrue("A task is scheduled.");
        }else{
            assertFalse("No task is scheduled.")
        }
    }
    @Test
    public void persistentSchedulerTest(){
        String filePath = "Task.txt";
        File file = new File(filePath);
        if(file.exists()){
            assertTrue("The file exists: " + filePath);
            file.delete();
        } else{
            assertFalse("File does not exist.");
        }
    }
}

