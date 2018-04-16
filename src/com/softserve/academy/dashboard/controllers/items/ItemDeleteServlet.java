package com.softserve.academy.dashboard.controllers.items;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.ItemDTO;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;
import com.softserve.academy.dashboard.tools.Path;

@WebServlet(Path.ITEM_PROFILE_DELETE_SERVLET_MAPPING)
public class ItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String login = (String) request.getSession().getAttribute(Attribute.LOGIN_ATTR);
		
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
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.USER_ITEMS_SERVLET_MAPPING);
			//request.getRequestDispatcher(Path.USER_ITEMS_JSP_PATH).forward(request, response);
		} else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Access Denied. You Must be Logged");
			request.getRequestDispatcher(Path.LOGIN_SERVLET_MAPPING).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
