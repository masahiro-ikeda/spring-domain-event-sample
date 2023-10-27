package event.domain;

import event.domain.Ticket;
import event.domain.TicketAddEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 非同期でチケット登録イベント発行するパブリッシャー.
 */
@Component
public class TicketAddEventAsyncPublisher {

  private final ApplicationEventPublisher publisher;

  TicketAddEventAsyncPublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Async
  public void publishMessageAddEvent(Ticket ticket) {
    TicketAddEvent event = new TicketAddEvent(this, ticket);
    publisher.publishEvent(event);
  }
}
