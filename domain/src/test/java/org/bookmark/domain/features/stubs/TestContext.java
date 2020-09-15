package org.bookmark.domain.features.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.enitites.UrlEntity;

public class TestContext {
    private List<UrlEntity> urlEntities;
    private List<Card> cards;
    private List<Catalogue> catalogues;

    public void init(){
        urlEntities = new ArrayList<>();
        cards = new ArrayList<>();
        catalogues = new ArrayList<>();
    }

    public void addUrlEntity(UrlEntity user){
        urlEntities.add(user);
    }

    public List<UrlEntity> getUrlEntities(){
        return Collections.unmodifiableList(urlEntities);
    }

    public List<Card> getCards() {
        return  Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Catalogue> getCatalogues() {
        return  Collections.unmodifiableList(catalogues);
    }

    public void addCatalogue(Catalogue catalogue) {
        catalogues.add(catalogue);
    }

    public void clearContext(){
        urlEntities.clear();
        cards.clear();
        catalogues.clear();
    }

}
