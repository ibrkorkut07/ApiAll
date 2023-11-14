package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public class BookingHeaders {

    private Integer statusCode;
    private String contentType;
    private String ContentLength;
    private String statusLine;
    private String Server;
    private String Connection;
    private String Via;

    public BookingHeaders() {}

    public BookingHeaders(Integer statusCode, String contentType, String statusLine, String server, String connection, String via) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.statusLine = statusLine;
        Server = server;
        Connection = connection;
        Via = via;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    public String getConnection() {
        return Connection;
    }

    public void setConnection(String connection) {
        Connection = connection;
    }

    public String getVia() {
        return Via;
    }

    public void setVia(String via) {
        Via = via;
    }

    @Override
    public String toString() {
        return "BookingHeaders{" +
                "statusCode=" + statusCode +
                ", contentType='" + contentType + '\'' +
                ", statusLine='" + statusLine + '\'' +
                ", Server='" + Server + '\'' +
                ", Connection='" + Connection + '\'' +
                ", Via='" + Via + '\'' +
                '}';
    }

}
