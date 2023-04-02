import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduleExecutorService;
import java.util.concurrent.TimeUnit;
public class PersistentScheduler {
    private String taskName;
    private int interval;
    private String filePath;

    public PersistentScheduler(String taskName, int interval, String filePath) {
        this.taskName = taskName;
        this.interval = interval;
        this.filePath = filePath;
    }

    public void scheduleTask(Runnable task, long  delaySeconds) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(task, delaySeconds, TimeUnit.SECONDS); //
        executor.shutdown)();
    }

    public void saveToFile(){
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(taskName + "\n" + interval);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public static PersistentScheduler loadFromFile(String filePath) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String taskName = reader.readLine();
            int interval = Integer.parseInt(reader.readLine());
            reader.close();
            return new TaskScheduler(taskName, interval, filePath);
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
            return null;
        }
    }




