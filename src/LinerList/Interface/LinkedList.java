package LinerList.Interface;

import LinerList.Exception.InvalidNodeException;
import LinerList.Exception.OutOfBoundaryException;
import LinerList.SingleLinkedList.SingleLinkListIterator;

/**
 * This interface is used to implement action on the level of node;
 * the list interface in this package implements action on the level of object,which is improper
 * for the linked list.
 * so, we write this interface;
 */
public interface LinkedList {
    //return the size of list;
    public int getSize();

    //return true if the list is empty, else return false;
    public boolean isEmpty();

    //return the first node;
    public Node first() throws OutOfBoundaryException;

    //return the last node;
    public Node last() throws OutOfBoundaryException;

    //return the node after p;
    public Node getNext(Node p) throws OutOfBoundaryException, InvalidNodeException;

    //return the node before p;
    public Node getPre(Node p) throws OutOfBoundaryException, InvalidNodeException;

    //insert object in the beginning and return the node represent the object;
    public Node insertFirst(Object object);

    //insert object in the ending and return the node represent the object;
    public Node insertLast(Object object);

    //insert the object after given node;
    public Node insertAfter(Node node, Object object) throws InvalidNodeException;

    //insert the object before given node;
    public Node insertBefore(Node node, Object object) throws InvalidNodeException;

    //remove the given node;
    public Object remove(Node node) throws InvalidNodeException;

    //remove the first node in the list;
    public Object removeFirst() throws OutOfBoundaryException, InvalidNodeException;

    //remove the last node in the list;
    public Object removeLast() throws OutOfBoundaryException, InvalidNodeException;

    //using the object to replace the given node;
    public Object replace(Node node, Object object) throws InvalidNodeException;

    //elements iterator;
    public SingleLinkListIterator elements();

}
