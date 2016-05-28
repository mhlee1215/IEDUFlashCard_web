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
	
	@Autowired
	private WordService		wordService;
	
	
	boolean isEncrypt = true;

	public List<WordBook> findAll() {
		return wordBookDao.findAll();
	}
	
	public List<WordBook> readWordBookList(WordBook word) throws Exception {
		List<WordBook> wordBookList = wordBookDao.readWordBookList(word);
		return wordBookList;
	}

	public int createWordBook(WordBook word) throws Exception {
		//Get next id
		int nextId = wordBookDao.getNextID();
		word.setId(nextId);
		wordBookDao.createWordBook(word);
		return nextId;
	}

	public WordBook readWordBook(WordBook word) throws Exception {
		return wordBookDao.readWordBook(word);
	}
	
	public WordBook readWordBookData(WordBook word) throws Exception {
		return wordBookDao.readWordBook(word);
	}

	public void updateWordBook(WordBook word) {
		wordBookDao.updateWordBook(word);
	}
	
	public int deleteWordBook(WordBook word) {
		wordBookDao.deleteWordBook(word);
		return 0;
	}
	
	public int getNextID() {
		return wordBookDao.getNextID();
	}
}
