package event.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketAddAsyncUsecaseTest {

  @Autowired
  TicketAddAsyncUsecase ticketAddAsyncUsecase;

  @Test
  void execute() {
    /* Given */
    String title = "XXXの仕様確認";
    String description = "XXX機能のYYYについて、ZZZという解釈で問題ないか実装者に確認する。15時まで。";

    /* When */
    ticketAddAsyncUsecase.execute(title, description);

    /* Then */
    // 省略
  }

}