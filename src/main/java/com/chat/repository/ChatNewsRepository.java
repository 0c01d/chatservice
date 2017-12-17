package com.chat.repository;

import com.chat.domain.ChatNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ChatNewsRepository extends JpaRepository<ChatNews, Integer> {

   /* void deleteChatNewsByName(String chatNews);

    Iterable<ChatNews> findAllByName(String name);*/
}
