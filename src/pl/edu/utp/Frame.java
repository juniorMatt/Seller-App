package pl.edu.utp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Frame {

	private JFrame MainFrame;
	public static JTable CarsTable = new JTable();
	private static JTextField textFieldMarka;
	private static JTextField textFieldModel;
	private static JTextField textFieldCena;
	private static JTextField textFieldTypNadwozia;
	private static JTextField textFieldKolor;
	static DefaultTableModel model;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.MainFrame.setVisible(true);
					JTableOperations jo = new JTableOperations();
					jo.getConnection();
					jo.showDataInJTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	


	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Frame() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		
		MainFrame = new JFrame("Seller App");
		BorderLayout borderLayout = (BorderLayout) MainFrame.getContentPane().getLayout();
		MainFrame.setBounds(100, 100, 879, 627);
		MainFrame.setAlwaysOnTop(false);
		MainFrame.setLocationRelativeTo(null); //wyœrodkowanie pozycji okna g³ównego naszej aplikacji
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		MainFrame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btno_Mnie = new JButton();
		btno_Mnie.setHorizontalAlignment(SwingConstants.RIGHT);
		btno_Mnie.setIcon(new ImageIcon("C:\\Users\\mdlen\\eclipse-workspace\\SellerApp\\resources\\questionIcon24.png"));
		btno_Mnie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btno_Mnie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon icon = new ImageIcon("C:\\Users\\mdlen\\eclipse-workspace\\SellerApp\\resources\\logo.jpg"); 
				JOptionPane.showMessageDialog(null,"Projekt wykona³ : Mateusz Didyk "," O Projekcie :", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		panel.add(btno_Mnie);
		
		JScrollPane scrollPane = new JScrollPane();
		MainFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		CarsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {   //dodanie obs³ugi klikniêcia myszk¹ oraz mo¿liwoœæ wyœwietlenia zawartoœci zanaczonego wiersza w polach tekstowych
				int i = CarsTable.getSelectedRow();
				model = (DefaultTableModel) CarsTable.getModel();
				textFieldMarka.setText(model.getValueAt(i, 1).toString());
				textFieldModel.setText(model.getValueAt(i, 2).toString());
				textFieldCena.setText(model.getValueAt(i, 3).toString());
				textFieldTypNadwozia.setText(model.getValueAt(i, 4).toString());
				textFieldKolor.setText(model.getValueAt(i, 5).toString());
			}
		});
		
		//CarsTable = new JTable(); // utworznie modelu tabeli
		CarsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SAMOCHOD_ID", "MARKA", "MODEL", "CENA", "TYP_NADWOZIA", "KOLOR", "DATA_CZAS_UTWORZENIA"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		CarsTable.getColumnModel().getColumn(0).setPreferredWidth(107);
		CarsTable.getColumnModel().getColumn(1).setPreferredWidth(84);
		CarsTable.getColumnModel().getColumn(2).setPreferredWidth(86);
		CarsTable.getColumnModel().getColumn(3).setPreferredWidth(82);
		CarsTable.getColumnModel().getColumn(4).setPreferredWidth(113);
		CarsTable.getColumnModel().getColumn(5).setPreferredWidth(81);
		CarsTable.getColumnModel().getColumn(6).setPreferredWidth(166);
		
	
		
		
		
		scrollPane.setViewportView(CarsTable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		MainFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 25));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(30, 15, 15, 100));
		panel_1.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(5,2, -130, 5));
		
		JLabel lblMarka = new JLabel("Marka :");
		lblMarka.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblMarka);
		
		textFieldMarka = new JTextField();
		textFieldMarka.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textFieldMarka);
		textFieldMarka.setColumns(20);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblModel);
		
		textFieldModel = new JTextField();
		textFieldModel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textFieldModel);
		textFieldModel.setColumns(10);
		
		JLabel lblCena = new JLabel("Cena :");
		lblCena.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblCena);
		
		textFieldCena = new JTextField();
		textFieldCena.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textFieldCena);
		textFieldCena.setColumns(10);
		
		JLabel lblTypNadwozia = new JLabel("Typ nadwozia :");
		lblTypNadwozia.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblTypNadwozia);
		
		textFieldTypNadwozia = new JTextField();
		textFieldTypNadwozia.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textFieldTypNadwozia);
		textFieldTypNadwozia.setColumns(10);
		
		JLabel lblKolor = new JLabel("Kolor :");
		lblKolor.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblKolor);
		
		textFieldKolor = new JTextField();
		textFieldKolor.setColumns(10);
		textFieldKolor.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textFieldKolor);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(25);
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		/*
		 * Ob³usga przycisku dodaj; tworzymy po³¹czenie z baz¹ danych, wywo³ujemy metodê sprawdzaj¹ca czy przypadkiem ¿adne pole nie jest puste
		 * po czym tworzymy nasze zapytanie SQL'owe i tworzymy interfejs PreparedStatement
		 * umo¿liwiaj¹cy wykonywanie parametryzowanych operacji na bazie danych, parametryzowane
		 * pobierania i zapisywania danych.  Zostaje utworzony obiekt PreparedStatement odpowiadaj¹cy instrukcji SQL
		 * wstawiaj¹cej nowe wiersze do tabeli SAMOCHODY ze wszystkimi parametrami. Nastêpnie ustawiamy wartoœci
		 * parametrów za pomoc¹ w³aœciwych metod interfesju a mianowice pst.setString(), metody te ustawiaj¹ Nam wartoœci wszystkich parametrów
		 * instrukcji SQL'owej odpowiadaj¹cej obiektowi pst.
		 */
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDodaj.setIcon(new ImageIcon("C:\\Users\\mdlen\\eclipse-workspace\\SellerApp\\resources\\addIcon24.png"));
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				

				try {
					JTableOperations.getConnection();

					Connection connection = JTableOperations.getConnection();
					String query = "Insert into SAMOCHODY (MARKA, MODEL, CENA, TYP_NADWOZIA, KOLOR, DATA_CZAS_UTWORZENIA) values(?,?,?,?,?,now())";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldMarka.getText());
					pst.setString(2, textFieldModel.getText());
					pst.setString(3, textFieldCena.getText());
					pst.setString(4, textFieldTypNadwozia.getText());
					pst.setString(5, textFieldKolor.getText());

					//W celu pobrania ID do naszej tabeli bez ingerencji u¿ytkownika posi³kowa³em siê nasz¹ list¹ samochodów
					//gdzie w metodzie get() s³u¿¹cej do pobierania indexu pobra³em rozmiar naszej listy samochodów zmniejszony o 1 i pobra³em ID, które zostaje wrzucone
					//do kolumny SAMOCHOD_ID;
					pst.execute();
					JOptionPane.showMessageDialog(null, "data saved");
					model = (DefaultTableModel)Frame.CarsTable.getModel();
					model.addRow(new Object [] {JTableOperations.carList().get(JTableOperations.carList().size()-1).getId(),(textFieldMarka.getText()), textFieldModel.getText(),
							Integer.parseInt(textFieldCena.getText()), textFieldTypNadwozia.getText(), textFieldKolor.getText(),g()});
					pst.close();
					
					
			          }
				catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		panel_3.add(btnDodaj);
		
		JButton btnAktualizuj = new JButton("Aktualizuj");
		btnAktualizuj.setIcon(new ImageIcon("C:\\Users\\mdlen\\eclipse-workspace\\SellerApp\\resources\\updateIcon24.png"));
		btnAktualizuj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAktualizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_3.add(btnAktualizuj);
		
		JButton btnUsun = new JButton("Usu\u0144");
		btnUsun.setIcon(new ImageIcon("C:\\Users\\mdlen\\eclipse-workspace\\SellerApp\\resources\\deleteIcon24.png"));
		btnUsun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteChoice();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_3.add(btnUsun);
	}
	
	/*
	 * Metoda tworz¹ca obiekt Date oraz Timestamp ; metoda umo¿liwi nam dodanie
	 * do kolumny DATA_CZAS_UTWORZENIA czasu i daty dodania obiektu w postaci samochodu ³¹cznie ze wszystkimi jego atrybutami
	 */
	
	 public static Object g() {  
	 Date date= new Date();
	 long time = date.getTime();
	 
	 Timestamp ts = new Timestamp(time);
	 System.out.println("Current Time Stamp: " + ts);
	return ts;
	    }
	 
	 

	 
	 public static void warningInfo() {
		 JOptionPane.showMessageDialog(null,"Wprowadz dane do wszystkich pól tekstowych !"); 
	 }
	 
	 
	 /*
	  * Metoda aktualizuj¹ca nasz¹ bazê danych ; aktualizuje równie¿ tabelê w naszym programie od razu po zmianie danego atrybutu
	  */
	 public static void updateData() throws SQLException {  
		 Connection connection = JTableOperations.getConnection();
		 int row = CarsTable.getSelectedRow();
		 JTableOperations.carList().get(0).getId();
		 String query = "UPDATE SAMOCHODY SET MARKA=?, MODEL=?, CENA=?, TYP_NADWOZIA=?, KOLOR=? WHERE SAMOCHOD_ID ="+JTableOperations.carList().get(row).getId();
			model = (DefaultTableModel)Frame.CarsTable.getModel();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, textFieldMarka.getText());
			pst.setString(2, textFieldModel.getText());
			pst.setString(3, textFieldCena.getText());
			pst.setString(4, textFieldTypNadwozia.getText());
			pst.setString(5, textFieldKolor.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "data updated !");
			if (row >= 0) {
				model.setValueAt(textFieldMarka.getText(), row, 1);
				model.setValueAt(textFieldModel.getText(), row, 2);
				model.setValueAt(textFieldCena.getText(), row, 3);
				model.setValueAt(textFieldTypNadwozia.getText(), row, 4);
				model.setValueAt(textFieldKolor.getText(), row, 5);
	 }
			else {
				
			}
	 }
	 
	 //metoda przypisana do przycisku USUÑ;
	 public void deleteChoice() throws SQLException {
		    int response = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usun¹æ ?", "Usuwanie",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      System.out.println("No button clicked");
		    } else if (response == JOptionPane.YES_OPTION) {
		    	deleteData();
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      System.out.println("JOptionPane closed");
		    }
		  }
	 
	 /*
	  * Metoda usuwaj¹ca nam wybrany samochód z bazy danych a takze aktualizuje nasz¹ tabele w programie ; wywo³ujemy j¹ w metodzie deleteChoice();
	  */
	 
	 public void deleteData() throws SQLException {
		 Connection connection = JTableOperations.getConnection();
		 int row = CarsTable.getSelectedRow();
		 String query = "DELETE FROM SAMOCHODY WHERE SAMOCHOD_ID ="+JTableOperations.carList().get(row).getId();
			model = (DefaultTableModel)Frame.CarsTable.getModel();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			JOptionPane.showMessageDialog(null, "pomyœlnie usuniêto !");
			if (row >= 0) {
				model.removeRow(row);
	 }
			else {
				
			}
	 }
	 
	 
	 

	

}
