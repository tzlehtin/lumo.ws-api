package ws.lumo.api.crawler;

/**
 * Defines a contract for a service that can crawl and ingest web content
 * for a specific adapter.
 */
public interface CrawlerServiceApi {

    /**
     * Triggers a crawl and ingest process for the given adapter ID.
     */
    void triggerCrawlForAdapter(String adapterId);
}