import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

public class AppointmentsMethods
{

    static String[] loadResultAppointments = new String[6];

    public static void Add(int carId, int serviceId, String time, String status,JTextArea outputArea)
    { 
        String sql = "INSERT INTO appointments (car_id, service_id, time, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
           PreparedStatement statement = connection.prepareStatement(sql))
            {
                statement.setInt(1, carId);
                statement.setInt(2, serviceId);
                statement.setString(3, time);
                statement.setString(4, status);

                statement.executeUpdate();
                outputArea.setText("Appointment added successfully!");
            }
         
            catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public static void Redact(Integer appointmentId,int carId, int serviceId, String time, String status,JTextArea outputArea)
    {

        String sql = "UPDATE appointments SET car_id = ?, service_id = ?, time = ?, status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root")) {
            

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, carId);
                statement.setInt(2, serviceId);
                statement.setString(3, time);
                statement.setString(4, status);
                statement.setInt(5, appointmentId);

                int rowsAffected = statement.executeUpdate();
                
                if (rowsAffected > 0) {
                    outputArea.setText("Appointment updated successfully!");
                } else {
                    outputArea.setText("No appointment found with ID: " + appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static void Delete(int appointment_Id, JTextArea outputArea) {
        String deleteQuery = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, appointment_Id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                outputArea.setText("Service with ID " + appointment_Id + " deleted successfully.");
            } else {
                outputArea.setText("No service found with ID " + appointment_Id + ". Deletion failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void List(JTextArea outputArea) 
    {
        outputArea.setText("");
        String sql = "SELECT * FROM appointments";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                outputArea.append( "ID: " + resultSet.getInt("appointment_id") +
                ", carId: " + resultSet.getInt("car_id") +
                ", serviceId: " + resultSet.getInt("service_id") +
                ", time: " + resultSet.getString("time") +
                ", status: " + resultSet.getString("status") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error occurred while listing appointments.");
        }
    }

    public static void Load(int appointmentId, JTextArea outputArea)
    {
        
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, appointmentId);
             // Execute the query
             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    loadResultAppointments[0] = String.valueOf(resultSet.getInt("appointment_id"));
                    loadResultAppointments[1] = String.valueOf(resultSet.getInt("car_id"));
                    loadResultAppointments[2] = String.valueOf(resultSet.getInt("service_id"));
                    loadResultAppointments[3] = resultSet.getString("time");
                    loadResultAppointments[4] = resultSet.getString("status");


                } else {
                    outputArea.setText("Please put down valid id.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
