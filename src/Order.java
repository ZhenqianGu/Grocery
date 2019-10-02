package cs425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import javax.swing.JTable;

public class Order {
	static Scanner sc = new Scanner(System.in);
	static final String url = "jdbc:postgresql://localhost/Grocery";
	static final String user = "postgres";
	static final String password = "1234";
	static public Object[][] cartList;
	public static JTable cartTable;

	public static void addToCart(int id, String productName, String productCate, int quantity) {
		try {

			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = ("select count(*) from shoppingcart where product_name=? and product_category=? and customer_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			pstmt.setString(2, productCate);
			pstmt.setInt(3, id);
			ResultSet rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				sql = "insert into shoppingcart values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2, productName);
				pstmt.setString(3, productCate);
				pstmt.setInt(4, quantity);
				pstmt.executeUpdate();
				Added added = new Added();
			} else {
				sql = "update shoppingcart set quantity=quantity+? where product_name=? and product_category=? and customer_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, quantity);
				pstmt.setString(2, productName);
				pstmt.setString(3, productCate);
				pstmt.setInt(4, id);
				pstmt.executeUpdate();
				CartUpdated cu = new CartUpdated();
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

	// check if the cart is ready to place an order, e.g. the address, credit card,
	// and product in the cart
	public static boolean cartCheck(int id) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from creditcard where customer_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				System.out.println("No credit card stored. ");
				return false;
			}

			sql = ("select count(*) from shipping_address where customer_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 0) {
				System.out.println("No address available");
				return false;
			}

			sql = ("select count(*) from shoppingcart where customer_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				System.out.println("Cart is empty");
				return false;
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
		return true;
	}

	// print the products in the cart
	public static boolean getCart(int id) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = ("select count(*) from shoppingcart where customer_id=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				CartEmpty ce = new CartEmpty();
				return false;
			}

			else if (count != 0) {
				sql = ("select state from shipping_address where customer_id=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();

				String state = null;

				if (rs.next()) {
					state = rs.getString(1);
				}

				sql = ("select product_name, product_category, quantity, price from shoppingcart natural join price where state=? and customer_id=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, state);
				pstmt.setInt(2, id);
				rs = pstmt.executeQuery();

				cartList = new Object[count + 1][1];
				int walk = 0;
				while (rs.next()) {
					cartList[walk][0] = rs.getString(1);
					walk++;
				}

				sql = ("select quantity*price from price natural join shoppingcart where state =? and customer_id=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, state);
				pstmt.setInt(2, id);
				rs = pstmt.executeQuery();
				int total = 0;
				while (rs.next()) {
					total = rs.getInt(1) + total;
				}
				cartList[walk][0] = "\n" + "Subtotal: $" + total;

				Object[] columnNames = { "Products in the cart" };
				Object[][] data = cartList;
				cartTable = new JTable(data, columnNames);
				CartList cartList = new CartList();
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
		return true;
	}

	// delete item in the cart
	public static void delete(int id, String productName, String productCategory) {
		try {

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("select count(*) from shoppingcart where product_name=? and product_category=? and customer_id=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			pstmt.setString(2, productCategory);
			pstmt.setInt(3, id);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count != 0) {
				sql = "delete from shoppingcart where product_name=? and product_category=? and customer_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productName);
				pstmt.setString(2, productCategory);
				pstmt.setInt(3, id);
				pstmt.executeUpdate();
				CartDeleted cd = new CartDeleted();
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

	// check if the products are available in the stock
//	public static boolean checkAvailable(int id) {
//
//		try {
//			Connection conn = DriverManager.getConnection(url, user, password);
//			String sql = ("Select state from shipping_address where customer_id=?");
//
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			ResultSet rs = pstmt.executeQuery();
//			String state = null;
//			while (rs.next()) {
//				state = rs.getString(1);
//			}
//
//			sql = ("select sum(available_amount), product_name, product_category, quantity "
//					+ "from address natural join warehouse natural join stock natural join shoppingcart "
//					+ "where state =? and customer_id=? group by product_name, product_category, quantity");
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, state);
//			pstmt.setInt(2, id);
//			rs = pstmt.executeQuery();
//			int num = 0;
//			while (rs.next()) {
//				num = rs.getInt(1) - rs.getInt(4);
//				if (num < 0) {
//					System.out.println("Not enough amount of product in store: " + rs.getString("product_name"));
//					return false;
//				}
//			}
//
//			conn.close();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				System.out.println("Exception:" + e);
//			}
//		} catch (SQLException e) {
//			System.out.println("Exception:" + e);
//		}
//		return true;
//	}

	// place an order
	public static void placeOrder(int id) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = ("Select state " + "from shipping_address " + "where customer_id=?");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			String state = null;
			while (rs.next()) {
				state = rs.getString(1);
			}

			sql = ("select quantity*price " + "from price natural join shoppingcart "
					+ "where state =? and customer_id=?");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();

			int totalamt = 0;
			while (rs.next()) {
				totalamt += rs.getInt(1);
			}

			String content = "Total amount: $" + totalamt;

			Message message = new Message("Amount", content);

			sql = ("update customer set balance = ? where customer_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, totalamt);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

			sql = ("select card_number from creditcard where customer_id=?");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			
			PlaceOrder po = new PlaceOrder(id, "Enter a card number to pay ", totalamt, state);
			
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

	public static void placeOrderCont(int id, long creditCard, int totalamt, String state) {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);		

			
			String sql = ("select count(*) from creditcard where card_number=?");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, creditCard);

			ResultSet rs = pstmt.executeQuery();

			boolean cardValid = false;
			
			while (rs.next()) {
				if (rs.getInt(1) == 0)
					new Message("Wront Input", "Wrong input, enter again. ");
				else
					cardValid = true;
			}

			if (cardValid) {
				sql = ("select street_number, street_name, apt_number, zipcode " + "from shipping_address where "
						+ "customer_id=?");

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
			}

			int orderNum = 0;
			Statement stat = conn.createStatement();
			rs = stat.executeQuery("select max(order_number) from orders");
			while (rs.next()) {
				orderNum = rs.getInt(1) + 1;
			}

			sql = ("Insert into orders values(?,?,?,?,?,'issued')");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.setInt(2, id);
			pstmt.setLong(3, creditCard);
			pstmt.setInt(4, totalamt);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(5, time);
			pstmt.executeUpdate();

			// update shopping cart, delete everything
			sql = ("select count(*) from shoppingcart where customer_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			int num = 0;
			while (rs.next()) {
				num = rs.getInt(1);
			}
			sql = ("select product_name, product_category, quantity from shoppingcart where customer_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			int[] quantity = new int[num];
			String[] productName = new String[num], productCate = new String[num];

			int i = 0;
			while (rs.next()) {
				productName[i] = rs.getString(1);
				productCate[i] = rs.getString(2);
				quantity[i] = rs.getInt(3);
				i++;
			}

			sql = ("select count(*) from warehouse natural join address where state = ?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			rs = pstmt.executeQuery();
			int warehouseCount = 0;
			while (rs.next()) {
				warehouseCount = rs.getInt(1);
			}

			sql = ("select warehouse_id from warehouse natural join address where state = ? order by warehouse_id");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			rs = pstmt.executeQuery();
			int[] warehouseId = new int[warehouseCount];
			i = 0;
			while (rs.next()) {
				warehouseId[i] = rs.getInt(1);
				i++;
			}
			for (i = 0; i < num; i++) {

				int[] available_amount = new int[warehouseCount];
				for (int j = 0; j < warehouseCount; j++) {
					
					sql = ("select available_amount "
							+ "from warehouse natural join stock natural join address "
							+ "where state =? and product_name=? and product_category=? and warehouse_id=?");

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, state);
					pstmt.setString(2, productName[i]);
					pstmt.setString(3, productCate[i]);
					pstmt.setInt(4, warehouseId[j]);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						available_amount[j] = rs.getInt(1);
					}
					
					if (available_amount[j] >= quantity[i]) {
						sql = ("update stock set available_amount = available_amount -? "
								+ "where product_name=? and product_category=? and warehouse_id=?");
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, quantity[i]);
						pstmt.setString(2, productName[i]);
						pstmt.setString(3, productCate[i]);
						pstmt.setInt(4, warehouseId[j]);
						pstmt.executeUpdate();
						quantity[i] = 0;
						break;

					}

					else {
						sql = ("update stock set available_amount = 0 where product_name=? and product_category=? and warehouse_id=?");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, productName[i]);
						pstmt.setString(2, productCate[i]);
						pstmt.setInt(3, warehouseId[j]);
						pstmt.executeUpdate();
						quantity[i] = quantity[i] - available_amount[j];
					}
				}
				
				//test if there are enough amount of products that can fulfil the order
				if(quantity[i] > 0) {
					new Message("Insufficient in stock", "Not enough of " + productName[i] + " in stock, the delivery will be delayed. \n");
				}
			}

			sql = ("delete from shoppingcart where customer_id=?");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			Message m = new Message("Success", "Order placed successfully. ");

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
