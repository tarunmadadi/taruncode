

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		HttpSession hs=request.getSession();
		pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;color:blue;'>HOME</button></a>");
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
		pw.println("<span><center><a href='http://10.0.0.144:8080/CallReminder/Display'><button type='button'style='position:absolute;color:blue;'>BACK</button></a></center></span></br></br>");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String cellnum=request.getParameter("cell");
		String emailid=request.getParameter("email");
		String prior=request.getParameter("priority");
		pw.println("<body background='wall.jpg'>");
		
		
		if(hs.getAttribute("psw")!=null)
		{
		
		
		hs.setAttribute("cellno",cellnum);
pw.println("<center><form action='./Update'><table><tr><td>FIRST_NAME:</td><td><input type='text' name='fname' value="+fname+">"
		+ "</td></tr><tr><td>LAST_NAME: </td><td><input type='text' name='lname' value="+lname+"></td></tr><tr><td>CELL_NO.:</td>"
				+ "<td><input type='text' name='cellno' value="+cellnum+"></td></tr><tr><td>EMAIL_ID:</td><td><input type='text' "
						+ "name='emailid' value="+emailid+"></td></tr><tr><td>PRIORITY:</td><td><input type='text' name='prior' "
								+ "value="+prior+"></td></tr><tr><td></td><td><input type='submit' value='UPDATE'></td>");
		}
		
		else
								{
									pw.println("<center><h1>YOUR SESSION WAS EXPIRED</h1></center>"
								+ "</tr></table></form><center>");
								}
		
	}

}
