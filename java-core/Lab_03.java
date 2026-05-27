public class Lab_03 {
    public static void main(String[] args) {

        Student student1 = new Student();

        student1.setName("Trung");
        student1.setAge(20);
        student1.setMajor("Computer Science");

        System.out.println("Name: " + student1.getName());
        System.out.println("Age: " + student1.getAge());
        System.out.println("Major: " + student1.getMajor());
    }
}

class Student {
    // constructor
    private String name;
    private int age;
    private String major;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }
}