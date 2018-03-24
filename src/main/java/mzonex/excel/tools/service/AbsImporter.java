package mzonex.excel.tools.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Manh on 12/9/2015.
 */
public abstract class AbsImporter {
	private static final Logger log = LogManager.getLogger(AbsImporter.class);

	public boolean importFile(String filePath) {

		Path path = Paths.get(filePath);
		if (!Files.exists(path)) {
			log.error("File [{}] does not exist", filePath);
			return false;
		}

		long t1 = System.currentTimeMillis();
		log.info("Importer is processing file [{}]", filePath);

		boolean flag;

		try {
			flag = readFile(path);

			if (!flag) {
				log.error("Read file error");
				flag = false;
			} else {
				flag = processData();
			}
		} catch (Exception e) {
			log.error("Can not read file", e);
			flag = false;
		}

		log.info("Importer has finished file processing [{}], consumed time = {} ms"
				, filePath
				, System.currentTimeMillis() - t1
		);

		return flag;
	}

	protected abstract boolean readFile(Path path) throws IOException;
	protected abstract boolean processData();
}