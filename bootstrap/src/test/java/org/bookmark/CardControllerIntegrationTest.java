package org.bookmark;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.val;
import org.bookmark.domain.repository.FluentJdbcRepository;
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
  private FluentJdbcRepository repository;

  @Test
  public void should_create_a_new_card_when_requested() throws Exception {

    val request = post("/card")
        .contentType(APPLICATION_JSON)
        .content("{\n"
            + "  \"title\": \"CARD_TITLE\",\n"
            + "  \"description\": \"CARD_DESC\",\n"
            + "  \"longUrl\": \"http://longUrl\",\n"
            + "  \"creator\": \"iron.man\"\n"
            + "}");


    mockMvc.perform(request)
        .andExpect(status().isCreated());
  }
}
