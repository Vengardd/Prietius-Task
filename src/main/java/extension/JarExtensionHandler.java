package extension;

import file.FilesCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class JarExtensionHandler {

    private final Path dev;
    private final Path test;
    private final FilesCounter filesCounter;

    public JarExtensionHandler(Path dev, Path test, FilesCounter filesCounter) {
        this.dev = dev;
        this.test = test;
        this.filesCounter = filesCounter;
    }

    public void handleExtension(Path pathToFile) throws IOException {
        LocalDateTime actualDateTime = LocalDateTime.now();
        int actualHour = actualDateTime.getHour();
        if (actualHour % 2 == 0) {
            moveToDev(pathToFile);
        } else {
            moveToTest(pathToFile);
        }
    }

    private void moveToDev(Path pathToFile) throws IOException {
        Files.move(pathToFile, dev.resolve(pathToFile.getFileName()));
        filesCounter.addedNewFileToDev();
    }

    private void moveToTest(Path pathToFile) throws IOException {
        Files.move(pathToFile, test.resolve(pathToFile.getFileName()));
        filesCounter.addedNewFileToTest();
    }
}
