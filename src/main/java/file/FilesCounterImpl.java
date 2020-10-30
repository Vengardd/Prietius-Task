package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FilesCounterImpl implements FilesCounter {

    private final Path path;
    private File file;
    private int allFiles;
    private int devFiles;
    private int testFils;

    public FilesCounterImpl(Path path) throws IOException {
        this.path = path;
        init();
    }

    private void init() throws IOException {
        file = path.toFile();
        if(!file.createNewFile()) {
            readFromFile();
        }
    }

    //ToDo This method looks ugly, should handle it better + bad handling bad file
    private void readFromFile() throws IOException {
        try {
            Scanner myReader = new Scanner(file);
            if (myReader.hasNextLine()) {
                allFiles = Integer.parseInt(myReader.nextLine());
            }
            if (myReader.hasNextLine()) {
                devFiles = Integer.parseInt(myReader.nextLine());
            }
            if (myReader.hasNextLine()) {
                testFils = Integer.parseInt(myReader.nextLine());
            }
            myReader.close();
        } catch (NumberFormatException e) {
            FileChannel.open(path, StandardOpenOption.WRITE).truncate(0).close();
        }
    }

    @Override
    public void addedNewFile() {

    }

    @Override
    public void addedNewFileToDev() {

    }

    @Override
    public void addedNewFileToTest() {

    }
}
