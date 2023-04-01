package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final String query = "update DATA set STUDENTNAME=?,ROLLNO=?,DOB=?,DEPARTMENT=?,ADDRESS=?,MOBILENO=? where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter(); 
		res.setContentType("text/html"); 
		int id = Integer.parseInt(req.getParameter("id"));
		String studentName = req.getParameter("studentName");
		int rollNo = Integer.parseInt(req.getParameter("rollNo"));
		int dob = Integer.parseInt(req.getParameter("dob"));
		String department = req.getParameter("department");
		String address = req.getParameter("address");
		String mobileNo = req.getParameter("mobileNo"); 
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} 
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///student","root","gowtham");
				PreparedStatement ps = con.prepareStatement(query);){
			ps.setString(1, studentName);
			ps.setInt(2, rollNo);
			ps.setInt(3, dob);
			ps.setString(4, department);
			ps.setString(5, address);
			ps.setString(6, mobileNo);
			ps.setInt(7,id);
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record is Edited Successfully</h2>");
			}else {
				pw.println("<h2>Record is not Edited Successfully</h2>");
			}
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='StudentList'>Student List</a>");

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}


