package DatabaseExample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;	
public class Delete {


    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "rajesh"; 

    public static void main(String[] args) {
        // Display current records
        displayStudents();

        // Delete a student record
        deleteStudent(1);

        // Display updated records
        displayStudents();
    }

    private static void displayStudents() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM students";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Student Records:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String major = resultSet.getString("major");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Major: " + major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(int studentId) {
        String deleteQuery = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, studentId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student record deleted successfully.");
            } else {
                System.out.println("No student found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
