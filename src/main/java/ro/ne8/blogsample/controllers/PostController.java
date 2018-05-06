package ro.ne8.blogsample.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ro.ne8.blogsample.dto.PostDTO;
import ro.ne8.blogsample.facades.PostFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Post endpoint", description = "Post endpoint for CRUD operations on Post entity",
        tags = {"Post"})
@RequestMapping(value = "/posts/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("#oauth2.hasScope('write') and hasRole('USER')")
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
    private static final String BY_ID = "{id}";

    @Autowired
    private PostFacade postFacade;

    @ApiOperation(value = "Save post to database", code = 201)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> savePost(@Valid @RequestBody final PostDTO postDTO, final UriComponentsBuilder ucBuilder) {
        LOGGER.debug("Creating new post");
        this.postFacade.save(postDTO);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/posts/{id}").buildAndExpand(postDTO.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update post within the database")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePost(@Valid @RequestBody final PostDTO postDTO) {
        LOGGER.debug("Updating post: " + postDTO.getId());
        this.postFacade.update(postDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all posts from database")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> getPosts() {
        return new ResponseEntity<>(this.postFacade.findAll(), HttpStatus.OK);
    }
}
