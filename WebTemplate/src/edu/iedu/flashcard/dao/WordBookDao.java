package edu.iedu.flashcard.dao;

import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;


public interface WordBookDao {
	public WordBook readWordBook(WordBook word);
	public int createWordBook(WordBook word);
	public void deleteWordBook(WordBook word);
	public void updateWordBook(WordBook word);
	public List<WordBook> readWordBookList(WordBook word);
	public List<WordBook> findAll();
	public int getNextID();
}
