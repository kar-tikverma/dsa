public class _07_BitManipulation {
    public static void BitOp(int a, int b) {
        System.out.print(a + " & " + b + " (AND) = ");
        System.out.println(a & b);
        System.out.print(a + " | " + b + " (OR) = ");
        System.out.println(a | b);
        System.out.print(a + " ^ " + b + " (XOR) = ");
        System.out.println(a ^ b);
        System.out.print("~" + a + " (NOT) = ");
        System.out.println(~a);
        System.out.print(a + " << " + b + " = ");
        System.out.println(a << b); // a << b = a * 2**b
        System.out.print(a + " >> " + b + " = ");
        System.out.println(a >> b); // a >> b = a / 2**b
    }

    public static void Odd_or_Even(int n) {
        int bitMask = 1;
        if ((n & bitMask) == 0) {
            System.out.println(n + " is Even");
        } else {
            System.out.println(n + " is Odd");
        }
    }

    public static int get_ith_bit(int n, int i) {
        int bitMask = 1 << i;
        if ((n & bitMask) == 0) {
            return 0;
        }
        return 1;
    }

    public static int set_ith_bit(int n, int i) {
        int bitMask = 1 << i;
        return n | bitMask;
    }

    public static int clear_ith_bit(int n, int i) {
        int bitMask = ~(1 << i);
        return n & bitMask;
    }

    public static int update_ith_bit(int n, int i, int newBit) {
        n = clear_ith_bit(n, i);
        int bitMask = newBit << i;
        return n | bitMask;
    }

    public static int clear_iLSB_bits(int n, int i) {
        int bitMask = ~0 << i; // where ~ has higher precedence than <<
        return n & bitMask;
    }

    public static int clear_rangeof_bits(int n, int start, int end) {
        int aux_bitMask1 = ~0 << end; // where ~ has higher precedence than <<
        int aux_bitMask2 = (1 << start) - 1;
        int bitMask = aux_bitMask1 | aux_bitMask2;
        return n & bitMask;
    }

    public static boolean is2_pow_n(int n) {
        return (n & n - 1) == 0;
    }

    public static int countSetBits(int n) { // O(b) where b is the no. of bits
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>= 1;
        }

        return count;
    }

    public static int BrianKernighanAlgorithm(int n) { // O(k) where k is the number of set bits
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Removes the rightmost set bit.
            count++;
        }

        return count;
    }

    public static int fastExponentiation(int a, int pow) { // Time Complexity -> O(log(2)n)
        int res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                res *= a;
            }
            a *= a;
            pow >>= 1;
        }

        return res;
    }

    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public static int add_1(int n) {
        return -~n;
    }

    public static String toLower(String str) {
        byte[] letters = str.getBytes();
        StringBuilder lower = new StringBuilder();

        for (int i = 0; i < letters.length; i++) {
            lower.append((char) (letters[i] | ' '));
        }

        return lower.toString();
    }

    public static void main(String[] args) {
        System.out.println(toLower("NJKBDeuiuwb[]BUDEB''' 27497"));
    }
}