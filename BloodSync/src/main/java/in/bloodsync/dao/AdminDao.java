package in.bloodsync.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import in.bloodsync.dbutil.DBConnection;

public class AdminDao {
	public static Map<String,Integer> getAllBloodBankCount() throws SQLException{
		Connection conn=DBConnection.getConnection();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT COUNT(*) FROM blood_donors");
		Map<String,Integer> counts=new HashMap<>();
		if(rs.next()) {
			int totalDonors=rs.getInt(1);
			counts.put("totalDonors",totalDonors);
		}
		st=conn.createStatement();
		rs=st.executeQuery("SELECT COUNT(*) FROM blood_requests WHERE status='Pending'");
		if(rs.next()) {
			int totalRequests=rs.getInt(1);
			counts.put("totalRequests",totalRequests);
		}
		st=conn.createStatement();
		rs=st.executeQuery("SELECT SUM(available_units) FROM blood_stock");
		if(rs.next()) {
			int totalStock=rs.getInt(1);
			counts.put("totalStock",totalStock);
		}
		return counts;
	}

}
