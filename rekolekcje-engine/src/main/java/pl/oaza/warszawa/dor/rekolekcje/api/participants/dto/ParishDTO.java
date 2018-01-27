package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public final class ParishDTO {

  private long id;
  private final String name;
  private final String address;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }
}
