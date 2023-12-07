import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

public class ServiceMethods 
{

    static String[] loadResultService = new String[6];

    public static void Add(String name, String description, BigDecimal price, JTextArea outputArea)
    {

        String insertQuery = "INSERT INTO services (name, description, price) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) 
            {

                    outputArea.setText("Service added");              
                }
             else 
                {
                    outputArea.setText("Unsuccessful. Service not added."); 
                }
            
            }
         
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public static void Redact(int service_id,String name, String description, BigDecimal price, JTextArea outputArea)
    {
        String sql = "UPDATE services SET name=?, description=?, price=? WHERE service_id=?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setInt(4, service_id);

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

    public static void Delete(int service_Id, JTextArea outputArea) {
        String deleteQuery = "DELETE FROM services WHERE service_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, service_Id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                outputArea.setText("Service with ID " + service_Id + " deleted successfully.");
            } else {
                outputArea.setText("No service found with ID " + service_Id + ". Deletion failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void List(JTextArea outputArea) 
    {
        outputArea.setText("");
        String sql = "SELECT * FROM services";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                outputArea.append( "ID: " + resultSet.getInt("service_id") +
                ", name: " + resultSet.getString("name") +
                ", description: " + resultSet.getString("description") +
                ", price: " + resultSet.getBigDecimal("price") +"\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error occurred while listing services.");
        }
    }

    public static void Load(int carId, JTextArea outputArea)
    {

        String sql = "SELECT * FROM services WHERE service_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, carId);
             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    loadResultService[0] = String.valueOf(resultSet.getInt("service_id"));
                    loadResultService[1] = resultSet.getString("name");
                    loadResultService[2] = resultSet.getString("description");
                    loadResultService[3]=  String.valueOf(resultSet.getBigDecimal("price"));

                } else {
                    outputArea.setText("Please put down valid id.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
