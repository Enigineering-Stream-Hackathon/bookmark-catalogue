package org.bookmark.domain.services;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.val;
import org.bookmark.domain.commands.CardCommand;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.repositories.CardRepository;

@AllArgsConstructor
public class CardService {

  private final CardRepository repository;

  public void create(CardCommand command) {
    val shortUrl = Hashing.sha256()
        .hashString(command.getLongUrl(), StandardCharsets.UTF_8)
        .toString();
    val card = new Card(UUID.randomUUID().toString(), command.getTitle(), command.getDescription(),
        command.getLongUrl(), shortUrl, command.getCreator(),
        LocalDate.now());
    repository.save(card);
  }

}
