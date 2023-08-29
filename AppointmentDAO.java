package com.advocate.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.advocate.model.Advocate;
import com.advocate.model.Appointment;
import com.advocate.model.Customer;
import com.advocate.util.DatabaseUtil;
public class AppointmentDAO {
    private Connection connection;
    public AppointmentDAO() {
        connection = DatabaseUtil.getConnection();
    }
    public void addAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments (appointmentDate, customerId, advocateId) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, new java.sql.Date(appointment.getAppointmentDate().getTime()));
        preparedStatement.setInt(2, appointment.getCustomer().getCustomerId());
        preparedStatement.setInt(3, appointment.getAdvocate().getAdvocateId());
        preparedStatement.executeUpdate();
    }
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        Appointment appointment = null;
        String query = "SELECT * FROM appointments WHERE appointmentId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, appointmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getCustomerById(resultSet.getInt("customerId"));
            AdvocateDAO advocateDAO = new AdvocateDAO();
            Advocate advocate = advocateDAO.getAdvocateById(resultSet.getInt("advocateId"));
            appointment = new Appointment(resultSet.getInt("appointmentId"),
                    resultSet.getDate("appointmentDate"), customer, advocate);
        }
        return appointment;
    }
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getCustomerById(resultSet.getInt("customerId"));
            AdvocateDAO advocateDAO = new AdvocateDAO();
            Advocate advocate = advocateDAO.getAdvocateById(resultSet.getInt("advocateId"));
            Appointment appointment = new Appointment(resultSet.getInt("appointmentId"),
                    resultSet.getDate("appointmentDate"), customer, advocate);
            appointments.add(appointment);
        }
        return appointments;
    }
    public void updateAppointment(Appointment appointment) throws SQLException {
        String updateQuery = "UPDATE appointments SET status = ? WHERE appointmentId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, appointment.getStatus());
        preparedStatement.setInt(2, appointment.getId());
        preparedStatement.executeUpdate();
    }
    public void deleteAppointment(int appointmentId) throws SQLException {
        String query = "DELETE FROM appointments WHERE appointmentId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, appointmentId);
        preparedStatement.executeUpdate();
    }
}
