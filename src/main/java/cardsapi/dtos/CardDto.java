package cardsapi.dtos;

import cardsapi.models.CardStatus;

public record CardDto(
        Long id,
        String name,
        String description,
        String colour,
        CardStatus status) {
}
