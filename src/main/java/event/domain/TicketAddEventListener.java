package event.domain;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * チケット登録イベントのリスナー.
 */
@Component
public class TicketAddEventListener {

  private final TicketRepository ticketRepository;

  TicketAddEventListener(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  /**
   * チケット登録後にメール通知等を行うことを想定.
   */
  @EventListener
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public void handle(TicketAddEvent event) {
    // TODO: ドメインサービス化するのが理想的

    /*
     * 同期処理の場合はトランザクションを引き継ぎたい。また非同期処理の場合は新たなトランザクションを開始したい。
     * そのためPropagation.REQUIREDを明示的に使用。
     */

    Ticket ticket = event.ticket();

    // メール送信の代わり
    System.out.println("===========================================");
    System.out.println("新しいチケットが登録されました。");
    System.out.println(String.format("タイトル：%s", ticket.title()));
    System.out.println(String.format("概要：%s", ticket.description()));
    System.out.println("===========================================");

    ticket.executeNotify();
    ticketRepository.replace(ticket);

    // 実際のトランザクションの挙動を見る場合は↓を有効にする
    // throw new RuntimeException();
  }
}
