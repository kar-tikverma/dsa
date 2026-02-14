public class _15_DoubleLL {

    private class Node {
        int data;
        Node next;
        Node prev;

        Node (int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public Node head = null;
    public Node tail = null;
    public int size = 0;

    public void addFirst (int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void addLast (int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

    public int removeFirst () {
        if (head == null) {
            System.out.println("DLL is empty");
            return Integer.MIN_VALUE;
        }
        int val = head.data;
        head = head.next;
        if (size > 1) {
            head.prev = null;
        }
        else {
            tail = null;
        }
        size--;
        return val;
    }

    public void reverse () {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        tail = head;
        head = prev;
    }

    public void printLL () {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void printLL_Reverse () {
        Node temp = tail;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    public static void main (String[] args) {
        _15_DoubleLL dll = new _15_DoubleLL();
        dll.addLast(2);
        dll.addLast(1);
        dll.addLast(0);
        dll.printLL();
        dll.printLL_Reverse();
        dll.reverse();
        dll.printLL();
        dll.printLL_Reverse();
    }
}