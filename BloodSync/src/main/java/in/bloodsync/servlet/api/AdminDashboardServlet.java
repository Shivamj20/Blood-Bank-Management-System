package in.bloodsync.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.AdminDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDashboardServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw=resp.getWriter();
		Gson g=new Gson();
		try {
			Map<String,Integer> counts=AdminDao.getAllBloodBankCount();
			
			String jsonData = g.toJson(counts);
			pw.print(jsonData);
		}catch(SQLException ex) {
			String errorJson = g.toJson(Map.of("status","failed","message",ex.getMessage()));
			pw.print(errorJson);
		}finally {
			pw.close();
		}
	}

}
