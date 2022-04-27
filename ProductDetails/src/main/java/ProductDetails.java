
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.DBConnection;


@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProductDetails() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id= request.getParameter("productId");
		    System.out.println(id);
		   response.setContentType("text/html");  
		   PrintWriter out = response.getWriter();
           InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
           Properties props = new Properties();
           props.load(in);
          
           DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
           String sql="SELECT * FROM products WHERE productId='"+id+"'";
           System.out.println(sql);
           PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
           ResultSet rs = pstm.executeQuery();
           String res ="";
          
           if (rs.next() == false) 
           { 
        	   res+="<tr><td>No Record Found!!!</td></tr>"; 
        	 
		} else {
			  /* Printing column names */  
            ResultSetMetaData rsmd=rs.getMetaData();  
            int total=rsmd.getColumnCount(); 
            
            res+="<tr>";  
            for(int i=1;i<=total;i++)  
            {  
         	   res+="<th>"+rsmd.getColumnName(i)+"</th>";  
            }  
              
            res+="</tr>"; 
            /* Printing result */  
            
            do
            {  
         	   res+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";  
                System.out.println(res);
            }while(rs.next())  ;  
				
				 
				 }
				 

         out.print(res);
           
          
			
           
	}catch(Exception e) {
		e.printStackTrace();
	
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
