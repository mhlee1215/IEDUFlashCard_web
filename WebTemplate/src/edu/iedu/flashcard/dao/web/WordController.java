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
import edu.iedu.flashcard.dao.service.WordService;
import edu.iedu.flashcard.dao.util.MyJsonUtil;





@Controller
public class WordController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private final UserService userService = null;
	
	@Autowired
	private final WordService wordService = null;

	



	
	
	@RequestMapping(value="/addWord.do")
    public @ResponseBody String addWord(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		int wordBookId = ServletRequestUtils.getIntParameter(request, "wordBookId", -1);
		String name = ServletRequestUtils.getStringParameter(request, "word", "");
		String meaning = ServletRequestUtils.getStringParameter(request, "meaning", "");
		
		System.out.println(wordBookId+", "+name+", "+meaning);
		
		Word temp = new Word(name, meaning);
		temp.setWordbookid(wordBookId);

		System.out.println(temp);
		
		try {
			wordService.createWord(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					
		return temp.toString();
    }
	
	@RequestMapping(value="/deleteWord.do")
    public @ResponseBody String deleteWord(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		int id = ServletRequestUtils.getIntParameter(request, "id", -1);
		int wordBookId = ServletRequestUtils.getIntParameter(request, "wordBookId", -1);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String meaning = ServletRequestUtils.getStringParameter(request, "meaning", "");
		
		Word temp = new Word(name, meaning);
		temp.setId(id);
		temp.setWordbookid(wordBookId);
		
		try {
			wordService.deleteWord(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return "removed \""+name+", "+meaning+"\" with wordbookId"+ wordBookId + "and id" + id;
    }
	
	@RequestMapping(value="/updateWord.do")
    public @ResponseBody String updateWord(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		int id = ServletRequestUtils.getIntParameter(request, "id", -1);
		int wordBookId = ServletRequestUtils.getIntParameter(request, "wordBookId", -1);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String meaning = ServletRequestUtils.getStringParameter(request, "meaning", "");
		
		Word temp = new Word(name, meaning);
		temp.setId(id);
		temp.setWordbookid(wordBookId);
		
		try {
			wordService.updateWord(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return "updated";
    }
	
	@RequestMapping(value="/readWordList.do")
    public @ResponseBody String readWord(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//get parameters
		String name = ServletRequestUtils.getStringParameter(request, "word", "");
		String meaning = ServletRequestUtils.getStringParameter(request, "meaning", "");
		
		Word temp = new Word(name, meaning);
		List<Word> word = null;
		try {
			word = wordService.readWordList(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(word);
		
		return "ayy";
    }
	
	@RequestMapping(value="/readWordList.do")
    public ResponseEntity<String> readWordList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		int wordbookid = ServletRequestUtils.getIntParameter(request, "wordbookid", -1);
					
		Word word = new Word("", "");
		word.setWordbookid(wordbookid);
		
		
		List<Word> wordList = null;
		
		try {
			wordList = wordService.readWordList(word);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(wordList, "words"), responseHeaders, HttpStatus.CREATED);
    }
	
}
