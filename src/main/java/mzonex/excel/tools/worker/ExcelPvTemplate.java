package mzonex.excel.tools.worker;

import lombok.extern.slf4j.Slf4j;
import mzonex.excel.tools.entity.Customer;
import mzonex.excel.tools.repository.CustomerRp;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelPvTemplate extends ExcelImporter {
    private final CustomerRp customerRp;

    private final String outputFolder = "/Users/manhvu/Desktop/pv-excels";

    public ExcelPvTemplate(CustomerRp customerRp) {
        this.customerRp = customerRp;
    }

    @Override
    protected boolean processData() {
        Workbook workbook = this.getWorkbook();
        Sheet sheet = workbook.getSheet("A&P Template");

        List<Customer> customerList = customerRp.findAll();

        log.debug("---- Start processing");

        // create evaluator
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        customerList.forEach(customer -> {
            log.debug("---- reading customer: {} - {}", customer.getCodeKh(), customer.getCustomername());

            Cell cell;

            // print location
            cell = sheet.getRow(9).getCell(5);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(customer.getLocation());

            // print pay to
            cell = sheet.getRow(11).getCell(2);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(customer.getCustomername());

            // print bank name
            cell = sheet.getRow(12).getCell(2);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(customer.getBankName());

            // print account no
            cell = sheet.getRow(13).getCell(6);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(customer.getAccountNumber());

            // print t tb 12 17
            cell = sheet.getRow(17).getCell(4);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(customer.getTTb1217());

            // print t q 4
            cell = sheet.getRow(18).getCell(4);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(customer.getTQ4());

            // print t nam 1
            cell = sheet.getRow(19).getCell(4);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(customer.getTNam1());

            // evaluate the formula
            evaluateAllHelper(evaluator, sheet);
            writeWorkbook(workbook, customer.getCustomername());
        });

        log.debug("---- Start processing -- end");

        try {
            workbook.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    private void evaluateAllHelper(final FormulaEvaluator evaluator, Sheet sheet) {
        for (Row r : sheet) {
            for (Cell c : r) {
                evaluateCellHelper(evaluator, c);
            }
        }
    }

    private void evaluateCellHelper(final FormulaEvaluator evaluator, Cell cell) {
        if (cell.getCellTypeEnum() == CellType.FORMULA) {
            log.debug("---- evaluate function: {}", cell.getCellFormula());
            try {
                evaluator.evaluateFormulaCellEnum(cell);
            } catch (Exception e) {
                log.error("---- can not evaluate cell {}: {}", cell.getAddress(), e.getMessage());
            }
        }
    }

    private void writeWorkbook(Workbook workbook, String fileName) {
        fileName = outputFolder + "/" + correctFileName(fileName) + ".xls";

        log.debug("---- write file: {}", fileName);

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private String correctFileName(String fileName) {
        fileName = fileName.replace('/', '-');
        return fileName;
    }
}
