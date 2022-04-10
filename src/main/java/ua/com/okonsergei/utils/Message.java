package ua.com.okonsergei.utils;

public enum Message {

    ERROR_INPUT("Invalid number entered"),
    SUCCESSFUL_OPERATION("Successful operation");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
