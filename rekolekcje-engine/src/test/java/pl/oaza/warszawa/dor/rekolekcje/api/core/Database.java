package pl.oaza.warszawa.dor.rekolekcje.api.core;

import org.springframework.jdbc.core.JdbcTemplate;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;

import java.util.List;

public class Database {

  private JdbcTemplate jdbcTemplate;

  Database(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public ParishData getSavedParishData(String parishName) {
    List<ParishData> foundParishes = jdbcTemplate.query("SELECT id, name, address FROM parish WHERE name = ?",
        new Object[]{parishName},
        (rs, rowNum) -> new ParishData(rs.getLong("id"), rs.getString("name"), rs.getString("address"))
    );
    return foundParishes.stream().findAny().orElseThrow(RuntimeException::new);
  }
}

