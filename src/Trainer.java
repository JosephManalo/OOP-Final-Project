public class Trainer {
    String name;
    int age;
    String contact;
    Member assignedMember;

    public Trainer(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.assignedMember = null;
    }

    @Override
    public String toString() {
        String assignedInfo = (assignedMember != null) ? "Assigned to: " + assignedMember.name : "Not assigned";
        return name + " (" + age + "), Contact: " + contact + ", " + assignedInfo;
    }
}
