package pl.brzozowski.maciej.clis.utilities;

import java.io.File;

public class ReadDataFile {

    public File readDataFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }
}
