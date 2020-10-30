import extension.Extensions;
import extension.JarExtensionHandler;
import extension.XmlExtensionHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class WatchEventHandler {

    private final JarExtensionHandler jarExtensionHandler;
    private final XmlExtensionHandler xmlExtensionHandler;
    private final Path mainPath;

    public WatchEventHandler(JarExtensionHandler jarExtensionHandler, XmlExtensionHandler xmlExtensionHandler, Path mainPath) {
        this.jarExtensionHandler = jarExtensionHandler;
        this.xmlExtensionHandler = xmlExtensionHandler;
        this.mainPath = mainPath;
    }

    public void handle(WatchEvent<?> event) throws IOException {
        if (event.kind() == ENTRY_CREATE) {
            String fileExtension = getFileExtension(event.context());
            Path pathToFile = mainPath.resolve((Path) event.context());
            if (fileExtension.equals(Extensions.XML)) {
                xmlExtensionHandler.handleExtension(pathToFile);
            } else if (fileExtension.equals(Extensions.JAR)) {
                jarExtensionHandler.handleExtension(pathToFile);
            }
        }
    }

    private String getFileExtension(Object context) {
        Path pathOfFile = (Path) context;
        String pathAsString = pathOfFile.getFileName().toString();
        String[] filePathSplittedByDot = pathAsString.split("\\.");
        return filePathSplittedByDot[filePathSplittedByDot.length - 1];
    }

}
