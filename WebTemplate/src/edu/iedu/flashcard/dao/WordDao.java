package edu.iedu.flashcard.dao;

import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;


public interface WordDao {
	public Word readWord(Word word);
	public int createWord(Word word);
	public void deleteWord(Word word);
	public void deleteWordbookWords(int wordbookId);
	public void updateWord(Word word);
	public List<Word> readWordList(Word word);
}
