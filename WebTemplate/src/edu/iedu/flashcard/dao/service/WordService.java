package edu.iedu.flashcard.dao.service;

import java.util.List;
import edu.iedu.flashcard.dao.domain.Word;


public interface WordService {
	public List<Word> findAll();
	public List<Word> readWordList(Word word) throws Exception;
	public Word readWord(Word word) throws Exception;
	public int createWord(Word word) throws Exception;
	public void updateWord(Word word);
	public int deleteWord(Word word);
	public int deleteWordbookWords(Word word);
}
