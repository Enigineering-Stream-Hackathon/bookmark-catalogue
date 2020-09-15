package org.bookmark.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CatalogueCommand {
  private String title;
  private Category category;
  private String subCategory;
  private String longUrl;
  private String creator;
}
