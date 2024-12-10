public class Member {
    String name;
    int membershipId;
    int age;
    String contact;
    String password;

    public Member(String name, int membershipId, int age, String contact, String password) {
        this.name = name;
        this.membershipId = membershipId;
        this.age = age;
        this.contact = contact;
        this.password = password;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return name + " (" + membershipId + "), Age: " + age + ", Contact: " + contact;
    }
}