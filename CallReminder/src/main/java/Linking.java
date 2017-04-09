

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Linking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<a href='http://10.0.0.144:8080/CallReminder/Signout'><button type='button'style='position:absolute;top:0;right:0;color:blue;'>SIGNOUT</button></a>");
	String cellno=request.getParameter("cellno");
	String prior=request.getParameter("prior");
		pw.println("<body background='wall.jpg'>");
HttpSession hs=request.getSession();

		if(hs.getAttribute("psw")!=null)
		{
			
		pw.println(" <span style='text-decoration: blink;'><center><h1>PLEASE WAIT ,CONNECTING...</h1></center></span>");
		pw.println("<span><center><a href='http://10.0.0.144:8080/CallReminder/Display'><button type='button'style='position:absolute;color:blue;'>CANCEL-CALL</button></a></center></span>");
		pw.println("<meta http-equiv='refresh' content=5;url=http://10.0.0.144:8080/CallReminder/Connect?cellno="+cellno+"&prior="+prior+" />");
		
		 
		
	}
	
	else
	{
		pw.println("<center><h1>YOUR SESSION WAS EXPIRED</h1></center>");
	}
}
}