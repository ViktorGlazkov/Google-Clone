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

    @Autowired
    private IndexSearcher indexSearcher;

    @Autowired
    private MultiFieldQueryParser queryParser;

    private static final int DEFAULT_LIMIT = 10;

    @Override
    public List<Message> search(int page, String toSearch) {
        List<Message> messageList = new ArrayList<>();
        for (ScoreDoc doc : fuzzySearch(page, toSearch)) {
            try {
                messageList.add(new Message(doc, indexerService.readIndex()));

            } catch (IOException e) {
                System.err.println("Can't read index");
                e.printStackTrace();
            }
        }

        return messageList;
    }

    @Override
    public int count(String toSearch) {
        try {
            return indexSearcher.count(queryParser.parse(toSearch));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private ScoreDoc[] fuzzySearch(int page, String toSearch) {
        try {
            return fuzzySearch(page, toSearch, DEFAULT_LIMIT);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ScoreDoc[] fuzzySearch(int page, String toSearch, int limit) throws IOException, ParseException {



        TopScoreDocCollector collector = TopScoreDocCollector.create(9999);
        int startIndex = (page-1) * limit;
        Query query = queryParser.parse(toSearch);
        indexSearcher.search(query, collector);
        TopDocs search = collector.topDocs(startIndex, limit);

//        TopDocs search = indexSearcher.search(queryParser.parse(toSearch), limit, Sort.RELEVANCE);

        return search.scoreDocs;
    }
}
