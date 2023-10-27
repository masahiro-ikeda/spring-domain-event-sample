package event.application;

import event.domain.Ticket;
import event.domain.TicketAddEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 同期でチケット登録イベント発行するパブリッシャー.
 */
@Component
public class TicketAddEventPublisher {

  private final ApplicationEventPublisher publisher;

  TicketAddEventPublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  public void publishMessageAddEvent(Ticket ticket) {
    TicketAddEvent event = new TicketAddEvent(this, ticket);
    publisher.publishEvent(event);
  }
}
