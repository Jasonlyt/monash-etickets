package fb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryTicketsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("ISO-8859-1");
		String fromCity = request.getParameter("fromCity");
		String toCity = request.getParameter("toCity");
		String departDate = request.getParameter("departDate");
		String returnDate = request.getParameter("returnDate");
		
		if(!fromCity.equals("")&&!toCity.equals("")){
			request.setAttribute("fromCity", fromCity);
			request.setAttribute("toCity", toCity);
			request.setAttribute("departDate", departDate);
			request.setAttribute("returnDate", returnDate);
			
			request.getRequestDispatcher("/listflights.jsp").forward(request, response); 
		}else{
			request.getRequestDispatcher("/index.jsp").forward(request, response); 
		}
		
	}
	
}
