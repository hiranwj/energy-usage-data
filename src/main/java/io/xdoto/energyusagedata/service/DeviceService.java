package io.xdoto.energyusagedata.service;

import io.xdoto.energyusagedata.entity.Device;
import io.xdoto.energyusagedata.repository.DeviceRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public void exportJasperReport(HttpServletResponse response) throws IOException, JRException {
        List<Device> device = deviceRepository.findAll();

        File file = ResourceUtils.getFile("classpath:device-tem.jrxml");

        // get absolute path
//        File file = ResourceUtils.getFile("/home/hiran/hiran-xdoto/9-Daily_tasks-September/2023-09-25-Mon/springboot-jasper-report/src/main/resources/device-tem.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(device);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Hiran");
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}
