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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ro.ne8.blogsample.dto.CommentDTO;
import ro.ne8.blogsample.facades.CommentFacade;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Api(value = "Comment endpoint", description = "Comment endpoint for CRUD operations on Comment entity",
        tags = {"Comment"})
@RequestMapping(value = "/comments/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("#oauth2.hasScope('write') and hasRole('USER')")
public class CommentController {
    private static final String BY_ID = "{id}";

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);


    @Autowired
    private CommentFacade commentFacade;

    @ApiOperation(value = "Save comment to database", code = 201)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveComment(@Valid @RequestBody final CommentDTO commentDTO, final UriComponentsBuilder ucBuilder) {
        LOGGER.debug("Creating new comment");
        this.commentFacade.save(commentDTO);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/comments/{id}").buildAndExpand(commentDTO.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update comment within the database")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateComment(@Valid @RequestBody final CommentDTO commentDTO) {
        LOGGER.debug("Updating comment: " + commentDTO.getId());
        this.commentFacade.update(commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Delete comment from database")
    @RequestMapping(value = BY_ID, method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@NotNull @PathVariable("id") final Long id) {
        LOGGER.debug("Removing comment with id: " + id);
//        this.commentFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
