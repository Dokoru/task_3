package Chess;

public class MyStack<Q> {

    public MyStack(Node last) {
        this.last = last;
        this.last.previous = null;
        this.size = 0;
    }

    public MyStack() {
        this.last = null;
        this.size = -1;
    }

    private class Node {
        private Q object;
        private Node previous;

        Node(Q object) {
            this.object = object;
            previous = null;
        }

        public Q getObject() {
            return object;
        }

        public void setObject(Q object) {
            this.object = object;
        }

        public Node getPrevious() {
            return previous;
        }
    }

    private Node last;
    private int size;

    public void push(Q object) {
        Node newNode = new Node (object);
        newNode.previous = last;
        last = newNode;
        size ++;
    }

    public Q peek() throws Exception {
        if (size == -1) {
            throw new Exception("StackIsEmpty");
        }
        return last.getObject();
    }

    public Q pop() throws Exception {
        if (size == -1) {
            throw new Exception("StackIsEmpty");
        }
        Q object = peek();
        last = last.previous;
        size--;
        return object;
    }
}
