package org.bookmark;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.repository.CardFJRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class CardControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CardFJRepository repository;

  @Test
  public void should_create_a_new_card_when_requested() throws Exception {

    val request = post("/card")
        .contentType(APPLICATION_JSON)
        .content("{\n"
            + "  \"title\": \"CARD_TITLE\",\n"
            + "  \"description\": \"CARD_DESC\",\n"
            + "  \"longUrl\": \"http://longUrl\",\n"
            + "  \"creator\": \"iron.man\",\n"
            + "  \"context\": \"iron.man\"\n"
            + "}");

    mockMvc.perform(request)
        .andExpect(status().isCreated());
  }


  @Test
  public void should_get_list_of_cards_when_requested_with_context() throws Exception {

    repository.save(
        new Card("id", "TITLE", "DESC", "http://longurl", "chris/1233sadasd1233432dasd1", "ironman",
            LocalDate.now(), "chris", "FT1", "Tribe1", "PlatForm1"));
    repository.save(
        new Card("id2", "TITLE2", "DESC", "http://longurl22", "chris/12300000000000000d1",
            "ironman",
            LocalDate.now(), "chris", "FT1", "Tribe1", "PlatForm1"));

    repository.save(
        new Card("id3", "TITLE2", "DESC", "http://longurl22", "chris/12304444400000000d1",
            "ironman",
            LocalDate.now(), "evans", "FT2", "Tribe1", "PlatForm1"));

    val request = get("/cards?context=" + "chris");

    val response = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    assertThat(response).isEqualTo(
        "[{\"cardId\":\"id\",\"title\":\"TITLE\",\"description\":\"DESC\",\"longUrl\":\"http://longurl\",\"shortUrl\":\"http://localhost:8080/tiny/chris/1233sadasd1233432dasd1\",\"creator\":\"ironman\",\"createdOn\":[2020,9,13],\"context\":\"chris\",\"featureTeam\":\"FT1\",\"tribe\":\"Tribe1\",\"platform\":\"PlatForm1\"},{\"cardId\":\"id2\",\"title\":\"TITLE2\",\"description\":\"DESC\",\"longUrl\":\"http://longurl22\",\"shortUrl\":\"http://localhost:8080/tiny/chris/12300000000000000d1\",\"creator\":\"ironman\",\"createdOn\":[2020,9,13],\"context\":\"chris\",\"featureTeam\":\"FT1\",\"tribe\":\"Tribe1\",\"platform\":\"PlatForm1\"}]");
  }
}
