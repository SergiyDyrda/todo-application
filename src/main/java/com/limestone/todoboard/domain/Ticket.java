package com.limestone.todoboard.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "tickets")
public class Ticket implements Persistable<String> {

    @Id
    private String id;
    private String name, description;
    private TicketStatus status;

    public Ticket(String name, String description, TicketStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
