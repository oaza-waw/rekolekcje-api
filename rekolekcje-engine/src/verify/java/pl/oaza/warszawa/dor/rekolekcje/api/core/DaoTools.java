package pl.oaza.warszawa.dor.rekolekcje.api.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DaoTools {

  public static Long getLong(ResultSet resultSet, String columnName) throws SQLException {
    long value = resultSet.getLong(columnName);
    return resultSet.wasNull() ? null : value;
  }

  public static Integer getInt(ResultSet resultSet, String columnName) throws SQLException {
    int value = resultSet.getInt(columnName);
    return resultSet.wasNull() ? null : value;
  }

  public static LocalDateTime getLocalDate(ResultSet resultSet, String columnName) throws SQLException {
    Timestamp timestamp = resultSet.getTimestamp(columnName);
    return timestamp != null ? timestamp.toLocalDateTime() : null;
  }
}
