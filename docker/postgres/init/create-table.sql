create TABLE ticket (
  id INTEGER NOT NULL PRIMARY KEY,
  title VARCHAR NOT NULL,
  description TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  notified BOOLEAN NOT NULL,
  notified_at TIMESTAMP
);

create sequence seq_ticket_id;