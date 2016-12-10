package core.indexer;

import core.model.Crawler;
import core.html.HtmlService;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentFactory {
    @Autowired
    private HtmlService htmlService;

    private Crawler crawler;
    private List<Document> documents;

    public List<Document> getDocuments() {
        this.crawler = Crawler.getInstance();
        this.documents = new ArrayList<>();

        String htmlPage = htmlService.getHTML(crawler.getSeed());
        parseDocuments(htmlPage, 0);
        return documents;
    }

    private void parseDocuments(String html, int step) {
        if (step == crawler.getDepth()) {
            return;
        }

        String seed = crawler.getSeed();

        for (String url : htmlService.getLinks(html, seed)) {
            if (!crawler.getLinks().contains(url)) {
                crawler.getLinks().add(url);

                String htmPage = htmlService.getHTML(url);
                this.documents.add(getDocument(htmPage, url));
                parseDocuments(htmPage, step + 1);
            }
        }
    }

    private Document getDocument(String html, String url) {

        String title = htmlService.getTitle(html);
        String content = htmlService.getContent(html);

        if (title == null) {
            title = "";
        }

        if (content == null) {
            content = "";
        }

        return createWith(title, content, url);
    }

    private static Document createWith(String titleStr, String bodyStr, String url) {
        Document document = new Document();

        FieldType textIndexedType = new FieldType();
        textIndexedType.setStored(true);
        textIndexedType.setIndexOptions(IndexOptions.DOCS);
        textIndexedType.setTokenized(true);

        Field title = new Field("title", titleStr, textIndexedType);
        Field body = new Field("body", bodyStr, textIndexedType);
        Field link = new Field("link", url, textIndexedType);

        document.add(title);
        document.add(body);
        document.add(link);
        return document;
    }
}
