package com.chat.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class ChatNewsRequest {

    @NotEmpty(message = "Enter comment")
    @Size(min = 2, max = 2000, message = "Size invalid")
    private String comment;

    private Integer newsId;

    @NotEmpty(message = "Enter Nickname")
    @Size(min = 2, max = 45, message = "Size invalid")
    private String name;
}
