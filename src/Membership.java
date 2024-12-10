public class Membership {
    int membershipId;
    String typeName;
    int duration;
    double fee;
    boolean paid;
    Trainer trainer;

    public Membership(int membershipId, String typeName, int duration, double fee, Trainer trainer) {
        this.membershipId = membershipId;
        this.typeName = typeName;
        this.duration = duration;
        this.fee = fee;
        this.paid = false;
        this.trainer = trainer;
    }

    public String details() {
        String trainerInfo = (trainer != null) ? ", Trainer: " + trainer.name : "";
        String paymentStatus = paid ? "Paid" : "Not Paid";
        return "Type: " + typeName + ", Duration: " + duration + " months, Fee: $" + fee + ", Payment Status: " + paymentStatus + trainerInfo;
    }
}
