package mzonex.excel.tools.controller;

import mzonex.excel.tools.service.ExcelPvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelToolController {

    @Autowired
    private ExcelPvService excelPvService;

    @GetMapping("/hello")
    public String hello() {
        excelPvService.generateTemplatePv();
        return "Hello from excel tools";
    }

}
