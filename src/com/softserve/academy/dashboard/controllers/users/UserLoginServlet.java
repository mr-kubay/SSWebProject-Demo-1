package com.softserve.academy.dashboard.controllers.users;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;

//mine
@WebServlet({"/", "/login"})
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			request.getSession().setAttribute(Attribute.loginAttr, request.getParameter("login"));
			request.getRequestDispatcher("/useritems").forward(request, response);
		}
		else {
			request.setAttribute("errorMessage", "Invalid Username or Password");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
		}
	}

}
