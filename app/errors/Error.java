package errors;

public class Error {

    private Integer httpCode;
    private String type;
    private String message;

    public Error() {
    }

    public Error(Integer httpCode, String type, String message) {
        this.httpCode = httpCode;
        this.type = type;
        this.message = message;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
