
import java.util.Scanner;
public class HelloWorld {
    public static void main(String[] args) {
        // int age = 20;
        // String name = "Trung";
        // System.out.println("My name is " + name);
        // System.out.println("I am " + age + " years old.");

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        System.out.println("My name is " + name);
        System.out.println("I am " + age + " years old.");
        scanner.close();

    }
}
