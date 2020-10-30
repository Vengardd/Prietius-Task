package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
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
        if (!file.createNewFile()) {
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
            allFiles = 0;
            devFiles = 0;
            testFils = 0;
            FileChannel.open(path, StandardOpenOption.WRITE).truncate(0).close();
        }
    }

    @Override
    public void addedNewFile() throws IOException {
        allFiles++;
        saveNewCountToFile();
    }

    @Override
    public void addedNewFileToDev() throws IOException {
        devFiles++;
        addedNewFile();
    }

    @Override
    public void addedNewFileToTest() throws IOException {
        testFils++;
        addedNewFile();
    }

    private void saveNewCountToFile() throws IOException {
        FileChannel.open(path, StandardOpenOption.WRITE).truncate(0).close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()));
        writer.write(Integer.toString(allFiles));
        writer.newLine();
        writer.write(Integer.toString(devFiles));
        writer.newLine();
        writer.write(Integer.toString(testFils));
        writer.close();
    }
}
