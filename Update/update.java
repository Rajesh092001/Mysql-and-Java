	package DatabaseExample;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.sql.SQLException;
	
	public class DatabaseExample {
	    public static void main(String[] args) {
	        String jdbcUrl = "jdbc:mysql://localhost:3306/student_db";
	        String username = "root"; 
	        String password = "rajesh"; 
	
	        try {
	            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
	            Statement statement = connection.createStatement();
	
	            String query = "SELECT * FROM students";
	            ResultSet resultSet = statement.executeQuery(query);
	
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                int age = resultSet.getInt("age");
	                String major = resultSet.getString("major");
	
	                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Major: " + major);
	            }
	
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}