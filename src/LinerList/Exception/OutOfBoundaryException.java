package LinerList.Exception;

public class OutOfBoundaryException extends RuntimeException {

    public OutOfBoundaryException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        System.out.println("Out of boundary exception");
    }
}
