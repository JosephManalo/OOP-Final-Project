public class Members {
    private String name;
    private int membershipId;
    private int age;
    private String contact;

    public Members(String name, int membershipId, int age, String contact) {
        this.name = name;
        this.membershipId = membershipId;
        this.age = age;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return name + " (" + membershipId + "), Age: " + age + ", Contact: " + contact;
    }
}
