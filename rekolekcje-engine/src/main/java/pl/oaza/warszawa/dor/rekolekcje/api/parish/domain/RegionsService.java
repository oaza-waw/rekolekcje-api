package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Objects.requireNonNull;

public class RegionsService {

  private final RegionsRepository repository;

  public RegionsService(RegionsRepository repository) {
    this.repository = repository;
  }

  /**
   * Create list of all regions
   * @return list of all regions
   */
  public List<RegionDTO> findAll() {
    return repository.findAll().stream()
            .map(Region::dto)
            .collect(Collectors.toList());
  }

  /**
   * Save Region data (aka "add or update").
   * Regions with <code>id == 0</code> or <code>null</code> should be added and assigned next ID. Regions
   * with id found in the repository should be updated. If no region with such ID found,
   * exception shall be thrown.
   * @param data region's data
   * @return regions data with assigned ID, if <code>data.getId() == null</code>; object
   * equal to <code>data</code> otherwise.
   */
  public RegionDTO save(RegionDTO data) {
    requireNonNull(data);
    Region region = new Region(data.getId(), data.getName());
    region = repository.save(region);
    return region.dto();
  }

  /**
   * Find one region id; throw <code>RegionNotFoundException</code> if not found.
   * @param byId ID of region to retrieve
   * @return region from the repository with <code>.getId() == byId</code>
   */
  public RegionDTO findOne(long byId) {
    Region found = repository.findOne(byId);
    if (found != null) {
      return found.dto();
    }
    throw new RegionNotFoundException(byId);
  }

  /**
   * Delete all regions with id that occur in <code>ids</code>.
   * @param ids list of IDs assigned with to-be-deleted regions
   */
  public void delete(long... ids) {
    requireNonNull(ids);
    Arrays.stream(ids).forEach(repository::delete);
  }

  /**
   * Update existing region.
   * If region has invalid ID (<code>null</code> or not found in repository), then
   * <code>IllegalArgumentException</code> is thrown.
   * @param regionWithUpdatedData region that already exists in repository and should be
   *   updated (with updated data)
   * @return updated region's DTO
   */
  public RegionDTO update(RegionDTO regionWithUpdatedData) {
    requireNonNull(regionWithUpdatedData);
    if (regionWithUpdatedData.getId() == null
      || repository.findOne(regionWithUpdatedData.getId()) == null) {
      throw new IllegalArgumentException("Invalid region to be updated (wrong id="
        + regionWithUpdatedData.getId() + ")");
    }

    Region region = Region.fromDTO(regionWithUpdatedData);
    region = repository.save(region);
    return region.dto();
  }
}
