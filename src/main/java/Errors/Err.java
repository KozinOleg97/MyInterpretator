package Errors;

public class Err extends Error {
    Integer line;
    public Err(String message, Integer line) {
        super(message);
        this.line = line;
    }
}
