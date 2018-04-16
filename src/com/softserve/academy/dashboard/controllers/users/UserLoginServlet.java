package com.softserve.academy.dashboard.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;
import com.softserve.academy.dashboard.tools.Path;

@WebServlet(Path.LOGIN_SERVLET_MAPPING)
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(Path.LOGIN_JSP_PATH).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = request.getParameter("login")!=null 
				&& !request.getParameter("login").isEmpty() 
				&& request.getParameter("password")!=null
				&& !request.getParameter("password").isEmpty();

		if(result) {
			LoginDTO loginDTO = new LoginDTO(request.getParameter("login"), request.getParameter("password"));
			
			
			result = Context.getInstance().getLoginService()
					.isLogged(loginDTO);
		}
		
		if(result) {
			request.getSession().setAttribute(Attribute.LOGIN_ATTR, request.getParameter("login"));
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.USER_ITEMS_SERVLET_MAPPING);
			//request.getRequestDispatcher(Path.USER_ITEMS_SERVLET_MAPPING).forward(request, response);
		}
		else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Invalid Username or Password");
			request.getRequestDispatcher(Path.LOGIN_JSP_PATH).forward(request, response);
		}
	}

}
