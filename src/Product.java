package cs425;

import java.util.Scanner;

import javax.swing.JTable;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Product {
	static Scanner sc = new Scanner(System.in);
	static final String url = "jdbc:postgresql://localhost/Grocery";
	static final String user = "postgres";
	static final String password = "1234";
	static public Object[][] productList;
	public static JTable productTable;
	static public Object[][] searchList;
	public static JTable searchTable;
	
	//search product
	public static void search(String productName, String productCate) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from stock where product_name=? and product_category=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			pstmt.setString(2, productCate);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				NoResult nr = new NoResult();
			} else {
				
				sql = ("select * " + "from stock " + "where product_name=? and product_category=?");

				pstmt = conn.prepareStatement(sql);

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productName);
				pstmt.setString(2, productCate);
				
				
				rs = pstmt.executeQuery();
				int num = 0;
				if (rs.next()) {
					num = rs.getInt(4);
				}
				searchList = new Object[1][1];
				
				searchList[0][0] = "Product: " + productName + " Available amount: " + num;
				
				Object[] columnNames = { "Matched product" };
				Object[][] data = searchList;
				searchTable = new JTable(data, columnNames);
				SearchList searchList = new SearchList();
			}

			conn.close();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception:" + e);
			}
		}

		catch (SQLException e) {
			System.out.println("Exception:" + e);
		}
	}

	//list all the product
	public static void browse() {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stat = conn.createStatement();
			
			
			String sql = ("select count(*) " + "from product ");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			productList = new Object[count][1];
			
			sql = ("select product_name, product_category " + "from product " + "order by product_category");

			rs = stat.executeQuery(sql);

			int walk = 0;
			while (rs.next()) {
				productList[walk][0] = rs.getString("product_name") + " " + rs.getString("product_category");
				walk++;
			}
			
			Object[] columnNames = { "Available product" };
			Object[][] data = productList;
			productTable = new JTable(data, columnNames);
			ProductList productList = new ProductList();

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

	//if the product is a drink, ask staff to input alcoholic content
	public static void setDrink(String name, int alcohol) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stat = conn.createStatement();

			String sql = ("select count(*) from alcoholic where product_name=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				sql = ("insert into alcoholic values(?,'drink',?)");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setInt(2, alcohol);
				pstmt.executeUpdate();
			} else {
				sql = ("update alcoholic set alcohol_content=? where product_name=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, alcohol);
				pstmt.setString(2, name);
				pstmt.executeUpdate();
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

	//if the product is food, ask staff to input calories
	public static void setNutrition(String name, int calories) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stat = conn.createStatement();

			String sql = ("select count(*) from nutrition where product_name=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				sql = ("insert into nutrition values(?,'food',?)");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setInt(2, calories);
				pstmt.executeUpdate();
			} else {
				sql = ("update nutrition set alcohol_content=? where product_name=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, calories);
				pstmt.setString(2, name);
				pstmt.executeUpdate();
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
