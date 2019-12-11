package be.rubus.payara.testcontainers.microshed;

public class Data {

    private String name;
    private int age;

    public Data() {
    }

    public Data(String name, int age) {
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
