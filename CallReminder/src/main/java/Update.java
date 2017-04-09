

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String cellnum=request.getParameter("cellno");
		String emailid=request.getParameter("emailid");
		String prior=request.getParameter("prior");
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		pw.println("<body background='wall.jpg'>");
HttpSession hs=request.getSession();
pw.println("<a href=http://10.0.0.144:8080/CallReminder/Home?uname="+hs.getAttribute("email").toString()+"&psw="+hs.getAttribute("psw").toString()+"><button type='button'style='position:absolute;color:blue;'>HOME</button></a>");
		
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
		
		try{
			String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

		     
		      Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

		      
		      Matcher m = r.matcher(emailid);
		      if (m.find( )) {
		         
		      }else {
		         throw new EmailException();
		      }
			if(cellnum.length()==10 && Integer.parseInt(prior)>0 && Integer.parseInt(prior)<10){
			
				 st =   con.prepareStatement("select Flag from Contacts where CellNo=?");
				 ((PreparedStatement) st).setString(1,(String)hs.getAttribute("cellno"));
				rs= ((PreparedStatement) st).executeQuery();
				rs.next();
				
				int flag=rs.getInt(1);
			
		
				
		st =   con.prepareStatement("Delete from Contacts where CellNo=?");
		((PreparedStatement) st).setString(1,(String)hs.getAttribute("cellno"));
		((PreparedStatement) st).executeUpdate();
		st =   con.prepareStatement("insert into Contacts values(?,?,?,?,?,?,?)");
		((PreparedStatement) st).setString(1,fname);
		((PreparedStatement) st).setString(2,lname);
		((PreparedStatement) st).setString(3,cellnum);
		((PreparedStatement) st).setInt(4,Integer.parseInt(prior));
		((PreparedStatement) st).setString(5,emailid);
		((PreparedStatement) st).setInt(6,flag);
		((PreparedStatement) st).setString(7,(hs.getAttribute("email")).toString());
		 ((PreparedStatement) st).executeUpdate();
		  pw.println("<center><h1>UPDATE SUCCESSFULL :)</h1></center>");
			}
			 else
			 {
				 pw.println("<center><h1>UPDATE UNSUCCESSFULL</h1></center>");
			 }
}
		catch(EmailException e)
		{
			pw.println(e.getMessage());
		}
catch(Exception e)	
	{
	pw.println(e);
	
pw.println("<center><h1>UPDATE  UNSUCCESSFULL</h1></center>");

	}
		}
		else
		{
			pw.println("<center><h1>YOUR SESSION WAS EXPIRED</h1></center>");
		}
	}

}
