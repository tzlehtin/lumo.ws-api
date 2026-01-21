package ws.lumo.api.search;

import java.util.List;

/**
 * A service for performing Google searches.
 */
public interface GoogleSearchService {
    List<SearchResult> search(String query, int numResults);

    record SearchResult(String title, String link, String snippet) {}
}
