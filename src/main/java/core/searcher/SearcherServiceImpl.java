package core.searcher;

import core.indexer.IndexerService;
import core.model.Message;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearcherServiceImpl implements SearcherService {
    @Autowired
    private IndexerService indexerService;

    private static final int DEFAULT_LIMIT = 10;

    @Override
    public List<Message> search(String toSearch) {
        List<Message> messageList = new ArrayList<>();
        for (ScoreDoc doc : fuzzySearch(toSearch.toLowerCase())) {
            try {
                messageList.add(new Message(doc, indexerService.readIndex()));

            } catch (IOException e) {
                System.err.println("Can't read index");
                e.printStackTrace();
            }
        }

        return messageList;
    }

    public ScoreDoc[] fuzzySearch(String toSearch) {
        try {
            return fuzzySearch(toSearch, DEFAULT_LIMIT);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ScoreDoc[] fuzzySearch(String toSearch, int limit) throws IOException, ParseException {
        IndexSearcher indexSearcher = new IndexSearcher(indexerService.readIndex());

        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
                new String[]{"body", "title"},
                new RussianAnalyzer());

        TopDocs search = indexSearcher.search(queryParser.parse(toSearch), limit, Sort.RELEVANCE);

        return search.scoreDocs;
    }
}
