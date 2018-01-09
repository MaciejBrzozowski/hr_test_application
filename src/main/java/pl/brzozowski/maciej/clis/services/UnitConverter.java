package pl.brzozowski.maciej.clis.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.brzozowski.maciej.clis.utilities.FileSearch;
import pl.brzozowski.maciej.clis.utilities.ReadDataFile;
import pl.brzozowski.maciej.clis.utilities.UnitConversionObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.logging.Logger;

@NoArgsConstructor
@AllArgsConstructor
public class UnitConverter {

    private final String DATA_FILE = "convert";
    @Autowired
    private FileSearch fileSearch;
    @Autowired
    private ReadDataFile readDataFile;
    private File file;
    private UnitConversionObject unitConversionObject;
    private Logger logger = Logger.getLogger(this.getClass()
                                                 .getSimpleName());

    public String getConvertedUnit(BigDecimal quantity, String unitIn, String unitOut) {
        convertUnit(unitIn, unitOut);
        return unitConversionObject.convertToString(quantity);
    }

    public UnitConversionObject getConvertedUnitAsObject(BigDecimal quantity, String unitIn, String unitOut) {
        convertUnit(unitIn, unitOut);
        unitConversionObject.convertToDouble(quantity);
        return unitConversionObject;
    }

    private void convertUnit(String unitIn, String unitOut) {
        file = readDataFile.readDataFileFromResources(DATA_FILE);
        logger.info("Read conversion data file.");
        unitConversionObject = fileSearch.returnFromFileDesiredConversionData(file, unitIn, unitOut);
    }

}
