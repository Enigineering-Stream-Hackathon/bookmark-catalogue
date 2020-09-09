package org.bookmark;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.enitites.UrlEntity;
import org.bookmark.domain.repository.FluentJdbcRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class UrlShorterControllerIntegrationTest {

  private final static String LONG_URL = "https://datackexhangry/58883/test-long-url";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private FluentJdbcRepository repository;

  @Autowired
  private ObjectMapper mapper;


  @Test
  public void should_create_short_url_when_long_url_is_provided() throws Exception {

    val request = post("/short-url")
        .contentType(APPLICATION_JSON)
        .content("{\n"
            + "  \"longUrl\": \"https://datackexhangry/58883/test-long-url\",\n"
            + "  \"expiryDate\": \"2020-10-10\"\n"
            + "}");
    // When
    val response = mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    // Then
    assertThat(response).isEqualTo("{\"shortUrl\":\"http://localhost:8080/e2dac7cff26fbec68f68b84adfc55ce949402ce1dd194a1c6b162d6d35502db9\"}");

  }

  @Test
  public void should_create_student_details_when_post_request() throws Exception {

    repository.save(new UrlEntity("75bd8d128757f634f1b55ac9c201ccfe736c0dec51c95ab87b8fecfa6ce255b4", LONG_URL, LocalDate.MAX, LocalDate.now()));

    val request = get("/75bd8d128757f634f1b55ac9c201ccfe736c0dec51c95ab87b8fecfa6ce255b4");
    // When
    val response = mockMvc.perform(request)
        .andExpect(status().isFound());
  }


}
