package com.softserve.academy.dashboard.controllers.items;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.ItemDTO;
import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.dto.UserItemsDto;
import com.softserve.academy.dashboard.tools.Context;

@WebServlet("/itemdelete")
public class ItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String login = (String) request.getSession().getAttribute("login");
		
		boolean result = login != null 
				&& !login.isEmpty() 
				&&  request.getParameter("idItem") != null 
				&&	!request.getParameter("idItem").isEmpty() ;
		
		if(result) {
			long idItem = Long.parseLong(request.getParameter("idItem"));
			if(Context.getInstance().getItemService().isExsistItem(idItem)) {
				ItemDTO itemDto = new ItemDTO(idItem);
				Context.getInstance().getItemService().removeItem(itemDto);
			}
			request.getRequestDispatcher("/useritems").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Access Denied. You Must be Logged");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
