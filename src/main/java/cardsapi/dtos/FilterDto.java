package cardsapi.dtos;

import cardsapi.models.CardStatus;

import java.util.Date;

public record FilterDto(
        String name,
        String colour,
        CardStatus status,
        Date createdAt) {
}
