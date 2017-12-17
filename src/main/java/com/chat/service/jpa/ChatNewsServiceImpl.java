package com.chat.service.jpa;

import com.chat.domain.ChatNews;
import com.chat.repository.ChatNewsRepository;
import com.chat.service.ChatNewsService;
import com.chat.web.model.ChatNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ChatNewsServiceImpl implements ChatNewsService {

    private final ChatNewsRepository chatNewsRepository;

    @Autowired
    public ChatNewsServiceImpl(ChatNewsRepository chatNewsRepository) {
        this.chatNewsRepository = chatNewsRepository;
    }

    @Override
    @Transactional
    public ChatNews createChatNews(ChatNewsRequest chatNewsRequest) {
        ChatNews chatNews = new ChatNews()
                .setComment(chatNewsRequest.getComment())
                .setIdNews(chatNewsRequest.getNewsId())
                .setName(chatNewsRequest.getName());
        return chatNewsRepository.save(chatNews);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChatNews> getListChatNews(Integer page, Integer size) {
        if(page == null)
            page = 0;
        if(size == null)
            size = 5;
        return chatNewsRepository.findAll(PageRequest.of(page, size));
    }

    /*@Override
    public Iterable<ChatNews> findAllByName(String name) {
       Iterable<ChatNews> chatNews = chatNewsRepository.findAllByName(name);
       return chatNews;
    }

    @Override
    public void deleteChatN(String name) {
        Iterable<ChatNews> chatNewsArrayList = chatNewsRepository.findAllByName(name);

    }*/
}