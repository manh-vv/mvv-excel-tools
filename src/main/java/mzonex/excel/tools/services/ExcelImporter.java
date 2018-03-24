package mzonex.excel.tools.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Manh on 12/9/2015.
 */
public abstract class ExcelImporter extends AbsImporter {

	private static final Logger log = LogManager.getLogger(ExcelImporter.class);

	protected Workbook workbook;

	@Override
	protected boolean readFile(Path path) throws IOException {
		File file = path.toFile();
		String fileName = file.getName().toLowerCase();

		if (fileName.endsWith(".xls")) {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} else if (fileName.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(new FileInputStream(file));
		} else {
			log.error("Unrecognized file [{}]", file.getName());
			return false;
		}

		return true;
	}
}