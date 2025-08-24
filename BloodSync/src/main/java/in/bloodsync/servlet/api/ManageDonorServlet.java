package in.bloodsync.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.BloodDonorDao;
import in.bloodsync.pojo.BloodDonorPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManageDonorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		Gson gson = new Gson();
		try {
			List<BloodDonorPojo> donorList=BloodDonorDao.getAllDonors();
			
			String jsonData = gson.toJson(donorList);
			pw.print(jsonData);
		}catch(SQLException ex) {
			String errorJson = gson.toJson(Map.of("status","failed","message",ex.getMessage()));
			pw.print(errorJson);
		}finally {
			pw.close();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		String idStr= req.getParameter("id");
		int id = Integer.parseInt(idStr);
		PrintWriter pw=resp.getWriter();
		Gson gson= new Gson();
		
		try {
			boolean ans = BloodDonorDao.deleteDonor(id);
			if(ans) {
				String json = gson.toJson(Map.of("status","success","message","Donor deleted successfully"));
				pw.print(json);
			}else {
				String json = gson.toJson(Map.of("status","failed","message","deletion failed..."));
				pw.print(json);
				
			}
		}catch(SQLException ex) {
			String json = gson.toJson(Map.of("status","error","message",ex.getMessage()));
			pw.print(json);
		}finally {
			pw.close();
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		Gson gson = new Gson();
		
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		
		String unitsStr = req.getParameter("units");
		int units = Integer.parseInt(unitsStr);
		
		
	}
}
