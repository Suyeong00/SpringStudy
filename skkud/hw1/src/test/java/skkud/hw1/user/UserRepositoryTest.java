package skkud.hw1.user;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();

    @Test
    void createAccountTest() {
        // given
        userRepository.createAccount("admin", "1234");

        // when
        boolean logined = userRepository.login("admin", "1234");

        // then
        Assertions.assertThat(logined).isEqualTo(true);
    }

}