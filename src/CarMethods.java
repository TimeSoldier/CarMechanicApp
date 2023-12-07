import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextArea;

import java.sql.ResultSet;

public class CarMethods 
{
    static String[] loadResult = new String[6];

    public static void Add(String brand, String model, String registration,
    int productionYear, String owner, JTextArea outputArea)
    {
        String insertQuery = "INSERT INTO cars (brand, model, registration, production_year, owner) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) 
            {

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, registration);
            preparedStatement.setInt(4, productionYear);
            preparedStatement.setString(5, owner);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) 
            {
                    outputArea.setText("Car added.");              
                }
             else 
                {
                    outputArea.setText("Unsuccessful. Car not added."); 
                }
            
            }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public static void Redact(int carId, String brand, String model, String registration,
    int productionYear, String owner, JTextArea outputArea)
    {
        String sql = "UPDATE cars SET brand=?, model=?, registration=?, production_year=?, owner=? WHERE car_id=?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, registration);
            preparedStatement.setInt(4, productionYear);
            preparedStatement.setString(5, owner);
            preparedStatement.setInt(6, carId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                outputArea.setText("Successfull update");
            } else {
                outputArea.setText("Unsuccessful update");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Delete(int carId, JTextArea outputArea) {
        String deleteQuery = "DELETE FROM cars WHERE car_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, carId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                outputArea.setText("Car with ID " + carId + " deleted successfully.");
            } else {
                outputArea.setText("No car found with ID " + carId + ". Deletion failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Search(String searchBy, String searchValue, JTextArea outputArea) 
    {
        String sql = "";
        switch (searchBy.toLowerCase()) {
            case "model":
                sql = "SELECT * FROM cars WHERE model = ?";
                break;
            case "brand":
                sql = "SELECT * FROM cars WHERE brand = ?";
                break;
            case "registration":
                sql = "SELECT * FROM cars WHERE registration = ?";
                break;
            default:
                System.out.println("Invalid search criteria");
                return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) 
            {
                preparedStatement.setString(1, searchValue);
                boolean searchSuccess = false;
                try (ResultSet resultSet = preparedStatement.executeQuery()) 
                {
                    while (resultSet.next()) 
                    {
                        outputArea.append( "ID: " + resultSet.getInt("car_id") +
                                ", Brand: " + resultSet.getString("brand") +
                                ", Model: " + resultSet.getString("model") +
                                ", Registration: " + resultSet.getString("registration") +
                                ", Production Year: " + resultSet.getInt("production_year") +
                                ", Owner: " + resultSet.getString("owner") + "\n");
                        searchSuccess = true;
                    }
                }

                if (!searchSuccess) 
                {
                    outputArea.setText("Search failed. No cars found.");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Load(int carId, JTextArea outputArea)

    {
        String sql = "SELECT * FROM cars WHERE car_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, carId);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    loadResult[0] = String.valueOf(resultSet.getString("car_id"));
                    loadResult[1] = resultSet.getString("brand");
                    loadResult[2] = resultSet.getString("model");
                    loadResult[3]= resultSet.getString("registration");
                    loadResult[4] = String.valueOf(resultSet.getInt("production_year"));
                    loadResult[5] = resultSet.getString("owner");

                } else {
                    outputArea.setText("Please put down valid id.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void List(JTextArea outputArea) {

        String sql = "SELECT * FROM cars";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            outputArea.setText(""); 

            while (resultSet.next()) {
                outputArea.append( "ID: " + resultSet.getInt("car_id") +
                    ", Brand: " + resultSet.getString("brand") +
                    ", Model: " + resultSet.getString("model") +
                    ", Registration: " + resultSet.getString("registration") +
                    ", Production Year: " + resultSet.getInt("production_year") +
                    ", Owner: " + resultSet.getString("owner") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error occurred while listing cars.");
        }
    }

   
}

