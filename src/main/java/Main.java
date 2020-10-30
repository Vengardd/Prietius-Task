import extension.JarExtensionHandler;
import extension.XmlExtensionHandler;
import file.FilesCounter;
import file.FilesCounterImpl;
import path.PathKeeper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PathKeeper pathKeeper = new PathKeeper();
        DirectoryCreator.createDirectoriesIfDoesntExists(pathKeeper.getHome(), pathKeeper.getDev(), pathKeeper.getTest());
        FilesCounter filesCounter = new FilesCounterImpl(pathKeeper.getCount());

        JarExtensionHandler jarExtensionHandler = new JarExtensionHandler(pathKeeper.getDev(), pathKeeper.getTest(), filesCounter);
        XmlExtensionHandler xmlExtensionHandler = new XmlExtensionHandler(pathKeeper.getDev(), filesCounter);
        WatchEventHandler watchEventHandler = new WatchEventHandler(jarExtensionHandler, xmlExtensionHandler, pathKeeper.getHome());
        HomeWatcherService homeWatcherService = new HomeWatcherService(pathKeeper, watchEventHandler);

        homeWatcherService.watch();
    }
}
