package mzonex.excel.tools.service;

import lombok.extern.slf4j.Slf4j;
import mzonex.excel.tools.repository.CustomerRp;
import mzonex.excel.tools.worker.ExcelPvTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ExcelPvService {
    @Autowired
    private CustomerRp customerRp;

    @Autowired
    private ApplicationContext applicationContext;

    private final String templatePath = "/excel-templates/templatePV.xls";

    public boolean generateTemplatePv() {
        ExcelPvTemplate excelPvTemplate = new ExcelPvTemplate(customerRp);

        log.debug("---- Hello log");
        try {
            Resource resource = applicationContext.getResource("classpath:" + templatePath);
            return excelPvTemplate.importFile(resource.getURL().getPath());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }
}
