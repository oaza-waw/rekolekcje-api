package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import java.util.List;
import org.springframework.data.repository.Repository;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishNotFoundException;

interface ParishRepository extends Repository<Parish, Long> {

  Parish findOne(long id);
  List<Parish> findAll();
  Parish save(Parish parish);
  void delete(long id);

  default Parish findOneOrThrow(long id) {
    Parish parish = findOne(id);
    if (parish == null) {
      throw new ParishNotFoundException(id);
    }
    return parish;
  }
}
