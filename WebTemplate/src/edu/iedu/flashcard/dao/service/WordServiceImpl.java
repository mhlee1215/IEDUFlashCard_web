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

import edu.iedu.flashcard.dao.WordDao;
import edu.iedu.flashcard.dao.domain.Word;
import edu.iedu.flashcard.dao.util.Crypto;



@Service
public class WordServiceImpl implements WordService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private WordDao		wordDao;
	
	boolean isEncrypt = true;

	public List<Word> findAll() {
		
		List<Word> wordList = wordDao.readWordList(new Word("", ""));
		return wordList;
	}

	public int createWord(Word word) throws Exception {
		
		wordDao.createWord(word);
		
		return 0;
		
	}

	public List<Word> readWordList(Word word) {
		
		String name = word.getName();
		String meaning = word.getMeaning();
		List<Word> a = wordDao.readWordList(new Word(name, meaning));
		return a;
	}
	
	public Word readWord(Word word) throws Exception {
		Word x = wordDao.readWord(word);
		return x;
	}
	
	public Word readWordData(Word word) throws Exception {
		return wordDao.readWord(word);
	}

	public void updateWord(Word word) {
		wordDao.updateWord(word);
	}
	
	public int deleteWordbookWords(int wordbookId) {
		wordDao.deleteWordbookWords(wordbookId);
		
		return 0;
	}
	
	public int deleteWord(Word word) {
		wordDao.deleteWord(word);
		
		return 0;
	}
}
