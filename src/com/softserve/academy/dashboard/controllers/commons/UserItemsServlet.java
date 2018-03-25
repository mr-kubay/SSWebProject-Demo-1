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

@WebServlet("/useritems")
public class UserItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserItemsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginName = (String) request.getSession().getAttribute(Attribute.loginAttr);
		if(loginName != null && !loginName.isEmpty()) {
			LoginDTO loginDTO = new LoginDTO(loginName, new String());
			
			UserItemsDto userItemsDto = Context
					.getInstance()
					.getUserItemsService()
					.getUserItems(loginDTO);
			
			request.setAttribute(Attribute.userItemsDtoAttr, userItemsDto);
			request.getRequestDispatcher("/WEB-INF/views/common/useritems.jsp").forward(request, response);			
		}else {
			request.setAttribute("errorMessage", "Access denied you must be logged in");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
