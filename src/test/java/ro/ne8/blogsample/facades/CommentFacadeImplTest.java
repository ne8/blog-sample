package ro.ne8.blogsample.facades;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ne8.blogsample.services.PrincipalSupplier;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentFacadeImplTest extends ParentTest {

    @Autowired
    private CommentFacade commentFacade;

    @MockBean
    private PrincipalSupplier principalSupplier;

    @Before
    public void setUp() {
        Mockito.when(this.principalSupplier.getSpringContextUserDetails())
                .thenReturn(new User(TEST_USERNAME, TEST_PASSWORD, this.getGrantedAuthorities()));
    }

    @Test
    public void test() {
        System.out.println("dummy test");
    }


    @After
    public void cleanDatabase() {
//        final List<PostDTO> postDTOList = this.commentFacade.findAll();
//        postDTOList.forEach(postDTO -> {
//            this.commentFacade.delete(postDTO);
//        });
    }

}
