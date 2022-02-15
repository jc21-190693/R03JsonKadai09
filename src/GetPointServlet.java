

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
			
			String shopid = "10";
			String userid = "ka";
			/*
			String shopid =request.getParameter("shopId");
			String userid =request.getParameter("userId");
			*/
			int point = 30;	//初回ポイント数
			
			Class.forName(driverName);
			Connection connection=DriverManager.getConnection(url,id,pass);
			
			PreparedStatement st = connection.prepareStatement("INSERT IGNORE point set tenpo_id= ?, user_id=?, point=?");
			st.setString(1, shopid);
			st.setString(2, userid);
			st.setInt(3, point);
			
			//PreparedStatement st = connection.prepareStatement("insert into point(tenpo_id, user_id, point) select * from(select tenpo_id as tenpo, shop_id as shop, point) as tmp where not exist(select * from point where 
			
			st.executeUpdate();
						
						
			PreparedStatement stmt = connection.prepareStatement("SELECT point FROM point WHERE tenpo_id = ? AND user_id = ?");
			stmt.setString(1, shopid);
			stmt.setString(2, userid);
			
			ResultSet result = stmt.executeQuery();
					
			while( result.next() == true) {
				point = result.getInt("point");
			}
			System.out.println(point);
			
			request.setAttribute("point", point);
			request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp").forward(request, response);
						

		}catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
		}
	}

}
