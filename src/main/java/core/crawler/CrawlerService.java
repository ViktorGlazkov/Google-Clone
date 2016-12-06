package core.crawler;

import org.apache.lucene.document.Document;

import java.util.List;

public interface CrawlerService {
    List<Document> getDocuments();
}
