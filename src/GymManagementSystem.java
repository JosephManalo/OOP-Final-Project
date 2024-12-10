import java.util.Scanner;
import java.util.ArrayList;

public class GymManagementSystem {

    private static ArrayList<Members> membersList = new ArrayList<>();
    private static ArrayList<Membership> membershipsList = new ArrayList<>();
    private static ArrayList<Trainer> trainersList = new ArrayList<>();
    private static int generateMembershipId = 101;
    private static int generateTrainerId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userRole = login(scanner);
            if (userRole.equals("admin")) {
                adminMenu(scanner);
            } else if (userRole.equals("user")) {
                Members member = getMemberByMembershipId(scanner);
                if (member != null) {
                    userMenu(scanner, member);
                } else {
                    System.out.println("No member found with the provided ID.");
                }
            } else {
                System.out.println("Access denied.");
                break;
            }
        }
    }

    private static String login(Scanner scanner) {
        System.out.println("Login as:\n1. Admin\n2. User\n3. Register New Member");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            return "admin";
        } else if (choice.equals("2")) {
            return "user";
        } else if (choice.equals("3")) {
            addMember(scanner);
            return "user";
        } else {
            return "invalid";
        }
    }

    private static Members getMemberByMembershipId(Scanner scanner) {
        System.out.print("Enter Membership ID: ");
        int membershipId = getInput(scanner, "Enter Membership ID: ");

        for (Members member : membersList) {
            if (member.getMembershipId() == membershipId) {
                return member;
            }
        }
        return null;
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Members");
            System.out.println("2. View Memberships");
            System.out.println("3. Add Trainer");
            System.out.println("4. View Trainers");
            System.out.println("5. Terminate Membership");
            System.out.println("6. Process Payment");
            System.out.println("7. Log out");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewMembers();
                    break;
                case "2":
                    viewMemberships();
                    break;
                case "3":
                    addTrainer(scanner);
                    break;
                case "4":
                    viewTrainers();
                    break;
                case "5":
                    terminateMembership(scanner);
                    break;
                case "6":
                    processPayment(scanner);
                    break;
                case "7":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner, Members member) {
        while (true) {
            System.out.println("\nUser Menu for " + member.getName() + ":");
            System.out.println("1. View Membership Details");
            System.out.println("2. View Trainer");
            System.out.println("3. View Remaining Duration");
            System.out.println("4. Register New Membership");
            System.out.println("5. Process Payment");
            System.out.println("6. Cancel Membership");
            System.out.println("7. Log Out");

            String choice = scanner.nextLine();
            Membership membership = getMembershipById(member.getMembershipId());

            switch (choice) {
                case "1":
                    if (membership != null) {
                        System.out.println(membership.details());
                    } else {
                        System.out.println("No membership found.");
                    }
                    break;
                case "2":
                    if (membership != null && membership.getTrainer() != null) {
                        System.out.println("Trainer: " + membership.getTrainer().getName());
                    } else {
                        System.out.println("No trainer assigned.");
                    }
                    break;
                case "3":
                    if (membership != null) {
                        System.out.println("Remaining Duration: " + membership.getDuration() + " months");
                    }
                    break;
                case "4":
                    addMembership(scanner, member);
                    break;
                case "5":
                    if (membership != null) {
                        processPayment(scanner, membership);
                    } else {
                        System.out.println("No active membership found.");
                    }
                    break;
                case "6":
                    cancelMembership(scanner, member);
                    break;
                case "7":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void cancelMembership(Scanner scanner, Members member) {
        Membership membership = getMembershipById(member.getMembershipId());
        if (membership != null) {
            membershipsList.remove(membership);
            System.out.println("Membership for " + member.getName() + " has been canceled.");
            
            if (membership.getTrainer() != null) {
                membership.getTrainer().setAssignedMember(null);
                System.out.println("Trainer " + membership.getTrainer().getName() + " has been unassigned.");
            }
        } else {
            System.out.println("No active membership found to cancel.");
        }
    }

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

    private static int getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static String getInputString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


    private static void addMember(Scanner scanner) {
        String name = getInputString(scanner, "Enter your username: ");
        int membershipId = generateMembershipId++;
        int age = getInput(scanner, "Enter your age: ");
        String contact = getInputString(scanner, "Enter your contact number: ");
        String gender = getInputString(scanner, "Enter your gender: ");

        Membership membership = createMembership(scanner);
        Members member = new Members(membershipId, name, age, gender, contact, membership);

        membersList.add(member);
        membershipsList.add(membership);
        System.out.println("You have been successfully registered!");
    }

    private static Membership createMembership(Scanner scanner) {
        int membershipId = generateMembershipId++;
        String typeName = getInputString(scanner, "Enter membership type (e.g., Basic, Premium): ");
        int duration = getInput(scanner, "Enter membership duration in months: ");
        double fee = getInput(scanner, "Enter membership fee: ");
        Trainer trainer = getTrainer(scanner);

        return new Membership(membershipId, typeName, duration, fee, trainer);
    }

    private static Trainer getTrainer(Scanner scanner) {
        System.out.println("Select a trainer from the list: ");
        for (Trainer trainer : trainersList) {
            System.out.println(trainer.getTrainerId() + ". " + trainer.getName());
        }

        int trainerId = getInput(scanner, "Select a trainer ID: ");
        for (Trainer trainer : trainersList) {
            if (trainer.getTrainerId() == trainerId) {
                return trainer;
            }
        }
        System.out.println("Trainer not found.");
        return null;
    }

    private static void addTrainer(Scanner scanner) {
        String name = getInputString(scanner, "Enter trainer's name: ");
        int age = getInput(scanner, "Enter trainer's age: ");
        String contact = getInputString(scanner, "Enter trainer's contact number: ");
        Trainer trainer = new Trainer(generateTrainerId++, name, age, contact);
        trainersList.add(trainer);
        System.out.println("Trainer added successfully!");
    }

    private static void viewMembers() {
        for (Members member : membersList) {
            System.out.println(member);
        }
    }

    private static void viewMemberships() {
        for (Membership membership : membershipsList) {
            System.out.println(membership.details());
        }
    }

    private static void viewTrainers() {
        for (Trainer trainer : trainersList) {
            System.out.println(trainer);
        }
    }

    private static void terminateMembership(Scanner scanner) {
        int membershipId = getInput(scanner, "Enter Membership ID to terminate: ");
        Membership membership = getMembershipById(membershipId);
        if (membership != null) {
            membershipsList.remove(membership);
            System.out.println("Membership " + membershipId + " has been terminated.");
        } else {
            System.out.println("Membership not found.");
        }
    }

    private static Membership getMembershipById(int membershipId) {
        for (Membership membership : membershipsList) {
            if (membership.getMembershipId() == membershipId) {
                return membership;
            }
        }
        return null;
    }
}
