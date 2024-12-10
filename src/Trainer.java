public class Trainer {
    private String name;
    private int age;
    private String contact;
    private Members assignedMember;

    public Trainer(int trainerId, String name, int age, String contact) {
        this.trainerId = trainerId;
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public int getTrainerId() {
        return this.trainerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public void assignMember(Members member) {
        this.assignedMember = member;
    }

    public Members getAssignedMember() {
        return assignedMember;
    }

    @Override
    public String toString() {
        return name + " (" + age + "), Contact: " + contact + ", Assigned to: " + (assignedMember != null ? assignedMember.getName() : "None");
    }
}
