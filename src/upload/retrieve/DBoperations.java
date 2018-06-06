package upload.retrieve;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBoperations {

	public static void InsertData(String id, String company, String salary, String location, String name) {
		try {
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
				PreparedStatement ps = conn.prepareStatement("insert into empdet values(?,?,?,?,?)");
				ps.setString(1, id);
				ps.setString(2, company);
				ps.setString(3, salary);
				ps.setString(4, location);
				ps.setString(5, name);
				int l=ps.executeUpdate();
				if (l>0) {
					System.out.println(id+" Inserted Successfully");
				}
				else {
					System.out.println(id+" Failed to Insert");
				}
		} catch (Exception e) {
			
		}
		
	}

	public static ResultSet GetData() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			Statement st=conn.createStatement();
			ResultSet rs = st.executeQuery("select * from empdet");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
