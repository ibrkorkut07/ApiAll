package pojos;

public class DummyErrorDelete {
    private String message;

    public DummyErrorDelete() {}

    public DummyErrorDelete(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyErrorDelete{" +
                "message='" + message + '\'' +
                '}';
    }
}
