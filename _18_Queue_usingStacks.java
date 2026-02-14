import java.util.Stack;
public class _18_Queue_usingStacks {

    static class Queues1 {

        Stack<Integer> stk = new Stack<>();
        Stack<Integer> aux = new Stack<>();

        boolean isEmpty () {
            return stk.isEmpty();
        }

        void add (int data) {
            while (! stk.isEmpty()) {
                aux.push(stk.pop());
            }
            stk.push(data);
            while (! aux.isEmpty()) {
                stk.push(aux.pop());
            }
        }

        int remove () {
            if (stk.isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            return stk.pop();
        }

        int peek () {
            if (stk.isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            return stk.peek();
        }

        void printQueue () {
            while (! stk.isEmpty()) {
                System.out.print(stk.pop() + " ");
            }
        }

    }
    static class Queues2 {

        Stack<Integer> stk = new Stack<>();
        Stack<Integer> aux = new Stack<>();

        void add (int data) {
            stk.push(data);
        }

        int remove () {
            if (stk.isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            while (! stk.isEmpty()) {
                aux.push(stk.pop());
            }
            int val = aux.pop();
            while (! aux.isEmpty()) {
                stk.push(aux.pop());
            }
            return val;
        }

        int peek () {
            if (stk.isEmpty()) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            while (! stk.isEmpty()) {
                aux.push(stk.pop());
            }
            int val = aux.peek();
            while (! aux.isEmpty()) {
                stk.push(aux.pop());
            }
            return val;
        }

        void printQueue () {
            while (! stk.isEmpty()) {
                aux.push(stk.pop());
            }
            while (! aux.isEmpty()) {
                System.out.print(aux.pop() + " ");
            }
        }
        
    }
}