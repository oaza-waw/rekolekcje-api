package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

class DateConverter {

  private DateConverter() {}

  static ZonedDateTime convertToUtc(LocalDateTime dateTime) {
    return dateTime != null ? ZonedDateTime.of(dateTime, ZoneId.of("UTC")) : null;
  }

  static LocalDateTime convertToDateTime(ZonedDateTime zonedDateTime) {
    return zonedDateTime != null ? zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime() : null;
  }
}
