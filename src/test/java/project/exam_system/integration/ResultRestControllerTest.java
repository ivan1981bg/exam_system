package project.exam_system.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.exam_system.model.entities.Exam;
import project.exam_system.model.entities.Result;
import project.exam_system.repository.ResultRepository;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
public class ResultRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResultRepository mockResultRepository;

    private Result result1, result2, result3;

    private long testResult1Id, testResult2Id, testResult3Id;


    @Before
    public void init() {
        mockResultRepository.deleteAll();
        result1 = new Result().
                setFullName("Admin adminov").

                setTotalCorrect(3).
                setNumberOfQuestions(13);
        result1 = mockResultRepository.save(result1);
        testResult1Id = result1.getId();

        result2 = new Result().
                setFullName("User userov").

                setTotalCorrect(5).
                setNumberOfQuestions(10);
        result2 = mockResultRepository.save(result2);
        testResult2Id = result2.getId();

        result3 = new Result().
                setFullName("Petko petkov").

                setTotalCorrect(2).
                setNumberOfQuestions(16);

        result3 = mockResultRepository.save(result3);
        testResult3Id = result3.getId();



    }

    @AfterEach
    public void tearDown(){
        mockResultRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testResultsReturnsCorrectStatusCode() throws Exception {
        mockMvc.perform(get("/results/api")).
                andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testResultsRestControllerShouldReturnAllResults() throws Exception {
        mockMvc.perform(get("/results/api")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(3))).
                andExpect(jsonPath("$.[0].id", is((int)testResult1Id))).
                andExpect(jsonPath("$.[0].fullName", is(result1.getFullName()))).
                andExpect(jsonPath("$.[1].id", is((int)testResult2Id))).
                andExpect(jsonPath("$.[1].fullName", is(result2.getFullName()))).
                andExpect(jsonPath("$.[2].id", is((int)testResult3Id))).
                andExpect(jsonPath("$.[2].fullName", is(result3.getFullName())));
    }
}
