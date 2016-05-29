package edu.iedu.flashcard.dao.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import edu.iedu.flashcard.dao.util.MyJsonUtil;




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
				connection = DriverManager.getConnection("jdbc:mysql://54.191.113.175:3306/iedu_flashcard","root", "a12345!");
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
	
	@RequestMapping("/userListQuery.do")
	public ResponseEntity<String>  userListQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> users = userService.findAll();
		System.out.println(users);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(users, "users"), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping("/readUser.do")
	public ResponseEntity<String>  readUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = ServletRequestUtils.getStringParameter(request, "email", "");
		User user = new User();
		user.setEmail(URLDecoder.decode(email, "UTF-8"));
		
		User readUser = userService.readUserData(user);
		
		List<User> userList = new ArrayList<User>();
		userList.add(readUser);
		
		System.out.println(readUser);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(userList, "users"), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/addUser.do")
    public @ResponseBody String addUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String email = ServletRequestUtils.getStringParameter(request, "email", "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
						
		User user = new User();
		user.setName(URLDecoder.decode(name, "UTF-8"));
		user.setEmail(URLDecoder.decode(email, "UTF-8"));
		user.setPassword(URLDecoder.decode(password, "UTF-8"));
		
		try {
			userService.createUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "fail-"+e.toString();
		}
		return "success";
    }
	
	@RequestMapping(value="/appLogin.do")
    public @ResponseBody String appLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String email = ServletRequestUtils.getStringParameter(request, "email", "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
						
		User user = new User();
		user.setName(URLDecoder.decode(name, "UTF-8"));
		user.setEmail(URLDecoder.decode(email, "UTF-8"));
		user.setPassword(URLDecoder.decode(password, "UTF-8"));
		
		try {
			//userService.createUser(user);
			int result = userService.readUser(user);
			if(result == User.STATUS_FOUNDED)
				return "success";
			else
				return "fail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "fail-"+e.toString();
		}
    }

}
