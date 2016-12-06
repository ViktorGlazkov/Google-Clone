package core.searcher;

import core.model.Message;
import org.apache.lucene.search.ScoreDoc;

import java.util.List;

public interface SearcherService {
    List<Message> search(String toSearch);

    ScoreDoc[] fuzzySearch(String toSearch);
}
