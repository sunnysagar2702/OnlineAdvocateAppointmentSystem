package com.advocate;
import com.advocate.model.Advocate;
import com.advocate.model.Appointment;
import com.advocate.model.Customer;
import com.advocate.service.AdvocateService;
import com.advocate.service.AppointmentService;
import com.advocate.service.CustomerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerService();
    private static final AdvocateService advocateService = new AdvocateService();
    private static final AppointmentService appointmentService = new AppointmentService();
    private static int advocateIdCounter = 1;
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/advocate_appointment";
    private static final String username = "root";
    private static final String password = "Wrd@2023";
    private static Connection connection;
    public static void main(String[] args) {
        establishDatabaseConnection();
        int choice;
        do {
            System.out.println("******** Online Advocate Appointment System ********");
            System.out.println("1. Customer Menu");
            System.out.println("2. Advocate Menu");
            System.out.println("3. Appointment Menu");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    advocateMenu();
                    break;
                case 3:
                    appointmentMenu();
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        closeDatabaseConnection();
        scanner.close();
    }
    private static void establishDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }
    private static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void customerMenu() {
        int choice;
        do {
            System.out.println("******** Customer Menu ********");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. View Customer by ID");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    viewCustomerById();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    deleteCustomer();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    private static void addCustomer() {
        System.out.println("Enter customer details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        boolean added = customerService.addCustomer(customer);
        if (added) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer. Please try again.");
        }
    }
    private static void viewAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("All Customers:");
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getCustomerId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println();
            }
        }
    }
    private static void viewCustomerById() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            System.out.println("Customer Details:");
            System.out.println("ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }
    private static void updateCustomer() {
        System.out.print("Enter Customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer existingCustomer = customerService.getCustomerById(customerId);
        if (existingCustomer != null) {
            System.out.println("Enter updated customer details:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            existingCustomer.setName(name);
            existingCustomer.setEmail(email);
            boolean updated = customerService.updateCustomer(existingCustomer);
            if (updated) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Failed to update customer. Please try again.");
            }
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }
    private static void deleteCustomer() {
        System.out.print("Enter Customer ID to delete: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        boolean deleted = customerService.deleteCustomer(customerId);
        if (deleted) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Failed to delete customer. Please try again.");
        }
    }
    private static void advocateMenu() {
        int choice;
        do {
            System.out.println("******** Advocate Menu ********");
            System.out.println("1. Add Advocate");
            System.out.println("2. View All Advocates");
            System.out.println("3. View Advocate by ID");
            System.out.println("4. Update Advocate");
            System.out.println("5. Delete Advocate");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addAdvocate();
                    break;
                case 2:
                    viewAllAdvocates();
                    break;
                case 3:
                    viewAdvocateById(); 
                    break;
                case 4:
                    updateAdvocate(); 
                    break;
                case 5:
                    deleteAdvocate();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    private static void appointmentMenu() {
        int choice;
        do {
            System.out.println("******** Appointment Menu ********");
            System.out.println("1. Create Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Appointment by ID");
            System.out.println("4. Update Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  
    
            switch (choice) {
                case 1:
                    createAppointment(); 
                    break;
                case 2:
                    viewAllAppointments(); 
                    break;
                case 3:
                    viewAppointmentById(); 
                    break;
                case 4:
                    updateAppointment(); 
                    break;
                case 5:
                    cancelAppointment(); 
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    private static void addAdvocate() {
        System.out.println("Enter Advocate details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        int advocateId = advocateIdCounter++;
        Advocate advocate = new Advocate(advocateId, name, specialization);
        advocate.setName(name);
        advocate.setSpecialization(specialization);
        boolean added = advocateService.addAdvocate(advocate);
        if (added) {
            System.out.println("Advocate added successfully.");
        } else {
            System.out.println("Failed to add advocate. Please try again.");
        }
    }
    private static void viewAllAdvocates() {
        List<Advocate> advocates = advocateService.getAllAdvocates();
        if (advocates.isEmpty()) {
            System.out.println("No advocates found.");
        } else {
            System.out.println("All Advocates:");
            for (Advocate advocate : advocates) {
                System.out.println("ID: " + advocate.getAdvocateId()); 
                System.out.println("Name: " + advocate.getName());
                System.out.println("Specialization: " + advocate.getSpecialization());
                System.out.println();
            }
        }
    }  
private static void viewAdvocateById() {
    System.out.print("Enter Advocate ID: ");
    int advocateId = scanner.nextInt();
    scanner.nextLine();
    Advocate advocate = advocateService.getAdvocateById(advocateId);
    if (advocate != null) {
        System.out.println("Advocate Details:");
        System.out.println("ID: " + advocate.getAdvocateId()); 
        System.out.println("Name: " + advocate.getName());
        System.out.println("Specialization: " + advocate.getSpecialization());
    } else {
        System.out.println("Advocate not found with ID: " + advocateId);
    }
} 
    private static void updateAdvocate() {
        System.out.print("Enter Advocate ID to update: ");
        int advocateId = scanner.nextInt();
        scanner.nextLine();
        Advocate existingAdvocate = advocateService.getAdvocateById(advocateId);
        if (existingAdvocate != null) {
            System.out.println("Enter updated Advocate details:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Specialization: ");
            String specialization = scanner.nextLine();
            existingAdvocate.setName(name);
            existingAdvocate.setSpecialization(specialization);
            boolean updated = advocateService.updateAdvocate(existingAdvocate);
            if (updated) {
                System.out.println("Advocate updated successfully.");
            } else {
                System.out.println("Failed to update advocate. Please try again.");
            }
        } else {
            System.out.println("Advocate not found with ID: " + advocateId);
        }
    }
    private static void deleteAdvocate() {
        System.out.print("Enter Advocate ID to delete: ");
        int advocateId = scanner.nextInt();
        scanner.nextLine();
    
        boolean deleted = advocateService.deleteAdvocate(advocateId);
        if (deleted) {
            System.out.println("Advocate deleted successfully.");
        } else {
            System.out.println("Failed to delete advocate. Please try again.");
        }
    } 
    private static void createAppointment() {
        System.out.println("Enter Appointment details:");
        System.out.print("Appointment Date (yyyy-MM-dd HH:mm:ss): ");
        String dateInput = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss.");
            return;
        }
        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Advocate ID: ");
        int advocateId = scanner.nextInt();
        scanner.nextLine();
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(parsedDate);
        appointment.setCustomer(new Customer(customerId)); 
        appointment.setAdvocate(new Advocate(advocateId)); 
        boolean created = appointmentService.addAppointment(appointment);
        if (created) {
            System.out.println("Appointment created successfully.");
        } else {
            System.out.println("Failed to create appointment. Please try again.");
        }
    }
    private static void viewAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments(); 
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("All Appointments:");
            for (Appointment appointment : appointments) {
                System.out.println("ID: " + appointment.getId());
                System.out.println();
            }
        }
    }
    private static void viewAppointmentById() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if (appointment != null) {
            System.out.println("Appointment Details:");
            System.out.println("ID: " + appointment.getId());
        } else {
            System.out.println("Appointment not found with ID: " + appointmentId);
        }
    }
    private static void updateAppointment() {
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        Appointment existingAppointment = appointmentService.getAppointmentById(appointmentId);
        if (existingAppointment != null) {
            System.out.println("Enter updated Appointment details:");
            boolean updated = appointmentService.updateAppointment(existingAppointment);
            if (updated) {
                System.out.println("Appointment updated successfully.");
            } else {
                System.out.println("Failed to update appointment. Please try again.");
            }
        } else {
            System.out.println("Appointment not found with ID: " + appointmentId);
        }
    }
    private static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        boolean canceled = appointmentService.cancelAppointment(appointmentId); 
        if (canceled) {
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Failed to cancel appointment. Please try again.");
        }
    }
    
}
