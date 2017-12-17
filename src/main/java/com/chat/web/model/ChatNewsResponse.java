package com.chat.web.model;

import com.chat.domain.ChatNews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatNewsResponse {
    private Integer id;
    private String comment;
    private Integer newsId;
    private String name;

    public ChatNewsResponse(ChatNews chatNews) {
        this.id = chatNews.getId();
        this.comment = chatNews.getComment();
        this.newsId = chatNews.getIdNews();
        this.name = chatNews.getName();
    }
}