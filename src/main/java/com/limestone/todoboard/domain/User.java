package com.limestone.todoboard.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "users")
public class User implements Persistable<String> {

    @Id
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

    private List<ObjectId> ticketIds;

    private Set<Role> roles;

    public User(ObjectId id, String name, String email, String password) {
        this(id.toString(), name, email, password, Collections.emptyList(), EnumSet.of(Role.ROLE_USER));
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

}
