package cardsapi.services.util;

import cardsapi.models.Role;
import cardsapi.models.User;
import java.util.Objects;

public class AccessUtil {
    public static boolean hasAccess(User user, Long createdBy) {
        if (user.getRole() == Role.ADMIN) {
            return true;
        }

        return user.getRole() == Role.MEMBER && Objects.equals(createdBy, user.getId());
    }
}
