package pl.edu.utp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JTableOperations {
	
	public static Connection getConnection() throws SQLException {  //utowrznie nowego po³¹czenia
		Connection con =DriverManager.getConnection("jdbc:hsqldb:file:base/test;ifexists=true;shutdown=true", "admin", "admin");
		return con;
	
	}
	
	/*
	 * Metoda tworz¹ca Nam listê samochodów, dane dotycz¹ce poszczególnego pojazdu bêd¹ pobierane
	 * z bazy danych ///
	 */
	public static ArrayList<Cars> carList() throws SQLException {
		ArrayList<Cars> carsList = new ArrayList<Cars>();
		Connection connection = getConnection();
		String query = "SELECT * FROM SAMOCHODY";
		Statement st;
		ResultSet rs;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Cars cars;
			while(rs.next()) {
				cars = new Cars(rs.getInt("SAMOCHOD_ID"),rs.getString("MARKA"),rs.getString("MODEL"),rs.getInt("CENA"),rs.getString("TYP_NADWOZIA"),
						rs.getString("KOLOR"),rs.getTimestamp("DATA_CZAS_UTWORZENIA"));
				carsList.add(cars);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return carsList;
		
	}
	
	
	public static void showDataInJTable() throws SQLException {
		ArrayList<Cars> carListTwo = carList();
		DefaultTableModel model = (DefaultTableModel)Frame.CarsTable.getModel();
		Object[] row = new Object[7];
		for(int i =0; i < carListTwo.size(); i++) {
			row[0] = carListTwo.get(i).getId();
			row[1] = carListTwo.get(i).getMarka();
			row[2] = carListTwo.get(i).getModel();
			row[3] = carListTwo.get(i).getCena();
			row[4] = carListTwo.get(i).getTypNadwozia();
			row[5] = carListTwo.get(i).getKolor();
			row[6] = carListTwo.get(i).getCzas();
			model.addRow(row);
			System.out.println(i);
		}
		
	}
	
		

	}
		
		

	
	
	


