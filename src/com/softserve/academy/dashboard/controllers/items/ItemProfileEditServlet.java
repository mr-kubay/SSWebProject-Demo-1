package com.softserve.academy.dashboard.controllers.items;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.dashboard.dto.ItemDTO;
import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.dto.UserItemsDto;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;

@WebServlet("/itemedit")
public class ItemProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemProfileEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(request.getParameter("login"));
		ItemDTO itemDTO = Context.getInstance().getItemService().getItemDto(request.getParameter("item"));
		if(itemDTO != null) {
			request.getSession().setAttribute("itemDto",itemDTO);
			request.getSession().setAttribute("itemTitle",itemDTO.getTitle());
			request.getSession().setAttribute("login",userDTO.getLogin());
		}
		request.getRequestDispatcher("/WEB-INF/views/item/itemProfile.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = request.getParameter("title") != null
				&& !request.getParameter("title").isEmpty()
				&&request.getParameter("description") != null
				&& !request.getParameter("description").isEmpty();
		String login = (String) request.getSession().getAttribute("login");
		if(result) {
			
			UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(login);
			String itemTitle = (String) request.getSession().getAttribute("itemTitle");
			ItemDTO itemDTO = Context.getInstance().getItemService().getItemDto(itemTitle);
			
			itemDTO.setTitle(request.getParameter("title"));
			itemDTO.setDescription(request.getParameter("description"));
			result = Context.getInstance().getItemService().setItemDto(itemDTO);
		}
		if(result) {
			LoginDTO loginDTO = new LoginDTO(login, new String());
			UserItemsDto userItemsDto = Context.getInstance()
					.getUserItemsService().getUserItems(loginDTO);
			request.getSession().setAttribute(Attribute.userItemsDtoAttr, userItemsDto);
			request.getRequestDispatcher("/WEB-INF/views/common/useritems.jsp").forward(request, response);	
		} else {
			request.setAttribute("errorMessage", "Cant edit item");
			request.getRequestDispatcher("/WEB-INF/views/item/itemProfile.jsp").forward(request, response);
		}
	
	}

}
