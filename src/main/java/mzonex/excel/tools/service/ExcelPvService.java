package mzonex.excel.tools.service;

import lombok.extern.slf4j.Slf4j;
import mzonex.excel.tools.repository.CustomerRp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExcelPvService {
    @Autowired
    private CustomerRp customerRp;

    public boolean generateTemplatePv() {
        log.debug("---- Hello log");
        log.debug("---- id 3 {}", customerRp.getOne(3).getCustomername());
        return true;
    }
}
