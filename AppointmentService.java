package com.advocate.service;
import com.advocate.dao.AppointmentDAO;
import com.advocate.model.Appointment;
import java.sql.SQLException;
import java.util.List;
public class AppointmentService {
    private AppointmentDAO appointmentDAO;
    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }
    public boolean addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    public Appointment getAppointmentById(int appointmentId) {
        try {
            return appointmentDAO.getAppointmentById(appointmentId);
        } catch (SQLException e) {
            e.printStackTrace(); 
            return null; 
        }
    }
    public List<Appointment> getAllAppointments() {
        try {
            return appointmentDAO.getAllAppointments();
        } catch (SQLException e) {
            e.printStackTrace(); 
            return null; 
        }
    }
    public boolean updateAppointment(Appointment appointment) {
        try {
            appointmentDAO.updateAppointment(appointment);
            return true; 
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    public boolean deleteAppointment(int appointmentId) {
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
public boolean cancelAppointment(int appointmentId) {
    Appointment appointment = getAppointmentById(appointmentId);
    if (appointment != null) {
        appointment.setStatus("Cancelled");
        return updateAppointment(appointment);
    }
    return false;
}
}
