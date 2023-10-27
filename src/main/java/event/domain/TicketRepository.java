package event.domain;

public interface TicketRepository {

  int nextIdentify();

  void add(Ticket ticket);

  void replace(Ticket ticket);
}
