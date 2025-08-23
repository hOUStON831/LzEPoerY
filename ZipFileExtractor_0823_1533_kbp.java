// 代码生成时间: 2025-08-23 15:33:23
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class ZipFileExtractor {
# 优化算法效率

    /**
     * Extracts a ZIP file to a specified directory.
     *
# 优化算法效率
     * @param zipFilePath The path to the ZIP file to extract.
     * @param outputDirectory The directory to extract the ZIP file into.
     * @throws IOException If an I/O error occurs.
     */
# NOTE: 重要实现细节
    public void unzip(String zipFilePath, String outputDirectory) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            // Create directories as needed.
            while (entry != null) {
                String filePath = outputDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // If the entry is a file, extract it.
                    extractFile(zipIn, filePath);
# 扩展功能模块
                } else {
                    // If the entry is a directory, make the directory.
                    Files.createDirectories(Paths.get(filePath));
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Extracts a file from a ZIP input stream.
     *
     * @param zipIn The ZIP input stream from which to extract the file.
# NOTE: 重要实现细节
     * @param filePath The path to extract the file to.
# FIXME: 处理边界情况
     * @throws IOException If an I/O error occurs.
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
# 扩展功能模块
    }

    /**
     * Main method to test the ZIP file extraction.
     *
     * @param args Command line arguments (ZIP file path and output directory).
     */
    public static void main(String[] args) {
        if (args.length != 2) {
# 增强安全性
            System.out.println(