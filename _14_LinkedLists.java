public class _14_LinkedLists {
    private class Node {
        private int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void add(int index, int data) {
        Node newNode = new Node(data);
        if (index == 0) {
            addFirst(data);
            return;
        }
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        Node temp = head;
        int idx = 0;
        while (idx < index - 1) {
            if (temp.next == null) {
                temp.next = newNode;
                return;
            }
            temp = temp.next;
            idx++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst() {
        if (size == 0) {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            tail = null;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {
        if (size == 0) {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            return removeFirst();
        }
        Node prev = head;
        for (int i = 1; i < size - 1; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    public int search_Iteration(int key) { // O(n)
        Node curr = head;
        for (int i = 0; curr != null; i++) {
            if (curr.data == key) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }

    public int search_Recursion(int key) {
        return search_Recursion_Aux(key, head);
    }

    public int search_Recursion_Aux(int key, Node head) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int idx = search_Recursion_Aux(key, head.next);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public void Reverse() { // O(n)
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void remove_NthfromEnd(int n) {
        if (n >= size) {
            head = head.next;
        }
        Node curr = head;
        for (int i = 1; i < size - n; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
    }

    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        // Step 1: Final middle node
        Node mid = findMid();

        // Step 2: Reverse 2nd Half
        Node prev = null;
        Node curr = mid;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node rightH = prev; // right head
        Node leftH = head; // left head

        // Step 3: Check Palindrome
        while (rightH != null) {
            if (leftH.data != rightH.data) {
                return false;
            }
            leftH = leftH.next;
            rightH = rightH.next;
        }
        return true;
    }

    // Slow-Fast Approach (Sometimes called Turtle-Hare)
    private Node findMid() { // O(n)
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean containsCycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public void removeCycle() {
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }
        slow = head;
        Node prev = null;
        while (slow != fast) {
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }
        prev.next = null;
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // find mid
        Node mid = getMid();
        Node rightH = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightH);
        return merge(newLeft, newRight);
    }

    private Node merge(Node head1, Node head2) {
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return mergedLL.next;
    }

    private Node getMid() {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void toZigZag() {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = getMid();

        Node prev = null;
        Node curr = mid.next;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        mid.next = null;
        Node head1 = head;
        Node head2 = prev;
        while (head2 != null) {
            Node next1 = head1.next;
            head1.next = head2;
            Node next2 = head2.next;
            head2.next = next1;

            head1 = next1;
            head2 = next2;

        }
    }

    public Node findIntersection(_14_LinkedLists LL) {
        for (Node temp = head; temp != null; temp = temp.next) {
            for (Node tempLL = LL.head; tempLL != null; tempLL = tempLL.next) {
                if (temp == tempLL) {
                    return temp;
                }
            }
        }
        return null;
    }

    public void evenThenOdd() {
        if (head == null || head.next == null) {
            return;
        }
        Node OddHead = new Node(-1);
        Node itr = OddHead;
        while (head != null && head.data % 2 == 1) {
            itr.next = head;
            itr = itr.next;
            head = head.next;
        }
        Node prev = head;
        Node temp = head.next;
        Node lastEven = head;
        while (temp != null) {
            if (temp.data % 2 == 1) {
                prev.next = temp.next;
                itr.next = temp;
                itr = itr.next;
                prev = temp;
                temp = temp.next;
                prev.next = null;
            } else {
                prev = temp;
                temp = temp.next;
                lastEven = prev;
            }
        }
        printLL(OddHead);
        lastEven.next = OddHead.next;
    }

    public void printLL() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void printLL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        _14_LinkedLists ll = new _14_LinkedLists();
        ll.addFirst(0);
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.printLL();
        ll.evenThenOdd();
        ll.printLL();
    }
}