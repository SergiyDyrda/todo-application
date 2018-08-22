package com.limestone.todoboard;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;

/**
 * Created by Sergiy Dyrda on 20.08.2018
 */
public class TicketTestData {
    private TicketTestData() {
    }

    public static Ticket petia_ticket_1 = new Ticket(
            "5b79c14d4488e92fc4620167",
            "Refresh the wardrobe",
            "It can be a miniature tree with miniature balls or a picture of the beach in Hawaii, where you want to go in the summer. In general, any cute or brutal gizmo that can motivate you to perform the tasks you set is faster and more inventive.",
            TicketStatus.IN_PROGRESS
    );

    public static Ticket petia_ticket_2 = new Ticket(
            "5b79c14d4488e92fc462016a",
            "Prepare gifts",
            "Buy bright New Year cards and thematic stamps to send out congratulatory news to friends, wherever they are. Make a list of gifts for those who are nearby, and prepare presents before the shelves are empty. Do not forget to include imagination and everything is beautiful to pack!",
            TicketStatus.COMPLETED
    );

    public static Ticket petia_ticket_3 = new Ticket(
            "5b79c14d4488e92fc4620174",
            "Write paper letters",
            "Forget about the advantages of e-mail for a minute and remember how much delight it was in childhood when parents took out multicolored envelopes and glossy postcards with beautiful stamps from the mailbox.",
            TicketStatus.TODO
    );

    public static Ticket vasia_ticket_1 = new Ticket(
            "5b79c14d4488e92fc4620177",
            "Cooking delicacies",
            "No, not complicated meat rolls and stuffed poultry. These New Year treats! Gingerbread in glaze, chocolate biscuits and sweets with a variety of fillings. Ready sweets can be eaten on their own, and can be timed for Christmas and given to caroling (if you meet such, of course).",
            TicketStatus.TODO
    );

    public static Ticket vasia_ticket_2 = new Ticket(
            "5b79c14d4488e92fc4620178",
            "Watch Christmas Movies",
            "Comedies and melodramas that make you laugh and cry, and then laugh again. Naive, simple and bright stories in the spirit of the most magical holiday. Not to enhance the skill of the movie fan, but exclusively for inspiration.",
            TicketStatus.IN_PROGRESS
    );

    public static Ticket vasia_ticket_3 = new Ticket(
            "5b79c14d4488e92fc4620179",
            "Collect winter play list",
            "Connect well-known music lovers and make up your own collection of positive New Year tracks that will not let you down on your way to work or study. Let it be a warm, cozy and necessarily cheerful list - a contrast to bad weather and cold.",
            TicketStatus.COMPLETED
    );

}
