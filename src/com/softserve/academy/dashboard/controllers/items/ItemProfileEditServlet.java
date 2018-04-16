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
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;
import com.softserve.academy.dashboard.tools.Path;

@WebServlet(Path.ITEM_PROFILE_EDIT_SERVLET_MAPPING)
public class ItemProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemProfileEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ItemDTO itemDTO = Context.getInstance().getItemService().getItemDto(request.getParameter("item"));
		if(itemDTO != null) {
			request.getSession().setAttribute(Attribute.ITEM_DTO_ATTR,itemDTO);
			request.getSession().setAttribute(Attribute.ITEM_TITLE_DTO_ATTR,itemDTO.getTitle());
		}
		request.getRequestDispatcher(Path.ITEM_PROFILE_JSP_PATH).forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = request.getParameter("title") != null
				&& !request.getParameter("title").isEmpty()
				&&request.getParameter("description") != null
				&& !request.getParameter("description").isEmpty();
		
		if(result) {
			
			String itemTitle = (String) request.getSession().getAttribute(Attribute.ITEM_TITLE_DTO_ATTR);
			ItemDTO itemDTO = Context.getInstance().getItemService().getItemDto(itemTitle);
			
			itemDTO.setTitle(request.getParameter("title"));
			itemDTO.setDescription(request.getParameter("description"));
			result = Context.getInstance().getItemService().setItemDto(itemDTO);
		}
		if(result) {
			String login = (String) request.getSession().getAttribute(Attribute.LOGIN_ATTR);
			System.out.println(login);
			LoginDTO loginDTO = new LoginDTO(login, new String());
			UserItemsDto userItemsDto = Context.getInstance()
					.getUserItemsService().getUserItems(loginDTO);
			request.getSession().setAttribute(Attribute.USER_ITEMS_DTO_ATTR, userItemsDto);
			request.getSession().removeAttribute(Attribute.ITEM_DTO_ATTR);
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.USER_ITEMS_SERVLET_MAPPING);
			//request.getRequestDispatcher(Path.USER_ITEMS_SERVLET_MAPPING).forward(request, response);	
		} else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Cant edit item");
			request.getRequestDispatcher(Path.ITEM_PROFILE_JSP_PATH).forward(request, response);
		}
	
	}

}
