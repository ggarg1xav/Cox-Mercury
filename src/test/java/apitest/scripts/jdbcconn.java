package apitest.scripts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class jdbcconn {
	
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@//10.5.3.205:1521/MERCURY";
	private static final String DB_USER = "mercury";
	private static final String DB_PASSWORD = "mercury123";

	public static void main(String[] argv) {

	/*	try {

			callOracleStoredProcCURSORParameter();

		} catch (SQLException e) {

			e.printStackTrace();
		}*/
		
		int x = 1;
		int z = 0 ,m = 1;
		for (int i  = 1 ; i < 99 ; i ++)
		{
			while (z > 0 ){
			z = i  / 10;
			m = i%10;
			}
			
		}

	}

	private static void callOracleStoredProcCURSORParameter()
			throws SQLException {

		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;

		String getDBUSERCursorSql = "{call PROC_VIEW_DETAILS( '1200004',   '1400004',   NULL,   NULL,   NULL,   '1100001',   '1',   ?,?)}";

		try {
			dbConnection = getDBConnection();
			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);

			callableStatement.setNull(1, OracleTypes.VARCHAR);
			callableStatement.registerOutParameter (2, OracleTypes.CURSOR);

			// execute getDBUSERCursor store procedure
			callableStatement.execute();

			 
			
			// get cursor and cast it to ResultSet
			rs = (ResultSet) callableStatement.getObject(1);

			 ResultSetMetaData rsmd = rs.getMetaData();
			 System.out.println(rsmd.getColumnCount());
			 int size = 0;
			 
			 System.out.println(rsmd.getColumnName(1));
			 
			 
			    while(rs.next())
			    	size ++;
			   
			 System.out.println(size);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
				DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}


}
