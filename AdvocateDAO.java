package com.advocate.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.advocate.model.Advocate;
import com.advocate.util.DatabaseUtil;
public class AdvocateDAO {
    private Connection connection;
    public AdvocateDAO() {
        connection = DatabaseUtil.getConnection();
    }
    public boolean addAdvocate(Advocate advocate) {
        try {
            String query = "INSERT INTO advocates (name, specialization) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, advocate.getName());
            preparedStatement.setString(2, advocate.getSpecialization());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    public Advocate getAdvocateById(int advocateId) {
        Advocate advocate = null;
        try {
            String query = "SELECT * FROM advocates WHERE advocateId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, advocateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                advocate = new Advocate(resultSet.getInt("advocateId"), resultSet.getString("name"),
                        resultSet.getString("specialization"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advocate;
    }
    public List<Advocate> getAllAdvocates() {
        List<Advocate> advocates = new ArrayList<>();
        try {
            String query = "SELECT * FROM advocates";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advocate advocate = new Advocate(resultSet.getInt("advocateId"), resultSet.getString("name"),
                        resultSet.getString("specialization"));
                advocates.add(advocate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advocates;
    }
public boolean updateAdvocate(Advocate advocate) {
    try {
        String query = "UPDATE advocates SET name=?, specialization=? WHERE advocateId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, advocate.getName());
        preparedStatement.setString(2, advocate.getSpecialization());
        preparedStatement.setInt(3, advocate.getAdvocateId());
        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false; 
    }
}
    public boolean deleteAdvocate(int advocateId) {
        try {
            String query = "DELETE FROM advocates WHERE advocateId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, advocateId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
