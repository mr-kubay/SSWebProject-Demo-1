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

@WebServlet(Path.USER_PROFILE_EDIT_SERVLET_MAPPING)
public class UserProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserProfileEditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String login = (String) request.getSession().getAttribute(Attribute.LOGIN_ATTR);
		if(login != null && !login.isEmpty()) {

			UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(login);

			if(userDTO != null) {
				request.getSession().setAttribute(Attribute.USER_DTO_ATTR,userDTO );
				request.getSession().setAttribute(Attribute.ID_USER_DTO_ATTR,userDTO.getIdUser());
			}
			request.getRequestDispatcher(Path.USER_PROFILE_JSP_PATH).forward(request, response);
		}
		else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "To edit user you must be logged in");
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.LOGIN_JSP_PATH);
			//request.getRequestDispatcher(Path.LOGIN_SERVLET_MAPPING).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean result = request.getParameter("login")!=null 
				&& !request.getParameter("login").isEmpty() 
				&& request.getParameter("password")!=null
				&& !request.getParameter("password").isEmpty()
				&& request.getParameter("email")!=null
				&& !request.getParameter("email").isEmpty();
		Long idValue = (Long) request.getSession().getAttribute(Attribute.ID_USER_DTO_ATTR);

		if(result) {
			UserDTO userDTO = new UserDTO(
					idValue
					, request.getParameter("login")
					, request.getParameter("email")
					, request.getParameter("password"));

			result = request.getParameter("password").equals(request.getParameter("confirmpass"));
			if(result) {
				result = Context.getInstance().getUserService().setUserDTO(userDTO);
			}
			if(result) {
				request.getSession().setAttribute(Attribute.LOGIN_ATTR, userDTO.getLogin());
				response.sendRedirect(Path.REDIRECTION_MAPPING + Path.USER_ITEMS_SERVLET_MAPPING);
				//request.getRequestDispatcher(Path.USER_ITEMS_SERVLET_MAPPING).forward(request, response);
			} else {
				request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Passwords should be equal");
				request.getRequestDispatcher(Path.USER_PROFILE_JSP_PATH).forward(request, response);
			}
		}
	}

}
