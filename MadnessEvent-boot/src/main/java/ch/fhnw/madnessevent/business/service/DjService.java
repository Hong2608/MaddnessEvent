package ch.fhnw.madnessevent.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.fhnw.madnessevent.business.exception.BadRequestException;
import ch.fhnw.madnessevent.business.exception.ResourceNotFoundException;
import ch.fhnw.madnessevent.controller.dto.DjRequest;
import ch.fhnw.madnessevent.data.domain.Dj;
import ch.fhnw.madnessevent.data.repository.DjRepository;

@Service
public class DjService {

    private final DjRepository djRepository;

    public DjService(DjRepository djRepository) {
        this.djRepository = djRepository;
    }

    public List<Dj> getAllDjs() {
        return djRepository.findAll();
    }

    public Dj getDjById(Long id) {
        return djRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DJ with id " + id + " was not found"));
    }

    public Dj createDj(DjRequest request) {
        validate(request);
        djRepository.findByNameIgnoreCase(request.name())
                .ifPresent(dj -> {
                    throw new BadRequestException("DJ with name " + request.name() + " already exists");
                });

        Dj dj = new Dj();
        applyRequest(dj, request);
        return djRepository.save(dj);
    }

    public Dj updateDj(Long id, DjRequest request) {
        validate(request);
        Dj dj = getDjById(id);
        applyRequest(dj, request);
        return djRepository.save(dj);
    }

    public void deleteDj(Long id) {
        if (!djRepository.existsById(id)) {
            throw new ResourceNotFoundException("DJ with id " + id + " was not found");
        }
        djRepository.deleteById(id);
    }

    Dj findOrCreateByName(String name) {
        if (isBlank(name)) {
            throw new BadRequestException("DJ name must not be empty");
        }
        return djRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> {
                    Dj dj = new Dj();
                    dj.setName(name.trim());
                    dj.setGenre("Techno");
                    dj.setDescription("Placeholder DJ profile");
                    return djRepository.save(dj);
                });
    }

    private void applyRequest(Dj dj, DjRequest request) {
        dj.setName(request.name().trim());
        dj.setGenre(request.genre().trim());
        dj.setDescription(request.description().trim());
    }

    private void validate(DjRequest request) {
        if (request == null || isBlank(request.name()) || isBlank(request.genre()) || isBlank(request.description())) {
            throw new BadRequestException("DJ name, genre, and description are required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
