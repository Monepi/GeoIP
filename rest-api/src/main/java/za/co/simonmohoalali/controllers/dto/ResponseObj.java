package za.co.simonmohoalali.controllers.dto;

public class ResponseObj {
    private String message;

    public ResponseObj() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseObj{" +
                "message='" + message + '\'' +
                '}';
    }
}
