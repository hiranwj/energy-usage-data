package io.xdoto.energyusagedata.controller;

import io.xdoto.energyusagedata.entity.Device;
import io.xdoto.energyusagedata.repository.DeviceRepository;
import io.xdoto.energyusagedata.service.DeviceService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getDevices")
    public List<Device> getDevice() {
        List<Device> device = deviceRepository.findAll();
        return device;
    }

    @GetMapping("/pdf/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {

       // Export the report to PDF
       response.setContentType("application/pdf");

       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
       String currentDateTime = dateFormatter.format(new Date());

       String headerKey = "Content-Disposition";
       String headerValue = "attachment; filename=device_" + currentDateTime + ".pdf";
       response.setHeader(headerKey, headerValue);

//        response.setHeader("Content-Disposition", "attachment; filename=device_pdf_report.pdf");

        deviceService.exportJasperReport(response);
    }

    @GetMapping("/excel/export")
    public void generateExcelReport(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=device_" + currentDateTime + ".xls";
        response.setHeader(headerKey, headerValue);

//        response.setHeader("Content-Disposition", "attachment; filename=device_excel_report.pdf");

        deviceService.exportExcel(response);
    }
}
