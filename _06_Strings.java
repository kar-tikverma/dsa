import java.util.Scanner;
import java.util.Arrays;

public class _06_Strings {
    static Scanner sc = new Scanner(System.in);

    public static void SingleWordInput() {
        System.out.print("Enter multiple words: ");
        String str = sc.next();
        System.out.println(str);
    }

    public static void MultipleWordInput() {
        System.out.print("Enter multiple words: ");
        String str = sc.nextLine();
        System.out.println(str);
    }

    public static char valueAtGivenIndex(String str, int index) {
        return str.charAt(index);
        // can return in int too.
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static float shortestPath(String path) {
        // takes only strings containing "N", "S", "w", "E"
        int NS = 0, WE = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'N') {
                NS++;
            } else if (path.charAt(i) == 'S') {
                NS--;
            } else if (path.charAt(i) == 'W') {
                WE++;
            } else {
                WE--;
            }
        }
        return (float) Math.sqrt(NS * NS + WE * WE);
    }

    public static String subString(String str, int si, int ei) {
        String substr = "";
        for (int i = si; i < ei; i++) {
            substr += str.charAt(i);
        }
        return substr;
    }

    public static int compareStrings(String str1, String str2) {
        return str1.compareTo(str2);
    }

    public static int compareStrings_IgnoreCase(String str1, String str2) {
        return str1.compareToIgnoreCase(str2);
    }

    public static String String_toUpperCase(String str) {
        StringBuilder sb = new StringBuilder("");
        char ch = Character.toUpperCase(str.charAt(0));
        sb.append(ch);
        for (int i = 1; i < str.length(); i++) {
            sb.append(str.charAt(i));
            if (str.charAt(i) == ' ' && i < str.length() - 1 && str.charAt(i + 1) != ' ') {
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            }
        }
        return sb.toString();
    }

    public static String StringCompression(String str) {
        String compStr = "";
        for (int i = 0; i < str.length(); i++) {
            Integer count = 1;
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            compStr += str.charAt(i);
            if (count > 1) {
                compStr += count.toString();
            }
        }
        return compStr;
    }

    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() == str2.length()) {
            char s1arr[] = str1.toCharArray();
            char s2arr[] = str2.toCharArray();
            Arrays.sort(s1arr);
            Arrays.sort(s2arr);
            if (Arrays.equals(s1arr, s2arr)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(20);
        sb.append("ndwiucneincincecnwncjkwn");
        System.out.println(sb);
        System.out.println(sb.capacity());
        sb.append("odjwwinddjwbwjncijenwi");
        System.out.println(sb.capacity());
    }
}