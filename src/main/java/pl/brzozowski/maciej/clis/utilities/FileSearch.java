package pl.brzozowski.maciej.clis.utilities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@NoArgsConstructor
public class FileSearch {

    @Autowired
    private UnitConversionObject unitConversionObject;
    @Autowired
    private Logger logger;
    private Scanner scanner;
    private String conversionData;
    private UnitConversionObject emptyConversionData = new UnitConversionObject("", "", "", "", "", 0D, 0D);

    public UnitConversionObject returnFromFileDesiredConversionData(File file, String unitInName, String unitOutName) {
        try {
            scanner = new Scanner(file);
            logger.info("Starting scanning");
            boolean stopSearch = false;
            while (scanner.hasNextLine() && !stopSearch) {
                conversionData = scanner.nextLine();
                if (conversionData.matches("(.*?,)" + unitInName + "(,.*?,)" + unitOutName + "(,.*?,.*?,.*?)")) {
                    stopSearch = true;
                } else {
                    conversionData = null;
                }
            }
            logger.info("Conversion data: " + conversionData);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        unitConversionObject = ofNullable(conversionData).isPresent() ? unitConversionObject.createObject(conversionData) : emptyConversionData;
        logger.info(unitConversionObject.toString());
        return unitConversionObject;
    }
}
