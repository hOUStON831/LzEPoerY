// 代码生成时间: 2025-08-16 23:07:59
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BatchFileRenamer {

    private static final String DIRECTORY_PATH = "/path/to/directory";
    private static final Pattern RENAME_PATTERN = Pattern.compile(".*\-(\d+)\..*");
    private static final int MAX_FILES = 100;

    public static void main(String[] args) {
        try {
            List<File> files = getFilesToRename();
            if (!files.isEmpty()) {
                renameFiles(files);
            } else {
                System.out.println("No files found to rename.");
            }
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of files from the specified directory.
     *
     * @return List of files in the directory.
     * @throws IOException If an I/O error occurs reading the directory.
     */
    private static List<File> getFilesToRename() throws IOException {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        List<Path> paths = Files.list(directoryPath).collect(Collectors.toList());
        List<File> files = new ArrayList<>();
        for (Path path : paths) {
            if (Files.isRegularFile(path)) {
                files.add(path.toFile());
            }
        }
        return files;
    }

    /**
     * Renames the files according to a specific pattern.
     *
     * @param files List of files to rename.
     */
    private static void renameFiles(List<File> files) {
        for (File file : files) {
            String oldName = file.getName();
            Matcher matcher = RENAME_PATTERN.matcher(oldName);
            if (matcher.find()) {
                int index = Integer.parseInt(matcher.group(1));
                String newName = "new_name_" + index + file.getName().substring(file.getName().lastIndexOf('.'));
                File newFile = new File(file.getParent(), newName);
                if (file.renameTo(newFile)) {
                    System.out.println("Renamed: " + oldName + " to " + newName);
                } else {
                    System.err.println("Error renaming file: " + oldName);
                }
            } else {
                System.err.println("No match found for file: " + oldName);
            }
        }
    }
}
