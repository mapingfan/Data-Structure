package LinerList;

public interface List {
    /**
     * @return return the size of thr list;
     */
    public int getSize();

    /**
     * @return if the list is empty return true, else return false;
     */
    public boolean isEmpty();

    /**
     * @param o
     * @return if o in list, return true, else return false;
     */
    public boolean contains(Object o);

    /**
     * @param o
     * @return return the index of o in the list;
     */
    public int indexOf(Object o);

    /**
     *
     * @param pos position for inserting;
     * @param o object for inserting into the list;
     * if pos is not proper, throws exception;
     */
    public void insert(int pos, Object o) throws OutOfBoundaryException;

    /**
     * @param o
     * @param e
     * insert e into list before o;
     * if o is not in the list, throws exception;
     * if insertion is successful, return true else return false;
     */
    public boolean insertBefore(Object o, Object e)throws ObjectNotFoundException;

    /**
     * @param o
     * @param e
     * @return
     * @throws ObjectNotFoundException
     */
    public boolean insertAfter(Object o, Object e) throws ObjectNotFoundException;

    public Object remove(int pos) throws OutOfBoundaryException;

    /**
     * @param o
     * @return remove the first object equals o in the list.
     * if not found ,return false, else return true;
     */
    public boolean remove(Object o);

    /**
     * @param pos
     * @param e
     * @return
     * @throws OutOfBoundaryException
     * replace the object in the position of pos with e;
     * if pos is not proper, throws exception;
     * return the object replaced.
     */
    public Object replace(int pos, Object e) throws OutOfBoundaryException;

    /**
     * @param pos
     * @return return the object in the position of pos;
     *if pos is not proper, throws exception;
     * @throws OutOfBoundaryException
     */
    public Object get(int pos) throws OutOfBoundaryException;

    public void printList();
}
