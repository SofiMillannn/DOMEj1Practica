package Konni;

public class Konni {
    private String id;
    private String name;
    private int age;
    private String rank;

    public Konni() {
    }

    public Konni(String id, String name, int age, String rank) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Konni{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", rank='" + rank + '\'' +
                '}';
    }
}
