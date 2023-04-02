
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
        return null;
    };

    public static Function<String, Task> deserialize = str -> {
        return null;
    };
}
