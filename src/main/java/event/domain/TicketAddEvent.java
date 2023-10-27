package event.domain;

import org.springframework.context.ApplicationEvent;

/**
 * チケット登録イベント.
 */
public class TicketAddEvent extends ApplicationEvent {

  private final Ticket ticket;

  public TicketAddEvent(Object source, Ticket ticket) {
    super(source);
    this.ticket = ticket;
  }

  public Ticket ticket() {
    return this.ticket;
  }
}
