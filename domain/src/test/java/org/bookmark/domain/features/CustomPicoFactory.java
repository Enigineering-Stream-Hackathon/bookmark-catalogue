package org.bookmark.domain.features;

import cucumber.runtime.java.picocontainer.PicoFactory;
import org.bookmark.domain.features.stubs.InMemoryRepositoryStub;
import org.bookmark.domain.features.stubs.TestContext;

public class CustomPicoFactory extends PicoFactory {
    public CustomPicoFactory(){
        super();
        addClass(InMemoryRepositoryStub.class);
        addClass(TestContext.class);
    }

}
