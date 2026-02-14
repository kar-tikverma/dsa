import java.util.*;

public class _28_HashMap_Implementation {
    static class Hashmap <Key, Value> {
        
        private class Node {
            Key key;
            Value value;

            Node (Key key, Value value) {
                this.key = key;
                this.value = value;
            }
        }
        
        private LinkedList<Node>[] buckets;
        private int n; // bucket size
        private int N; // size of buckets array

        @SuppressWarnings ("unchecked")
        public Hashmap () {
            this.N = 4;
            this.buckets = new LinkedList[4];

            for (int i = 0; i < 4; i++) {
                this.buckets[i] = new LinkedList<>();                
            }
        }

        private int hashFunction (Key key) {
            int hc = key.hashCode();
            return Math.abs (hc) % N;
        }

        private int searchInLL (Key key, int bucketIdx) {
            for (int i = 0; i < buckets[bucketIdx].size(); i++) {
                if (buckets[bucketIdx].get(i).key == key) {
                    return i;
                }
            }
            
            return -1;
        }

        @SuppressWarnings ("unchecked")
        private void rehash () {
            LinkedList <Node> oldBuck[] = buckets;
            N *= 2;
            buckets = new LinkedList[N];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (int i = 0; i < oldBuck.length; i++) {
                LinkedList<Node> ll = oldBuck[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.remove();
                    put(node.key, node.value);
                }
            }
        }

        void put (Key key, Value val) { // TC -> O(lambda) = O(1)
            int bucketIdx = hashFunction(key);
            int dataIdx = searchInLL (key, bucketIdx);

            if (dataIdx != -1) {
                Node node = buckets[bucketIdx].get(dataIdx);
                node.value = val;
            }
            else {
                buckets[bucketIdx].add(new Node(key, val));
                n++;
            }

            double lambda = (double)n / N;
            if (lambda > 2.0) {
                rehash ();
            }
        }

        boolean containsKey (Key key) { // TC -> O(1)
            int bucketIdx = hashFunction(key);
            return ( (searchInLL (key, bucketIdx) == -1) ? false : true );
        }

        Value get (Key key) { // TC -> O(1)
            int bucketIdx = hashFunction(key);
            int dataIdx = searchInLL (key, bucketIdx);

            return ( (dataIdx == -1) ? null : buckets[bucketIdx].get(dataIdx).value );
        }

        Value remove (Key key) { // TC -> O(1)
            int bucketIdx = hashFunction(key);
            int dataIdx = searchInLL (key, bucketIdx);

            if (dataIdx != -1) {
                n--;
                return buckets[bucketIdx].remove(dataIdx).value;
            }

            return null;
        }

        Set<Key> keySet () {
            Set<Key> keys = new HashSet<>();
            for (int i = 0; i < buckets.length; i++) {
                for (Node node : buckets[i]) {
                    keys.add (node.key);
                }
            }

            return keys;
        }
        
        boolean isEmpty () {
            return n == 0;
        }
    }

    public static void main (String[] args) {
        Hashmap<String, Integer> hm = new Hashmap<>();
        hm.put("India", 100);
        hm.put("China", 150);
        hm.put("US", 50);
        hm.put("Nepal", 5);

        // Set<String> s = hm.keySet();
        // System.out.println(hm.remove("India"));
        System.out.println(hm.get("India"));
    }
}