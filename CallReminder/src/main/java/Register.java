

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends HttpServlet {
	
public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String cellnum=request.getParameter("cellno");
		String emailid=request.getParameter("emailid");
		String psw=request.getParameter("psw");
		Connection con=null;
		Statement st=null;
		
		
		pw.println("<body background='wall.jpg'>");
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/LandingPage.html'><button type='button'style='position:absolute;color:blue;top:0;right:0;'>LOGIN</button></a>");
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/SignUp.html'><button type='button'style='position:absolute;color:blue;top:0'>BACK FOR RGISTRATION </button></a>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "root", "1234");
		   } 
		catch (Exception e)
		{
			pw.println(e);
			
		}
		
	
			try{
				
				String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

			     
			      Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

			      
			      Matcher m = r.matcher(emailid);
			      if (m.find( )) {
			         
			      }else {
			         throw new EmailException();
			      }
				 if(cellnum.length()==10){
				
			st =   con.prepareStatement("insert into register values(?,?,?,?,?)");
			((PreparedStatement) st).setString(1,fname);
			((PreparedStatement) st).setString(2,lname);
			((PreparedStatement) st).setLong(3,Long.parseLong(cellnum));
			((PreparedStatement) st).setString(4,emailid);
			((PreparedStatement) st).setString(5,psw);
			
			  ((PreparedStatement) st).executeUpdate();
			  pw.println("<center><h1>REGISTRATION SUCCESSFULL</h1></center>");
				}
				 else
				 {
					 pw.println("<center><h1>REGISTRATION UNSUCCESSFULL</h1></center>");
				 }
}
			catch(EmailException e)
			{
				pw.println(e.getMessage());
			}
catch(Exception e)	
		{
	
	pw.println("<center><h1>REGISTRATION UNSUCCESSFULL</h1></center>");
	
		}
}
}
class EmailException extends RuntimeException
{
	EmailException()
	{
		super("<center><h1>INVALID EMAILID</h1></center>");
	}
}
