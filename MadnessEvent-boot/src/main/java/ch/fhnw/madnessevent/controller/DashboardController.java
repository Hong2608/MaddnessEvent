package ch.fhnw.madnessevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(Model model) {
        // Add sample dashboard data
        model.addAttribute("totalEvents", 45);
        model.addAttribute("activeEvents", 8);
        model.addAttribute("completedEvents", 37);
        model.addAttribute("attendees", 1250);
        model.addAttribute("revenue", 15750.50);
        
        return "dashboard";
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        model.addAttribute("eventsByMonth", new int[]{10, 15, 12, 20, 18, 22});
        model.addAttribute("months", new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"});
        
        return "stats";
    }
}
