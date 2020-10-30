import java.io.IOException;
import java.nio.file.*;

public class HomeWatcherService {

    private final PathKeeper pathKeeper;
    private final WatchEventHandler watchEventHandler;

    public HomeWatcherService(PathKeeper pathKeeper, WatchEventHandler watchEventHandler) {
        this.pathKeeper = pathKeeper;
        this.watchEventHandler = watchEventHandler;
    }

    public void watch() throws IOException, InterruptedException {
        WatchService watchService
                = FileSystems.getDefault().newWatchService();

        Path homePath = pathKeeper.getHome();

        homePath.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                watchEventHandler.handle(event);
            }
            key.reset();
        }
    }


}
