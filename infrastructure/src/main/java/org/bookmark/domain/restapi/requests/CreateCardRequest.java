package org.bookmark.domain.restapi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookmark.domain.commands.CardCommand;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {

  private String title;
  private String description;
  private String longUrl;
  private String creator;
  private String context;
  private String featureTeam;
  private String tribe;
  private String platform;

  public CardCommand toCardCommand(){
    return new CardCommand(
        this.title,
        this.description,
        this.longUrl,
        this.creator,
        this.context,
        this.featureTeam,
        this.tribe,
        this.platform);
  }
}
