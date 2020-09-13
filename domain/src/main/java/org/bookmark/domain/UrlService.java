package org.bookmark.domain;

import static java.util.Objects.nonNull;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.bookmark.domain.commands.UrlCommand;
import org.bookmark.domain.enitites.UrlEntity;
import org.bookmark.domain.exception.UrlExpiredException;

@Slf4j
@AllArgsConstructor
public class UrlService {

  private final UrlRepository repository;

  public String createShortUrl(UrlCommand command) {
    val url = Hashing.sha256()
        .hashString(command.getLongUrl(), StandardCharsets.UTF_8)
        .toString();
    log.info("Shorter url id generated : {}", url);
    val entity = new UrlEntity(url, command.getLongUrl(), command.getExpiryDate(), LocalDate.now());
    repository.save(entity);
    return url;
  }

  public String getLongUrl(String shortUrl) {
    val entity =  repository.get(shortUrl);
    if(nonNull(entity) && entity.getExpiryDate().isAfter(LocalDate.now())) {
      return entity.getLongUrl();
    }
    throw new UrlExpiredException();
  }

}
