package core.crawler;

import core.html.HtmlService;
import core.indexer.DocumentFactory;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    @Autowired
    HtmlService htmlService;

    Crawler crawler;

    @Override
    public List<Document> getDocuments() {
        this.crawler = Crawler.getInstance();
        addInnerLinks();

        List<Document> documents = new ArrayList<>();
        for (String link : this.crawler.getLinks()) {
            documents.add(getDocument(link));
        }

        return documents;
    }

    private void addInnerLinks() {
        crawler.getLinks().add(crawler.getSeed());

        for(String link : htmlService.getLinks(crawler.getSeed(), crawler.getSeed())) {
            crawler.getLinks().add(link);
            addInnerLinks(link, 1);
        }
    }

    private void addInnerLinks(String link, int step) {
        if(step == crawler.getDepth()) {
            return;
        }

        for(String url : htmlService.getLinks(link, crawler.getSeed())) {
            if (!crawler.getLinks().contains(url)) {
                crawler.getLinks().add(url);
                addInnerLinks(url, step++);
            }
        }
    }

    private Document getDocument(String link) {
        String html = htmlService.getHTML(link);

        String title = htmlService.getTitle(html);
        String content = htmlService.getContent(html);

        if(title == null) {
            title = "";
        }

        if(content == null) {
            content = "";
        }

        return DocumentFactory.createWith(title.toLowerCase(), content.toLowerCase(), link);
    }
}
