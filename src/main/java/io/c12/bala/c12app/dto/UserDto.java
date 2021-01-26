package io.c12.bala.c12app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "users", itemRelation = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends RepresentationModel<UserDto> {

    private String id;

    @NotEmpty(message = "firstName is required")
    private String firstName;

    @NotEmpty(message = "lastName is required")
    private String lastName;

    @Email(message = "invalid email format")
    @NotEmpty(message = "email is required")
    private String emailId;

    @NotEmpty(message = "userId is required")
    private String userId;
    private List<String> interests;
}
