package ws.lumo.api.blacklist;

/**
 * API for managing blacklisted emails and domains.
 */
public interface BlacklistApi {

    void addEmail(String email);
    void addDomain(String domain);
    boolean isBlacklisted(String item);
}