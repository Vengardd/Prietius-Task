import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PathKeeper pathKeeper = new PathKeeper();
        Path homePath = pathKeeper.getHome();

        DirectoryCreator.createDirectoriesIfDoesntExists(homePath, pathKeeper.getDev(), pathKeeper.getTest());
        //ToDo add loading count.txt file
        createFileIfDoesntExists(pathKeeper.getCount());

        HomeWatcherService homeWatcherService = new HomeWatcherService(pathKeeper, new WatchEventHandler());
        homeWatcherService.watch();
    }

    private static void createFileIfDoesntExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}
