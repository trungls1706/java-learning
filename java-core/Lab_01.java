import java.util.Scanner;
public class Lab_01 {
    public static void main(String[] args) {

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
