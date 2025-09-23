// 代码生成时间: 2025-09-24 01:10:37
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/formValidator")
public class FormValidator {

    // Validator instance
    private final Validator validator;

    public FormValidator() {
        // Initialize the validator factory and validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateFormData(MyFormData data) {
        try {
            // Validate the form data using the validator
            Set<ConstraintViolation<MyFormData>> violations = validator.validate(data);

            // Check if there are any constraint violations
            if (violations.isEmpty()) {
                // No violations, return a success response
                return Response.ok().entity("Form data is valid.").build();
            } else {
                // There are violations, return an error response with details
                StringBuilder errorDetails = new StringBuilder();
                for (ConstraintViolation<MyFormData> violation : violations) {
                    errorDetails.append("Error in field: ").append(violation.getPropertyPath()).append(" - ")
                        .append(violation.getMessage()).append("
");
                }
                return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails.toString()).build();
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while validating the form data.").build();
        }
    }
}

/*
 * MyFormData.java
 *
 * A simple Java bean class representing the form data.
 * This class should be annotated with validation constraints.
 */

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MyFormData {

    @NotEmpty(message = "Name cannot be empty.")
    private String name;

    @Email(message = "Invalid email format.")
    private String email;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Additional methods as needed
}
