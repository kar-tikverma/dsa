public class _16_Stack_usingLinkedList {

    private class Node {
        int data;
        Node next;
        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node top = null;
    public int size = 0;
    
    public void push (int data) {
        Node newNode = new Node(data);
        size++;
        if (isEmpty()) {
            top = newNode;
            return;
        }
        newNode.next = top;
        top = newNode;
    }

    public int pop () {
        if (isEmpty()) {
            System.out.println("Underflow!!");
            return Integer.MIN_VALUE;
        }
        int val = top.data;
        top = top.next;
        size--;
        return val;
    }

    public int peek () {
        if (isEmpty()) {
            System.out.println("Underflow!!");
            return Integer.MIN_VALUE;
        }
        return top.data;
    }

    public boolean isEmpty () {
        return top == null;
    }

    public void pushAtBottom (int data) {
        if (top == null) {
            push (data);
            return;
        }
        int t = top.data;
        pop();
        pushAtBottom(data);
        push(t);
    }
    
    public void printStack () {
        System.out.println(top.data + " <- top");
        for (Node i = top.next; i != null; i = i.next) {
            System.out.println(i.data);
        }
    }

    public static void main (String[] args) {
        _16_Stack_usingLinkedList stk = new _16_Stack_usingLinkedList();
        stk.push(2);
        stk.push(4);
        stk.push(3);
        stk.pushAtBottom(6);
        stk.printStack();
    }
}