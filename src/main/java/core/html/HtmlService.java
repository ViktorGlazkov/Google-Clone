package core.html;

import java.util.Set;

public interface HtmlService {
    String getHTML(String link);

    Set<String> getLinks(String html, String seed);

    String getTitle(String html);

    String getContent(String html);
}
