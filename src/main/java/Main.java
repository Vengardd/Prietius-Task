import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("aa");
        try {
            String homePath = "G:\\InteliJWorkspace\\prietius-task\\HOME";
            createDirectoryIfDoesntExists(homePath);
            String devPath = "G:\\InteliJWorkspace\\prietius-task\\DEV";
            createDirectoryIfDoesntExists(devPath);
            String testPath = "G:\\InteliJWorkspace\\prietius-task\\TEST";
            createDirectoryIfDoesntExists(testPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectoryIfDoesntExists(String path) throws IOException {
        Path homePath = Path.of(path);
        if(!Files.exists(homePath)) {
            Files.createDirectory(homePath);
        }
    }
}
