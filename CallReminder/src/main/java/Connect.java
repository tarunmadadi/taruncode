

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		HttpSession hs=request.getSession();
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
		pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;color:blue;'>HOME</button></a>");
		pw.println("<body background='wall.jpg'>");
		
		String s=(String)hs.getAttribute("email");
		if(hs.getAttribute("psw")!=null)
		{
		
		String cellno=request.getParameter("cellno");
		String prior=request.getParameter("prior");
		Connection con=null;
		Statement st=null;
		ResultSet rs=null,rs1=null;
		
		
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
		st=con.prepareStatement(" Select FirstName,LastName from Contacts where CellNo=?");
		((PreparedStatement) st).setString(1,cellno);
		rs=((PreparedStatement) st).executeQuery();
		st=con.prepareStatement(" Select Cellno from Register where Email=?");
		((PreparedStatement) st).setString(1,(hs.getAttribute("email")).toString());
		rs1=((PreparedStatement) st).executeQuery();
		rs.next();
		rs1.next();
		
		
	    pw.println("<center><h1>CONNECTION SUCCESSFULL</h1></center>");
		pw.println("<style>table, th, td {border: 1px solid black;}</style>");
		pw.println("<center><table><tr><th>CONNECTION</th><th>STATUS</th></tr>");
		pw.println("<tr><td><img src='call.gif' height='42' width='42'/>"+rs1.getString(1)+"(YOU)  "
				+ "<img src='share-right-arrow-symbol_318-53262.png' height='32' width='32'/>"
				+ "          <img src='call.gif' height='42' width='42'/>"+cellno+"("+rs.getString(1)+""+rs.getString(2)+")"
						+ "</td><td><img src='Facebook-like-thumbs-up-symbol.jpg'height='42' width='42'/></td></tr>");
		pw.println("</table></center>");
		st=con.createStatement();
		st.executeUpdate(" update Contacts set Flag=1 where CellNo="+cellno);
		
		rs=st.executeQuery(" select * from Contacts");
		int count1=0,count2=0;
		while(rs.next())
		{
			if(rs.getString(7).equals(s))
			{
				count1++;
				if(rs.getInt(6)==1)
				{
					count2++;
				}
				else{
					break;
				}
			}
		}
		
	
		
		if(count1==count2)
		{
			
			rs=st.executeQuery(" SELECT * FROM Contacts ");
			while(rs.next())
			{
				if(rs.getString(7).equals(s))
				{
				st=con.createStatement();
				st.executeUpdate(" update Contacts set Flag=0 where CellNo="+rs.getString(3));
				}
			}
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
