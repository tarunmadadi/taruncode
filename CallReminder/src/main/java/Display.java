

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Display extends HttpServlet {

  
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		HttpSession hs=request.getSession(false);
		pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;color:blue;'>HOME/BACK</button></a>");
		response.setContentType("text/html");
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Map m=new HashMap();
		Object maxValue;
		int flag=0;
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
			pw.println("UNABLE TO CONNECT To DATABASE");
			//(Thread.currentThread()).stop();
		}
		
		try
		{
			
			st =   con.prepareStatement(" SELECT COUNT(*) FROM Contacts where email=?");
			((PreparedStatement) st).setString(1,(String)hs.getAttribute("email"));
			rs= ((PreparedStatement) st).executeQuery();
			rs.next();
			if(rs.getInt(1)>0)
			{
				pw.println("<style>table, th, td {border: 1px solid black;}</style>");
				st=con.prepareStatement(" SELECT CellNo,Priority,Flag from Contacts where email=? ");
				((PreparedStatement) st).setString(1,(String)hs.getAttribute("email"));
				rs= ((PreparedStatement) st).executeQuery();
				pw.println("<center><table><tr><th>FIRST_NAME</th><th>LAST_NAME</th><th>CELL_NO.</th>"
						+ "<th>EMAIL_ID</th>"
						+ "<th>CALL</th></tr>");
			while(rs.next())
			{
				
				if(rs.getInt((3))==0)
              {
					
m.put(rs.getString(1),Integer.valueOf(rs.getInt(2)));

               }
			}
			
			maxValue= Collections.max(m.values());
			st=con.prepareStatement(" Select * from Contacts where Priority=?");
			((PreparedStatement) st).setInt(1,Integer.parseInt(maxValue.toString()));
			rs=((PreparedStatement) st).executeQuery();
			
			while(rs.next())
			{
				if(rs.getInt(6)==0)  
				{
					
					if(hs.getAttribute("email").equals(rs.getString(7)))
					{
					
						
				pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)
				+"</td><td>"+rs.getString(5)+"</td><td><a href=http://10.0.0.144:8080/CallReminder/Linking?cellno="+rs.getString(3)+"&prior="+rs.getString(4)+"><img src='call.gif' height='42' width='42'/></a></td></tr>");
				}
				}
			}
			pw.println("</table>");
			pw.println("<center><table><tr><td><a href='http://10.0.0.144:8080/CallReminder/AddContact.html'> "
					+ "<button type='button'>ADD CONTACT</button></a></td></tr></table><center>");
			}
			else
			{
				
				pw.println("<center><table><tr><td><h1>NO CONTACTS.SO, PLEASE </h1></td><td>"
						+ "<a href='http://10.0.0.144:8080/CallReminder/AddContact.html'> "
						+ "<button type='button'><h1>ADD CONTACT</h1></button></a></td></tr></table><center>");
			}
			
	}
	
		catch(Exception e)
		{
			
			pw.println(e);
		}
		}
		else
		{
			pw.println("<center><h1>YOUR SESSION WAS EXPIRED</h1></center>");
		}
}
}