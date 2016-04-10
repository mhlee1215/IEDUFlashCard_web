package edu.iedu.flashcard.dao.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import edu.iedu.flashcard.dao.WordBookDao;
import edu.iedu.flashcard.dao.WordDao;
import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.dao.util.Crypto;



@Service
public class WordBookServiceImpl implements WordBookService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private WordBookDao		wordBookDao;
	
	@Autowired
	private WordDao		wordDao;
	
	boolean isEncrypt = true;

	public List<WordBook> findAll() {
		
		List<WordBook> wordBookList = new ArrayList<WordBook>();
		
		for(int i = 0 ; i < 10 ; i++){
			WordBook book = new WordBook("wordBook_"+i);
			book.setWordList(wordDao.findAll());
			wordBookList.add(book);
		}
		
		return wordBookList;
	}
	
	public List<WordBook> readWordBookList(WordBook wordBook) {
		
		List<WordBook> wordBookList = new ArrayList<WordBook>();
		
		for(int i = 0 ; i < 10 ; i++){
			WordBook book = new WordBook("wordBook_"+i);
			book.setWordList(wordDao.findAll());
			wordBookList.add(book);
		}
		
		return wordBookList;
	}

	public int createWordBook(WordBook word) throws Exception {
		
		return 0;
		
	}

	public WordBook readWordBook(WordBook word) throws Exception {
		WordBook book = new WordBook("MywordBook");
		book.setWordList(wordDao.findAll());
		return book;
	}
	
	public WordBook readWordBookData(WordBook word) throws Exception {
		return wordBookDao.readWordBook(word);
	}

	public void updateWordBook(WordBook word) {
		wordBookDao.updateWordBook(word);
	}
	
	public int deleteWordBook(String email) {
		return 0;
	
	}
}
