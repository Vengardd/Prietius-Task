import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryCreator {

    public static void createDirectoriesIfDoesntExists(Path... paths) {
        try {
            for (Path path : paths) {
                createDirectoryIfDoesntExists(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectoryIfDoesntExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

}
