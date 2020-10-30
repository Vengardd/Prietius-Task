import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("aa");

        Path homePath = Path.of("G:\\InteliJWorkspace\\prietius-task\\HOME");
        Path devStringPath = Path.of("G:\\InteliJWorkspace\\prietius-task\\DEV");
        Path testStringPath = Path.of("G:\\InteliJWorkspace\\prietius-task\\TEST");
        try {
            createDirectoryIfDoesntExists(homePath);
            createDirectoryIfDoesntExists(devStringPath);
            createDirectoryIfDoesntExists(testStringPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WatchService watchService
                = FileSystems.getDefault().newWatchService();

        homePath.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }

    private static void createDirectoryIfDoesntExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }
}
