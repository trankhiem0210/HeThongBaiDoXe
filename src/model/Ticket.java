package model;

import java.time.LocalDateTime;

import enums.TicketType;

public abstract class Ticket {
    protected String ticketId;
    protected TicketType ticketType;
    protected LocalDateTime issueTime;
}
