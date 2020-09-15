package org.bookmark;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.repository.CardFJRepository;
import org.bookmark.domain.repository.CatalogueFJRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class CatalogueControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CardFJRepository repository;

  @Autowired
  private CatalogueFJRepository catalogueRepository;

  @Test
  public void should_create_a_new_catalogue_when_requested() throws Exception {
    repository.save(
        new Card("id", "TITLE", "DESC", "http://longurl", "chris/1233sadasd1233432dasd1", "ironman",
            LocalDate.now(), "chris", "ft1", "Tribe1", "PlatForm1"));
    repository.save(
        new Card("id2", "TITLE2", "DESC", "http://longurl22", "chris/12300000000000000d1",
            "ironman",
            LocalDate.now(), "chris", "ft1", "Tribe1", "PlatForm1"));

    val request = post("/catalogue")
        .contentType(APPLICATION_JSON)
        .content("{\n"
            + "    \"title\": \"TITLE\",\n"
            + "    \"category\": \"FEATURE_TEAM\",\n"
            + "    \"subCategory\": \"ft1\",\n"
            + "    \"longUrl\": \"http://localhost:8080/tiny/chris/1233sadasd1233432dasd1\",\n"
            + "    \"creator\": \"ironman\"\n"
            + "  }");

    mockMvc.perform(request)
        .andExpect(status().isCreated());
  }


  @Test
  public void should_get_all_list_of_catalogues() throws Exception {

    catalogueRepository.save(
        new Catalogue("1231434234121", "Catalogue 1", asList("card1", "card2"), "http://longUrl",
            "http://shortUrl", "ironman"));
    catalogueRepository.save(
        new Catalogue("1231434235521", "Catalogue 2", asList("card1", "card2"), "http://longUrl",
            "http://shortUrl", "ironman"));
    catalogueRepository.save(
        new Catalogue("1231434235621", "Catalogue 3", asList("card1", "card2"), "http://longUrl",
            "http://shortUrl", "ironman"));

    val request = get("/catalogues");

    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

  }

  @Test
  public void should_get_catalogue_when_requested_with_catalogue_id() throws Exception {

    catalogueRepository.save(
        new Catalogue("1231434234121", "Catalogue 1", asList("id", "id2"), "http://longUrl",
            "http://shortUrl", "ironman"));
    catalogueRepository.save(
        new Catalogue("1231434235521", "Catalogue 2", asList("id", "id2"), "http://longUrl",
            "http://shortUrl", "ironman"));
    catalogueRepository.save(
        new Catalogue("1231434235621444444", "Catalogue 3", asList("id", "id2"), "http://longUrl",
            "http://shortUrl", "ironman"));

    repository.save(
        new Card("id", "TITLE", "DESC", "http://longurl", "chris/1233sadasd1233432dasd1", "ironman",
            LocalDate.now(), "chris", "ft1", "Tribe1", "PlatForm1"));
    repository.save(
        new Card("id2", "TITLE2", "DESC", "http://longurl22", "chris/12300000000000000d1",
            "ironman",
            LocalDate.now(), "chris", "ft1", "Tribe1", "PlatForm1"));

    val request = get("/catalogue?catalogueId=" + "1231434235621444444");

    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

  }

}
