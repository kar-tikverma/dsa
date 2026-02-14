public class _18_Queue_usingLL {

    static class Node {
        int data;
        Node next;

        Node (int data) {
            this.data = data;
            next = null;
        }
    }

    static class Queues {

        Node head = null;
        Node tail = null;
        int size = 0;

        boolean isEmpty () {
            return head == null;
        }

        void add (int data) {
            Node newNode = new Node(data);
            size++;
            if (isEmpty()) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }

        int remove () {
            if (isEmpty()) {
                System.out.println("Underflow!");
                return Integer.MIN_VALUE;
            }
            int val = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return val;
        }

        int peek() {
            if (isEmpty()) {
                System.out.println("Underflow!");
                return Integer.MIN_VALUE;
            }
            return head.data;
        }

        void printQueue () {
            for (Node i = head; i != null; i = i.next) {
                System.out.print(i.data + " ");
            }
        }
        
    }
    
    public static void main (String[] args) {
        Queues ll = new Queues();
        ll.add(5);
        //ll.add(98);
        ll.remove();
        ll.add(9);
        ll.add(89);
        ll.printQueue();
    }
}