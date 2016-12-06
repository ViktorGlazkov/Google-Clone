package core.indexer;

import core.crawler.Crawler;
import core.crawler.CrawlerService;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class IndexerServiceImpl implements IndexerService {

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void index(String link, int depth) {
        Crawler.getInstance().setParams(depth, link);

        IndexWriterConfig iwc = new IndexWriterConfig(new RussianAnalyzer());
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try {
            Directory dir  = FSDirectory.open(Paths.get("./"));
            IndexWriter iw = new IndexWriter(dir, iwc);

            indexWebPage(iw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void indexWebPage(IndexWriter w) throws IOException {

        indexDocuments(w, crawlerService.getDocuments());
    }

    @Override
    public IndexReader readIndex() throws IOException {
        Directory dir = FSDirectory.open(Paths.get("./"));
        return DirectoryReader.open(dir);
    }

    private void indexDocuments(IndexWriter w, List<Document> documents) throws IOException {
        w.addDocuments(documents);
        w.close();
    }
}
