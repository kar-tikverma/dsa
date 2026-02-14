import java.util.LinkedList;
import java.util.Queue;

public class _16_Stack_usingQueues {

    static class Stacks {
        Queue <Integer> q1 = new LinkedList<>();
        Queue <Integer> q2 = new LinkedList<>();

        boolean isEmpty () {
            return q1.isEmpty() && q2.isEmpty();
        }

        void push (int data) {
            if (! q1.isEmpty()) {
                q1.add(data);
            }
            else {
                q2.add(data);
            }
        }

        int pop () {
            if (isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            int a = 0;
            if (! q1.isEmpty()) {
                while (! q1.isEmpty()) {
                    a = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(a);
                }
            }
            else {
                while (! q2.isEmpty()) {
                    a = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(a);
                }
            }
            
            return a;
        }
        
        int peek () {
            if (isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            int a = 0;
            if (! q1.isEmpty()) {
                while (! q1.isEmpty()) {
                    a = q1.remove();
                    q2.add(a);
                }
            }
            else {
                while (! q2.isEmpty()) {
                    a = q2.remove();
                    q1.add(a);
                }
            }
            return a;
        }
    }
}