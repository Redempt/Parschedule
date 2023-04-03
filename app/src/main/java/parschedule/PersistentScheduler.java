import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistentScheduler {
    private ArrayList<String> tasks = new ArrayList<String>();
    private String filePath;


    public PersistentScheduler(String filePath) {
        this.filePath = filePath;
    }

    public void scheduleTask(Runnable task, long  delaySeconds) {

    }

    public void saveToFile(){

    }

    public static PersistentScheduler loadFromFile(String filePath) {
        return null;
    }
}




