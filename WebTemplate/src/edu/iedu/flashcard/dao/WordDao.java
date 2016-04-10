package edu.iedu.flashcard.dao;

import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;


public interface WordDao {
	public Word readWord(Word word);
	public void createWord(Word word);
	public void deleteWord(Word word);
	public void updateWord(Word word);
	public List<Word> findAll();
}
