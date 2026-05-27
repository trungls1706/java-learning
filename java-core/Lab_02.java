import java.util.Scanner;
public class Lab_02 {
    public static void main(String[] args) {
        // chu vi hinh chua nhat 
        // dai + rong * 2
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap chieu dai: ");
        double length = scanner.nextDouble();
        System.out.println("Nhap chieu rong: ");
        double width = scanner.nextDouble();
        double perimeter = (length + width) * 2;
        System.out.println("Chu vi hinh chu nhat la: " + perimeter);
        // dien tich hinh chu nhat
        double area = length * width;
        System.out.println("Dien tich hinh chu nhat la: " + area);
        scanner.close();
    }
}
