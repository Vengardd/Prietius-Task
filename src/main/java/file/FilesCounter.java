package file;

import java.io.IOException;

public interface FilesCounter {
    void addedNewFile() throws IOException;

    void addedNewFileToDev() throws IOException;

    void addedNewFileToTest() throws IOException;
}
