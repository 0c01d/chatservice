package com.chat.service;

import com.chat.domain.ChatNews;
import com.chat.web.model.ChatNewsRequest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface ChatNewsService {

    Page<ChatNews> getListChatNews(Integer page, Integer size);

    ChatNews createChatNews(ChatNewsRequest chatNewsRequest);

   /* Iterable<ChatNews> findAllByName(String name);

    void deleteChatN(String name);*/
}

