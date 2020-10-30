import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.time.LocalDateTime;

public class WatchEventHandler {

    public void handle(WatchEvent<?> event) {
        LocalDateTime actualDateTime = LocalDateTime.now();
        int actualHour = actualDateTime.getHour();
        System.out.println(
                "Event kind:" + event.kind()
                        + ". File affected: " + event.context()
                        + " at " + actualHour + " hour.");
        String fileExtension = getFileExtension(event.context());
        if (fileExtension.equals(Extensions.XML)) {
            moveToDev();
        } else if (fileExtension.equals(Extensions.JAR) && actualHour % 2 == 0) {
            moveToDev();
        } else if (fileExtension.equals(Extensions.JAR) && actualHour % 2 == 1) {
            moveToTest();
        }
    }

    private String getFileExtension(Object context) {
        Path pathOfFile = (Path) context;
        String[] asd = pathOfFile.getFileName().toString().split("\\.");
        return asd[asd.length - 1];
    }

    private void moveToDev() {
        throw new RuntimeException("Not implemented");
        updateCount();
    }

    private void moveToTest() {
        throw new RuntimeException("Not implemented");
        updateCount();
    }

}
