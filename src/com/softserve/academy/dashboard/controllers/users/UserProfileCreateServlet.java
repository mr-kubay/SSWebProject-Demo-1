package com.softserve.academy.dashboard.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;
import com.softserve.academy.dashboard.tools.Path;

@WebServlet(Path.USER_PROFILE_CREATE_SERVLET_MAPPING)
public class UserProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserProfileCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(Attribute.LOGIN_ATTR)!=null) {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "To create user please logout first");
			//response.sendRedirect(Path.REDIRECTION_MAPPING + Path.USER_ITEMS_SERVLET_MAPPING);
			request.getRequestDispatcher(Path.USER_ITEMS_SERVLET_MAPPING).forward(request, response); 
		}else {
			request.getRequestDispatcher(Path.USER_PROFILE_JSP_PATH).forward(request, response);
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
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.LOGIN_SERVLET_MAPPING);
			//request.getRequestDispatcher(Path.LOGIN_SERVLET_MAPPING).forward(request, response);
		} else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Problem with creating user");
			request.getRequestDispatcher(Path.USER_PROFILE_JSP_PATH).forward(request, response);
		}
	}

}
