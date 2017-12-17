package com.chat.web.controller;

import com.chat.domain.ChatNews;
import com.chat.service.ChatNewsService;
import com.chat.web.model.ChatNewsRequest;
import com.chat.web.model.ChatNewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatnews")
public class ChatNewsController {

    private final ChatNewsService chatNewsService;

    @Autowired
    public ChatNewsController(ChatNewsService chatNewsService){
        this.chatNewsService = chatNewsService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ChatNewsResponse createChatNews(@Valid @RequestBody ChatNewsRequest chatNewsRequest, HttpServletResponse response) {
        ChatNews chatNews = chatNewsService.createChatNews(chatNewsRequest);
        response.addHeader(HttpHeaders.LOCATION, "/chatnews/" + chatNews.getId());
        return new ChatNewsResponse(chatNews);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ChatNewsResponse> getListChatNews(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        Page<ChatNews> chatNewsPage = chatNewsService.getListChatNews(page, size);
        return chatNewsPage
                .stream()
                .map(ChatNewsResponse::new)
                .collect(Collectors.toList());
    }

   /* @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void deleteChatByName(@PathVariable String name) {
        chatNewsService.deleteChatN(name);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
     public Iterable<ChatNews> findAllByName(@PathVariable("name") String name) {
        Iterable<ChatNews> chatNews = chatNewsService.findAllByName(name);
        return chatNews;
    }*/

}
