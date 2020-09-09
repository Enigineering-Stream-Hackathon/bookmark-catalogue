package org.bookmark.domain.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/", glue = "org.bookmark.domain.features.steps")
public class RunCukeTest {
}
