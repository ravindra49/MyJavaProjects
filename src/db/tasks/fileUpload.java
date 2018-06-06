package db.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class fileUpload {
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		String employeeId;
	   String name;
	   String salary;
	   String location;
	   String company;
		
	    PreparedStatement ps = null;
	    Connection con = null;
	    ResultSet rs = null;

	    try
	    {
	        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ravindra\\Desktop\\JavaMaterial\\flatfiles\\empdata.log"));
	        String username = "system";
	        String pwd = "123456";
	        String connurl = "jdbc:oracle:thin:@localhost:1521:xe";

	        con = DriverManager.getConnection(connurl, username, pwd);
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        String line = null;
	        while((line=br.readLine()) != null){
	     
	        	if (line != null && !line.equals("") ){
	          String tmp[] = line.split("\\|");
	            employeeId = tmp[0];
	            name = tmp[4];
	            company = tmp[1];
	            salary = tmp[2];
	            location = tmp[3];
	            System.out.print(employeeId+" ");
	            System.out.print(company+" ");
	            System.out.print(salary+" ");
	            System.out.print(location+" ");
	            System.out.print(name+" ");

	         //   System.out.println(date + "\t" + heure + "\t" + parametre + "\t" + valeur);
	            String sql ="insert into empdet values(?,?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, employeeId);
	            ps.setString(2, company);
	            ps.setString(3, salary);
	            ps.setString(4, location);
	            ps.setString(5, name);
	            ps.executeUpdate();  
	        		
	        	}
	        	System.out.println(" ");
	        }

	        br.close();
	        con.close();
	        ps.close();

	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}
}
