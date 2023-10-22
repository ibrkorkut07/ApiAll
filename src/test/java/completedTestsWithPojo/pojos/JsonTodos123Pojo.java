package completedTestsWithPojo.pojos;

public class JsonTodos123Pojo {

      /*  status kodunun 200,
          content type'inin "application/json"
          Headers'daki
          "Server" in "cloudflare"
          response body'deki
                "userId"'nin 7,
                "id" nin 123,
                "title" in "esse et quis iste est earum aut impedit" ve
                "completed" bolumunun false oldugunu test edin  */

    private int statusCode;
    private String contentType;
    private String Server;
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public JsonTodos123Pojo() {
    }

    public JsonTodos123Pojo(int statusCode, String contentType, String server, int userId, int id, String title, boolean completed) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        Server = server;
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "JsonTodos123Pojo{" +
                "statusCode=" + statusCode +
                ", contentType='" + contentType + '\'' +
                ", Server='" + Server + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
