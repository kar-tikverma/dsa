public class _02_Functions_Methods {
    public static int Reverse(int num) {
        int rev_num = 0;
        while (num != 0) {
            rev_num = rev_num * 10 + num % 10;
            num /= 10;
        }
        return rev_num;
    }

    public static double BinToDec(double bin_num) {
        int n = 0;
        while (bin_num != (int) bin_num) {
            bin_num *= 10;
            n++;
        }
        n = -n;
        double dec_num = 0;
        while (bin_num != 0) {
            dec_num += (bin_num % 10) * Math.pow(2, n++);
            bin_num /= 10;
            bin_num = (int) bin_num;
        }
        return dec_num;
    }

    public static double DecToBin(double dec_num) {
        int int_dec = (int) dec_num;
        int rev_bin = 0;
        boolean one_arrived = false;
        int no_of_zeroes = 0;
        while (int_dec != 0) {
            if (one_arrived == true) {
                rev_bin = rev_bin * 10 + int_dec % 2;
                int_dec /= 2;
            } else {
                if (int_dec % 2 == 1) {
                    one_arrived = true;
                    continue;
                }
                int_dec /= 2;
                no_of_zeroes++;
            }
        }
        double bin_num = Reverse(rev_bin) * (int) Math.pow(10, no_of_zeroes);

        // fractional part starts here
        double frac_part = dec_num - (int) dec_num;
        int bin_frac_part = 0;
        int bin_bit;
        int cnt = 0;
        while (frac_part != 0) {
            frac_part *= 2;
            bin_bit = (int) frac_part;
            bin_frac_part = bin_frac_part * 10 + bin_bit;
            frac_part = frac_part - bin_bit;
            cnt++;
            if (cnt > 5)
                break;
        }
        int rev_bin_frac_part = Reverse(bin_frac_part);
        int pow = 1;
        while (rev_bin_frac_part != 0) {
            bin_num = bin_num + (rev_bin_frac_part % 10) / Math.pow(10, pow++);
            rev_bin_frac_part /= 10;
        }
        return bin_num;
    }

    public static void main(String args[]) {
        //
    }
}