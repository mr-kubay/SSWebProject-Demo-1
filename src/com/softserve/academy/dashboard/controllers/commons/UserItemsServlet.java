package com.softserve.academy.dashboard.controllers.commons;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.dto.UserItemsDto;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;
import com.softserve.academy.dashboard.tools.Path;

@WebServlet(Path.USER_ITEMS_SERVLET_MAPPING)
public class UserItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserItemsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginName = (String) request.getSession().getAttribute(Attribute.LOGIN_ATTR);
		if(loginName != null && !loginName.isEmpty()) {
			LoginDTO loginDTO = new LoginDTO(loginName, new String());
			
			UserItemsDto userItemsDto = Context
					.getInstance()
					.getUserItemsService()
					.getUserItems(loginDTO);
			
			request.getSession().setAttribute(Attribute.USER_ITEMS_DTO_ATTR, userItemsDto);
			
			request.getRequestDispatcher(Path.USER_ITEMS_JSP_PATH).forward(request, response);			
		}else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Access denied you must be logged in");
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.LOGIN_SERVLET_MAPPING);
			//request.getRequestDispatcher(Path.LOGIN_SERVLET_MAPPING).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
