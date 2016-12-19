package core.searcher;

import core.model.Message;

import java.util.List;

public interface SearcherService {
    List<Message> search(int page, String toSearch);

    int count(String toSearch);
}
