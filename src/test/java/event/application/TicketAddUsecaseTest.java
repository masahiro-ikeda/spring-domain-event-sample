package event.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketAddUsecaseTest {

  @Autowired
  TicketAddUsecase ticketAddUsecase;

  @Test
  void execute() {
    /* Given */
    String title = "AAAのテストケース作成";
    String description = "AAA機能の受入テストケースを作成する。BBBさんにレビューしてもらう必要がある。";

    /* When */
    ticketAddUsecase.execute(title, description);

    /* Then */
    // 省略
  }

}