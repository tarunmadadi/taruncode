

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		HttpSession hs=request.getSession();

		pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;color:blue;'>HOME</button></a>");
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
		pw.println("<span><center><a href='http://10.0.0.144:8080/CallReminder/Display'><button type='button'style='position:absolute;color:blue;'>BACK</button></a></center></span></br></br>");
		String cellnum=request.getParameter("cell");
		Connection con=null;
		Statement st=null;
		
		pw.println("<body background='wall.jpg'>");

		
		if(hs.getAttribute("psw")!=null)
		{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "root", "1234");
		   } 
		catch (Exception e)
		{
			pw.println(e);
			
		}
		try
		{
		st =   con.prepareStatement("Delete from Contacts where CellNo=?");
		((PreparedStatement) st).setString(1,cellnum);
		((PreparedStatement) st).executeUpdate();
		pw.println("<center><h1>HEY,DELETE OPERATION SUCCESSFULL :)</h1><center>" );
		}
		catch(Exception e)
		{
			pw.println("<center><h1>SORRY,DELETE OPERATION UNSUCCESSFULL</h1><center>" );
		}
		}
		else
		{
			pw.println("<center><h1>YOUR SESSION WAS EXPIRED</h1></center>");
		}
	}

}
