import java.util.Stack;

public class _17_Stacks {
    public static void pushAtBottom(Stack<Integer> stk, int data) {
        if (stk.isEmpty()) {
            stk.push(data);
            return;
        }
        int top = stk.pop();
        pushAtBottom(stk, data);
        stk.push(top);
    }

    public static String reverseString(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            s.push(str.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    public static void reverseStack(Stack<Integer> stk) {
        if (stk.isEmpty()) {
            return;
        }
        int top = stk.pop();
        reverseStack(stk);
        pushAtBottom(stk, top);
    }

    public static void stockSpan(int[] price) {
        int[] sp = new int[price.length];
        stockSpan_Aux(price, sp);
        for (int i = 0; i < sp.length; i++) {
            System.out.print(sp[i] + "  ");
        }
    }

    private static void stockSpan_Aux(int[] price, int[] span) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);
        for (int i = 1; i < price.length; i++) {
            while (!s.isEmpty() && price[i] > price[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - s.peek();
            }
            s.push(i);
        }
    }

    public static void nextGreater_right(int[] arr) {
        int[] nextG = new int[arr.length];
        nextGreater_right_Aux(arr, nextG);
        for (int i = 0; i < nextG.length; i++) {
            System.out.print(nextG[i] + "  ");
        }
    }

    private static void nextGreater_right_Aux(int[] arr, int[] nextG) {
        Stack<Integer> stk = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && arr[stk.peek()] <= arr[i]) {
                stk.pop();
            }
            nextG[i] = (stk.isEmpty()) ? -1 : arr[stk.peek()];
            stk.push(i);
        }
    }

    public static boolean validParentheses(String str) {
        if (str.length() == 0) {
            return true;
        }
        if (str.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '{' || ch == '(' || ch == '[') {
                stk.push(ch);
            } else if (stk.isEmpty()) {
                return false;
            } else {
                switch (ch) {
                    case '}':
                        if (stk.peek() != '{') {
                            return false;
                        }
                        break;
                    case ']':
                        if (stk.peek() != '[') {
                            return false;
                        }
                        break;
                    case ')':
                        if (stk.peek() != '(') {
                            return false;
                        }
                }
                stk.pop();
            }
        }
        if (!stk.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean duplicateParentheses(String str) {
        if (str.length() == 0 || str.length() == 1) {
            return false;
        }
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                if (stk.peek() == '(') {
                    return true;
                }
                while (stk.pop() != '(')
                    ;
            } else {
                stk.push(ch);
            }
        }
        return false;
    }

    public static int maxAreaHistogram(int[] heights) { // TC -> O(n)
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int[] nextSR = new int[len];
        Stack<Integer> stk = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            while (!stk.isEmpty() && heights[i] <= heights[stk.peek()]) {
                stk.pop();
            }
            nextSR[i] = (stk.isEmpty()) ? len : stk.peek();
            stk.push(i);
        }
        stk.clear();
        int[] nextSL = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stk.isEmpty() && heights[i] <= heights[stk.peek()]) {
                stk.pop();
            }
            nextSL[i] = (stk.isEmpty()) ? -1 : stk.peek();
            stk.push(i);
        }
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Integer.max(maxArea, heights[i] * (nextSR[i] - nextSL[i] - 1));
        }
        return maxArea;
    }

    public static boolean isPalindrome(LinkList ll) {
        Stack<Character> stk = new Stack<>();
        Node slow = ll.head;
        Node fast = ll.head;
        while (fast != null && fast.next != null) {
            stk.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            // odd
            slow = slow.next;
        }
        while (!stk.isEmpty()) {
            if (stk.pop() != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    static String simplify(String A) {
        Stack<String> st = new Stack<String>();
        String res = "";
        res += "/";
        int len_A = A.length();
        for (int i = 0; i < len_A; i++) {
            String dir = "";
            while (i < len_A && A.charAt(i) == '/') {
                i++;
            }
            while (i < len_A && A.charAt(i) != '/') {
                dir += A.charAt(i);
                i++;
            }
            if (dir.equals("..") == true) {
                if (!st.empty()) {
                    st.pop();
                }
            } else if (dir.equals(".") == true) {
                continue;
            } else if (dir.length() != 0) {
                st.push(dir);
            }
        }
        Stack<String> st1 = new Stack<String>();
        while (!st.empty()) {
            st1.push(st.pop());
        }
        while (!st1.empty()) {
            if (st1.size() != 1) {
                res += (st1.pop() + "/");
            } else {
                res += st1.pop();
            }
        }
        return res;
    }

    public static String decode(String str) {
        Stack<Integer> integerstack = new Stack<>();
        Stack<Character> stringstack = new Stack<>();
        String temp, result = "";
        for (int i = 0; i < str.length(); i++) {
            int count = 0;
            if (Character.isDigit(str.charAt(i))) {
                while (Character.isDigit(str.charAt(i))) {
                    count = count * 10 + str.charAt(i) - '0';
                    i++;
                }
                i--;
                integerstack.push(count);
            } else if (str.charAt(i) == ']') {
                temp = "";
                if (!integerstack.isEmpty()) {
                    count = integerstack.pop();
                }
                while (!stringstack.isEmpty() && stringstack.peek() != '[') {
                    temp = stringstack.pop() + temp;
                }
                if (!stringstack.isEmpty()) {
                    stringstack.pop();
                }
                for (int j = 0; j < count; j++) {
                    result += temp;
                }
                for (int j = 0; j < result.length(); j++) {
                    stringstack.push(result.charAt(j));
                }
                result = "";
            } else if (str.charAt(i) == '[') {
                stringstack.push(str.charAt(i));
                if (!Character.isDigit(str.charAt(i - 1))) {
                    integerstack.push(1);
                }
            } else {
                stringstack.push(str.charAt(i));
            }
        }
        while (!stringstack.isEmpty()) {
            result = stringstack.pop() + result;
        }
        return result;
    }

    public static int trappingRainwater(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while ((!stack.isEmpty()) && (height[stack.peek()] < height[i])) {
                int pop_height = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int dist = i - stack.peek() - 1;
                int min_height = Math.min(height[stack.peek()], height[i]) - pop_height;
                ans += dist * min_height;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void printStack(Stack<Integer> stk) {
        System.out.println(stk.peek() + " <- top");
        for (int i = stk.size() - 2; i >= 0; i--) {
            System.out.println(stk.get(i));
        }
    }

    public static void main(String[] args) {
        // String str = "/a/./b/../../c/";
        // System.out.println(simplify(str));
    }
}

class LinkList {
    Node head = null;
    Node tail = null;
    int size = 0;

    public void addLast(char data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }
}

class Node {
    char data;
    Node next;

    Node(char data) {
        this.data = data;
        next = null;
    }
}