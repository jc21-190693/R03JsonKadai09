

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetPointServlet
 */
@WebServlet("/getTicketList")
public class GetTicketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String driverName = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://192.168.54.190/jsonkadai09";
		final String id = "jsonkadai09";
		final String pass = "JsonKadai09";
		
		try {
			/*
			String shop = "500";
			String user = "ka";
			*/
			
			String shop =request.getParameter("shopId");
			String user =request.getParameter("userId");
			
			
			Class.forName(driverName);
			Connection connection=DriverManager.getConnection(url,id,pass);
			
			PreparedStatement st = connection.prepareStatement("select ticket_id, ticket_name, ns_point from all_ticket where tenpo_id = ? and ns_point <=(SELECT point FROM point WHERE tenpo_id = ? AND user_id = ?)" );
			st.setString(1, shop);
			st.setString(2, shop);
			st.setString(3, user);
			
			ResultSet result = st.executeQuery();
			
			List<String[]> list = new ArrayList<>();
			while( result.next() == true) {
				String[] s = new String[3];
				s[0]=Integer.toString(result.getInt("ticket_id"));
				s[1]=result.getString("ticket_name");
				s[2]=Integer.toString(result.getInt("ns_point"));
				
				System.out.println(s[0] + s[1] + s[2]);
				list.add(s);
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/jsp/getTicketList.jsp").forward(request, response);
		}catch (SQLException e) {
			// TODO: handle exception
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		
	}

}
