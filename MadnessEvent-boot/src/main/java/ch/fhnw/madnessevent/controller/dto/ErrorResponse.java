package ch.fhnw.madnessevent.controller.dto;

public record ErrorResponse(
        int code,
        String message
) {
}
