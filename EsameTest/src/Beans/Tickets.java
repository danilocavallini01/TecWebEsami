package Beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Tickets implements Serializable {
    Set<Ticket> tickets;

    public Tickets() {
        tickets = new HashSet<Ticket>();
    }

    public void addTicket(Ticket t) {
        this.tickets.add(t);
    }

    public Ticket get(int id) {
        for (Ticket t : this.tickets) {
            if ( id == t.getId() ) {
                return t;
            }
        }
        return null;
    }

    public void empty() {
        this.tickets.clear();
    }
    
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
