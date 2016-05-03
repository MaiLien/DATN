package datn.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.util.Iterator;

public class ImportDataServiceImpl{

    protected String getValueOfRowAt(Row row, int i) {
        String res = "";
        Cell cell = row.getCell(i);
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    res = new BigDecimal(cell.getNumericCellValue()).toString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    res = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    res = getFormulaCellValue(cell);
                default:
                    break;
            }
        }
        return StringUtils.trim(res);
    }

    protected String getFormulaCellValue(Cell cell) {
        String res = "";
        if (cell != null) {
            switch (cell.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    res = new BigDecimal(cell.getNumericCellValue()).toString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    res = cell.getStringCellValue();
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    protected boolean isNotValidRow(String ... inputStr) {
        boolean invalidRow = false;
        if (inputStr != null) {
            for (String s : inputStr) {
                invalidRow |= StringUtils.isBlank(s);
            }
        }
        return invalidRow;
    }

    protected void ignoreFistRow(Iterator iterator) {
        if (iterator.hasNext()) {
            iterator.next();
        }
    }

}
