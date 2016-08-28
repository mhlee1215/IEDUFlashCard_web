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
import edu.iedu.flashcard.dao.domain.Word;
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


	
	

	@RequestMapping(value="/searchWordBookList.do")
    public ResponseEntity<String> searchWordBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
					
		WordBook wordBook = new WordBook(name);
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
	
	@RequestMapping(value="/addWordBookFromString.do")
    public @ResponseBody String addWordBookFromString(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int userid = ServletRequestUtils.getIntParameter(request, "userid", 0);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String author = ServletRequestUtils.getStringParameter(request, "author", "");
		String text = ServletRequestUtils.getStringParameter(request, "text", "");
		
		WordBook wb = new WordBook(name);
		String[] words = text.split("///");
		wb.setAuthor(author);
		wb.setUserid(userid);
		ArrayList<Word> word = new ArrayList<Word>();
		int nextID = wordBookService.getNextID();
		int counter = 0;
		try {
			for(int i = 0; i < words.length; i+=2) {
				word.add(new Word(words[i],words[i+1]));
				word.get(counter).setWordbookid(nextID);
				wordService.createWord(word.get(counter));
				counter++;
			}
			wordBookService.createWordBook(wb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
    }
	
	
	@RequestMapping(value="/importWordBook.do")
    public @ResponseBody String importWordBook(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		int userid = ServletRequestUtils.getIntParameter(request, "userid", 0);
		
		WordBook wb = new WordBook();
		wb.setId(id);
		WordBook samewb = new WordBook();
		
		try {
			samewb = wordBookService.readWordBook(wb);
			samewb.setUserid(userid);
			samewb.setId(wordBookService.getNextID());
			wordBookService.createWordBook(samewb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
    }

	@RequestMapping(value="/deleteWordBookAndWords.do")
    public @ResponseBody String deleteWordBookAndWords(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		WordBook wb = new WordBook();
		wb.setId(id);
		Word word = new Word();
		word.setWordbookid(id);
		try {
			wordBookService.deleteWordBook(wb);
			wordService.deleteWordbookWords(word);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
    }
	
	@RequestMapping(value="/addWordBook.do")
    public @ResponseBody String addWordBook(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String author = ServletRequestUtils.getStringParameter(request, "author", "");
		int userid = ServletRequestUtils.getIntParameter(request, "userid", 0);
		
		WordBook wb = new WordBook(name);
		wb.setAuthor(author);
		wb.setUserid(userid);
		try {
			wordBookService.createWordBook(wb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
    }
	
	@RequestMapping(value="/getNextID.do")
    public @ResponseBody String getNextID() throws UnsupportedEncodingException {
		int maxidplusone = wordBookService.getNextID();		
		return "" +maxidplusone;
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
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
					
		WordBook wordBook = new WordBook(name);
		wordBook.setId(id);
		wordBook.setName(name);
		wordBook.setUserid(userId);
		
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

	@RequestMapping(value="/updateWordBook.do")
    public @ResponseBody String updateWordBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String author = ServletRequestUtils.getStringParameter(request, "author", "");
		int userid = ServletRequestUtils.getIntParameter(request, "userid", 0);
		String isFavorite = ServletRequestUtils.getStringParameter(request, "isfavorite", "");
		
		WordBook wb = new WordBook(name);
		wb.setId(id);
		wb.setAuthor(author);
		wb.setUserid(userid);
		wb.setIsfavorite(isFavorite);
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
