package com.softserve.academy.dashboard.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.entity.UserEntity;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;

@WebServlet("/usercreate")
public class UserProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserProfileCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(Attribute.loginAttr)!=null) {
			request.getSession().setAttribute("errorMessageToUserItems", "To create user please logout first");
			request.getRequestDispatcher("/useritems").forward(request, response); 
		}else {
			request.getRequestDispatcher("/WEB-INF/views/user/userProfile.jsp").forward(request, response);
		}
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = 
				request.getParameter("login") != null
				&& !request.getParameter("login").isEmpty()
				&& request.getParameter("password") !=null
				&& !request.getParameter("password").isEmpty()
				&& request.getParameter("confirmpass") !=null
				&& !request.getParameter("confirmpass").isEmpty()
				&& request.getParameter("email") !=null
				&& !request.getParameter("email").isEmpty();

		
		if(result) {
			request.getSession().invalidate();
			UserDTO userDTO = new UserDTO(-1, request.getParameter("login"), request.getParameter("email"), request.getParameter("password"));
			result = Context.getInstance().getUserService().setUserDTO(userDTO);
		}
		if(result) {
			
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Problem with creating user");
			request.getRequestDispatcher("/WEB-INF/views/user/userProfile.jsp").forward(request, response);
		}
	}

}
