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
import com.softserve.academy.dashboard.entity.ItemEntity;
import com.softserve.academy.dashboard.tools.Attribute;
import com.softserve.academy.dashboard.tools.Context;

@WebServlet("/itemcreate")
public class ItemProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemProfileCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		System.out.println("login =" + login);
		UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(login);
		if(userDTO != null) {
			request.getSession().setAttribute("userLogin",userDTO.getLogin());
		}
		request.getRequestDispatcher("/WEB-INF/views/item/itemProfile.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean result = request.getParameter("title") != null
				&& !request.getParameter("title").isEmpty()
				&&request.getParameter("description") != null
				&& !request.getParameter("description").isEmpty();
		
		String userLogin = (String)request.getSession().getAttribute("userLogin");
		if(result) {
			UserDTO userDTO = Context.getInstance().getUserService().getUserDTO(userLogin);
			
			ItemDTO itemDTO = new ItemDTO(-1, request.getParameter("title"), request.getParameter("description"),userDTO.getIdUser());
			result = Context.getInstance().getItemService().setItemDto(itemDTO);
		}
		
		if(result) {
			LoginDTO loginDto = new LoginDTO(userLogin, new String());
			UserItemsDto userItemsDto = Context.getInstance()
					.getUserItemsService().getUserItems(loginDto);
			request.getSession().setAttribute(Attribute.userItemsDtoAttr, userItemsDto);
			//response.sendRedirect("/WebProject/useritems");
			request.getRequestDispatcher("/WEB-INF/views/common/useritems.jsp").forward(request, response);	
		} else {
			request.setAttribute("errorMessage", "Cant save item to DB");
			request.getRequestDispatcher("/WEB-INF/views/item/itemProfile.jsp").forward(request, response);
		}
		
	}

}
