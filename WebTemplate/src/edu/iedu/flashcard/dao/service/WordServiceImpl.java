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
		
		List<Word> wordList = new ArrayList<Word>(); 
		for(int i = 0 ; i < 30 ; i++){
			wordList.add(new Word("Word_"+i, "Meaning_"+i));
		}
		
		return wordList;
	}

	public int createWord(Word word) throws Exception {
		
		return 0;
		
	}

	public List<Word> readWordList(Word word) {
		
		List<Word> wordList = new ArrayList<Word>(); 
		for(int i = 0 ; i < 30 ; i++){
			wordList.add(new Word("Word_"+i, "Meaning_"+i));
		}
		
		return wordList;
	}
	
	public Word readWord(Word word) throws Exception {
		Word myWord = new Word("myWord", "myMeaning");
		return myWord;
	}
	
	public Word readWordData(Word word) throws Exception {
		return wordDao.readWord(word);
	}

	public void updateWord(Word word) {
		wordDao.updateWord(word);
	}
	
	public int deleteWord(String email) {
		return 0;
	
	}
}
