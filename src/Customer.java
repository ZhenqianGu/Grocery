package cs425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
	static Scanner sc = new Scanner(System.in);
	static final String url = "jdbc:postgresql://localhost/Grocery";
	static final String user = "postgres";
	static final String password = "1234";

	//for customers to see the balance
	public static int getBalance(int id) {
		int bal = 0;

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select balance from customer where customer_id=?");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				bal = rs.getInt("balance");
			}
			conn.close();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
		return bal;
	}

	//for log in 
	public static String getPassword(int id) {
		String customPassword = null;

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = ("select password from Customer where customer_id=?");

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				customPassword = rs.getString("password");
			}
			conn.close();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
		return customPassword;
	}

	//for new customer to register an account
	public static void register(String first, String middle, String last, String userPassword) {
		
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select max(customer_id) from customer");
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1) + 1;
			}
			
			String sql = ("insert into customer values(?,?,?,?,0,?)");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, first);
			pStmt.setString(3, middle);
			pStmt.setString(4, last);
			pStmt.setString(5, userPassword);
			pStmt.executeUpdate();
			new Message("Success", "New account created. Your customer ID is " + id);
			conn.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		} catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
		return;
	}

	
}
