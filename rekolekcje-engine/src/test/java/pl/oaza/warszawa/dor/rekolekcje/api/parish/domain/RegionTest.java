package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionNotFoundException;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class RegionTest {

  private String names[] = {
    "Crystal Oasis",
    "Desert Highlands",
    "Elon Reverlands",
    "The Desolation",
    "Domain of Vabbi",
    "Domain of Istan",
    "Sandswept Isles",
  };

  @Test
  public void shouldAddAndRetrieveRegions() {
    RegionsService regionsService = new RegionsConfiguration().regionsService();

    // Add
    for (int i = 0; i < names.length; ++i) {
      RegionDTO ret = regionsService.save(new RegionDTO(null, names[i]));
      assertThat(ret)
        .hasFieldOrPropertyWithValue("id", (long)i + 1)
        .hasFieldOrPropertyWithValue("name", names[i]);
    }

    //Retrieve one
    Random rng = new Random();
    for (int i = 0; i < names.length; ++i) {
      assertThat(regionsService.findOne(i + 1)).hasFieldOrPropertyWithValue("name", names[i]);
    }

    //Retrieve all
    assertThat(regionsService.findAll().stream().map(e -> e.getName()))
      .hasSize(names.length)
      .containsAll(Arrays.asList(names));
  }

  @Test
  public void shouldDeleteRegion() {
    RegionsService regionsService = new RegionsConfiguration().regionsService();
    regionsService.save(new RegionDTO(null, "Metrica Province"));
    RegionDTO toDelete = regionsService.save(new RegionDTO(null, "Brisban Wildlands"));
    regionsService.save(new RegionDTO(null, "Caledon Forest"));

    assertThat(regionsService.findAll()).hasSize(3);
    regionsService.delete(toDelete.getId());
    assertThat(regionsService.findAll().stream().map(e -> e.getName()))
      .hasSize(2)
      .containsOnlyOnce("Metrica Province", "Caledon Forest");
  }

  @Test
  public void shouldThrowRegionNotFound() {
    RegionsService regionsService = new RegionsConfiguration().regionsService();
    assertThatExceptionOfType(RegionNotFoundException.class)
      .isThrownBy(() -> regionsService.findOne(1));

    for (int i = 0; i < names.length; ++i) {
      regionsService.save(new RegionDTO(null, names[i]));
    }
    assertThat(regionsService.findOne(2).getName()).isEqualTo(names[1]);
    regionsService.delete(2);
    assertThatExceptionOfType(RegionNotFoundException.class)
      .isThrownBy(() -> regionsService.findOne(2));
  }

  @Test
  public void shouldUpdateRegion() {
    RegionsService regionsService = new RegionsConfiguration().regionsService();
    for (int i = 0; i < names.length; ++i) {
      regionsService.save(new RegionDTO(null, names[i]));
    }

    regionsService.update(new RegionDTO(1L, "Free City of Amnoon"));
    assertThat(regionsService.findOne(1))
      .hasFieldOrPropertyWithValue("name", "Free City of Amnoon");
  }

  @Test
  public void shouldNotAddWhenUpdating() {
    RegionsService regionsService = new RegionsConfiguration().regionsService();
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> regionsService.update(new RegionDTO(null, "Verdant Brink")));
    assertThat(regionsService.findAll()).hasSize(0);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> regionsService.update(new RegionDTO(42L, "Auric Basin")));
    assertThat(regionsService.findAll()).hasSize(0);
  }

}
