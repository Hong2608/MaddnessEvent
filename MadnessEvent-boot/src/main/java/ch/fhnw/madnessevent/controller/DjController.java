package ch.fhnw.madnessevent.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.madnessevent.business.service.DjService;
import ch.fhnw.madnessevent.controller.dto.DjRequest;
import ch.fhnw.madnessevent.data.domain.Dj;

@RestController
@RequestMapping("/api/djs")
public class DjController {

    private final DjService djService;

    public DjController(DjService djService) {
        this.djService = djService;
    }

    @GetMapping
    public List<Dj> getAllDjs() {
        return djService.getAllDjs();
    }

    @GetMapping("/{id}")
    public Dj getDjById(@PathVariable Long id) {
        return djService.getDjById(id);
    }

    @PostMapping
    public ResponseEntity<Dj> createDj(@RequestBody DjRequest request) {
        Dj dj = djService.createDj(request);
        return ResponseEntity.created(URI.create("/api/djs/" + dj.getId())).body(dj);
    }

    @PutMapping("/{id}")
    public Dj updateDj(@PathVariable Long id, @RequestBody DjRequest request) {
        return djService.updateDj(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDj(@PathVariable Long id) {
        djService.deleteDj(id);
        return ResponseEntity.noContent().build();
    }
}
