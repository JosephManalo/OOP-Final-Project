import java.util.ArrayList;
import java.util.Scanner;

public class GymManagementSystem {
    static ArrayList<Member> membersList = new ArrayList<>();
    static ArrayList<Membership> membershipsList = new ArrayList<>();
    static ArrayList<Trainer> trainersList = new ArrayList<>();
    static int generateMembershipId = 101;
    static int generateTrainerId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        trainersList.add(new Trainer("John Doe", 35, "0924-468-1357"));
        trainersList.add(new Trainer("Jane Smith", 29, "0998-765-4321"));
        trainersList.add(new Trainer("Alex Brown", 40, "0912-345-6789"));

        while (true) {
            String userRole = login(scanner);
            if (userRole.equals("admin")) {
                adminMenu(scanner);
            } else if (userRole.equals("user")) {
                Member member = membersList.get(membersList.size() - 1);
                userMenu(scanner, member);
            } else {
                System.out.println("Access denied.");
                login(scanner);
                break;
            }
        }
    }


    public static String login(Scanner scanner) {
        System.out.println("=======FitFlow=======\nGym Management System\nLogin as:\n1. Admin\n2. User\n3. Register New Member\n4. Exit\n=====================");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            return "admin";
        } else if (choice.equals("2")) {
            System.out.print("Enter Membership ID: ");
            int membershipId = Integer.parseInt(scanner.nextLine());
            Member member = findMemberById(membershipId);

            if (member != null) {
                boolean validPassword = false;
                while (!validPassword) {
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    if (member.validatePassword(password)) {
                        validPassword = true;
                        return "user";
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                }
            } else {
                System.out.println("Membership ID not found.");
                return "";
            }
        } else if (choice.equals("3")) {
            addMember(scanner);
            return "user";
        } else if (choice.equals("4")) {
            System.exit(0);
        }

        return "";
    }



    public static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=====Admin Menu=====");
            System.out.println("1. View Members");
            System.out.println("2. View Memberships");
            System.out.println("3. Add Trainer");
            System.out.println("4. View Trainers");
            System.out.println("5. Terminate Membership");
            System.out.println("6. Process Payment");
            System.out.println("7. Log out");
            System.out.println("====================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    for (Member member : membersList) {
                        System.out.println(member);
                    }
                    break;
                case "2":
                    for (Membership membership : membershipsList) {
                        System.out.println(membership.details());
                    }
                    break;
                case "3":
                    addTrainer(scanner);
                    break;
                case "4":
                    for (Trainer trainer : trainersList) {
                        System.out.println(trainer);
                    }
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
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void userMenu(Scanner scanner, Member member) {
        while (true) {
            System.out.println("\n======User Menu for " + member.name + "======");
            System.out.println("1. View Membership Details");
            System.out.println("2. Register New Membership");
            System.out.println("3. Process Payment");
            System.out.println("4. Cancel Membership");
            System.out.println("5. Update Member Information"); // New Option to Update Information
            System.out.println("6. Log Out");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            Membership membership = findMembershipById(member.membershipId);

            switch (choice) {
                case "1":
                    if (membership != null) {
                        System.out.println(membership.details());
                    } else {
                        System.out.println("You do not have a membership. Please register for one.");
                    }
                    break;
                case "2":
                    addMembership(scanner, member);
                    break;
                case "3":
                    processPayment(scanner);
                    break;
                case "4":
                    cancelMembership(member);
                    break;
                case "5":
                    updateMemberInfo(scanner, member);
                    break;
                case "6":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void updateMemberInfo(Scanner scanner, Member member) {
        System.out.println("===== Update Member Information =====");

        System.out.print("Current Name: " + member.name + "\nEnter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            member.name = name;
        }

        System.out.print("Current Age: " + member.age + "\nEnter new age (leave blank to keep current): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                member.age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Age not updated.");
            }
        }
        System.out.print("Current Contact: " + member.contact + "\nEnter new contact number (leave blank to keep current): ");
        String contact = scanner.nextLine();
        if (!contact.isEmpty()) {
            member.contact = contact;
        }

        System.out.print("Enter new password (leave blank to keep current): ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) {
            if (password.length() >= 6) {
                member.password = password;
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Password must be at least 6 characters long. Password not updated.");
            }
        }

        System.out.println("Member information updated successfully!");
    }

    public static void addMember(Scanner scanner) {
        System.out.println("===== Register as a Member =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int membershipId = generateMembershipId++;

        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your contact number: ");
        String contact = scanner.nextLine();

        String password = "";
        boolean validPassword = false;

        while (!validPassword) {
            System.out.print("Enter your password (at least 6 characters): ");
            password = scanner.nextLine();
            if (password.length() >= 6) {
                validPassword = true;
            } else {
                System.out.println("Password must be at least 6 characters long. Please try again.");
            }
        }

        Member member = new Member(name, membershipId, age, contact, password);
        membersList.add(member);

        System.out.println("Member registered successfully.");
        System.out.println("Your Membership ID is: " + membershipId);
        System.out.println("Logging you in...");
    }


    public static void addMembership(Scanner scanner, Member member) {
        System.out.print("Enter Membership Type (Basic/Premium): ");
        String typeName = scanner.nextLine();
        System.out.print("Enter Membership Duration (in months): ");
        int duration = Integer.parseInt(scanner.nextLine());
        double fee = (typeName.equalsIgnoreCase("basic")) ? 50 * duration : 100 * duration;
        Trainer trainer = null;

        if (typeName.equalsIgnoreCase("premium")) {
            trainer = selectTrainer(scanner);
            if (trainer != null) {
                trainer.assignedMember = member;
            }
        }

        Membership membership = new Membership(member.membershipId, typeName, duration, fee, trainer);
        membershipsList.add(membership);
        System.out.println("Membership added for " + member.name + ".\n" + membership.details());
    }


    public static void addTrainer(Scanner scanner) {
        System.out.print("Enter trainer's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter trainer's age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter trainer's contact number: ");
        String contact = scanner.nextLine();

        Trainer trainer = new Trainer(name, age, contact);
        trainersList.add(trainer);
        generateTrainerId++;
        System.out.println("Trainer " + name + " added successfully!");
    }

    public static Trainer selectTrainer(Scanner scanner) {
        if (trainersList.isEmpty()) {
            System.out.println("No trainers available.");
            return null;
        }

        System.out.println("\nAvailable Trainers:");
        for (int i = 0; i < trainersList.size(); i++) {
            System.out.println((i + 1) + ". " + trainersList.get(i));
        }

        System.out.print("Select a trainer by number: ");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        return (choice >= 0 && choice < trainersList.size()) ? trainersList.get(choice) : null;
    }

    public static void processPayment(Scanner scanner) {
        System.out.print("Enter Membership ID to process payment: ");
        int membershipId = Integer.parseInt(scanner.nextLine());
        Membership membership = findMembershipById(membershipId);

        if (membership == null) {
            System.out.println("Membership not found.");
            return;
        }

        if (membership.paid) {
            System.out.println("Membership is already paid.");
            return;
        }

        System.out.println("Payment required: $" + membership.fee);
        System.out.print("Enter payment amount: $");
        double payment = Double.parseDouble(scanner.nextLine());

        if (payment >= membership.fee) {
            membership.paid = true;
            System.out.println("Payment successful. Membership is now active.");
        } else {
            System.out.println("Insufficient payment.");
        }
    }

    public static void terminateMembership(Scanner scanner) {
        System.out.print("Enter Membership ID to terminate: ");
        int membershipId = Integer.parseInt(scanner.nextLine());
        Membership membership = findMembershipById(membershipId);

        if (membership != null) {
            membershipsList.remove(membership);
            System.out.println("Membership terminated.");
        } else {
            System.out.println("Membership not found.");
        }
    }

    public static void cancelMembership(Member member) {
        Membership membership = findMembershipById(member.membershipId);
        if (membership != null) {
            membershipsList.remove(membership);
            System.out.println("Membership canceled for " + member.name);
        }
    }

    public static Member findMemberById(int membershipId) {
        for (Member member : membersList) {
            if (member.membershipId == membershipId) {
                return member;
            }
        }
        return null;
    }

    public static Membership findMembershipById(int membershipId) {
        for (Membership membership : membershipsList) {
            if (membership.membershipId == membershipId) {
                return membership;
            }
        }
        return null;
    }
}
