package exception;

public class NotEnoughWordsInList extends RuntimeException {
    public NotEnoughWordsInList(String exceptionMessage) {
        super(exceptionMessage);
    }
}
