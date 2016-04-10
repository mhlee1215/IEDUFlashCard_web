package edu.iedu.flashcard.dao;

import java.util.List;

import javax.annotation.Resource;



import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;

import edu.iedu.flashcard.dao.domain.Word;

@Repository
public class WordDaoImpl extends SqlMapClientDaoSupport implements WordDao {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Word> findAll() {	
		List<Word> array = getSqlMapClientTemplate().queryForList("WordSql.readWordList");
		return array;
	}


	public Word readWord(Word word) {
		Word result = (Word)getSqlMapClientTemplate().queryForObject("WordSql.readWord", word);
		return result;
	}


	public void createWord(Word word) {
		getSqlMapClientTemplate().insert("WordSql.createWord", word);
	}


	public void deleteWord(Word word) {
		getSqlMapClientTemplate().delete("WordSql.deleteWord", word);
		
	}


	public void updateWord(Word word) {
		getSqlMapClientTemplate().update("WordSql.updateWord", word);
	}

}
