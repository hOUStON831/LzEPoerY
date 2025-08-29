// 代码生成时间: 2025-08-29 11:24:04
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rename")
public class BulkFileRenamer {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response renameFiles(List<RenameRequest> renameRequests) {
        try {
            List<String> failedRenamings = new ArrayList<>();
            for (RenameRequest request : renameRequests) {
                File originalFile = new File(request.getOriginalPath());
                File newFile = new File(request.getNewPath());

                // Check if the original file exists
                if (!originalFile.exists()) {
                    failedRenamings.add(request.getOriginalPath());
                    continue;
                }

                // Check if the new file name is already taken
                if (newFile.exists()) {
                    failedRenamings.add(request.getNewPath());
                    continue;
                }

                // Rename the file
                Path originalPath = Paths.get(request.getOriginalPath());
                Path newPath = Paths.get(request.getNewPath());
                Files.move(originalPath, newPath);
            }

            if (failedRenamings.isEmpty()) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(failedRenamings)
                        .build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred during file renaming: " + e.getMessage())
                    .build();
        }
    }

    // A helper class to represent a file renaming request
    public static class RenameRequest {
        private String originalPath;
        private String newPath;

        public String getOriginalPath() {
            return originalPath;
        }

        public void setOriginalPath(String originalPath) {
            this.originalPath = originalPath;
        }

        public String getNewPath() {
            return newPath;
        }

        public void setNewPath(String newPath) {
            this.newPath = newPath;
        }
    }
}
