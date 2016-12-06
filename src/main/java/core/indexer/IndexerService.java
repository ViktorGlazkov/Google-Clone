package core.indexer;

import org.apache.lucene.index.IndexReader;

import java.io.IOException;

public interface IndexerService {
    void index(String link, int depth);

    IndexReader readIndex() throws IOException;
}
