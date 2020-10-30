package extension;

import file.FilesCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlExtensionHandler {

    private final Path dev;
    private final FilesCounter filesCounter;

    public XmlExtensionHandler(Path dev, FilesCounter filesCounter) {
        this.dev = dev;
        this.filesCounter = filesCounter;
    }

    public void handleExtension(Path pathToFile) throws IOException {
        Files.move(pathToFile, dev.resolve(pathToFile.getFileName()));
        filesCounter.addedNewFileToDev();
    }
}
