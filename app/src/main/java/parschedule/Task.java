import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Task {
    private String name;
    private String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Function<Task, String> serialize = task -> {
        return task.getName() + "|" + task.getDescription();
    };

    public static Function<String, Task> deserialize = str -> {
        String[] parts = str.split("\\|");
        return new Task(parts[0], parts[1]);
    };

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.addTask(new Task("Task 1", "Do something"));
        scheduler.addTask(new Task("Task 2", "Do something else"));

        List<String> taskStrings = scheduler.getTaskStrings();
        System.out.println("Serialized tasks:");
        taskStrings.forEach(System.out::println);

        List<Task> tasks = scheduler.getTasks();
        System.out.println("Deserialized tasks:");
        tasks.forEach(task -> System.out.println(task.getName() + " - " + task.getDescription()));
    }
}

class Scheduler {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<String> getTaskStrings() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(Task.serialize.apply(task));
        }
        return taskStrings;
    }

    public void setTaskStrings(List<String> taskStrings) {
        tasks.clear();
        for (String taskString : taskStrings) {
            tasks.add(Task.deserialize.apply(taskString));
        }
    }
}
