package apitest.scripts;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

import org.testng.annotations.Test;

import com.xavient.util.ExcelCache;
public class test_api_View1 {
	
	@Test
	public void testing_api()
	{
		
		String APIs = ExcelCache.getExpectedData("Test_View1", "API");
		
		
	/*Response	response  =  given()
      .body(APIs).contentType("application/json").when().
        post(Properties_Reader.readProperty("API_URL")).then().extract().response();
	
System.out.println(response.asString());*/
		
	}
	@Test
	public void connect_oracle()
	{
		
		
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@//10.5.3.205:1521/MERCURY","mercury","mercury");  
			  
			//step3 create the statement object  
			//Statement stmt=con.createStatement();  
			 CallableStatement stmt =       con.prepareCall ("{ ? = call java_refcursor.PROC_VIEW_DETAILS (?)}");
			 stmt.setString(1, "1200004");
			 stmt.setString(2, "1400004");
			 stmt.setString(3, "NULL");
			 stmt.setString(4, "NULL");
			 stmt.setString(5, "NULL");
			 stmt.setString(6, "1100001");
			 stmt.setString(7, "1");
			 stmt.setString(8, "NULL");
			 stmt.registerOutParameter (9, OracleTypes.CURSOR);
stmt.execute();
			 
			//step4 execute query  
ResultSet resultSet = (ResultSet)stmt.getObject (1);
			/*while(rs.next())  
			System.out.println(rs.getNString(1)); */
		

			while (resultSet.next()) {
			  System.out.println(resultSet.getInt(1));}
			  
			//step5 close the connection object  
			con.close();  
			  
			}catch(Exception e){ System.out.println(e);}  
			  
			}  
	}


