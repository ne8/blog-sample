package ro.ne8.blogsample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "UserDTO")
public class UserDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 255)
    private String username;


    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }


}
