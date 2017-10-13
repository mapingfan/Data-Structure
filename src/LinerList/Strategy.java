package LinerList;

public interface Strategy {
    /**
     * @param o
     * @param e
     * @return return true if equals else return false;
     */
    public boolean equal(Object o, Object e);

    /**
     * @param o
     * @param e
     * @return return 1 if o>e ; if o=e, return 0, if o < e return -1;
     */
    public int compare(Object o, Object e);
}
