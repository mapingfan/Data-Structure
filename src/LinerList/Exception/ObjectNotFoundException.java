package LinerList.Exception;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        System.out.println("Object is not in the list");
    }
}
