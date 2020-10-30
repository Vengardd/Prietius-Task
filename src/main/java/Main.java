import file.FilesCounter;
import file.FilesCounterImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PathKeeper pathKeeper = new PathKeeper();
        Path homePath = pathKeeper.getHome();

        DirectoryCreator.createDirectoriesIfDoesntExists(homePath, pathKeeper.getDev(), pathKeeper.getTest());
        FilesCounter filesCounter = new FilesCounterImpl(pathKeeper.getCount());

        HomeWatcherService homeWatcherService = new HomeWatcherService(pathKeeper, new WatchEventHandler());
        homeWatcherService.watch();
    }
}
