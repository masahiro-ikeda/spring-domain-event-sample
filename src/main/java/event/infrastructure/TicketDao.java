package event.infrastructure;

import event.domain.Ticket;
import event.domain.TicketRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class TicketDao implements TicketRepository {

  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  TicketDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public int nextIdentify() {
    String sql = "select nextval('seq_ticket_id');";
    return jdbcTemplate.queryForObject(sql, Integer.class);
  }

  @Override
  public void add(Ticket ticket) {
    String sql = "INSERT INTO ticket (id, title, description, created_at, notified, notified_at) "
        + "values (:id, :title, :description, :created_at, :notified, :notified_at)";
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id", ticket.id())
        .addValue("title", ticket.title())
        .addValue("description", ticket.description())
        .addValue("created_at", ticket.createdAt())
        .addValue("notified", ticket.isNotified())
        .addValue("notified_at", ticket.notifiedAt());
    namedParameterJdbcTemplate.update(sql, params);
  }

  @Override
  public void replace(Ticket ticket) {
    StringBuilder sql = new StringBuilder();
    sql.append(" UPDATE ticket SET ");
    sql.append("   title = :title ");
    sql.append("  ,description = :description ");
    sql.append("  ,created_at = :created_at ");
    sql.append("  ,notified = :notified ");
    sql.append("  ,notified_at = :notified_at ");
    sql.append(" WHERE id = :id");

    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id", ticket.id())
        .addValue("title", ticket.title())
        .addValue("description", ticket.description())
        .addValue("created_at", ticket.createdAt())
        .addValue("notified", ticket.isNotified())
        .addValue("notified_at", ticket.notifiedAt());
    namedParameterJdbcTemplate.update(sql.toString(), params);
  }

}
