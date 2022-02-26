import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        if(checkUser(email, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("Dashboard");
            rs.forward(request, response);
        }
        else
        {
        	request.getRequestDispatcher("index.html").include(request, response);
        	out.println("<script>document.getElementById('error').innerHTML='Invalid Credential'; </script>");  
        }
    }  
    
    public boolean checkUser(String email,String pass) 
    {
        
        if(email.equals("admin@gmail.com") && pass.equals("admin"))
        {
	            return true;
        }else {
               return false;  
        }
    }   
}