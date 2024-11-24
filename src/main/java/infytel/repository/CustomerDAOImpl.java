package infytel.repository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.infytel.entity.Customer;

public class CustomerDAOImpl implements ICustomerDAO {

	private Connection connection = null;
	private PreparedStatement prepStmt = null;
	private ResultSet resultSet = null;

	@Override
	public void insert(Customer customer) {
		try (FileInputStream file = new FileInputStream("resources/application.properties");) {
			Properties prop = new Properties();
			prop.load(file);

			String dname = (String) prop.get("JDBC_DRIVER");
			String url = (String) prop.get("JDBC_URL");
			String username = (String) prop.get("USER");
			String password = (String) prop.get("PASSWORD");
			Class.forName(dname);

			connection = DriverManager.getConnection(url, username, password);
			String query = "insert into customer values (?,?,?,?,?,?)";

			int count = 0;

			prepStmt = connection.prepareStatement(query);
			prepStmt.setLong(++count, customer.getPhoneNumber());
			prepStmt.setString(++count, customer.getName());
			prepStmt.setInt(++count, customer.getAge());
			prepStmt.setString(++count, customer.getGender().toString());
			prepStmt.setString(++count, customer.getAddress());
			prepStmt.setInt(++count, customer.getPlanId());

			prepStmt.executeUpdate();
			System.out.println("Record created");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (prepStmt != null) prepStmt.close();
	            if (connection != null) connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public int remove(Long phoneNo) {
		int result = 1;

		try (FileInputStream file = new FileInputStream("resources/application.properties");) {
			Properties prop = new Properties();
			prop.load(file);

			String dname = (String) prop.get("JDBC_DRIVER");
			String url = (String) prop.get("JDBC_URL");
			String username = (String) prop.get("USER");
			String password = (String) prop.get("PASSWORD");
			Class.forName(dname);

			connection = DriverManager.getConnection(url, username, password);
			String query = "Delete from customer where phone_no = ?";

			prepStmt = connection.prepareStatement(query);
			prepStmt.setLong(1, phoneNo);

			prepStmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				prepStmt.close();
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

}
