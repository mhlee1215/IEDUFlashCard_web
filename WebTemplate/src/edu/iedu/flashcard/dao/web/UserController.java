package edu.iedu.flashcard.dao.web;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import edu.iedu.flashcard.dao.domain.User;
import edu.iedu.flashcard.dao.service.UserService;




@Controller
public class UserController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private final UserService userService = null;




	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String myParameter = ServletRequestUtils.getStringParameter(request, "myParameter", "");

		ModelAndView model = new ModelAndView("index");
		//model.addObject("page_title", lang.getStringHazardReportingSystem());
		model.addObject("myMessage", "It is my message, "+myParameter);

		return model;
	}

	@RequestMapping("/userList.do")
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String myParameter = ServletRequestUtils.getStringParameter(request, "myParameter", "");

		ModelAndView model = new ModelAndView("userList");

		model.addObject("myMessage", "It is my message, "+myParameter);
		List<User> userList = new ArrayList<User>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = null;
			try
			{
				// create a database connection
				connection = DriverManager.getConnection("jdbc:mysql://54.201.57.109:3306/iedu_flashcard","root", "a12345!");
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);  // set timeout to 30 sec.

				ResultSet rs = statement.executeQuery("select * from user");
				while(rs.next())
				{
					// read the result set

					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					System.out.println("id = " + id);
					System.out.println("name = " + name);
					System.out.println("email = " + email);
					System.out.println("password = " + password);
					User user = new User();
					user.setId(id);
					user.setName(name);
					user.setEmail(email);
					user.setPassword(password);
					userList.add(user);
				}
			}
			catch(SQLException e)
			{
				// if the error message is "out of memory", 
				// it probably means no database file is found
				System.err.println(e.getMessage());
			}
			finally
			{
				try
				{
					if(connection != null)
						connection.close();
				}
				catch(SQLException e)
				{
					// connection close failed.
					System.err.println(e);
				}
			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(userList);;
		model.addObject("userList",userList);
		return model;
	}

}
