package in.bloodsync.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.BloodStockDao;
import in.bloodsync.pojo.BloodStockPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManageBloodStockServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw=resp.getWriter();
		Gson gson=new Gson();
		try {
			List<BloodStockPojo> stockList=BloodStockDao.getAllBloodStock();
			String jsonData=gson.toJson(stockList);
			pw.print(jsonData);
			
		}catch(SQLException ex) {
			String errorJson=gson.toJson(Map.of("status","failed","message",ex.getMessage()));
			pw.print(errorJson);
		}finally {
			pw.close();
		}
	}

}
