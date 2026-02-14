import java.util.*;
public class _22_BinarySearchTrees {

    static class Node {
        int data;
        Node left;
        Node right;

        Node (int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static class BST {

        Node root = null;

        void printLevelOrder () {
            if (root == null) {
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while (! q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    if (q.isEmpty()) {
                        break;
                    }
                    System.out.println();
                    q.add(null);
                    continue;
                }
                System.out.print(currNode.data + " ");
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            System.out.println();
        }

        void buildBST (int[] values) {
            for (int i = 0; i < values.length; i++) {
                root = insertNewNode(root, values[i]);
            }
        }
        private Node insertNewNode (Node root, int val) {
            if (root == null) {
                root = new Node(val);
                return root;
            }
            if (val < root.data) {
                root.left = insertNewNode(root.left, val);
            }
            else {
                root.right = insertNewNode(root.right, val);
            }
            return root;
        }

        Node search (int key) {
            return search_Aux (root, key);
        }
        private Node search_Aux (Node root, int key) {
            if (root == null) {
                return null;
            }
            if (root.data == key) {
                return root;
            }

            if (key > root.data) {
                return search_Aux(root.right, key);
            }
            return search_Aux(root.left, key);
        }

        Node remove (int key) {
            return remove_Aux (root, key);
        }
        private Node remove_Aux (Node root, int key) {
            if (root == null) {
                return null;
            }
            if (key > root.data) {
                root.right = remove_Aux(root.right, key);
            }
            else if (key < root.data) {
                root.left = remove_Aux(root.left, key);
            }
            else {
                if (root.left == null && root.right == null) {
                    return null;
                }
                if (root.left == null) {
                    return root.right;
                }
                else if (root.right == null) {
                    return root.left;
                }

                Node IS = inorderSuccessor (root.right);
                root.data = IS.data;
                root.right = remove_Aux(root.right, IS.data);

            }
            return root;
        }
        private Node inorderSuccessor (Node root) {
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }

        void printInRange (int start, int end) {
            printRange(root, start, end);
        }
        private void printRange (Node root, int start, int end) {
            if (root == null) {
                return;
            }
            if (root.data < start) {
                printRange(root.right, start, end);
            }
            else if (root.data > end) {
                printRange(root.left, start, end);
            }
            else {
                printRange(root.left, start, end);
                System.out.print(root.data + " ");
                printRange(root.right, start, end);
            }
        }

        void printRootTOLeafPaths () {
            ArrayList <Integer> path = new ArrayList<>();
            printRootTOLeafPaths_Aux(root, path);
        }
        private void printRootTOLeafPaths_Aux (Node root, ArrayList <Integer> path) {
            if (root == null) {
                return;
            }
            path.add(root.data);
            printRootTOLeafPaths_Aux(root.left, path);
            if (root.left == null && root.right == null) {
                int i;
                for (i = 0; i < path.size()-1; i++) {
                    System.out.print(path.get(i) + " -> ");
                }
                System.out.println(path.get(i));
            }
            printRootTOLeafPaths_Aux(root.right, path);
            path.remove(path.size()-1);
        }

        boolean isValidBST () {
            Node min = null, max = null;
            return isVB (root, min, max);
        }
        private boolean isVB (Node root, Node min, Node max) {
            if (root == null) {
                return true;
            }
            if (min != null && root.data <= min.data) {
                return false;
            }
            else if (max != null && root.data >= max.data) {
                return false;
            }
            
            return isVB(root.left, min, root) && isVB(root.right, root, max);
        }

        void mirror () {
            mirror (root);
        }
        private void mirror (Node root) {
            if (root == null) {
                return;
            }

            Node temp = root.left;
            root.left = root.right;
            root.right = temp;

            mirror(root.left);
            mirror(root.right);
        }

        void buildBalancedBST (int[] values) { // TC -> O(n)
            Arrays.sort(values);
            root = buildBalancedBST_Aux(values, 0, values.length-1);
        }
        private Node buildBalancedBST_Aux (int[] values, int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = start + (end - start)/2;
            Node root = new Node(values[mid]);
            root.left = buildBalancedBST_Aux(values, start, mid-1);
            root.right = buildBalancedBST_Aux(values, mid+1, end);
            return root;
        }

        Node toBalancedBST () { // TC -> O(n)
            ArrayList<Integer> inorder = new ArrayList<>();
            inorderTraverse(root, inorder);

            return toBalBST(inorder, 0, inorder.size()-1);
        }
        private Node toBalBST (ArrayList<Integer> values, int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = start + (end - start)/2;
            Node root = new Node(values.get(mid));
            root.left = toBalBST(values, start, mid-1);
            root.right = toBalBST(values, mid+1, end);
            return root;
        }
        private void inorderTraverse (Node root, ArrayList<Integer> inorder) {
            if (root == null) {
                return;
            }
            inorderTraverse(root.left, inorder);
            inorder.add(root.data);
            inorderTraverse(root.right, inorder);
        }

        private static class Info {
            boolean validBST;
            int size;
            int min;
            int max;

            Info () {}
            Info (boolean valid, int s, int min, int max) {
                this.validBST = valid;
                this.size = s;
                this.min = min;
                this.max = max;
            }
        }
        int largestBST () {
            return largestBST(root).size;
        }
        private Info largestBST (Node root) {
            if (root == null) {
                return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }
            Info leftInfo = largestBST(root.left);
            Info rightInfo = largestBST(root.right);
            Info currInfo = new Info();

            if (leftInfo.validBST == true && rightInfo.validBST == true && root.data < rightInfo.min && root.data > leftInfo.max) {
                currInfo.validBST = true;
                currInfo.size = leftInfo.size + rightInfo.size + 1;
                currInfo.min = Math.min (Math.min (leftInfo.min, rightInfo.min), root.data);
                currInfo.max = Math.max (Math.max (leftInfo.max, rightInfo.max), root.data);
            }
            else {
                currInfo.validBST = false;
                currInfo.size = Math.max(leftInfo.size, rightInfo.size);
            }
            return currInfo;
        }

        Node mergeBSTs (BST tree) { // TC -> O(n + m)
            ArrayList <Integer> inorder1 = new ArrayList<>();
            ArrayList <Integer> inorder2 = new ArrayList<>();
            inorderTraverse(root, inorder1);
            inorderTraverse(tree.root, inorder2);

            ArrayList <Integer> inorder = new ArrayList<>();
            int i = 0, j = 0;
            while (i < inorder1.size() && j < inorder2.size()) {
                if (inorder1.get(i) < inorder2.get(j)) {
                    inorder.add(inorder1.get(i++));
                }
                else {
                    inorder.add(inorder2.get(j++));
                }
            }
            while (i < inorder1.size()) {
                inorder.add(inorder1.get(i++));
            }
            while (j < inorder2.size()) {
                inorder.add(inorder2.get(j++));
            }

            return toBalBST (inorder, 0, inorder.size()-1);
        }

        int rangeSum (int start, int end) {
            return rangeSum_Aux(root, start, end);
        }
        private int rangeSum_Aux (Node root, int start, int end) {
            if (root == null) {
                return 0;
            }

            if (root.data < start) {
                return rangeSum_Aux(root.right, start, end);
            }
            if (root.data > end) {
                return rangeSum_Aux(root.left, start, end);
            }
            return rangeSum_Aux(root.left, start, end)
                    + rangeSum_Aux(root.right, start, end) + root.data;
        }

        int closestElement (int K) {
            return closestE(root, K).data;
        }
        private Node closestE (Node root, int K) {
            if (root == null) {
                return null;
            }
            
            if (root.data > K) {
                Node left = closestE(root.left, K);
                if (left != null && Math.abs(root.data - K) > Math.abs(K - left.data)) {
                    return left;
                }
                else {
                    return root;
                }
            }
            if (root.data < K) {
                Node right = closestE(root.right, K);
                if (right != null && Math.abs(root.data - K) > Math.abs(K - right.data)) {
                    return right;
                }
                else {
                    return root;
                }
            }
            return root;
        }

        int KthSmallest (int K) {
            Node res = inorder(root, K);
            if (res == null) {
                System.out.println("There are less than " + K + " nodes in the tree.");
                return Integer.MIN_VALUE;
            }
            return res.data;
        }
        int count = 0;
        private Node inorder (Node root, int K) {
            if (root == null) {
                return null;
            }

            Node left = inorder(root.left, K);
            if (left != null) {
                return left;
            }
            count++;
            if (count == K) {
                return root;
            }
            return inorder(root.right, K);
        }

        int countPairs (BST tree, int pairSum) { // IMPORTANT
            if (root == null || tree.root == null) {
                return 0;
            }
            Stack<Node> st1 = new Stack<>();
            Stack<Node> st2 = new Stack<>();
            Node top1, top2;
            int count = 0;
            while (true) {
                while (root != null) {
                    st1.push(root);
                    root = root.left;
                }
                while (tree.root != null) {
                    st2.push(tree.root);
                    tree.root = tree.root.right;
                }
                if (st1.empty() || st2.empty()) break;
                top1 = st1.peek();
                top2 = st2.peek();
                if ((top1.data + top2.data) == pairSum) {
                    count++;
                    st1.pop();
                    st2.pop();
                    root = top1.right;
                    tree.root = top2.left;
                }
                else if ((top1.data + top2.data) < pairSum) {
                    st1.pop();
                    root = top1.right;
                }
                else {
                    st2.pop();
                    tree.root = top2.left;
                }
            }
            return count;
        }

        static class INFO {
            int max;
            int min;
            boolean isBST;
            int sum;
            int currmax;

            INFO () {}

            INFO (int m, int mi, boolean is, int su, int cur) {
                max = m;
                min = mi;
                isBST = is;
                sum = su;
                currmax = cur;
            }
        }
        static class INT {
            int a;
        }
        int MaxSumBST (Node root) {
            INT maxsum = new INT();
            maxsum.a = Integer.MIN_VALUE;
            return MaxSumBSTUtil (root, maxsum).currmax;
        }
        private INFO MaxSumBSTUtil (Node root, INT maxsum) {
            if (root == null) {
                return new INFO (Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0, 0);
            }

            if (root.left == null && root.right == null) {
                maxsum.a = Math.max(maxsum.a, root.data);
                return new INFO(root.data, root.data,true, root.data, maxsum.a );
            }
            INFO L = MaxSumBSTUtil(root.left, maxsum);
            INFO R = MaxSumBSTUtil(root.right, maxsum);
            INFO BST = new INFO();
            if (L.isBST && R.isBST && L.max < root.data && R.min > root.data) {
                BST.max = Math.max(root.data, Math.max(L.max, R.max));
                BST.min = Math.min(root.data, Math.min(L.min, R.min));
                maxsum.a = Math.max(maxsum.a, R.sum + root.data + L.sum);
                BST.sum = R.sum + root.data + L.sum;
                BST.currmax = maxsum.a;
                BST.isBST = true;
                return BST;
            }
            BST.isBST = false;
            BST.currmax = maxsum.a;
            BST.sum = R.sum + root.data + L.sum;
            return BST;
        }
    }

    public static void main (String[] args) {
        BST tree = new BST();
        int[] v = {20, 8, 22, 4, 12, 10, 14};
        tree.buildBalancedBST(v);

        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(7);
        tree.root.left.left = new Node(2);
        tree.root.left.right = new Node(4);
        // tree.root.left.right.left = new Node (5);
        // tree.root.left.right.right = new Node (7);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(8);
        // tree.root.right.right.left = new Node(20);
        // tree.root.right.right.left = new Node(65);
        // tree.root.right.right.right = new Node(80);
        // tree.root.right.left.left = new Node(78);
        // tree.root.left.left.left = new Node(3);
        // tree.root.right.right.right = new Node(12);

        BST t2 = new BST();
        t2.root = new Node(10);
        t2.root.left = new Node(6);
        t2.root.right = new Node(15);
        t2.root.left.left = new Node(3);
        t2.root.left.right = new Node(8);
        t2.root.right.right = new Node(18);
        t2.root.right.left = new Node(11);
        
        // tree.printLevelOrder();
        System.out.println(tree.countPairs (t2, 16));
        // tree.printLevelOrder();
    }
}