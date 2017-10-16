package Queue.Interface;

public interface Queue {
    int getSize();
    boolean isEmpty();
    //make the object enter the queue;
    void enqueue(Object object);
    //remove the first object in the queue;
    Object dequeue();
   //get the first element in the queue;
    Object peek();
}
