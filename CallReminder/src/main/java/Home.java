

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


public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		HttpSession hs=request.getSession(true);
		response.setContentType("text/html");
		pw.println("<body background='wall.jpg'>");
		
		String uname=request.getParameter("uname");
		String psw=request.getParameter("psw");
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "root", "1234");
		   } 
		catch (Exception e)
		{
			pw.println(e);
			pw.println("UNABLE TO CONNECT To DATABASE");
			//(Thread.currentThread()).stop();
		}
		try
		{
			st =   con.createStatement();
			rs=st.executeQuery("select  Email ,Password from  register");
			while(rs.next())
			{
				
				
				if(rs.getString(1).equals(uname) && rs.getString(2).equals(psw)) 
				{
					
					pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
					count=1;
				
				pw.println("<center><table><tr>&nbsp&nbsp<td><h1>YOU WERE SUCCESSFULLY LOGGED IN</h1></td></tr><center>");
				pw.println("<tr><td><center><a href='http://10.0.0.144:8080/CallReminder/Display'> <button type='button' >SHOW</button></a>"
						+ "&nbsp<a href='http://10.0.0.144:8080/CallReminder/Contacts'> <button type='button' >CONTACTS</button></a><center></td></tr></table>");
				
				hs.setAttribute("email",uname);
				hs.setAttribute("psw", psw);
				pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;top:0;left:0;color:blue;'>HOME/REFRESH</button></a>");
				
				
				break;
				
				}
			}
			if(count==0)
			{
				pw.println("</br><span><center><a href='http://10.0.0.144:8080/CallReminder/LandingPage.html'><button type='button'style='position:absolute;top:0;color:blue;'>BACK/LOGIN</button></a></center></span>");
				pw.println("<h1><center>SORRY,LOGIN FAILURE : INVALID CREDENTIALS</h1></center>");
			}
}
catch(Exception e)	
		{
	pw.println(e.getMessage());
		}
		
	}
		
	}

	


