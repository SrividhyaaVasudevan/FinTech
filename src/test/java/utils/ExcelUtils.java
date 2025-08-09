package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    String excelName;


    public ExcelUtils(String excelName){
        this.excelName = "testdata.xlsx";
    }

    public Map<String, String> getExcelData(String sheetName, String value) {
        String filePath = ConfigReader.get("ExcelDataPath");
        Map<String, String> rowData = null;
        try (FileInputStream fis = new FileInputStream(filePath + excelName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            rowData = getRowByReference(sheet, value);

            if (rowData.isEmpty()) {
                System.out.println("No row found with 'validData' in first column");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowData;
    }

    private Map<String, String> getRowByReference(XSSFSheet sheet, int headerRowIndex, String referenceValue) {
        Map<String, String> rowMap = new HashMap<>();

        Row headerRow = sheet.getRow(headerRowIndex);
        if (headerRow == null) {
            return rowMap; // Return empty map if header row doesn't exist
        }

        // Find the row with matching first column value
        Row targetRow = findRowByFirstColumn(sheet, referenceValue, headerRowIndex + 1);
        if (targetRow == null) {
            return rowMap; // Return empty map if no matching row found
        }

        // Create map with headers as keys and row values as values
        for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
            Cell headerCell = headerRow.getCell(cellIndex);
            String headerValue = getCellValueAsString(headerCell);

            Cell dataCell = targetRow.getCell(cellIndex);
            String dataValue = getCellValueAsString(dataCell);

            rowMap.put(headerValue, dataValue);
        }

        return rowMap;
    }

    private Map<String, String> getRowByReference(XSSFSheet sheet, String referenceValue) {
        return getRowByReference(sheet, 0, referenceValue);
    }

    private Row findRowByFirstColumn(XSSFSheet sheet, String referenceValue, int startRowIndex) {
        int lastRowNum = sheet.getLastRowNum();

        for (int rowIndex = startRowIndex; rowIndex <= lastRowNum; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;

            Cell firstCell = row.getCell(0);
            String cellValue = getCellValueAsString(firstCell);

            if (referenceValue.equals(cellValue)) {
                return row;
            }
        }

        return null; // No matching row found
    }

    /**
     * Helper method to convert cell value to string, returns empty string if cell is null
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if it's a date
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Format numeric value to avoid scientific notation
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    // If formula evaluates to numeric
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BLANK:
            case _NONE:
            default:
                return "";
        }
    }

}
