package by.bsu.fpmi.olya.levels;

/**
 * Created by Lenovo on 02.05.2016.
 */
public class LevelException extends Exception {
    public LevelException() {
    }

    public LevelException(String message) {
        super(message);
    }

    public LevelException(String message, Throwable cause) {
        super(message, cause);
    }

    public LevelException(Throwable cause) {
        super(cause);
    }
}
