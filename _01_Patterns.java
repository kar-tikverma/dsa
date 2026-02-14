public class _01_Patterns {

    public static void HollowRectangle (int l, int b) {
        for (int i = 1; i <= b; i++) {
            for (int j = 1; j <= l; j++) {
                if (i == 1 || i == b || j == 1 || j == l) {
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void InvertedHalfPyramid (int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= height-i; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void InvertedHalfPyramid_withNumbers (int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= height-i+1; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    public static void FLOYDsTriangle (int height) {
        int cnt = 1;
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(cnt++ + "\t");
            }
            System.out.println();
        }
    }

    public static void _01Triangle (int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                if ((i + j) % 2 == 0){
                    System.out.print(1 + " ");
                }
                else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

    public static void Butterfly (int wing_length) {
        for (int i = 1; i <= wing_length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            for (int j = 1; j <= 2*(wing_length-i); j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        for (int i = wing_length; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            for (int j = 1; j <= 2*(wing_length-i); j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void HollowRhombus (int side) {
        for (int i = 1; i <= side; i++) {
            for (int j = 1; j <= side-i; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= side; j++) {
                if (i == 1 || i == side || j == 1 || j == side){
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void SolidRhombus (int side) {
        for (int i = 1; i <= side; i++) {
            //Spaces
            for (int j = 1; j <= side-i; j++) {
                System.out.print("  ");
            }
            //Stars
            for (int j = 1; j <= side; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void SolidDiamond (int height) {
        for (int i = 1; i <= height/2; i++) {
            for (int j = 1; j <= height/2-i+1; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        if (height % 2 == 1) {
            for (int j = 1; j <= height; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        for (int i = height/2; i >= 1; i--) {
            for (int j = 1; j <= height/2-i+1; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void NumberPyramid (int height) {
        for (int i = 1; i <= height; i++) {
            for (int k = 1; k <= height-i; k++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(i+"   ");
            }
            System.out.println();
        }
    }

    public static void PalindromicPyramid (int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= height-i; j++) {
                System.out.print("  ");
            }
            //descending order loop
            for (int j = i; j >= 1; j--) {
                System.out.print(j+" ");
            }
            //ascending order loop
            for (int j = 2; j <= i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    public static void main(String s[]){
        InvertedHalfPyramid(5);
    }
}