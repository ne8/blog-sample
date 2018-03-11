package ro.ne8.blogsample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "CommentDTO")
public class CommentDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @Size(min = 6, max = 600)
    @ApiModelProperty(required = true)
    private String title;

    @NotNull
    @Size(min = 100)
    @ApiModelProperty(required = true)
    private String textContent;

    @ApiModelProperty(readOnly = true)
    private Date creationDate;

    @ApiModelProperty(readOnly = true)
    private Date updateTime;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTextContent() {
        return this.textContent;
    }

    public void setTextContent(final String textContent) {
        this.textContent = textContent;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
}
