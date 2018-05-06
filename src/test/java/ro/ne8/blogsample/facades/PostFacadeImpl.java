package ro.ne8.blogsample.facades;

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
import ro.ne8.blogsample.dto.PostDTO;
import ro.ne8.blogsample.services.PrincipalSupplier;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostFacadeImpl extends ParentTest {

    private static final String TEST_SLUG = "test-slug";
    private static final String TEST_TEXT_CONTENT = "test-text-content";
    private static final String TEST_TITLE = "test-title";

    @Autowired
    private PostFacade postFacade;

    @MockBean
    private PrincipalSupplier principalSupplier;

    @Before
    public void setUp() {
        Mockito.when(this.principalSupplier.getSpringContextUserDetails())
                .thenReturn(new User(TEST_USERNAME, TEST_PASSWORD, this.getGrantedAuthorities()));
    }

    @Test
    public void succeedOnSavingPostToDatabase() {
        //Given
        final PostDTO postDTO = new PostDTO();
        postDTO.setSlug(TEST_SLUG);
        postDTO.setTextContent(TEST_TEXT_CONTENT);
        postDTO.setTitle(TEST_TITLE);
        this.postFacade.save(postDTO);

        //When
        final List<PostDTO> postDTOList = this.postFacade.findAll();

        //Then
        assertTrue(postDTOList.get(0).getSlug().equals(TEST_SLUG));
        assertTrue(postDTOList.get(0).getTitle().equals(TEST_TITLE));
        assertTrue(postDTOList.get(0).getTextContent().equals(TEST_TEXT_CONTENT));

    }


}
