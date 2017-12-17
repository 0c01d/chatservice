package com.chat.web;

import com.chat.domain.ChatNews;
import com.chat.repository.ChatNewsRepository;
import com.chat.web.model.ChatNewsRequest;
import com.chat.web.model.ChatNewsResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DisplayName("ChatController Test")
class ChatNewsControllerTest {

    private final ChatNews chatNews = new ChatNews()
            .setId(1)
            .setIdNews(1)
            .setName("Name")
            .setComment("Comment");


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ChatNewsRepository chatNewsRepositoryMock;

    @Test
    @DisplayName("Create ChatNews")
    void createChatNews() {
        ChatNews chatNewsNoId = new ChatNews()
                .setIdNews(1)
                .setName("Name")
                .setComment("Comment");
        when(chatNewsRepositoryMock.save(chatNewsNoId)).thenReturn(chatNews);

        ChatNewsRequest chatNewsRequest = new ChatNewsRequest()
                .setNewsId(chatNews.getIdNews())
                .setName(chatNews.getName())
                .setComment(chatNews.getComment());

        ChatNewsResponse expectedResponse = new ChatNewsResponse(chatNews);

        ChatNewsResponse actualResponse = restTemplate.postForObject("/chatnews/", chatNewsRequest, ChatNewsResponse.class);

        verify(chatNewsRepositoryMock, times(1)).save(chatNewsNoId);
        assertEquals("Invalid user response", expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Get News List")
    void getNewsList() throws IOException {

        List<ChatNews> chatNewsList = new ArrayList<ChatNews>() {{
            add(chatNews);
        }};

        PageRequest pr = PageRequest.of(0, 5);
        PageImpl<ChatNews> chatNewsPage = new PageImpl<>(chatNewsList, pr, 100);

        when(chatNewsRepositoryMock.findAll(any(Pageable.class))).thenReturn(chatNewsPage);

        List<ChatNewsResponse> expectedResponse = chatNewsList
                .stream()
                .map(ChatNewsResponse::new)
                .collect(Collectors.toList());

        String responseString = restTemplate.getForObject("/chatnews", String.class);
        List<ChatNewsResponse> actualResponse = new ObjectMapper().readValue(responseString, new TypeReference<List<ChatNewsResponse>>() {});
        assertEquals("Invalid response", expectedResponse, actualResponse);
    }
}
