public class Membership {
    private int membershipId;
    private String typeName;
    private int duration;
    private double fee;
    private boolean paid;
    private Trainer trainer;

    // Correcting the method call to match the method signature
    private static void processPayment(Scanner scanner, Membership membership) {
        if (membership.isPaid()) {
            System.out.println("Membership is already paid.");
            return;
        }

        System.out.println("Payment required: $" + membership.getFee());
        double payment = getInput(scanner, "Enter payment amount: ");

        if (payment >= membership.getFee()) {
            membership.setPaid(true);
            System.out.println("Payment accepted. Membership is now paid.");
        } else {
            System.out.println("Insufficient payment. Try again.");
        }
    }
    private static void addMembership(Scanner scanner, Members member) {
        // Example membership creation process
        String typeName = getInputString(scanner, "Enter membership type (e.g., Basic, Premium): ");
        int duration = getInput(scanner, "Enter membership duration in months: ");
        double fee = getInput(scanner, "Enter membership fee: ");
        Trainer trainer = getTrainer(scanner);

        Membership membership = new Membership(generateMembershipId++, typeName, duration, fee, trainer);
        member.setMembership(membership);  // Update the member with the new membership
        membershipsList.add(membership);

        System.out.println("New membership added for " + member.getName());
    }

    // Constructor
    public Membership(int membershipId, String typeName, int duration, double fee, Trainer trainer) {
        this.membershipId = membershipId;
        this.typeName = typeName;
        this.duration = duration;
        this.fee = fee;
        this.paid = false; // default to false
        this.trainer = trainer;
    }

    // Getters and setters
    public int getMembershipId() {
        return membershipId;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getDuration() {
        return duration;
    }

    public double getFee() {
        return fee;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    // Method to display membership details
    public String details() {
        return "Type: " + typeName + ", Duration: " + duration + " months, Fee: $" + fee + ", Paid: " + (paid ? "Yes" : "No") + ", Trainer: " + (trainer != null ? trainer.getName() : "None");
    }
}
