package upload.retrieve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.Scanner;

public class samle {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Upload Flat File\n2.Generate Flat File\nEnter your Choice");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter file path");
			String sPath=sc.next();
			ReadLastFile(sPath);
			break;
		case 2:GenerateFlatFile();break;
		default:System.out.println("Wrong Input");
			break;
		}
	}

	private static void GenerateFlatFile() {
		try {
			String OutPath="C:\\Users\\Ravindra\\Desktop\\JavaMaterial\\flatfiles\\empDetails.txt";
			int rows=0;
			ResultSet rs=DBoperations.GetData();
			File f1=new File(OutPath);
			if (!f1.exists()) {
				f1.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(OutPath);
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("E.Id|E.Name|E.Company|E.Salary|E.Location");
			bw.newLine();
			while (rs.next()) {
				bw.write(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
				bw.newLine();
				rows++;
			}
			bw.close();
			System.out.println(rows+" Rows Exported Successfully...!");
			System.out.println("Please find the file under path:"+OutPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void ReadLastFile(String fPath) {
		String line="";
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(fPath));
			while ((line=br.readLine())!=null) {
				String[] data=line.split("\\|");
				DBoperations.InsertData(data[0],data[1],data[2],data[3],data[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(br !=null)
			{
				try {
					br.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		
	}

}
