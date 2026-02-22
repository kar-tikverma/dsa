public class _09_OOPsPracticeQuestions {
    public static void main(String[] args) {
        Complex c1 = new Complex(6, 5);
        Complex c2 = new Complex(2, -7);
        c1 = Complex.diff(c1, c2);
        System.out.println(c1.real + " " + c1.imag);
        c1 = c1.product(c2);
        System.out.println(c1.real + " " + c1.imag);
    }
}

class Complex {
    
    int real;
    int imag;

    Complex(int real, int imag) {
        this.real = real;
        this.imag = imag;
    }

    // Non-static way
    Complex sum(Complex c) {
        return new Complex(this.real + c.real, this.imag + c.imag);
    }

    // Static way
    static Complex diff(Complex c, Complex cc) {
        return new Complex(c.real - cc.real, c.imag - cc.imag);
    }

    Complex product(Complex c) {
        int r = this.real * c.real - this.imag * c.imag;
        int i = this.real * c.imag + this.imag * c.real;
        return new Complex(r, i);
    }
}