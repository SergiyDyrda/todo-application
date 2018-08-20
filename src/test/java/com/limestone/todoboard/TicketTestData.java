package com.limestone.todoboard;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;

/**
 * Created by Sergiy Dyrda on 20.08.2018
 */
public class TicketTestData {
    private TicketTestData() {}

    public static Ticket petia_ticket_1 = new Ticket(
            "5b79c14d4488e92fc4620165",
            "develop app",
            "write test CRUD application",
            TicketStatus.TODO
    );

    public static Ticket petia_ticket_2 = new Ticket(
            "5b79c14d4488e92fc4620166",
            "wash hands",
            "get clean or die",
            TicketStatus.TODO
    );

    public static Ticket petia_ticket_3 = new Ticket(
            "5b79c14d4488e92fc4620167",
            "change shit",
            "changing something",
            TicketStatus.IN_PROGRESS
    );

    public static Ticket vasia_ticket_1 = new Ticket(
            "5b79c14d4488e92fc4620168",
            "eat",
            "make dinner and eat",
            TicketStatus.IN_PROGRESS
    );

    public static Ticket vasia_ticket_2 = new Ticket(
            "5b79c14d4488e92fc4620169",
            "Chuck Norris",
            "Chuck Norris",
            TicketStatus.COMPLETED
    );
}
