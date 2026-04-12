package ch.fhnw.madnessevent.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EventResponse(
        Long id,
        String name,
        LocalDate date,
        String venue,
        List<String> djs,
        BigDecimal price,
        int capacity,
        int availableTickets
) {
}
