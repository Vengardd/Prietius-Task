import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path homePath = Path.of("G:\\InteliJWorkspace\\prietius-task\\HOME");
        Path devStringPath = Path.of("G:\\InteliJWorkspace\\prietius-task\\DEV");
        Path testStringPath = Path.of("G:\\InteliJWorkspace\\prietius-task\\TEST");
        Path countPath = Path.of("G:\\InteliJWorkspace\\prietius-task\\HOME\\count.txt");
        try {
            createDirectoryIfDoesntExists(homePath);
            createDirectoryIfDoesntExists(devStringPath);
            createDirectoryIfDoesntExists(testStringPath);
            createDirectoryIfDoesntExists(countPath);
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
                LocalDateTime actualDateTime = LocalDateTime.now();
                int actualHour = actualDateTime.getHour();
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context()
                                + " at " + actualHour + " hour.");
                String fileExtension = getFileExtension(event.context());
                if (fileExtension.equals("xml")) {
                    moveToDev();
                } else if (fileExtension.equals("jar") && actualHour % 2 == 0) {
                    moveToDev();
                } else if (fileExtension.equals("jar") && actualHour % 2 == 1) {
                    moveToTest();
                }
            }
            key.reset();
        }
    }

    private static void moveToDev() {
        throw new RuntimeException("Not implemented");
        updateCount();
    }

    private static void moveToTest() {
        throw new RuntimeException("Not implemented");
        updateCount();
    }

    private static String getFileExtension(Object context) {
        Path pathOfFile = (Path) context;
        String[] asd = pathOfFile.getFileName().toString().split("\\.");
        return asd[asd.length - 1];
    }

    private static void createDirectoryIfDoesntExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }
}
