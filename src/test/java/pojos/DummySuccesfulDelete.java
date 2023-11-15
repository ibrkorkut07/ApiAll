package pojos;

public class DummySuccesfulDelete {
    private String status;
    private String message;

    public DummySuccesfulDelete() {}

    public DummySuccesfulDelete(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummySuccesfulDelete{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
