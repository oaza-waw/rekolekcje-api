package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import org.junit.After;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishNotFoundException;

public class ParishTest {

  private ParishDTO firstParish = createParish(1L,"First", "Address no 1.");
  private ParishDTO secondParish = createParish(2L,"Second", "Address no 2.");

  private ParishService parishService = new ParishConfiguration().parishService();

  @After
  public void tearDown() {
    parishService.delete(firstParish.getId(), secondParish.getId());
  }

  @Test
  public void shouldAddNewParish() {
    parishService.save(firstParish);

    final ParishDTO foundParish = parishService.findOne(firstParish.getId());
    assertThat(foundParish).isEqualTo(firstParish);
  }

  @Test
  public void shouldGetAllParishes() {
    parishService.save(firstParish);
    parishService.save(secondParish);

    final List<ParishDTO> foundParishes = parishService.findAll();
    assertThat(foundParishes).hasSize(2);
    assertThat(foundParishes).containsOnlyOnce(firstParish, secondParish);
  }

  @Test
  public void shouldGetSingleParish() {
    parishService.save(firstParish);
    parishService.save(secondParish);

    final ParishDTO foundParish = parishService.findOne(secondParish.getId());
    assertThat(foundParish).isEqualTo(secondParish);
  }

  @Test
  public void shouldThrowExceptionWhenParishNotFound() {
    parishService.save(firstParish);

    assertThatExceptionOfType(ParishNotFoundException.class)
        .isThrownBy(() -> parishService.findOne(secondParish.getId()))
        .withMessageContaining("id " + secondParish.getId());
  }

  @Test
  public void shouldDeleteParish() {
    parishService.save(firstParish);
    parishService.save(secondParish);

    parishService.delete(firstParish.getId());

    final List<ParishDTO> remainingParishes = parishService.findAll();
    assertThat(remainingParishes).hasSize(1);
    assertThat(remainingParishes).containsOnly(secondParish);
  }

  @Test
  public void shouldUpdateParish() {
    parishService.save(firstParish);
    parishService.save(secondParish);
    final ParishDTO parishWithUpdatedData =
        createParish(secondParish.getId(), "New name", "New address");

    parishService.save(parishWithUpdatedData);

    final ParishDTO foundParish = parishService.findOne(secondParish.getId());
    assertThat(foundParish).isEqualTo(parishWithUpdatedData);
  }

  private ParishDTO createParish(long id, String name, String address) {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }
}
