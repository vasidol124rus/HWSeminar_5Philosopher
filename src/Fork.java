public class Fork {
    private final int id;
    private volatile boolean isAvailable = true;

    public Fork(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
