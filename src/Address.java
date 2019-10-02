package cs425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Address {
	static Scanner sc = new Scanner(System.in);
	static final String url = "jdbc:postgresql://localhost/Grocery";
	static final String user = "postgres";
	static final String password = "1234";

	// for customers to add new address
	public static void add(int id, int streetNum, String streetName, int aptNum, int zipCode, String city,
			String state) {

		try {

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "Insert into address values(?,?,?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, streetNum);
			pstmt.setString(2, streetName);
			pstmt.setInt(3, aptNum);
			pstmt.setInt(4, zipCode);
			pstmt.setString(5, city);
			pstmt.setString(6, state);
			pstmt.setInt(7, id);
			pstmt.executeUpdate();
			UpdateAddress ua = new UpdateAddress(id, streetNum, streetName, aptNum, zipCode, state);

			conn.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
	}

	// for customers to chooose an address as the shipping address
	public static void updateShippingAddress(int id, int streetNum, String streetName, int aptNum, int zipCode,
			String state) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from shipping_address where customer_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				sql = "Insert into shipping_address values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setInt(2, streetNum);
				pstmt.setString(3, streetName);
				pstmt.setInt(4, aptNum);
				pstmt.setInt(5, zipCode);
				pstmt.setString(6, state);
				pstmt.executeUpdate();
			} else {
				sql = "update shipping_address set street_number=?, street_name=?, apt_number=?, zipcode=?, state=? where customer_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, streetNum);
				pstmt.setString(2, streetName);
				pstmt.setInt(3, aptNum);
				pstmt.setInt(4, zipCode);
				pstmt.setString(5, state);
				pstmt.setInt(6, id);
				pstmt.executeUpdate();
			}
			new Message("Updated", "Shipping address updated.");

			conn.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
	}

	// delete an address
	public static boolean delete(int id, int curStreetNum, String curStreetName, int curAptNum, int curZipCode) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) " + "from address "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				new Message("Error", "No address found ");
				return false;
			}

			sql = "delete from shipping_address "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);
			pstmt.executeUpdate();

			sql = "delete from creditcard "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);
			pstmt.executeUpdate();

			sql = "delete from address "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);
			pstmt.executeUpdate();

			conn.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
		return true;
	}

	// modify an address
	public static void modify(int id) {

		System.out.println("Enter the current delivery address information");
		System.out.println("Street number: ");
		int curStreetNum = sc.nextInt();
		System.out.println("Street name: ");
		String curStreetName = sc.next();
		System.out.println("Apartment number: ");
		int curAptNum = sc.nextInt();
		System.out.println("Zipcode: ");
		int curZipCode = sc.nextInt();

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) " + "from address "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				System.out.println("No match. ");
				return;
			}

			sql = "delete from shipping_address "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);
			pstmt.executeUpdate();

			sql = "delete from creditcard "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);
			pstmt.executeUpdate();

			sql = "delete from address "
					+ "where customer_id=? and street_number=? and street_name=? and apt_number=? and zipcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, curStreetNum);
			pstmt.setString(3, curStreetName);
			pstmt.setInt(4, curAptNum);
			pstmt.setInt(5, curZipCode);
			pstmt.executeUpdate();

			System.out.println("Enter new street number: ");
			int newStreetNum = sc.nextInt();
			System.out.println("Enter new street name: ");
			String newStreetName = sc.next();
			System.out.println("Enter new apartment number: ");
			int newAptNum = sc.nextInt();
			System.out.println("Enter new zipcode: ");
			int newZipCode = sc.nextInt();
			System.out.println("Enter new city: ");
			String newCity = sc.next();
			System.out.println("Enter new state: ");
			String newState = sc.next();

			sql = "Insert into address values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newStreetNum);
			pstmt.setString(2, newStreetName);
			pstmt.setInt(3, newAptNum);
			pstmt.setInt(4, newZipCode);
			pstmt.setString(5, newCity);
			pstmt.setString(6, newState);
			pstmt.setInt(7, id);
			pstmt.executeUpdate();

			updateShippingAddress(id, newStreetNum, newStreetName, newAptNum, newAptNum, newState);

			conn.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
	}

}
