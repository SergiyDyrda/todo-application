package com.limestone.todoboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String name;

    @SafeHtml
    private String description;


    @SafeHtml
    @NotEmpty
    private String status;

}
