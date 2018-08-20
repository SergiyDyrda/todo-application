package com.limestone.todoboard.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTo implements Serializable {

    @NotEmpty
    private String id;

    @NotEmpty
    @SafeHtml
    private String name;

    @Email
    @NotEmpty
    @SafeHtml
    private String email;

    @Length(min = 5)
    @NotEmpty
    @SafeHtml
    private String password;
}
