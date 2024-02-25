package cardsapi.controllers;

import cardsapi.dtos.CardDto;
import cardsapi.dtos.FilterDto;
import cardsapi.models.User;
import cardsapi.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping("{id}")
    public ResponseEntity<CardDto> fetch(
            @PathVariable Long id,
            @AuthenticationPrincipal User user){
        CardDto card = cardService.find(id, user);

        return ResponseEntity.ok(card);
    }

    @PostMapping
    public ResponseEntity<CardDto> create(
            @RequestBody CardDto cardDto,
            @AuthenticationPrincipal User user){
        CardDto newCard = cardService.insert(cardDto, user);

        return ResponseEntity.ok(newCard);
    }

    @PutMapping
    public ResponseEntity<CardDto> update(
            @RequestBody CardDto cardDto,
            @AuthenticationPrincipal User user){
        CardDto updatedCard = cardService.update(cardDto, user);

        return ResponseEntity.ok(updatedCard);
    }

    @PostMapping("search")
    public ResponseEntity<List<CardDto>> search(
            @RequestBody FilterDto filterDto,
            Pageable pageable,
            @AuthenticationPrincipal User user){
        List<CardDto> results = cardService.search(filterDto, pageable, user);

        return ResponseEntity.ok(results);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal User user){
        cardService.remove(id, user);
    }
}
