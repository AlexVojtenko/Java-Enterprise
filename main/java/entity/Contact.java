package entity;

public class Contact {

    private long id;
    private String number;
    private long numberId;
    private String fullName;
    private int age;
    private String info;

    public Contact(long id, String number, String fullName, int age, String info) {
        this.id = id;
        this.number = number;
        this.numberId = id;
        this.fullName = fullName;
        this.age = age;
        this.info = info;
    }

    public Contact() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getNumberId() {
        return numberId;
    }

    public void setNumberId(long numberId) {
        this.numberId = numberId;
    }
}
