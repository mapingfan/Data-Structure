package LinerList.Strategy;

public class IntegerStrategy implements Strategy {
    @Override
    public boolean equal(Object o, Object e) {
        if (o instanceof Integer && e instanceof Integer) {
            Integer o_copy = (Integer)o;
            Integer e_copy = (Integer)e;
            if (o_copy.equals(e_copy)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compare(Object o, Object e) {
        if (o instanceof Integer && e instanceof Integer) {
            Integer o_copy = (Integer)o;
            Integer e_copy = (Integer)e;
            return o_copy.compareTo(e_copy);
        }
        return -1;
    }
}
