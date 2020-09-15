package org.bookmark.domain.restapi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookmark.domain.commands.CatalogueCommand;
import org.bookmark.domain.commands.Category;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCatalogueRequest {

  private String title;
  private Category category;
  private String subCategory;
  private String longUrl;
  private String creator;

    public CatalogueCommand toCardCommand() {
        return new CatalogueCommand(
            title,
            category,
            subCategory,
            longUrl,
            creator
        );
    }
}
