package cardsapi.repositories;

import cardsapi.models.Card;
import cardsapi.models.CardStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class CardSpecification {
    public static Specification<Card> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Card> hasColour(String colour) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("colour"), colour);
    }

    public static Specification<Card> hasStatus(CardStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Card> hasCreatedAt(Date createdAt) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("createdAt"), createdAt);
    }

    public static Specification<Card> createdBy(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("createdBy").get("id"), id);
    }
}
