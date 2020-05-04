package Chess;

import java.util.LinkedList;

public class MyStack<Q> {

    private int size;
    private LinkedList<Q> list = new LinkedList<>();

    public void push(Q object) {
        list.add(object);
        size++;
    }

    public Q pop() throws Exception {
        if (size == 0) {
            throw new Exception("StackIsEmpty");
        }
        Q object = list.getLast();
        list.removeLast();
        size--;
        return object;
    }

    public Q peek() throws Exception {
        if (size == 0) {
            throw new Exception("StackIsEmpty");
        }
        return list.getLast();
    }
}
