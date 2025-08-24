package in.bloodsync.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.BloodRequestDao;
import in.bloodsync.pojo.BloodRequestPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManageBloodRequestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw=resp.getWriter();
		Gson gson=new Gson();
		try {
			List<BloodRequestPojo> requestsList=BloodRequestDao.getAllBloodRequest();
			String jsonData=gson.toJson(requestsList);
			pw.print(jsonData);
		}catch(SQLException ex) {
			String errorJson=gson.toJson(Map.of("status","failed","message",ex.getMessage()));
			pw.print(errorJson);
		}finally {
			pw.close();
		}
	}

}
