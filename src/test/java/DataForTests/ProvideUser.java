package DataForTests;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.json.UserJson;

import java.math.BigDecimal;
import java.util.List;

public interface ProvideUser {
    default User provideUser() {
        return User.builder()
                .id(1)
                .username("Dominik")
                .points(5)
                .email("admin@wp.pl")
                .userType(UserRole.ADMIN)
                .balance(BigDecimal.valueOf(100))
                .build();
    }

    default UserJson provideUserJson() {
        return UserJson.builder()
                .id(2)
                .username("Marcin")
                .points(3)
                .email("user@gmail.com")
                .build();
    }

    default List<User> provideUserListOf4Elements() {
        return List.of(
                User.builder().id(1).username("Dominik").points(5).email("admin@wp.pl").userType(UserRole.ADMIN).balance(BigDecimal.valueOf(100)).build(),
                User.builder().id(2).username("Marcin").points(3).email("user@wp.pl").userType(UserRole.USER).balance(BigDecimal.valueOf(50)).build(),
                User.builder().id(3).username("Kamil").points(2).email("user@gmail.com").userType(UserRole.USER).balance(BigDecimal.valueOf(0)).build(),
                User.builder().id(4).username("Krzysztof").points(1).email("user2@gmail.com").userType(UserRole.USER).balance(BigDecimal.valueOf(0)).build());
    }
}
