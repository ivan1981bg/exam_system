package project.exam_system.integration;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.exam_system.repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private static final String USER_CONTROLLER_PREFIX = "/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void register_Should_ReturnCorrectStatusCode_AndView() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void register_Should_RegisterUserSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_CONTROLLER_PREFIX + "/register").
                        param("username", "pesho").
                        param("firstName", "First").
                        param("lastName", "Last").
                        param("email", "test@student.com").
                        param("password", "h[v`8r2TA')!-Ln3").
                        param("confirmPassword", "h[v`8r2TA')!-Ln3").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, userRepository.count());
        Assertions.assertTrue(userRepository.existsByUsername("pesho"));
    }
}
