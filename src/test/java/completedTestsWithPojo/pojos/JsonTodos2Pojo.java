package completedTestsWithPojo.pojos;

public class JsonTodos2Pojo {

    private int userId;
    private int id;
    private String title;
    private boolean completed;
    private String Via;
    private String Server;

    public String getVia() {
        return Via;
    }

    public void setVia(String via) {
        Via = via;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public JsonTodos2Pojo() {
    }

    public JsonTodos2Pojo(int userId, int id, String title, boolean completed, String via, String server) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
        Via = via;
        Server = server;
    }

    @Override
    public String toString() {
        return "JsonTodos2Pojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", Via='" + Via + '\'' +
                ", Server='" + Server + '\'' +
                '}';
    }

}
