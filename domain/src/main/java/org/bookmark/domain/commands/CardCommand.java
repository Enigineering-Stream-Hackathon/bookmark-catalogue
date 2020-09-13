package org.bookmark.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardCommand {
  private String title;
  private String description;
  private String longUrl;
  private String creator;
  private String context;
  private String featureTeam;
  private String tribe;
  private String platform;
}
