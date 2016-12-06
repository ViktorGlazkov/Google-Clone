package core.indexer;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

public interface IndexerService {
    void index(String link, int depth);

    void indexWebPage(IndexWriter w) throws IOException;

    IndexReader readIndex() throws IOException;
}
