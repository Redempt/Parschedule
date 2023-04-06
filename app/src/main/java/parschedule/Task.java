package parschedule;

public class Task {
    private Runnable run;
    private String payload;
    private long deliveryTime;

    public Task(Runnable run, String payload) {
        this.run = run;
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void run() {
        run.run();
    }

    public void setDeliveryTime(long time) {
        this.deliveryTime = time;
    }

    public long getDeliveryTime() {
        return deliveryTime;
    }

    public String toString() {
        return deliveryTime + " " + payload.replace("\n", "\\n");
    }
}
