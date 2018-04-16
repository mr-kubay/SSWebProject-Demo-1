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
import com.softserve.academy.dashboard.tools.Path;

@WebServlet(Path.ITEM_PROFILE_CREATE_SERVLET_MAPPING)
public class ItemProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemProfileCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String login = (String)request.getSession().getAttribute(Attribute.LOGIN_ATTR);
		UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(login);
		if(userDTO != null) {
			request.getSession().setAttribute("userLogin",userDTO.getLogin());
		}*/
		request.getRequestDispatcher(Path.ITEM_PROFILE_JSP_PATH).forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean result = request.getParameter("title") != null
				&& !request.getParameter("title").isEmpty()
				&&request.getParameter("description") != null
				&& !request.getParameter("description").isEmpty();
		
		String login = (String)request.getSession().getAttribute(Attribute.LOGIN_ATTR);
		if(result) {
			UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(login);
			
			ItemDTO itemDTO = new ItemDTO(-1, request.getParameter("title"), request.getParameter("description"),userDTO.getIdUser());
			result = Context.getInstance().getItemService().setItemDto(itemDTO);
		}
		
		if(result) {
			LoginDTO loginDto = new LoginDTO(login, new String());
			UserItemsDto userItemsDto = Context.getInstance()
					.getUserItemsService().getUserItems(loginDto);
			request.getSession().setAttribute(Attribute.USER_ITEMS_DTO_ATTR, userItemsDto);
			response.sendRedirect(Path.REDIRECTION_MAPPING + Path.USER_ITEMS_SERVLET_MAPPING);
			//request.getRequestDispatcher("/useritems").forward(request, response);	
		} else {
			request.setAttribute(Attribute.ERROR_MESSAGE_ATTR, "Cant save item to DB");
			request.getRequestDispatcher(Path.ITEM_PROFILE_JSP_PATH).forward(request, response);
		}
		
	}

}
