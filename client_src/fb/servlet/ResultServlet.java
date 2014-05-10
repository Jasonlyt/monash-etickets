package fb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fb.client.FlightBookingClientHOPP;

public class ResultServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	FlightBookingClientHOPP clientHOPP;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String outFid = request.getParameter("outfid");
		String backFid = request.getParameter("backfid");
		String username = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String creditcard = request.getParameter("creditcard");
			
		clientHOPP = new FlightBookingClientHOPP();
		if(clientHOPP.regReq(username, phone, email, creditcard)){
			if(backFid.equals("")){
				//one way
				if(clientHOPP.bookReq(outFid, username)){
					request.setAttribute("username", username);
					request.setAttribute("outfid",outFid);
					request.setAttribute("message", "Congratulations, you have successfully ordered the one way ticket!");
				}else{
					request.setAttribute("message", "Your hand is too slow! No remaining tickets!");
				}
			}else{
				//return trip
				if(clientHOPP.bookReq(outFid, username)&&clientHOPP.bookReq(backFid, username)){
					//book successfully
					request.setAttribute("username", username);
					request.setAttribute("outfid",outFid);
					request.setAttribute("backfid",backFid );
					request.setAttribute("message", "Congratulations, you have successfully ordered the return trip tickets!");
				}else{
					request.setAttribute("message", "Your hand is too slow! No remaining tickets!");
				}
				
			}
		}else{
			//user existed!
			request.setAttribute("message", "Username is existed! Please change a new name.");
		}
		request.getRequestDispatcher("/result.jsp").forward(request, response); 
	}

}
