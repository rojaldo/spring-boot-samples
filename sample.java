class Student {
    private String name;
    private int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}

public class sample {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Student s1 = new Student("John", 20);
        System.out.println(s1.getAge());
        Student s2 = s1;
        s2.setAge(30);
        System.out.println(s1.getAge());
    }
}
