package org.bookmark.domain;

import org.bookmark.domain.enitites.UrlEntity;

public interface UrlRepository {

    void save(UrlEntity entity);

    UrlEntity get(String shortUrl);
}
