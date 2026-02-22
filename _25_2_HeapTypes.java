import java.util.ArrayList;

public class _25_2_HeapTypes {
    static class MinHeap {
        ArrayList<Integer> heap = new ArrayList<>();

        void add(int data) { // TC -> O(log2n)
            heap.add(data);

            int idx = heap.size() - 1;
            while (idx > 0 && heap.get(idx) < heap.get((idx - 1) / 2)) {
                int temp = heap.get(idx);
                heap.set(idx, heap.get((idx - 1) / 2));
                heap.set((idx - 1) / 2, temp);

                idx = (idx - 1) / 2;
            }
        }

        int peek() {
            return heap.get(0);
        }

        int remove() { // TC -> O(log2n)
            int val = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);

            heapify(0);

            return val;
        }

        private void heapify(int root) { // TC -> O(log2n)
            int left = 2 * root + 1;
            int right = left + 1;
            int minIdx = root;

            if (left < heap.size() && heap.get(left) < heap.get(minIdx)) {
                minIdx = left;
            }
            if (right < heap.size() && heap.get(right) < heap.get(minIdx)) {
                minIdx = right;
            }

            if (minIdx != root) {
                int temp = heap.get(root);
                heap.set(root, heap.get(minIdx));
                heap.set(minIdx, temp);

                heapify(minIdx);
            }
        }

        void printHeap() {
            while (!heap.isEmpty()) {
                System.out.println(remove());
            }
        }
    }

    static class MaxHeap {

        ArrayList<Integer> heap = new ArrayList<>();

        void add(int data) { // TC -> O(log2n)
            heap.add(data);

            int idx = heap.size() - 1;
            while (idx > 0 && heap.get(idx) > heap.get((idx - 1) / 2)) {
                int temp = heap.get(idx);
                heap.set(idx, heap.get((idx - 1) / 2));
                heap.set((idx - 1) / 2, temp);

                idx = (idx - 1) / 2;
            }
        }

        int peek() {
            return heap.get(0);
        }

        int remove() { // TC -> O(log2n)
            int val = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);

            heapify(0);

            return val;
        }

        private void heapify(int root) { // TC -> O(log2n)

            int left = 2 * root + 1;
            int right = left + 1;
            int maxIdx = root;

            if (left < heap.size() && heap.get(left) > heap.get(maxIdx)) {
                maxIdx = left;
            }
            if (right < heap.size() && heap.get(right) > heap.get(maxIdx)) {
                maxIdx = right;
            }

            if (maxIdx != root) {
                int temp = heap.get(root);
                heap.set(root, heap.get(maxIdx));
                heap.set(maxIdx, temp);

                heapify(maxIdx);
            }
        }

        void printHeap() {
            while (!heap.isEmpty()) {
                System.out.println(remove());
            }
        }
    }

    public static void main(String[] args) {
        MaxHeap minH = new MaxHeap();
        minH.add(3);
        minH.add(4);
        minH.add(1);
        minH.add(5);

        minH.printHeap();
    }
}