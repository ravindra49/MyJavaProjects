package db.tasks;

import java.io.*;
import java.sql.*;
import java.util.*;
public class fileRetrieve {
    public static void main(String[] args) {
    	
    		
            List data = new ArrayList();
            try {
                    Connection con = null;
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("Select * from empdet");

                    while (rs.next()) {
                            String eid = rs.getString("id");
                            String ecompany = rs.getString("company");
                            String esal = rs.getString("salary");
                            String elocation = rs.getString("location");
                            String ename = rs.getString("name");
                            data.add(eid +" "+ename+ " " + ecompany + " " + esal + " " + elocation);

                    }
                    writeToFile(data, "C:\\Users\\Ravindra\\Desktop\\JavaMaterial\\flatfiles\\Employee.txt");
                    rs.close();
                    st.close();
            } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                    
            }
    }

    private static void writeToFile(java.util.List list, String path) {
            BufferedWriter out = null;
            try {
                    File file = new File(path);
                    out = new BufferedWriter(new FileWriter(file, true));
                    for (Object s : list) {
                            out.write((String)s);
                            out.newLine();

                    }
                    out.close();
            } catch (IOException e) {
            }
    }
}