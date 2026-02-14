import java.util.Arrays;
public class _8_OOPS {
    public static void main(String[] args) {
        Vehicle v1 = new Car();
        v1.print();

        Vehicle v2 = new Vehicle();
        v2.print();
    }
}

class Pen {
    private String color;
    private int tipSize;

    void setColor(String newColor) { // Setter
        color = newColor;
    }
    void setTip(int newTip) {
        tipSize = newTip;
    }
    String getColor(){ // Getter
        return color;
    }
    int getTipSize(){ // Getter
        return tipSize;
    }
}
class Bank {
    public String username;
    private String password;
    void setPassword(String password) {
        this.password = password;
    }
    boolean checkPassword (String pass) {
        if (pass == password) {
            return true;
        }
        return false;
    }
}

class Ex {
      
    private int[] data;
  
    // altered to make a deep copy of values
    public Ex(int[] values) {
        data = new int[values.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = values[i];
        }
    }
  
    public void showData() {
        System.out.println(Arrays.toString(data));
    }
}

//Base Class
class Animal {
    String color;
    void eat() {
        System.out.println("eats");
    }
    void breathe() {
        System.out.println("breathes");
    }
}

//Derived class
class Fish extends Animal {
    int fins;
    void swim() {
        System.out.println("swims in water");
    }
}

interface Herbivore {
    void eats();
}
interface Carnivore {
    void eat();
}

class Vehicle {
    void print(){
        System.out.println("Base class function");
    }
}
class Car extends Vehicle {
    void print1() {
        System.out.println("derived class function");
    }
}