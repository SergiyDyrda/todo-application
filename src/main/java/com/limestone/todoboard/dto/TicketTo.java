package com.limestone.todoboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketTo implements Serializable {
    @SafeHtml
    private String id;

    @SafeHtml
    @NotBlank(message = "name must not be blank")
    private String name;

    @SafeHtml
    private String description;


    @SafeHtml
    @NotBlank(message = "status must not be blank")
    private String status;

}
