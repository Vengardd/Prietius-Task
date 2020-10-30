import java.nio.file.Path;

public class PathKeeper {
    private final Path home;
    private final Path dev;
    private final Path test;
    private final Path count;

    public PathKeeper() {
        home = Path.of("G:\\InteliJWorkspace\\prietius-task\\HOME");
        dev = Path.of("G:\\InteliJWorkspace\\prietius-task\\DEV");
        test = Path.of("G:\\InteliJWorkspace\\prietius-task\\TEST");
        count = Path.of("G:\\InteliJWorkspace\\prietius-task\\HOME\\count.txt");
    }

    public PathKeeper(Path home, Path dev, Path test, Path count) {
        this.home = home;
        this.dev = dev;
        this.test = test;
        this.count = count;
    }

    public Path getHome() {
        return home;
    }

    public Path getDev() {
        return dev;
    }

    public Path getTest() {
        return test;
    }

    public Path getCount() {
        return count;
    }
}
