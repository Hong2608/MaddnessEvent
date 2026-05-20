package ch.fhnw.madnessevents.business;

import ch.fhnw.madnessevents.data.domain.Merchandise;
import ch.fhnw.madnessevents.data.repository.MerchandiseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    public List<Merchandise> findAll() {
        return merchandiseRepository.findAll();
    }

    public Merchandise findById(long id) {
        return merchandiseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchandise not found"));
    }

   public Merchandise save(Merchandise merchandise) {
    if (merchandise.getStock() < 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock cannot be negative");
    }
    return merchandiseRepository.save(merchandise);
}

    public Merchandise update(Long id, Merchandise merchandise) {
    merchandise.setId(id);
    if (merchandise.getStock() < 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock cannot be negative");
    }
    return merchandiseRepository.save(merchandise);
}

    public void deleteById(long id) {
        merchandiseRepository.deleteById(id);
    }
}