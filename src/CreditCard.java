package cs425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JTable;

public class CreditCard {

	static Scanner sc = new Scanner(System.in);
	static final String url = "jdbc:postgresql://localhost/Grocery";
	static final String user = "postgres";
	static final String password = "1234";
	public static long oldNum = 0;
	public static String newBank;
	public static int newExpirationDate;
	public static int newStreetNum;
	public static String newStreetName;
	public static int newAptNum;
	public static int newZipCode;
	public static String newCity;
	public static String newState;
	public static JTable cardTable;
	static public Object[][] cardList;
	
	// for customer to add a new credit card
	public static boolean add(int id, long cardNum, String bank, int expirationDate, int streetNum, String streetName,
			int aptNum, int zipCode, String city, String state) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from creditcard where card_number=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cardNum);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count != 0) {
				new Message("Error", "Card already in the system.");
				return false;
			}

			sql = ("select count(*) " + "from address "
					+ "where street_number=? and street_name=? and apt_number=? and zipcode=?");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, streetNum);
			pstmt.setString(2, streetName);
			pstmt.setInt(3, aptNum);
			pstmt.setInt(4, zipCode);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				sql = "Insert into address values(?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, streetNum);
				pstmt.setString(2, streetName);
				pstmt.setInt(3, aptNum);
				pstmt.setInt(4, zipCode);
				pstmt.setString(5, city);
				pstmt.setString(6, state);
				pstmt.setInt(7, id);
				pstmt.executeUpdate();
			}

			sql = "Insert into creditcard values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cardNum);
			pstmt.setString(2, bank);
			pstmt.setInt(3, expirationDate);
			pstmt.setInt(4, id);
			pstmt.setInt(5, streetNum);
			pstmt.setString(6, streetName);
			pstmt.setInt(7, aptNum);
			pstmt.setInt(8, zipCode);
			pstmt.executeUpdate();

			new Message("Added", "New card added. ");
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

	// delete a credit card
	public static void delete(int id, long cardNum) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from creditcard where card_number=? and customer_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cardNum);
			pstmt.setInt(2, id);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				new Message("Error", "No match. ");
				return;
			}

			sql = "delete from creditcard where card_number=? and customer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cardNum);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

			new Message("Deleted", "Card deleted successfully");
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

	// browse the credit cards in the system.
	public static void browseCard(int id) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from creditcard where customer_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				new Message("No match", "There is no credit card in the system. ");
				return;
			}

			sql = "select card_number from creditcard where customer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			cardList = new Object[count][1];
			//cardList[0][0] = "Card number: ";
			int walk = 0;
			while (rs.next()) {
				cardList[walk][0] = rs.getLong(1) ;
				walk++;
			}
			
			Object[] columnNames = { "Available card number(s): " };
			Object[][] data = cardList;
			cardTable = new JTable(data, columnNames);
			CardList cardList = new CardList();
			
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

	// modify a credit card
	public static void modify(int id, long cardNum) {

		try {

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from creditcard where card_number=?and customer_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cardNum);
			pstmt.setInt(2, id);
			ResultSet rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				new Message("Error", "No card found");
				return;
			}
			ModifyCardCont mcc = new ModifyCardCont(id);

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

	public static void modifyCont(int id) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = ("select count(*) from address where street_number=? and street_name=? and apt_number=? and zipcode=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newStreetNum);
			pstmt.setString(2, newStreetName);
			pstmt.setInt(3, newAptNum);
			pstmt.setInt(4, newZipCode);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				sql = "Insert into address values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, newStreetNum);
				pstmt.setString(2, newStreetName);
				pstmt.setInt(3, newAptNum);
				pstmt.setInt(4, newZipCode);
				pstmt.setString(5, newCity);
				pstmt.setString(6, newState);
				pstmt.executeUpdate();
			}

			sql = "update creditcard set bank=?, expiration_date=?, street_number=?, street_name=?, apt_number=?, zipcode=? where card_number=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newBank);
			pstmt.setInt(2, newExpirationDate);
			pstmt.setInt(3, newStreetNum);
			pstmt.setString(4, newStreetName);
			pstmt.setInt(5, newAptNum);
			pstmt.setInt(6, newZipCode);
			pstmt.setLong(7, oldNum);
			pstmt.executeUpdate();

			new Message("Success", "Card information updated successfully. ");

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
