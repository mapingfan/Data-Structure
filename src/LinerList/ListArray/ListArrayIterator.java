package LinerList.ListArray;

import LinerList.Interface.Iterator;
import LinerList.Interface.Node;

import javax.jws.Oneway;

public class ListArrayIterator implements Iterator {
    private ListArray listArray;
    private int pnt;

    public ListArrayIterator(ListArray listArray) {
        this.listArray = listArray;
        pnt = -1;
    }

    @Override
    public void first() {
        pnt = 0;
    }

    @Override
    public Object next() {
        if (pnt+1 <= listArray.getSize()-1) {
            return listArray.get(++pnt);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (pnt+1 < listArray.getSize()) {
            return true;
        }
        return false;
    }

    @Override
    public Object currentItem() {
        return listArray.get(pnt);
    }
}
