import java.util.Comparator;
import java.util.PriorityQueue;

public class _25_1_PriorityQueues {
    static class Student implements Comparable<Student> {
        String name;
        int rank;

        Student(String n, int r) {
            this.name = n;
            this.rank = r;
        }

        @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank;
        }
    }

    static PriorityQueue<Student> create_PQ_forObjects() {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        pq.add(new Student("A", 4));
        pq.add(new Student("B", 5));
        pq.add(new Student("C", 2));
        pq.add(new Student("D", 12));
        return pq;
    }

    static void printPQ_Student(PriorityQueue<Student> pq) {
        while (!pq.isEmpty()) {
            System.out.println(pq.peek().name + " -> " + pq.remove().rank);
        }
    }

    static PriorityQueue<Integer> create_PQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(5);
        pq.add(6);
        pq.add(4);
        return pq;
    }

    static PriorityQueue<Integer> create_PQ_descending() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(5);
        pq.add(6);
        pq.add(4);
        return pq;
    }

    public static void main(String[] args) {
        PriorityQueue<Student> pq2 = create_PQ_forObjects();
        printPQ_Student(pq2);
    }
}