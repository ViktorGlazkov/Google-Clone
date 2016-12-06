package core.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class HtmlServiceImpl implements HtmlService {

    @Override
    public String getHTML(String link) {
        String html = "";

        try {
            html = download(link);

        } catch (InterruptedException e) {
            System.err.println("InterruptedException for: " + link);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException for: " + link);
            e.printStackTrace();
        }

        return html;
    }

    @Override
    public Set<String> getLinks(String link, String seed) {
        String html = getHTML(link);
        Set<String> links = extractLinks(html, seed);
        return links;
    }

    @Override
    public String getTitle(String html) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("title");
        if (elements != null && !elements.isEmpty()) {
            return elements.text();
        }

        return null;
    }

    @Override
    public String getContent(String html) {
        Document doc = Jsoup.parse(html);
        return doc.select("div").text();
    }

    private String download(String link) throws IOException, InterruptedException {
        IOException ioe;
        int retry = 5;

        do {
            try {
                Thread.sleep(1);
                Document bDoc = Jsoup.connect(link).userAgent("Mozilla").timeout(30000).get();
                return bDoc.html();

            } catch (IOException ex) {
                ioe = ex;
            }
        } while (--retry > 0);

        throw ioe;
    }

    private Set<String> extractLinks(String html, String seed) {
        Document document = Jsoup.parse(html, seed);
        Set<String> linksSet = new HashSet<>();

        for (Element link : document.select("a")) {
            String strLink = link.attr("abs:href").trim().toLowerCase();
            if (!strLink.isEmpty()) {
                linksSet.add(strLink);
            }
        }

        return linksSet;
    }
}
