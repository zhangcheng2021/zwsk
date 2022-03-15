package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.entity.review.ExportGpsBean;
import com.hsmap.yuezhihui.service.review.IComplexRelationService;
import com.hsmap.yuezhihui.utils.customize.ExcelExport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExportTest extends BootTest {

    @Autowired
    private ExcelExport excelExport;
    @Autowired
    private IComplexRelationService complexRelationService;

    @Test
    public void gpsExport() {
        ExportGpsBean bean = complexRelationService.selectGPSExportInfo(1);
        excelExport.exportGps("D:\\temp\\cgj\\gps105.xlsx", bean);
    }
}
