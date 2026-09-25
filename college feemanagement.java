import java.util.ArrayList;
import java.util.Scanner;


// Student class
class Student {

    String studentId;
    String name;
    String course;
    int semester;

    double totalFee;
    double discount;
    double fine;
    double amountPaid;


    // Constructor
    Student(String studentId, String name, String course,
            int semester, double totalFee) {

        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.semester = semester;
        this.totalFee = totalFee;

        this.discount = 0;
        this.fine = 0;
        this.amountPaid = 0;
    }


    // Calculate outstanding balance
    double getOutstandingBalance() {

        return totalFee - discount + fine - amountPaid;
    }


    // Make payment
    void makePayment(double amount) {

        amountPaid += amount;
    }


    // Apply discount
    void applyDiscount(double amount) {

        discount += amount;
    }


    // Add fine
    void addFine(double amount) {

        fine += amount;
    }


    // Display student details
    void displayDetails() {

        System.out.println("----------------------------------");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Semester: " + semester);
        System.out.println("Total Fee: " + totalFee);
        System.out.println("Discount: " + discount);
        System.out.println("Fine: " + fine);
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("Outstanding Balance: "
                + getOutstandingBalance());
        System.out.println("----------------------------------");
    }
}


// Main class
public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Student> students = new ArrayList<>();


    // Add student
    static void addStudent() {

        System.out.println("\n===== ADD STUDENT =====");

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Semester: ");
        int semester = scanner.nextInt();

        System.out.print("Enter Total Fee: ");
        double fee = scanner.nextDouble();

        scanner.nextLine();


        Student student =
                new Student(id, name, course, semester, fee);

        students.add(student);

        System.out.println("Student added successfully.");
    }


    // Search student
    static Student searchStudent() {

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        for (Student student : students) {

            if (student.studentId.equals(id)) {

                return student;
            }
        }

        return null;
    }


    // Make payment
    static void makePayment() {

        System.out.println("\n===== MAKE PAYMENT =====");

        Student student = searchStudent();

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }


        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();

        scanner.nextLine();


        if (amount <= 0) {

            System.out.println("Invalid payment amount.");
            return;
        }


        if (amount > student.getOutstandingBalance()) {

            System.out.println(
                    "Payment cannot be greater than outstanding balance."
            );

            return;
        }


        student.makePayment(amount);

        System.out.println("Payment successful.");
        System.out.println("Receipt generated.");
        System.out.println("Amount Paid: " + amount);
        System.out.println("Remaining Balance: "
                + student.getOutstandingBalance());
    }


    // Apply discount
    static void applyDiscount() {

        System.out.println("\n===== APPLY DISCOUNT =====");

        Student student = searchStudent();

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }


        System.out.print("Enter discount amount: ");
        double discount = scanner.nextDouble();

        scanner.nextLine();


        if (discount <= 0) {

            System.out.println("Invalid discount.");
            return;
        }


        student.applyDiscount(discount);

        System.out.println("Discount applied successfully.");
    }


    // Add fine
    static void addFine() {

        System.out.println("\n===== ADD FINE =====");

        Student student = searchStudent();

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }


        System.out.print("Enter fine amount: ");
        double fine = scanner.nextDouble();

        scanner.nextLine();


        if (fine <= 0) {

            System.out.println("Invalid fine.");
            return;
        }


        student.addFine(fine);

        System.out.println("Fine added successfully.");
    }


    // Display student details
    static void displayStudent() {

        System.out.println("\n===== STUDENT DETAILS =====");

        Student student = searchStudent();

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        student.displayDetails();
    }


    // Collection report
    static void collectionReport() {

        System.out.println("\n===== COLLECTION REPORT =====");

        double totalFees = 0;
        double totalDiscount = 0;
        double totalFine = 0;
        double totalCollected = 0;
        double totalOutstanding = 0;


        for (Student student : students) {

            totalFees += student.totalFee;
            totalDiscount += student.discount;
            totalFine += student.fine;
            totalCollected += student.amountPaid;
            totalOutstanding +=
                    student.getOutstandingBalance();
        }


        System.out.println("Total Students: " + students.size());
        System.out.println("Total Fees: " + totalFees);
        System.out.println("Total Discount: " + totalDiscount);
        System.out.println("Total Fine: " + totalFine);
        System.out.println("Total Collected: " + totalCollected);
        System.out.println("Total Outstanding: "
                + totalOutstanding);
    }


    // Main method
    public static void main(String[] args) {

        int choice;


        do {

            System.out.println("\n================================");
            System.out.println("   COLLEGE FEE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Make Payment");
            System.out.println("3. Apply Discount");
            System.out.println("4. Add Fine");
            System.out.println("5. Search Student");
            System.out.println("6. Collection Report");
            System.out.println("7. Exit");
            System.out.println("================================");


            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            scanner.nextLine();


            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    makePayment();
                    break;

                case 3:
                    applyDiscount();
                    break;

                case 4:
                    addFine();
                    break;

                case 5:
                    displayStudent();
                    break;

                case 6:
                    collectionReport();
                    break;

                case 7:
                    System.out.println(
                            "Thank you for using the system."
                    );
                    break;

                default:
                    System.out.println("Invalid choice.");
            }


        } while (choice != 7);


        scanner.close();
    }
}