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
import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.dao.service.UserService;
import edu.iedu.flashcard.dao.service.WordBookService;
import edu.iedu.flashcard.dao.service.WordService;
import edu.iedu.flashcard.dao.util.MyJsonUtil;





@Controller
public class WordBookController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private final UserService userService = null;
	
	@Autowired
	private final WordService wordService = null;
	
	@Autowired
	private final WordBookService wordBookService = null;





	
	
	@RequestMapping(value="/addWord.do")
    public @ResponseBody String addWord(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String userId = ServletRequestUtils.getStringParameter(request, "userId", "");
		String wordBookId = ServletRequestUtils.getStringParameter(request, "wordBookId", "");
		String name = ServletRequestUtils.getStringParameter(request, "word", "");
		String email = ServletRequestUtils.getStringParameter(request, "meaning", "");
					
		
		return "success";
    }
	
	@RequestMapping(value="/readWordBook.do")
    public ResponseEntity<String> readWordBook(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
		int wordBookId = ServletRequestUtils.getIntParameter(request, "wordBookId", -1);
		String name = ServletRequestUtils.getStringParameter(request, "word", "");
		String email = ServletRequestUtils.getStringParameter(request, "meaning", "");
					
		WordBook wordBook = new WordBook("My List");
		wordBook.setId(wordBookId);
		
		List<WordBook> wordBookList = null;
		
		try {
			wordBookList = wordBookService.readWordBookList(wordBook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(wordBookList, "users"), responseHeaders, HttpStatus.CREATED);
    }

}
