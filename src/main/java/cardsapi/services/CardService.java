package cardsapi.services;

import cardsapi.dtos.CardDto;
import cardsapi.dtos.FilterDto;
import cardsapi.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {
    CardDto insert(CardDto cardDto, User user);
    CardDto update(CardDto cardDto, User user);
    List<CardDto> search(FilterDto filterDto, Pageable pageable, User user);
    CardDto find(Long id, User user);
    void remove(Long id, User user);
}
