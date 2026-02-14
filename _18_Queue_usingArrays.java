public class _18_Queue_usingArrays {
    public static class Queues {

        int[] arr;
        int size;
        int front;
        int rear;

        Queues (int n) {
            arr = new int[n];
            size = n;
            front = -1;
            rear = -1;
        }

        boolean isEmpty () {
            return front == -1;
        }

        boolean isFull () {
            return (rear + 1) % size == front;
        }

        void add (int data) {
            if (isFull()) {
                System.out.println("Overflow!!");
                return;
            }
            if (isEmpty()) {
                front++;
            }
            rear = (rear+1) % size;
            arr[rear] = data;
        }

        int remove () {
            if (isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            int val = arr[front];
            if (rear == front) {
                rear = front = -1;
            }
            else {
                front = (front+1) % size;
            }
            return val;
        }

        int peek () {
            if (isEmpty()) {
                System.out.println("Queue is Empty!");
                return Integer.MIN_VALUE;
            }
            return arr[front];
        }

        void printQueue () {
            if (isEmpty()) {
                System.out.println("Queue is Empty!");
                return;
            }
            for (int i = front; i != rear; i++) {
                if (i == size) {
                    i = -1;
                    continue;
                }
                System.out.print(arr[i] + " ");
            }
            System.out.println(arr[rear]);
        }
    }
    
    public static void main (String[] args) {
        Queues q = new Queues(4);
        q.add(1);
        q.add(6);
        q.add(5);
        q.add(3);
        q.remove();
        q.remove();
        q.add(9);
        q.add(7);
        q.printQueue();
    }
}