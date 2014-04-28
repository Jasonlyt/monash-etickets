package fb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String flightOut = request.getParameter("flightout");
		String flightBack = request.getParameter("flightback");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String creditcard = request.getParameter("creditcard");

	}

}
