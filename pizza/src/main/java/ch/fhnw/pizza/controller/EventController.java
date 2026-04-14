
package ch.fhnw.madnessevents.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @GetMapping("/events")
    public String getEvents() {
        return "Events API is working!";
    }
}
