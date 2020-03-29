package car.model;

import java.sql.*;

public class CarDAO {
    final static String URL = "jdbc:mysql://localhost:3306/db";
    public String add(Car car){
        String query = "insert into car (make, model, feature, fuel, door, user_id)" +
                "values (?,?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getFeature());
            preparedStatement.setString(4, car.getFuel());
            preparedStatement.setInt(5, car.getDoor());
            preparedStatement.setInt(6, car.getUserid());
            preparedStatement.executeUpdate();

            return "Successfully created new entry";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failure creating new entry";
        }
    }

    public ResultSet searchByMake(String make, User user){
        String query2 = "";
        if (user.isAdmin()) { // display all results- it's admin after all ;)
            if (make.equals("")) {//Admin didn't entered any team_name - displaying all entries
                query2 = "SELECT * FROM car";
            } else {// Admin entered team_name displaying specific entries
                query2 = "SELECT * FROM car WHERE make LIKE '" + make + "'";
            }
        } else { // display only specific user results
            if (make.equals("")) {//User didn't entered any team_name - displaying all entries created by him
                query2 = "SELECT * FROM car WHERE user_id = '" + user.getId() + "'";
            } else {// User entered team_name displaying specific entries created by him
                query2 = "SELECT * FROM car WHERE user_id = '" + user.getId() + "' AND make LIKE '" + make + "'";
            }
        }

        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, "root", "");
            preparedStatement = connection.prepareStatement(query2);
            resultSet = preparedStatement.executeQuery(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void editById(Car car){
        String query = "update car set make=?, model=?, feature=?, fuel=?, door=? " +
                " where id=?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getFeature());
            preparedStatement.setString(4, car.getFuel());
            preparedStatement.setInt(5, car.getDoor());
            preparedStatement.setInt(6, car.getId());
            preparedStatement.executeUpdate();

            System.out.println("Succeeded edit entry");
        } catch (SQLException e) {
            System.out.println("Failed edit entry");
            e.printStackTrace();
        }
    }
    public void deleteById(int id){
        String query = "delete from car where id=?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Succes delete entry");
        } catch (SQLException e) {
            System.out.println("Failed delete entry");
            e.printStackTrace();
        }
    }
}
