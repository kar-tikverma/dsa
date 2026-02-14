public class _23_AVLTrees {
    
    static class Node {
        int data;
        int height;
        Node left;
        Node right;

        Node (int data) {
            this.data = data;
            height = 1;
            left = null;
            right = null;
        }
    }

    Node root;

    void printPreorder () {
        printPreorder_Aux(root);
    }
    private void printPreorder_Aux (Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + "  ");
        printPreorder_Aux(root.left);
        printPreorder_Aux(root.right);
    }

    int height (Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    int max (int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalanceFactor (Node root) {
        if (root == null) {
            return 0;
        }
        return height (root.left) - height(root.right);
    }

    Node insert (Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.data) {
            root.left = insert (root.left, key);
        }
        else if (key > root.data) {
            root.right = insert(root.right, key);
        }
        else {
            return root;
        }

        root.height = 1 + Math.max (height (root.left), height(root.right));

        int bf = getBalanceFactor (root);

        // Left Left case
        if (bf > 1 && getBalanceFactor(root.left) >= 0) {
            return rightRotate (root);
        }

        // Right Right case
        if (bf < -1 && getBalanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }

        // Left Right case
        if (bf > 1 && getBalanceFactor(root.left) < 0) {
            root.left = leftRotate (root.left);
            return rightRotate(root);
        }

        // Right Left case
        if (bf < -1 && getBalanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node leftRotate (Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max (height (x.left), height (x.right)) + 1;
        y.height = max (height (y.left), height (y.right)) + 1;

        return y;
    }

    Node rightRotate (Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max (height (y.left), height (y.right)) + 1;
        x.height = max (height (x.left), height (x.right)) + 1;

        return x;
    }

    Node remove (Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.data) {
            root.left = remove(root.left, key);
        }
        else if (key > root.data) {
            root.right = remove(root.right, key);
        }
        else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node inorderSucc = inorderSuccessor(root.right);
            root.data = inorderSucc.data;
            root.right = remove(root.right, inorderSucc.data);
            return root;
        }

        root.height = Math.max (height(root.left), height(root.right)) + 1;
        int bf = getBalanceFactor(root);

        // LL case
        if (bf > 1 && getBalanceFactor(root.left) > 0) {
            return rightRotate (root);
        }

        // Right Right case
        if (bf < -1 && getBalanceFactor(root.left) < 0) {
            return leftRotate(root);
        }

        // Left Right case
        if (bf > 1 && getBalanceFactor(root.left) < 0) {
            root.left = leftRotate (root.left);
            return rightRotate(root);
        }

        // Right Left case
        if (bf < -1 && getBalanceFactor(root.left) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    private Node inorderSuccessor (Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void main (String[] args) {
        _23_AVLTrees avl = new _23_AVLTrees();
        avl.root = avl.insert (avl.root, 10);
        avl.root = avl.insert (avl.root, 20);
        avl.root = avl.insert (avl.root, 30);
        avl.root = avl.insert (avl.root, 40);
        avl.root = avl.insert (avl.root, 50);
        avl.root = avl.insert (avl.root, 25);

        avl.printPreorder();


    }
}