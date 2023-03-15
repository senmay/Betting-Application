package DataForTests;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.json.UserJson;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.model.mapper.UserMapperImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface UserProvider {
    UserMapper userMapper = new UserMapperImpl();
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
        return userMapper.mapToJson(provideUser());
    }

    default List<User> provideUserListOfElements(Integer size) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            userList.add(provideUser());
        }
        return userList;

    }
}
