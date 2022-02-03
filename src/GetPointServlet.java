

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetPointServlet
 */
@WebServlet("/getPoint")
public class GetPointServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		final String driverName = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://192.168.54.190/jsonkadai09";
		final String id = "jsonkadai09";
		final String pass = "JsonKadai09";
		
		try {
			String shopid = "10100001";//request.getParameter("watanabbe");
			String userid = "wa"; //request.getParameter("ABCD");
			int point = 20202020;	//初回ポイント数
			
			Class.forName(driverName);
			Connection connection=DriverManager.getConnection(url,id,pass);
			
			
			//PreparedStatement st = connection.prepareStatement("INSERT IGNORE point set tenpo_id= ?, user_id=?, point=?");
			PreparedStatement st = connection.prepareStatement("INSERT IGNORE point set tenpo_id= ?, user_id=?, point=?");
			st.setString(1, shopid);
			st.setString(2, userid);
			st.setInt(3, point);
			//st.setString(4, shopid);
			//st.setString(5, userid);
			
			st.execute();
						
						
			PreparedStatement stmt = connection.prepareStatement("SELECT point FROM point WHERE tenpo_id = ? AND user_id = ?");
			stmt.setString(1, shopid);
			stmt.setString(2, userid);
			
			ResultSet result = stmt.executeQuery();
					
			while( result.next() == true) {
				shopid = result.getString("point");
			}
			System.out.println(shopid);
			
			
			
			request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp").forward(request, response);
			request.setAttribute("point", point);			

		}catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
		}
	}

}
