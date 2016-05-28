package edu.iedu.flashcard.dao;

import java.util.List;

import javax.annotation.Resource;



import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;

import edu.iedu.flashcard.dao.domain.WordBook;

@Repository
public class WordBookDaoImpl extends SqlMapClientDaoSupport implements WordBookDao {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<WordBook> findAll() {	
		List<WordBook> array = getSqlMapClientTemplate().queryForList("WordBookSql.readWordBookList");
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<WordBook> readWordBookList(WordBook word) {
		List<WordBook> array = getSqlMapClientTemplate().queryForList("WordBookSql.readWordBookList", word);
		return array;
	}

	public WordBook readWordBook(WordBook word) {
		return (WordBook)getSqlMapClientTemplate().queryForObject("WordBookSql.readWordBook", word);
	}


	public void createWordBook(WordBook word) {
		getSqlMapClientTemplate().insert("WordBookSql.createWordBook", word);
	}


	public void deleteWordBook(WordBook word) {
		getSqlMapClientTemplate().delete("WordBookSql.deleteWordBook", word);
		
	}


	public void updateWordBook(WordBook word) {
		getSqlMapClientTemplate().update("WordBookSql.updateWordBook", word);
	}

}
