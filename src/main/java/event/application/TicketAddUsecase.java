package event.application;

import event.domain.Ticket;
import event.domain.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketAddUsecase {

  private final TicketRepository ticketRepository;
  private final TicketAddEventPublisher publisher;

  TicketAddUsecase(TicketRepository ticketRepository, TicketAddEventPublisher publisher) {
    this.ticketRepository = ticketRepository;
    this.publisher = publisher;
  }

  /**
   * チケット登録.
   */
  @Transactional(rollbackFor = Exception.class)
  public void execute(String title, String description) {
    int id = ticketRepository.nextIdentify();
    Ticket ticket = new Ticket(id, title, description);
    ticketRepository.add(ticket);

    publisher.publishMessageAddEvent(ticket);
  }

}
