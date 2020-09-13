package org.bookmark.domain.services;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import lombok.val;

public class GenerateShortUrl {


  public String getShortUrl(String longUrl, String context) {
    val generateId = Hashing.sha256()
        .hashString(longUrl, StandardCharsets.UTF_8)
        .toString();

    return context.concat("/").concat(generateId);
  }

}
