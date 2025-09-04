// 代码生成时间: 2025-09-04 16:39:33
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ApiResponseFormatter {

    // Generic method to format the response
    public static String formatResponse(Object data, Status status, String message) {
        try {
            Response response = Response.status(status).entity(data).build();
            // Convert the response to JSON
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            // Handle the exception and return a formatted error message
            return formatError("Error formatting response", e.getMessage());
        }
    }

    // Method to format an error response
    public static String formatError(String errorType, String errorMessage) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorType(errorType);
        errorDetails.setErrorMessage(errorMessage);
        return formatResponse(errorDetails, Status.INTERNAL_SERVER_ERROR, "An error occurred");
    }

    // Inner class to represent error details
    public static class ErrorDetails {
        private String errorType;
        private String errorMessage;

        public String getErrorType() {
            return errorType;
        }

        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}