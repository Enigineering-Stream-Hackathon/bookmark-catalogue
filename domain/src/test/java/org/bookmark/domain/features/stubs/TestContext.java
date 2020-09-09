package org.bookmark.domain.features.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bookmark.domain.enitites.UrlEntity;

public class TestContext {
    private List<UrlEntity> urlEntities;

    public void init(){
        urlEntities = new ArrayList<>();
    }

    public void addUrlEntity(UrlEntity user){
        urlEntities.add(user);
    }

    public List<UrlEntity> getUrlEntities(){
        return Collections.unmodifiableList(urlEntities);
    }

    public void clearContext(){
        urlEntities.clear();
    }

}
