package com.chat.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatNewsRequest {
    private String comment;
    private Integer newsId;
    private String name;
}
