import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _21_BinaryTrees {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static class BinaryTree {
        Node root = null;
        int idx = -1;

        Node buildTree(int[] nodes) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            if (idx == 0) {
                root = newNode;
            }
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        void printPreorder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            printPreorder(root.left);
            printPreorder(root.right);
        }

        void printInorder(Node root) {
            if (root == null) {
                return;
            }
            printInorder(root.left);
            System.out.print(root.data + " ");
            printInorder(root.right);
        }

        void printPostorder(Node root) {
            if (root == null) {
                return;
            }
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(root.data + " ");
        }

        void printLevelOrder() {
            if (root == null) {
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while (!q.isEmpty()) {
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

        int findHeight(Node root) {
            if (root == null) {
                return 0;
            }
            return (int) Math.max(findHeight(root.left), findHeight(root.right)) + 1;
        }

        int countNodes(Node root) {
            if (root == null) {
                return 0;
            }
            return countNodes(root.left) + countNodes(root.right) + 1;
        }

        int sum(Node root) {
            if (root == null) {
                return 0;
            }
            return root.data + sum(root.left) + sum(root.right);
        }

        int findDiameter2(Node root) { // TC -> O(n^2)
            if (root == null) {
                return 0;
            }
            int leftDia = findDiameter2(root.left);
            int rightDia = findDiameter2(root.right);
            int leftHeight = findHeight(root.left);
            int rightHeight = findHeight(root.right);

            int dia = leftHeight + rightHeight + 1;

            return Math.max(dia, Math.max(leftDia, rightDia));
        }

        static class Info {
            int diameter;
            int height;

            Info(int d, int h) {
                diameter = d;
                height = h;
            }
        }

        Info findDiameter(Node root) { // TC -> O(n)
            if (root == null) {
                return new Info(0, 0);
            }

            Info leftInfo = findDiameter(root.left);
            Info rightInfo = findDiameter(root.right);

            int diameter = leftInfo.height + rightInfo.height + 1;

            return new Info(Math.max(diameter, Math.max(leftInfo.diameter, rightInfo.diameter)),
                    Math.max(leftInfo.height, rightInfo.height) + 1);
        }

        boolean isSubTree(Node root, Node subRoot) {
            if (root == null) {
                return false;
            }
            if (root.data == subRoot.data) {
                if (equals(root, subRoot)) {
                    return true;
                }
            }
            return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
        }

        boolean equals(Node root1, Node root2) {
            if (root1 == null && root2 == null) {
                return true;
            }
            if (root1 == null || root2 == null || root1.data != root2.data) {
                return false;
            }

            return equals(root1.left, root2.left) && equals(root1.right, root2.right);
        }

        static class INFO {
            Node node;
            int horDist;

            INFO(Node n, int hd) {
                node = n;
                horDist = hd;
            }
        }

        void topView(Node root) {
            Queue<INFO> q = new LinkedList<>();
            HashMap<Integer, Node> map = new HashMap<>();
            int min = 0, max = 0;
            q.add(new INFO(root, 0));
            q.add(null);

            while (!q.isEmpty()) {
                INFO curr = q.remove();
                if (curr == null) {
                    if (q.isEmpty()) {
                        break;
                    }
                    q.add(null);
                    continue;
                }
                if (!map.containsKey(curr.horDist)) {
                    map.put(curr.horDist, curr.node);
                }
                if (curr.node.left != null) {
                    q.add(new INFO(curr.node.left, curr.horDist - 1));
                    min = Math.min(min, curr.horDist - 1);
                }
                if (curr.node.right != null) {
                    q.add(new INFO(curr.node.right, curr.horDist + 1));
                    max = Math.max(max, curr.horDist + 1);
                }
            }

            for (int i = min; i <= max; i++) {
                System.out.print(map.get(i).data + " ");
            }
        }

        void KthLvl(int K) {
            KthLvl_Aux(root, K, 0);
        }

        private void KthLvl_Aux(Node root, int K, int currLvl) {
            if (root == null) {
                return;
            }
            if (currLvl == K) {
                System.out.print(root.data + "  ");
                return;
            }
            KthLvl_Aux(root.left, K, currLvl + 1);
            KthLvl_Aux(root.right, K, currLvl + 1);
        }

        Node LowestCommonAncestor(int n1, int n2) { // TC -> O(n), SC -> O(n)
            List<Node> n1_path = new ArrayList<>();
            List<Node> n2_path = new ArrayList<>();

            getPath(root, n1, n1_path);
            getPath(root, n2, n2_path);

            int i = 0;
            for (; i < Math.min(n1_path.size(), n2_path.size()); i++) {
                if (n1_path.get(i) != n2_path.get(i)) {
                    return n1_path.get(i - 1);
                }
            }
            return n1_path.get(i - 1);
        }

        private boolean getPath(Node root, int nodeValue, List<Node> node_path) {
            if (root == null) {
                return false;
            }
            if (root.data == nodeValue) {
                return true;
            }
            if (getPath(root.left, nodeValue, node_path)) {
                node_path.add(0, root);
                return true;
            }
            if (getPath(root.right, nodeValue, node_path)) {
                node_path.add(0, root);
                return true;
            }
            return false;
        }

        Node LCA(int n1, int n2) { // TC -> O(n), SC -> O(1)
            return LCA_Aux(root, n1, n2);
        }

        private Node LCA_Aux(Node root, int n1, int n2) {
            if (root == null || root.data == n1 || root.data == n2) {
                return root;
            }

            Node leftLCA = LCA_Aux(root.left, n1, n2);
            Node rightLCA = LCA_Aux(root.right, n1, n2);

            if (leftLCA == null) {
                return rightLCA;
            }
            if (rightLCA == null) {
                return leftLCA;
            }
            return root;
        }

        int minDistance(int n1, int n2) {
            Node lca = LCA(n1, n2);
            return findDistance(lca, n1) + findDistance(lca, n2);
        }

        private int findDistance(Node n1, int n2) {
            if (n1 == null) {
                return -1;
            }
            if (n1.data == n2) {
                return 0;
            }

            int leftDist = findDistance(n1.left, n2);
            if (leftDist == -1) {
                int rightDist = findDistance(n1.right, n2);
                if (rightDist == -1) {
                    return -1;
                }
                return rightDist + 1;
            }
            return leftDist + 1;
        }

        void KthAncestor(int K, int n) {
            KthAncestor_Aux(root, K, n);
        }

        private int KthAncestor_Aux(Node root, int K, int n) {
            if (root == null) {
                return -1;
            }
            if (root.data == n) {
                return 0;
            }

            int leftDist = KthAncestor_Aux(root.left, K, n);

            if (leftDist == -1) {
                int rightDist = KthAncestor_Aux(root.right, K, n);
                if (rightDist == -1) {
                    return -1;
                }
                if (rightDist + 1 == K) {
                    System.out.println(root.data);
                }
                return rightDist + 1;
            }
            if (leftDist + 1 == K) {
                System.out.println(root.data);
            }
            return leftDist + 1;
        }

        void sumTree() {
            sumTree_Aux(root);
        }

        private int sumTree_Aux(Node root) {
            if (root == null) {
                return 0;
            }

            int val = root.data;
            root.data = sumTree_Aux(root.left) + sumTree_Aux(root.right);
            return val + root.data;
        }

        boolean isUnivalued() {
            return isUnivalued(root);
        }

        private boolean isUnivalued(Node root) {
            if (root == null) {
                return true;
            }
            if ((root.left != null && root.left.data != root.data)
                    || (root.right != null && root.right.data != root.data)) {
                return false;
            }

            return isUnivalued(root.left) && isUnivalued(root.right);
        }

        void mirror() {
            mirror(root);
        }

        private void mirror(Node root) {
            if (root == null) {
                return;
            }

            Node temp = root.left;
            root.left = root.right;
            root.right = temp;

            mirror(root.left);
            mirror(root.right);
        }

        void deleteLeafNodes_containing_x(int x) {
            deleteLeaf_Aux(root, x);
        }

        private Node deleteLeaf_Aux(Node root, int x) {
            if (root == null) {
                return null;
            }

            root.left = deleteLeaf_Aux(root.left, x);
            root.right = deleteLeaf_Aux(root.right, x);

            if (root.data == x && root.left == null && root.right == null) {
                return null;
            }
            return root;
        }

        void printDuplicateSubtreeRoots() {
            HashMap<String, Integer> map = new HashMap<>();
            inorder(root, map);
        }

        private String inorder(Node node, HashMap<String, Integer> dup) {
            if (node == null) {
                return "";
            }
            String str = "(";
            str += inorder(node.left, dup);
            str += Integer.toString(node.data);
            str += inorder(node.right, dup);
            str += ")";
            if (dup.get(str) != null) {
                if (dup.get(str) == 1) {
                    System.out.print(node.data + " ");
                }
                dup.put(str, dup.get(str) + 1);
            } else {
                dup.put(str, 1);
            }
            return str;
        }

        static class Result {
            int val;

            Result(int data) {
                val = data;
            }
        }

        int maximumPathSum() {
            Result res = new Result(Integer.MIN_VALUE);
            maximumPathSum_Aux(root, res);
            return res.val;
        }

        private int maximumPathSum_Aux(Node root, Result res) {
            if (root == null) {
                return 0;
            }

            int leftSum = maximumPathSum_Aux(root.left, res);
            int rightSum = maximumPathSum_Aux(root.right, res);
            int max_single = Math.max(Math.max(leftSum, rightSum) + root.data, root.data);
            int max_all = Math.max(max_single, leftSum + rightSum + root.data);

            res.val = Math.max(res.val, max_all);

            return max_single;
        }

        public int minDepth_dfs(Node root) {
            if (root == null) {
                return 0;
            }

            int[] dep = { Integer.MAX_VALUE };
            minDepth_Util(root, dep, 1);
            return dep[0];
        }

        private void minDepth_Util(Node root, int[] dep, int currDep) {
            if (currDep > dep[0]) {
                return;
            }
            if (root.left != null) {
                minDepth_Util(root.left, dep, currDep + 1);
            }

            if (root.right != null) {
                minDepth_Util(root.right, dep, currDep + 1);
            }

            if (root.left == null && root.right == null) {
                dep[0] = currDep;
            }
        }

        public int minDepth_bfs(Node root) {
            if (root == null)
                return 0;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            int depth = 1;
            while (!q.isEmpty()) {
                Node curr = q.poll();
                if (curr == null) {
                    q.add(null);
                    depth++;
                    continue;
                }
                if (curr.left == null && curr.right == null)
                    return depth;
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        // int[] n = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(3);
        tree.root.left = new Node(9);
        tree.root.right = new Node(20);
        // tree.root.left.left = new Node(3);
        // tree.root.left.right = new Node(3);
        tree.root.right.left = new Node(15);
        tree.root.right.right = new Node(7);
        // tree.root.right.left.left = new Node(3);

        tree.printDuplicateSubtreeRoots();
    }
}