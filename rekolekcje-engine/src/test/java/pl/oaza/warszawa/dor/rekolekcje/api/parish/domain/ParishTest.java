package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import org.junit.After;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParishTest {

  private ParishDTO firstParish = createParish(1L,"First", "Address no 1.");
  private ParishDTO secondParish = createParish(2L,"Second", "Address no 2.");

  private ParishDTO createParish(long id, String name, String address) {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }

  private ParishService parishService = new ParishConfiguration().parishService();

  @After
  public void tearDown() {
    parishService.delete(firstParish.getId(), secondParish.getId());
  }

  @Test
  public void shouldAddNewParish() {
    parishService.add(firstParish);

    final ParishDTO foundParish = parishService.findOne(firstParish.getId());
    assertThat(foundParish).isEqualTo(firstParish);
  }

  @Test
  public void shouldGetAllParishes() {
    parishService.add(firstParish);
    parishService.add(secondParish);

    final List<ParishDTO> foundParishes = parishService.findAll();
    assertThat(foundParishes).hasSize(2);
    assertThat(foundParishes).containsOnlyOnce(firstParish, secondParish);
  }

  @Test
  public void shouldGetSingleParish() {
    parishService.add(firstParish);
    parishService.add(secondParish);

    final ParishDTO foundParish = parishService.findOne(secondParish.getId());
    assertThat(foundParish).isEqualTo(secondParish);
  }

  @Test
  public void shouldDeleteParish() {
    parishService.add(firstParish);
    parishService.add(secondParish);

    parishService.delete(firstParish.getId());

    final List<ParishDTO> remainingParishes = parishService.findAll();
    assertThat(remainingParishes).hasSize(1);
    assertThat(remainingParishes).containsOnly(secondParish);
  }
}
