package core.model;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.ScoreDoc;

import java.io.IOException;

public class Message {
    private String title;
    private String url;


    public Message(ScoreDoc hit, IndexReader reader) {
        try {
            this.title = reader.document(hit.doc).get("title");
            this.url = reader.document(hit.doc).get("link");

        } catch (IOException e) {
            System.err.println("Can't read document:" + hit.doc);
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
