package Beans;

import java.util.HashSet;
import java.util.Set;

public class Cart {
    private Set<Ticket> tickets;

    public Cart() {
        this.tickets = new HashSet<Ticket>();
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
