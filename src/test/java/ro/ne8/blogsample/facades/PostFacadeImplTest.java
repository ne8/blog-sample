package ro.ne8.blogsample.facades;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
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
public class PostFacadeImplTest extends ParentTest {

    public static final String FAIL_TEST_USERNAME = "fail-test-username";
    private static final String TEST_SLUG_FAIL = "test-slug-fail";
    private static final String TEST_SLUG = "test-slug";
    private static final String TEST_TEXT_CONTENT = "test-text-content";
    private static final String TEST_TITLE = "test-title";
    @Autowired
    private PostFacade postFacade;

    @MockBean
    private PrincipalSupplier principalSupplier;

    @Autowired
    private ModelMapper modelMapper;


    @Before
    public void setUp() {
        Mockito.when(this.principalSupplier.getSpringContextUserDetails())
                .thenReturn(new User(TEST_USERNAME, TEST_PASSWORD, this.getGrantedAuthorities()));
    }

    @Test
    public void succeedOnSavingPostToDatabase() {
        //Given
        final PostDTO postDTO = this.createPostDto();

        //When
        this.postFacade.save(postDTO);

        //Then
        final List<PostDTO> postDTOList = this.postFacade.findAll();
        assertTrue(postDTOList.get(0).getSlug().equals(TEST_SLUG));
        assertTrue(postDTOList.get(0).getTitle().equals(TEST_TITLE));
        assertTrue(postDTOList.get(0).getTextContent().equals(TEST_TEXT_CONTENT));
    }

    @Test
    public void succeedOnFindingAll() {
        //Given
        this.postFacade.save(this.createPostDto());

        //When
        final List<PostDTO> postDTOList = this.postFacade.findAll();

        //Then
        assertTrue(postDTOList.size() == 1);
        assertTrue(postDTOList.get(0).getSlug().equals(TEST_SLUG));
        assertTrue(postDTOList.get(0).getTitle().equals(TEST_TITLE));
        assertTrue(postDTOList.get(0).getTextContent().equals(TEST_TEXT_CONTENT));
        assertTrue(postDTOList.get(0).getCreationDate() != null);
        assertTrue(postDTOList.get(0).getId() != null);
    }

    @Test
    public void succeedOnDelete() {
        //Given
        this.postFacade.save(this.createPostDto());
        final PostDTO toBeDeleted = this.postFacade.findAll().get(0);

        //When
        this.postFacade.delete(toBeDeleted);

        //Then
        final List<PostDTO> postDTOList = this.postFacade.findAll();
        assertTrue(postDTOList.size() == 0);
    }

    @Test
    public void succeedOnDeleteAllBySlug() {
        //Given
        this.postFacade.save(this.createPostDto());
        final String slug = this.postFacade.findAll().get(0).getSlug();

        //When
        this.postFacade.deleteAllBySlug(slug);

        //Then
        final List<PostDTO> postDTOList = this.postFacade.findAll();
        assertTrue(postDTOList.size() == 0);
    }

    @Test
    public void succeedOnFindingBySlug() {
        //Given
        this.postFacade.save(this.createPostDto());

        //When
        final PostDTO postDTO = this.postFacade.findBySlug(TEST_SLUG);

        //Then
        assertTrue(postDTO.getSlug().equals(TEST_SLUG));
        assertTrue(postDTO.getTitle().equals(TEST_TITLE));
        assertTrue(postDTO.getTextContent().equals(TEST_TEXT_CONTENT));
        assertTrue(postDTO.getId() != null);
        assertTrue(postDTO.getCreationDate() != null);

    }

    @Test
    public void succeedOnFindingByUsername() throws InterruptedException {
        //Given
        this.postFacade.save(this.createPostDto());
        Thread.sleep(2000);
        this.postFacade.save(this.createPostDto());

        //When
        final List<PostDTO> postDTOList = this.postFacade.findByUsername(TEST_USERNAME);

        //Then
        assertTrue(postDTOList.size() == 2);
        assertTrue(postDTOList.get(0).getSlug().equals(TEST_SLUG));
        assertTrue(postDTOList.get(0).getTitle().equals(TEST_TITLE));
        assertTrue(postDTOList.get(0).getTextContent().equals(TEST_TEXT_CONTENT));
        assertTrue(postDTOList.get(0).getCreationDate() != null);
        assertTrue(postDTOList.get(0).getId() != null);
        assertTrue(postDTOList.get(0).getCreationDate().after(postDTOList.get(1).getCreationDate()));
    }

    @Test
    public void failOnSavingPostToDatabase() {
        try {
            //When
            this.postFacade.save(null);
            Assert.fail("should have failed");
        } catch (final IllegalArgumentException e) {

        }
    }


    @Test
    public void failOnDeleteAllBySlug() {
        //Given
        this.postFacade.save(this.createPostDto());
        final String slug = TEST_SLUG_FAIL;

        //When
        this.postFacade.deleteAllBySlug(slug);

        //Then
        final List<PostDTO> postDTOList = this.postFacade.findAll();
        assertTrue(postDTOList.size() != 0);
    }

    @Test
    public void failOnFindingBySlug() {
        //Given
        this.postFacade.save(this.createPostDto());

        //When
        try {
            final PostDTO postDTO = this.postFacade.findBySlug(TEST_SLUG_FAIL);
            //Then
            Assert.fail("Should have failed");
        } catch (final IllegalArgumentException e) {

        }
    }

    @Test
    public void failOnFindingByUsername() {
        //Given
        this.postFacade.save(this.createPostDto());

        //When
        final List<PostDTO> postDTOList = this.postFacade.findByUsername(FAIL_TEST_USERNAME);

        //Then
        assertTrue(postDTOList.size() == 0);
    }


    @After
    public void cleanDatabase() {
        final List<PostDTO> postDTOList = this.postFacade.findAll();
        postDTOList.forEach(postDTO -> {
            this.postFacade.delete(postDTO);
        });
    }

    private PostDTO createPostDto() {
        final PostDTO postDTO = new PostDTO();
        postDTO.setSlug(TEST_SLUG);
        postDTO.setTextContent(TEST_TEXT_CONTENT);
        postDTO.setTitle(TEST_TITLE);
        return postDTO;
    }
}
