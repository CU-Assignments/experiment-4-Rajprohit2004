import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // toString method for displaying employee details
    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeeManagementSystem {

    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        
        // Main menu loop
        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    searchEmployee();
                    break;
                case 5:
                    displayEmployees();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    // Method to add a new employee
    public static void addEmployee() {
        System.out.println("\nEnter Employee Details:");

        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        Employee newEmployee = new Employee(id, name, salary);
        employees.add(newEmployee);

        System.out.println("Employee added successfully!");
    }

    // Method to update employee details
    public static void updateEmployee() {
        System.out.print("\nEnter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Employee employee = findEmployeeById(id);

        if (employee != null) {
            System.out.println("\nEmployee found: " + employee);
            
            System.out.print("Enter new Name (leave blank to keep unchanged): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                employee.setName(name);
            }

            System.out.print("Enter new Salary (enter 0 to keep unchanged): ");
            double salary = scanner.nextDouble();
            if (salary != 0) {
                employee.setSalary(salary);
            }

            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Error: Employee not found.");
        }
    }

    // Method to remove an employee
    public static void removeEmployee() {
        System.out.print("\nEnter Employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Employee employee = findEmployeeById(id);

        if (employee != null) {
            employees.remove(employee);
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Error: Employee not found.");
        }
    }

    // Method to search for an employee by ID
    public static void searchEmployee() {
        System.out.print("\nEnter Employee ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Employee employee = findEmployeeById(id);

        if (employee != null) {
            System.out.println("Employee found: " + employee);
        } else {
            System.out.println("Error: Employee not found.");
        }
    }

    // Method to display all employees
    public static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("\n--- Employee List ---");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    // Helper method to find employee by ID
    public static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;  // Return null if employee not found
    }
}
