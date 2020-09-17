package org.bookmark.domain.services;

import java.time.LocalDate;
import java.util.List;
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
    val shortUrl = new GenerateShortUrl().getShortUrl(command.getLongUrl(), command.getContext());
    val card = new Card(
        UUID.randomUUID().toString(),
        command.getTitle(),
        command.getDescription(),
        command.getLongUrl(),
        shortUrl,
        command.getCreator(),
        LocalDate.now(),
        command.getContext(),
        command.getFeatureTeam(),
        command.getTribe(),
        command.getPlatform());
    repository.save(card);
  }

  public List<Card> getCardsByContext(String context) {
    val cards = repository.getByContext(context);
    cards.forEach(it -> it.setShortUrl("https://bookmark-catalogue.herokuapp.com/tiny/".concat(it.getShortUrl())));
    return cards;
  }

  public String getCardLongUrl(String context, String shortUrl) {
    return repository.getByContext(context).stream()
        .filter(it -> it.getShortUrl().contains(shortUrl))
        .findFirst()
        .map(Card::getLongUrl)
        .orElse(null);
  }

}
