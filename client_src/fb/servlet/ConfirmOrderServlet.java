package fb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String flightOut = request.getParameter("flightOut");
		String flightBack = request.getParameter("flightBack");
		
		if(flightOut.equals("")&&flightBack.equals("")){
			request.getRequestDispatcher("/listflights.jsp").forward(request, response); 
		}else{
			request.setAttribute("flightOutFid", flightOut);
			request.setAttribute("flightBackFid", flightBack);
			request.getRequestDispatcher("/confirm.jsp").forward(request, response); 
		}
	}

}
