package com.softserve.academy.dashboard.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.dto.UserItemsDto;
import com.softserve.academy.dashboard.services.UserService;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;

/**
 * Servlet implementation class UserProfileEdit
 */
@WebServlet("/useredit")
public class UserProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserProfileEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String login = request.getParameter("login");
		UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(login);
		if(userDTO != null) {
			request.getSession().setAttribute(Attribute.userDtoAttr,userDTO );
		}
		request.getRequestDispatcher("/WEB-INF/views/user/userProfile.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean result = request.getParameter("login")!=null 
				&& !request.getParameter("login").isEmpty() 
				&& request.getParameter("password")!=null
				&& !request.getParameter("password").isEmpty()
				&& request.getParameter("email")!=null
				&& !request.getParameter("email").isEmpty();
		
		if(result) {
		UserDTO userDTO = new UserDTO(
				Long.parseLong(request.getParameter("id"))
				, request.getParameter("login")
				, request.getParameter("email")
				, request.getParameter("password"));
		
		result = Context.getInstance().getUserService().setUserDTO(userDTO);
		
		if(result) {
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("errorMessage", "Cant update user");
			request.getRequestDispatcher("/WEB-INF/views/user/userProfile.jsp").forward(request, response);
		}
	}
}

}
