package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

public class ParishService {

  private ParishCreator parishCreator = new ParishCreator();
  private ParishRepository parishRepository;

  public ParishService(ParishRepository parishRepository) {
    this.parishRepository = parishRepository;
  }

  public ParishDTO save(ParishDTO parishDTO) {
    requireNonNull(parishDTO);
    Parish parish = parishCreator.from(parishDTO);
    parish = parishRepository.save(parish);
    return parish.dto();
  }

  public List<ParishDTO> findAll() {
    return parishRepository.findAll().stream()
        .map(Parish::dto)
        .collect(Collectors.toList());
  }

  public void delete(Long... ids) {
    requireNonNull(ids);
    Arrays.stream(ids)
        .forEach(parishRepository::delete);
  }

  public ParishDTO findOne(Long id) {
    requireNonNull(id);
    final Parish parish = parishRepository.findOneOrThrow(id);
    return parish.dto();
  }
}
