package cs425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JTable;

public class Staff {
	static Scanner sc = new Scanner(System.in);
	static final String url = "jdbc:postgresql://localhost/Grocery";
	static final String user = "postgres";
	static final String password = "1234";
	static public Object[][] customerList;
	public static JTable customerTable;

	// for staff to login
	public static String password(int id) {
		String staffPassword = null;

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = ("select password from staff where staff_id=?");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				staffPassword = rs.getString("password");
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
		return staffPassword;
	}

	// for staff to see the list of customers that have registered
	public static void customerList() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) " + "from customer");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			
			
			
			sql = ("select * from customer ");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			
			
			customerList = new Object[count][1];
			int walk = 0;
			while (rs.next()) {
				customerList[walk][0] = "Customer ID: " + rs.getString(1) + ", first name: " + rs.getString(2)
				+ ", last name: " + rs.getString(4) + ", balance: " + rs.getString(5);
				walk++;
			}
			Object[] columnNames = { "Customers" };
			Object[][] data = customerList;
			customerTable = new JTable(data, columnNames);
			CustomerList customerList = new CustomerList();


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

	// add a new product to the system.
	public static void addProduct(String name, String category, int size) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = ("select count(*) " + "from product " + "where product_name=? and product_category=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, category);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count != 0) {
				new Message("Error", "This product already exists. ");
				return;
			}

			sql = "Insert into product values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			pstmt.setInt(3, size);
			pstmt.executeUpdate();

			if (category.equals("drink")) {
				SetDrink sd = new SetDrink(name);
			}

			if (category.equals("food")) {
				SetCal sc = new SetCal(name);
			}
			new Message("Added", "New product added. ");

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

	// modify a product
	public static void modify(String name, String category, int newSize) {

		try {

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from product where product_name=? and product_category=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, category);

			ResultSet rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				new Message("Error", "Product not in the databases. Add it first. ");
				return;
			}

			if (category.equals("drink")) {
				SetDrink sd = new SetDrink(name);
			}

			if (category.equals("food")) {
				SetCal sc = new SetCal(name);
			}
			

			sql = "update product set sizes=? where product_name=? and product_category=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newSize);
			pstmt.setString(2, name);
			pstmt.setString(3, category);
			pstmt.executeUpdate();

			new Message("Updated", "Product information updated.");
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

	// delete a product
	public static void delete(String name, String category) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt;
			String sql;

			if (category.equals("food")) {
				sql = "delete from nutrition where product_name =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			}
			if (category.equals("drink")) {
				sql = "delete from alcoholic where product_name =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			}

			sql = "delete from price where product_name =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();

			sql = "delete from stock where product_name =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();

			sql = "delete from product where product_name =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();

			new Message("Deleted", "Product deleted. ");
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

	// set the price of a product
	public static void setPrice(String name, String category, String state, int price) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from price where product_name=? and product_category=? and state=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			pstmt.setString(3, state);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				sql = ("insert into price values(?,?,?,?)");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, category);
				pstmt.setString(3, state);
				pstmt.setInt(4, price);
				pstmt.executeUpdate();
			}

			else {
				sql = ("update price set price=? where product_name=? and product_category=? and state=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, price);
				pstmt.setString(2, name);
				pstmt.setString(3, category);
				pstmt.setString(4, state);
				pstmt.executeUpdate();
			}
			new Message("Updated","Price is updated. ");
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

	// add a product to the warehouse
	public static void addToWareHouse(String name, String category, int warehouseId, int newAmount) {
		try {
		
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from product where product_name=? and product_category=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, category);

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				new Message("Error", "Product not in the database, add it first.");
				return;
			}

			sql = ("select storage_capacity from warehouse where warehouse_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, warehouseId);
			rs = pstmt.executeQuery();
			int totalSize = 0;
			if (rs.next()) {
				totalSize = rs.getInt(1);
			}

			sql = ("select sizes from product where product_name=? and product_category=? ");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			rs = pstmt.executeQuery();
			int productSize = 0;
			if (rs.next()) {
				productSize = rs.getInt(1);
			}

			sql = ("select available_amount*sizes from stock natural join product where warehouse_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, warehouseId);
			rs = pstmt.executeQuery();
			int currentSize = 0;
			while (rs.next()) {
				currentSize = rs.getInt(1) + currentSize;
			}

			if (newAmount * productSize + currentSize > totalSize) {
				new Message("Error", "No enough space, start over. ");
				return;
			} else {

				sql = ("select count(*) from stock where product_name=? and product_category=? and warehouse_id =?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, category);
				pstmt.setInt(3, warehouseId);

				rs = pstmt.executeQuery();
				count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 0) {
					sql = ("insert into stock values(?,?,?,?)");
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, category);
					pstmt.setInt(3, warehouseId);
					pstmt.setInt(4, newAmount);
					pstmt.executeUpdate();

				} else {
					sql = ("update stock set available_amount=available_amount +? where product_name=? and product_category=? and warehouse_id=?");
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, newAmount);
					pstmt.setString(2, name);
					pstmt.setString(3, category);
					pstmt.setInt(4, warehouseId);
					pstmt.executeUpdate();
				}
				new Message("Added", "Added to warehouse successfully. ");
				
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
	}
}
