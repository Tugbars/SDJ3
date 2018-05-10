
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.mysql.*;
import com.mysql.cj.xdevapi.Statement;

public class MyServer {
	

	private static Connection con;
	private static java.sql.Statement state;
	public static ArrayList<Car> cars;
	public static ArrayList<Part> parts;
	public static ArrayList<Pallet> pallets;
	
	public MyServer()
	{
	cars = new ArrayList<Car>();
	parts = new ArrayList<Part>();
	pallets = new ArrayList<Pallet>();
	}
	
	
	public static Connection getConnection() throws Exception
	{
		 state = null;
		try
		{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/students?useSSL=false";
			String username = "Tugbars";
			String password = "Th662452";
			Class.forName(driver);
			
	         con = DriverManager.getConnection(url, username, password);
			System.out.println("connected");
			
			

			return con;
		}
		catch (Exception e) { 
		System.out.println(e); }
	return null;
	}
	
	public static void CreateDatabase() throws SQLException
	{
	      System.out.println("Creating database...");
	      state = con.createStatement();
	      
	      String sql = "CREATE DATABASE STUDENTS";
	      state.executeUpdate(sql);
	      System.out.println("Database created successfully...");
	}
	
	public static void createCarTable() throws SQLException
	{
	state = con.createStatement();
	
	String sql = "CREATE TABLE CARTABLE " +
            "(CHASSIS_ID INTEGER not NULL, " +
            " MODEL VARCHAR(255), " + 
            " WEIGHT INTEGER not NULL, " + //primarykey'den önce virgül var. 
            " PRIMARY KEY (CHASSIS_ID))"; 

	state.executeUpdate(sql);	
	}

	
	public static void InsertCarData(int i, String model,  int weight) throws SQLException
	{

		String query = "INSERT INTO CARTABLE(CHASSIS_ID, MODEL, WEIGHT)" + "values(?, ?, ?)"
		 	+	"ON DUPLICATE KEY UPDATE CHASSIS_ID = CHASSIS_ID + 1";
		
		
		//statement is executed regardless what "on duplicate says"
		PreparedStatement statement = con.prepareStatement(query);
		statement.setInt(1, i);
		statement.setString(2 ,model); 
		statement.setInt(3, weight);
		
		statement.execute();
	}


	public static void createPartsTable() throws SQLException
	{
	state = con.createStatement();
	
	String sql = "CREATE TABLE PARTS " +
            "(part_name VARCHAR(50), " +
            " part_number VARCHAR(50),  " + 
            " CHASSIS_ID INTEGER(50),  " + 
            " FOREIGN KEY (CHASSIS_ID) REFERENCES CARTABLE(CHASSIS_ID), " + 
            " PRIMARY KEY (part_number))"; 

	state.executeUpdate(sql);	
	}
	


	
	public static void packageTable() throws SQLException
	{
	state = con.createStatement();
	
	String sql = "CREATE TABLE PARTS " +
            "(part_name VARCHAR(50), " +
            " part_number VARCHAR(50),  " + 
            " CHASSIS_ID INTEGER(50),  " + 
            " FOREIGN KEY (CHASSIS_ID) REFERENCES CARTABLE(CHASSIS_ID), " + 
            " PRIMARY KEY (part_number))"; 

	state.executeUpdate(sql);	
	}
	
	
	public static void InsertPartData(String part_name, int part_number, int CarID, String databasename) throws SQLException //quantity should be added later. 
	{
		//FOREIGN KEY (chassis_nr) REFERENCES car(chassis_nr),
		
		String query2 = "INSERT INTO PARTS(PART_NAME, PART_NUMBER, CHASSIS_ID)" + "values(?, ?, ?)" 
			+	"ON DUPLICATE KEY UPDATE part_number = part_number + 1";
		//burasý çok try hard olacak asýl . 
		

		String query =
		        "select CHASSIS_ID " +
		        "from " + databasename  + ".CARTABLE WHERE CHASSIS_ID = " + CarID;
		//next eklemem gerekiyor. 
		try {
		 state = con.createStatement();
		 ResultSet rs = state.executeQuery(query);
		
		 rs.next();
		System.out.println(rs.getInt("CHASSIS_ID"));

		 int chassis_nr = rs.getInt("CHASSIS_ID");
		 System.out.println(chassis_nr);
		 
		 //sorun burada
		 PreparedStatement statement = con.prepareStatement(query2);
			statement.setString(1,  part_name);
			statement.setInt(2, part_number);
			statement.setInt(3,  chassis_nr);
			statement.execute();
		}
		catch (SQLException e ) {
	        System.out.println(e);
	    } finally {
	        if (state != null) 
	        { state.close(); }
	    }

	}
	
	
	
	public static void createPalletTable() throws SQLException
	{
	state = con.createStatement();
	
	String sql = "CREATE TABLE PALLETS " +
            " (pallet_nr INTEGER PRIMARY KEY auto_increment, " +
            "  max_weight_cap INTEGER, " + 
            "  type_of_parts VARCHAR(50) " + 
            ") "; 

	state.executeUpdate(sql);	
	}
	
	public static void insertPalletData(int pallet_nr, int max_weight_cap, String type_of_parts) throws SQLException
	{
		String query = "INSERT INTO PALLETS(PALLET_NR, MAX_WEIGHT_CAP, TYPE_OF_PARTS)" + "values(?, ?, ?)";
		
		PreparedStatement statement = con.prepareStatement(query);
		statement.setInt(1, pallet_nr);
		statement.setInt(2, max_weight_cap);
		statement.setNString(3 , type_of_parts); 
		statement.execute();
	}

	public static void retrieveCarData(String databasename) throws SQLException
	{
		 String query =
			        "select CHASSIS_ID, MODEL, WEIGHT " +
			        "from " + databasename  + ".CARTABLE";
		
		try {
			state = con.createStatement();
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				int ID = rs.getInt("CHASSIS_ID");
				String model = rs.getString("MODEL");
				int weight = rs.getInt("WEIGHT");
		
			}
		}
		catch (SQLException e ) {
	        System.out.println(e);
	    } finally {
	        if (state != null) 
	        { state.close(); }
	    }
		
	} 
	
	public static void getWeightofPart()
	{
		
	}
	
	

	
	public static void getCarArrayList(String databasename) throws SQLException {
		
		String query =
		        "select CHASSIS_ID, MODEL, WEIGHT " +
		        "from " + databasename  + ".CARTABLE";
	
	try {
		state = con.createStatement();
		ResultSet rs = state.executeQuery(query);
		while(rs.next())
		{
			int ID = rs.getInt("CHASSIS_ID");
			String model = rs.getString("MODEL");
			int weight = rs.getInt("WEIGHT");
	
			Car car = new Car(ID, model, weight);
			cars.add(car);
		}
	}
	catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (state != null) 
        { state.close(); }
    }

		
	}
	
 //part'larýn da pallet ID'yi içinde tutmasý mümkün. 
	// InsertPartData(String part_name, int part_number, int CarID, String databasename) 
	public static void getPartsArrayList(String databasename) throws SQLException
	{
		
		String query =
		        "select PART_NAME, PART_NUMBER, CarID " +
		        "from " + databasename  + ".CARTABLE";
	
	try {
		state = con.createStatement();
		ResultSet rs = state.executeQuery(query);
		while(rs.next())
		{
			
			String part_name = rs.getString("PART_NAME");
			int part_number = rs.getInt("PART_NUMBER");
			int  carid = rs.getInt("CarID");
			
			Part newpart = new Part(part_name, part_number, carid);
			parts.add(newpart);
	
		}
	}
	catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (state != null) 
        { state.close(); }
    }
		
		
	}
	
//	public static void getTotalWeightofPallet(Pallet pallet, String databasename)
//	{
//		String query =
//		        "select PALLET_NR, MAX_WEIGHT_CAP,TYPE_OF_PARTS" +
//		        "from " + databasename  + ".CARTABLE";
//		
//		//part'lara gidip 
//		
//		
//	}
//	
	
//	int pallet_nr, int max_weight_cap, String type_of_parts) 
	public static void getPalletArrayList(String databasename) throws SQLException
	{
		
		String query =
		        "select PALLET_NR, MAX_WEIGHT_CAP, TYPE_OF_PARTS " +
		        "from " + databasename  + ".CARTABLE";
	
	try {
		state = con.createStatement();
		ResultSet rs = state.executeQuery(query);
		while(rs.next())
		{
			int palletnr = rs.getInt("CHASSIS_ID");
			String parts = rs.getString("TYPE_OF_PARTS");
			int maxweight = rs.getInt("MAX_WEIGHT_CAP");
			
			Pallet newpallet = new Pallet();
			pallets.add(newpallet);
	
		}
	}
	catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (state != null) 
        { state.close(); }
    }
	
		
	}


	
	
	
//	public static void 
	
	public static void main(String[] args) throws Exception
	{
		getConnection();
//		createCarTable();
//		 InsertCarData(17, "Ferrari", 1450);
//		 InsertCarData();
//		 InsertCarData();
//		 InsertCarData();
//		 InsertCarData(17, "Ferrari", 1450);
//		 InsertCarData(9, "Aston Martin", 1850);
//		 InsertCarData(14, "Ferrari", 1450);
// createPartsTable();
 
 //parteklemeye baþlaayabiliriz. 
//		InsertPartData(String part_name, int part_number, int CarID)

		
//		retrieveData("students");
//		createPalletTable();
   InsertPartData("wheel", 15, 9, "students");
   InsertPartData("wheel", 15, 9, "students");
   InsertPartData("wheel", 15, 9, "students");

 
	}

}

