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
import ro.ne8.blogsample.dto.CommentDTO;
import ro.ne8.blogsample.dto.PostDTO;
import ro.ne8.blogsample.services.PrincipalSupplier;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentFacadeImplTest extends ParentTest {

    public static final String TEST_UPDATED_TITLE = "test-updated-title";
    public static final String TEXT_UPDATED_CONTENT = "text-updated-content";
    private static final String TEST_TEXT_CONTENT = "test-text-content";
    private static final String TEST_TEXT_TITLE = "test-text-title";
    private static final String TEST_SLUG = "test-slug";
    private static final String TEST_TITLE = "test-title";
    @Autowired
    private CommentFacade commentFacade;

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
    public void succeedOnSavingCommentToDatabase() {
        //Given
        final CommentDTO commentDTO = this.createCommentDTO();
        final PostDTO postDTO = PostFacadeImplTest.createPostDto();

        this.postFacade.save(postDTO);
        final PostDTO retrievedPostDTO = this.postFacade.findAll().get(0);
        commentDTO.setPostId(retrievedPostDTO.getId());

        //When
        this.commentFacade.save(commentDTO);

        //Then
        final List<CommentDTO> commentDTOList = this.commentFacade.findAll();

        assertTrue(commentDTOList.get(0).getTextContent().equals(TEST_TEXT_CONTENT));
        assertTrue(commentDTOList.get(0).getTitle().equals(TEST_TEXT_TITLE));
        assertTrue(commentDTOList.get(0).getCreationDate() != null);
        assertTrue(commentDTOList.get(0).getUpdateTime() == null);
    }

    @Test
    public void succeedOnDelete() {
        //Given
        final CommentDTO commentDTO = this.createCommentDTO();
        final PostDTO postDTO = PostFacadeImplTest.createPostDto();

        this.postFacade.save(postDTO);
        final PostDTO retrievedPostDTO = this.postFacade.findAll().get(0);
        commentDTO.setPostId(retrievedPostDTO.getId());
        this.commentFacade.save(commentDTO);
        final CommentDTO toBeDeleted = this.commentFacade.findAll().get(0);

        //When
        this.commentFacade.delete(toBeDeleted);

        //Then
        final List<CommentDTO> commentDTOList = this.commentFacade.findAll();
        assertTrue(commentDTOList.size() == 0);
    }

    @Test
    public void succeedOnUpdate() {
        //Given
        final CommentDTO commentDTO = this.createCommentDTO();
        final PostDTO postDTO = PostFacadeImplTest.createPostDto();
        this.postFacade.save(postDTO);
        final PostDTO retrievedPostDTO = this.postFacade.findAll().get(0);
        commentDTO.setPostId(retrievedPostDTO.getId());
        this.commentFacade.save(commentDTO);

        final CommentDTO toBeUpdated = this.commentFacade.findAll().get(0);
        toBeUpdated.setTitle(TEST_UPDATED_TITLE);
        toBeUpdated.setTextContent(TEXT_UPDATED_CONTENT);

        //Whentatu
        this.commentFacade.update(toBeUpdated);
        final CommentDTO updatedCommentDTO = this.commentFacade.findAll().get(0);

        //Then
        assertTrue(updatedCommentDTO.getTitle().equals(TEST_UPDATED_TITLE));
        assertTrue(updatedCommentDTO.getTextContent().equals(TEXT_UPDATED_CONTENT));
        assertTrue(updatedCommentDTO.getUpdateTime() != null);
    }

    private CommentDTO createCommentDTO() {

        final CommentDTO commentDTO = new CommentDTO();
        commentDTO.setTextContent(TEST_TEXT_CONTENT);
        commentDTO.setTitle(TEST_TEXT_TITLE);

        return commentDTO;
    }


    @After
    public void cleanDatabase() {
        final List<CommentDTO> commentDTOList = this.commentFacade.findAll();
        final List<PostDTO> postDTOList = this.postFacade.findAll();
        commentDTOList.forEach(commentDTO -> this.commentFacade.delete(commentDTO));
        postDTOList.forEach(postDTO -> this.postFacade.delete(postDTO));

    }


}
