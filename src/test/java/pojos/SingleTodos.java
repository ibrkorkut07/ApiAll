package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleTodos {
    /*
    {   "userId": 1,
        "id": 2,
        "title": "quis ut nam facilis et officia qui",
        "completed": false  }
     */
        /*
        And "completed" value should be false
        And "title” should be “quis ut nam facilis et officia qui”
        Then "userId"  should be 1
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
    */

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public SingleTodos() {
    }

    public SingleTodos(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
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

    @Override
    public String toString() {
        return "SingleTodos{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
