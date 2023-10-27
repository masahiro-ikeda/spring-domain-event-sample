package event.application;

import event.domain.Ticket;
import event.domain.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketAddAsyncUsecase {

  private final TicketRepository ticketRepository;
  private final TicketAddEventAsyncPublisher publisher;

  TicketAddAsyncUsecase(TicketRepository ticketRepository, TicketAddEventAsyncPublisher publisher) {
    this.ticketRepository = ticketRepository;
    this.publisher = publisher;
  }

  /**
   * チケット登録（イベント非同期）.
   */
  @Transactional(rollbackFor = Exception.class)
  public void execute(String title, String description) {
    int id = ticketRepository.nextIdentify();
    Ticket ticket = new Ticket(id, title, description);
    ticketRepository.add(ticket);

    publisher.publishMessageAddEvent(ticket);
  }

}
