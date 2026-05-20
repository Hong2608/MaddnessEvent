package ch.fhnw.madnessevents.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.madnessevents.data.domain.Dj;
import ch.fhnw.madnessevents.data.repository.DjRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DjService {

    private final DjRepository djRepository;

    public DjService(DjRepository djRepository) {
        this.djRepository = djRepository;
    }

    public List<Dj> findAll() {
        return djRepository.findAll();
    }

    public Dj save(@org.springframework.lang.NonNull Dj dj) {
        return djRepository.save(dj);
    }
    public void deleteById(long id) {
    djRepository.deleteById(id);
    }
    public Dj update(long id, Dj dj) {
    dj.setId(id);
    return djRepository.save(dj);
}
    public Dj findById(long id) {
    return djRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DJ not found"));
}
}