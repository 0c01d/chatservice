package com.chat.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "chat_news", schema = "chat_service")
public class ChatNews {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "account_name")
    public String name;

    @Column(name = "idNews")
    private Integer idNews;

    @Column(name = "comment")
    public String comment;
}
