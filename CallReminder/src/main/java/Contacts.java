

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Contacts extends HttpServlet {

  
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
		

		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		HttpSession hs=request.getSession();
		pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;color:blue;'>HOME/BACK</button></a>");
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
				PreparedStatement pst =   con.prepareStatement(" SELECT * FROM Contacts where email=?");
				pst.setString(1,(hs.getAttribute("email")).toString());
				rs=pst.executeQuery();
				pw.println("<span><center><a href='http://10.0.0.144:8080/CallReminder/Contacts'><button type='button'style='position:absolute;color:blue;'>REFRESH</button></a></span></center></br></br>");
				pw.println("<center><table><tr><th>FIRST_NAME</th><th>LAST_NAME</th><th>CELL_NO.</th>"
						+ "<th>PRIORITY</th><th>EMAIL_ID</th>"
						+ "<th>STATUS</th><th>OPERATIONS</th></tr>");
			while(rs.next())
			{
				pw.println("<style>table, th, td {border: 1px solid black;}</style>");
				if(rs.getInt((6))==0)
{
					
pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"
		+rs.getString(3)
+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td><img src='smiley1.jpg' height='42' width='42'>"
		+ "[IN QUEUE]</td><td><a href=http://10.0.0.144:8080/CallReminder/Edit?fname="+rs.getString(1)+"&lname="+rs.getString(2)
		+"&cell="+rs.getString(3)+"&priority="+rs.getInt(4)+"&email="+rs.getString(5)+"> <button type='button'>"
				+ "EDIT</button></a>  <a href=http://10.0.0.144:8080/CallReminder/Delete?cell="+rs.getString(3)+"><button type='button'>"
				+ "DELETE</button></a></td></tr>");

}
				
				else
				{
			
					pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"
							+rs.getString(3)
					+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td><img src='smiley2.jpg' height='42' width='42'>"
							+ "[DONE]</td><td><a href=http://10.0.0.144:8080/CallReminder/Edit?fname="+rs.getString(1)+"&lname="+rs.getString(2)
							+"&cell="+rs.getString(3)+"&priority="+rs.getInt(4)+"&email="+rs.getString(5)+"> <button type='button'>"
									+ "EDIT</button></a>  <a href=http://10.0.0.144:8080/CallReminder/Delete?cell="+rs.getString(3)+"><button type='button'>"
									+ "DELETE</button></a></td></tr>");
					
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