package event.domain;

import java.time.LocalDateTime;

/**
 * タイトルと概要を持つ簡単なチケット.
 */
public class Ticket {
  private int id;
  private String title;
  private String description;
  private LocalDateTime createdAt;
  private boolean notified;
  private LocalDateTime notifiedAt;

  public Ticket(int id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.createdAt = LocalDateTime.now();
    this.notified = false;
    this.notifiedAt = null;
  }

  public int id() {
    return id;
  }

  public String title() {
    return title;
  }

  public String description() {
    return description;
  }

  public LocalDateTime createdAt() {
    return createdAt;
  }

  public boolean isNotified() {
    return notified;
  }

  public LocalDateTime notifiedAt() {
    return notifiedAt;
  }

  /**
   * チケット登録されたことを通知する.
   */
  public void executeNotify() {
    if (isNotified()) {
      throw new IllegalStateException("Registration notification has been completed.");
    }

    this.notified = true;
    this.notifiedAt = LocalDateTime.now();
  }
}
