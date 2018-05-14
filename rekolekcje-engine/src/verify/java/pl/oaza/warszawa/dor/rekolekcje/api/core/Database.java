package pl.oaza.warszawa.dor.rekolekcje.api.core;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.region.RegionData;

public class Database {

  private JdbcTemplate jdbcTemplate;

  Database(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ParishData> getAllParishData() {
    return jdbcTemplate.query("SELECT * FROM parish",
        (rs, rowNum) -> ParishData.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .address(rs.getString("address"))
            .build()
    );
  }

  public ParishData getSavedParishData(Long parishId) {
    List<ParishData> foundParishes = jdbcTemplate.query("SELECT id, name, address FROM parish WHERE id = ?",
        new Object[]{parishId},
        (rs, rowNum) -> ParishData.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .address(rs.getString("address"))
            .build()
    );
    return foundParishes.stream()
        .findAny()
        .orElseThrow(() -> new ParishNotFoundException(parishId));
  }

  public ParishData getSavedParishData(String parishName) {
    List<ParishData> foundParishes = jdbcTemplate.query("SELECT id, name, address FROM parish WHERE name = ?",
        new Object[]{parishName},
        (rs, rowNum) -> ParishData.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .address(rs.getString("address"))
            .build()
    );
    return foundParishes.stream()
        .findAny()
        .orElseThrow(() -> new ParishNotFoundException(0));
  }

  public void clearParish() {
    jdbcTemplate.execute("DELETE FROM parish");
  }

  public List<RegionData> getAllRegions() {
    return jdbcTemplate.query("SELECT * FROM region",
      (rs, rowNum) -> new RegionData(rs.getLong("id"), rs.getString("name")));
  }

  public void clearRegion() {
    jdbcTemplate.execute("DELETE FROM region");
  }

}

