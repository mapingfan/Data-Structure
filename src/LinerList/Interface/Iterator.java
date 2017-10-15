package LinerList.Interface;

public interface Iterator {
    //move to first element;
    void first();
    //move to next element;
    Object next();
    //if has next element,return true, else return false;
    boolean hasNext();
    Object currentItem();
}
