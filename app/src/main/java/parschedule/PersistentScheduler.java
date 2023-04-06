package parschedule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PersistentScheduler {
    private List<Task> tasks = new ArrayList<>();
    private String filename;
    private ScheduledExecutorService scheduler;

    public PersistentScheduler(String filename) {
        this.filename = filename;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void scheduleTask(Task task, long delayMillis) {
        task.setDeliveryTime(System.currentTimeMillis() + delayMillis);
        scheduler.schedule(task::run, delayMillis, TimeUnit.MILLISECONDS);
    }

    public void saveToFile() throws IOException {
        String contents = tasks.stream().map(Task::toString).collect(Collectors.joining("\n"));
        Path path = Paths.get(filename);
        Files.writeString(path, contents, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static PersistentScheduler loadFromFile(String filePath) {
        return null;
    }

}




