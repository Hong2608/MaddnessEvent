package ch.fhnw.madnessevents.controller;
import org.springframework.http.HttpStatus;
import ch.fhnw.madnessevents.business.DjService;
import ch.fhnw.madnessevents.data.domain.Dj;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/djs")
public class DjController {

    private final DjService djService;

    public DjController(DjService djService) {
        this.djService = djService;
    }

    @GetMapping
    public List<Dj> getAllDjs() {
        return djService.findAll();
    }
@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Dj createDj(@RequestBody @org.springframework.lang.NonNull Dj dj) {
        return djService.save(dj);
    }
    @DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteDj(@PathVariable Long id) {
    djService.deleteById(id);
}
@PutMapping("/{id}")
public Dj updateDj(@PathVariable Long id, @RequestBody Dj dj) {
    return djService.update(id, dj);
}
@GetMapping("/{id}")
public Dj getDjById(@PathVariable Long id) {
    return djService.findById(id);
}
}