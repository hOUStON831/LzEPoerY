// 代码生成时间: 2025-08-18 17:05:52
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/form")
public class FormValidator {

    // Fields for form data
    public static class FormData {
        @NotNull(message = "Name cannot be null")
        @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name must contain only letters and spaces")
        private String name;

        @NotNull(message = "Email cannot be null")
        @Email(message = "Email must be valid")
        private String email;

        @NotNull(message = "Age cannot be null")
        @Min(value = 18, message = "Age must be at least 18")
        private int age;

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    // POST method to validate form data
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateFormData(@Valid FormData formData) {
        try {
            // Validate the data using Bean Validation API
            Set<ConstraintViolation<FormData>> violations =
                javax.validation.Validation.buildDefaultValidatorFactory().getValidator().validate(formData);

            if (!violations.isEmpty()) {
                // Handle validation errors
                String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(errorMessage))
                    .build();
            }

            // If no errors, return success response
            return Response.ok(new SuccessResponse("Form data is valid")).build();
        } catch (ConstraintViolationException e) {
            // Handle Bean Validation API exception
            String errorMessage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

            return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(errorMessage))
                .build();
        }
    }

    // Response classes for success and error
    public static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
