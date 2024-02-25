package cardsapi.services.impl;

import cardsapi.dtos.CardDto;
import cardsapi.dtos.FilterDto;
import cardsapi.models.Card;
import cardsapi.models.CardStatus;
import cardsapi.models.Role;
import cardsapi.models.User;
import cardsapi.repositories.CardRepository;
import cardsapi.repositories.CardSpecification;
import cardsapi.services.CardService;
import cardsapi.services.util.AccessUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public CardDto insert(@NonNull CardDto cardDto, User user) {
        if (StringUtils.isEmpty(cardDto.name())) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (!StringUtils.isEmpty(cardDto.colour()) && !cardDto.colour().matches("#[0-9A-Za-z]{6}")) {
            throw new IllegalArgumentException("Wrong colour format");
        }

        Card card = new Card();
        card.setName(cardDto.name());
        card.setDescription(cardDto.description());
        card.setColour(cardDto.colour());
        card.setStatus(CardStatus.TODO);
        card.setCreatedBy(user);

        cardRepository.save(card);

        return toCardDto(card);
    }

    @Override
    public CardDto update(CardDto cardDto, User user) {
        Card card = fetchCard(cardDto.id(), user);

        if (!StringUtils.isEmpty(cardDto.name())) {
            card.setName(cardDto.name());
        }

        if (StringUtils.isEmpty(cardDto.colour())) {
            card.setColour(null);
        } else {
            if (cardDto.colour().matches("#[0-9A-Za-z]{6}")) {
                card.setColour(cardDto.colour());
            } else {
                throw new IllegalArgumentException("Wrong colour format");
            }
        }

        card.setDescription(cardDto.description());

        if (cardDto.status() != null) {
            card.setStatus(cardDto.status());
        }

        cardRepository.save(card);

        return toCardDto(card);
    }

    @Override
    public List<CardDto> search(FilterDto filterDto, Pageable pageable, User user) {
        Specification<Card> cardSpecification = Specification.where(null);

        if (user.getRole() == Role.MEMBER) {
            cardSpecification = cardSpecification.and(CardSpecification.createdBy(user.getId()));
        }

        if (!StringUtils.isEmpty(filterDto.name())) {
            cardSpecification = cardSpecification.and(CardSpecification.hasName(filterDto.name()));
        }

        if (!StringUtils.isEmpty(filterDto.colour())) {
            cardSpecification = cardSpecification.and(CardSpecification.hasColour(filterDto.colour()));
        }

        if (filterDto.status() != null) {
            cardSpecification = cardSpecification.and(CardSpecification.hasStatus(filterDto.status()));
        }

        if (filterDto.createdAt() != null) {
            cardSpecification = cardSpecification.and(CardSpecification.hasCreatedAt(filterDto.createdAt()));
        }

        return cardRepository.findAll(cardSpecification, pageable)
                .stream()
                .map(CardServiceImpl::toCardDto)
                .toList();
    }

    @Override
    public CardDto find(Long id, User user) {
        Card card = fetchCard(id, user);

        return toCardDto(card);
    }

    @Override
    public void remove(Long id, User user) {
        Card card = fetchCard(id, user);

        cardRepository.delete(card);
    }

    private Card fetchCard(Long id, User user) {
        return cardRepository.findById(id)
                .filter(card -> AccessUtil.hasAccess(user, card.getCreatedBy().getId()))
                .orElseThrow(NoSuchElementException::new);
    }

    private static CardDto toCardDto(Card card) {
        return new CardDto(card.getId(), card.getName(), card.getDescription(), card.getColour(), card.getStatus());
    }
}
