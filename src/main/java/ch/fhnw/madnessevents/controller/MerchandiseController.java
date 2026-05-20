package ch.fhnw.madnessevents.controller;

import ch.fhnw.madnessevents.business.MerchandiseService;
import ch.fhnw.madnessevents.data.domain.Merchandise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchandise")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping
    public List<Merchandise> getAllMerchandise() {
        return merchandiseService.findAll();
    }

    @GetMapping("/{id}")
    public Merchandise getMerchandiseById(@PathVariable Long id) {
        return merchandiseService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Merchandise createMerchandise(@RequestBody Merchandise merchandise) {
        return merchandiseService.save(merchandise);
    }

    @PutMapping("/{id}")
    public Merchandise updateMerchandise(@PathVariable Long id, @RequestBody Merchandise merchandise) {
        return merchandiseService.update(id, merchandise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMerchandise(@PathVariable Long id) {
        merchandiseService.deleteById(id);
    }
}