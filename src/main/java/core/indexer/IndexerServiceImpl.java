package core.indexer;

import core.crawler.Crawler;
import core.crawler.CrawlerService;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
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

@Service
public class IndexerServiceImpl implements IndexerService {

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void index(String link, int depth) {
        Crawler.getInstance().setParams(depth, link);

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new RussianAnalyzer());
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try {
            Directory directory  = FSDirectory.open(Paths.get("./"));
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

            indexWriter.addDocuments(crawlerService.getDocuments());
            indexWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IndexReader readIndex() throws IOException {
        Directory dir = FSDirectory.open(Paths.get("./"));
        return DirectoryReader.open(dir);
    }
}

