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





	
	
	@RequestMapping(value="/addWordBook.do")
    public @ResponseBody String addWordBook(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		
		WordBook wb = new WordBook(name);
		wb.setId(id);
		
		try {
			wordBookService.createWordBook(wb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
    }
	
	@RequestMapping(value="/readWordBook.do")
    public ResponseEntity<String> readWordBook(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String name = ServletRequestUtils.getStringParameter(request, "word", "");
					
		WordBook book = new WordBook(name);
		book.setId(id);
		
		List<WordBook> wordBookList = new ArrayList<WordBook>();
		try {
			wordBookList.add(wordBookService.readWordBook(book));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		
		return new ResponseEntity<String>(MyJsonUtil.toString(wordBookList, "wordbooks"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/readWordBookList.do")
    public ResponseEntity<String> readWordBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		int id = ServletRequestUtils.getIntParameter(request, "id", -1);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
					
		WordBook wordBook = new WordBook(name);
		wordBook.setId(id);
		wordBook.setName(name);
		
		List<WordBook> wordBookList = new ArrayList<WordBook>();
		
		try {
			wordBookList = wordBookService.readWordBookList(wordBook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(wordBookList, "wordbooks"), responseHeaders, HttpStatus.CREATED);
    }

	@RequestMapping(value="/updateWordBookList.do")
    public @ResponseBody String updateWordBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		
		WordBook wb = new WordBook(name);
		wb.setId(id);
		wordBookService.updateWordBook(wb);
		
		return "success";
    }
	
	@RequestMapping(value="/deleteWordBookList.do")
    public @ResponseBody String deleteWordBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		WordBook wb = new WordBook("");
		wb.setId(id);
		
		wordBookService.deleteWordBook(wb);
		
		return "success";
    }
}
