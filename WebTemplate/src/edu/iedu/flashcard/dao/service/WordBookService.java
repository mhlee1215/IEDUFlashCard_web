package edu.iedu.flashcard.dao.service;

import java.util.List;
import edu.iedu.flashcard.dao.domain.WordBook;


public interface WordBookService {
	public List<WordBook> findAll();
	public List<WordBook> readWordBookList(WordBook word) throws Exception;
	public WordBook readWordBook(WordBook word) throws Exception;
	public int createWordBook(WordBook word) throws Exception;
	public void updateWordBook(WordBook word);
	public int deleteWordBook(String id);
}
