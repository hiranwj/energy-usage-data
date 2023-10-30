package io.xdoto.energyusagedata.service;

import io.xdoto.energyusagedata.entity.Device;
import io.xdoto.energyusagedata.repository.DeviceRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
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

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(device);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Hiran");

        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }

    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Device> deviceList = deviceRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Device Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("device_01");
        row.createCell(1).setCellValue("device_02");
        row.createCell(2).setCellValue("device_03");
        row.createCell(3).setCellValue("device_04");

        int dataRowIndex = 1;

//        for (Course course : deviceList) {
//            HSSFRow dataRow = sheet.createRow(dataRowIndex);
//            dataRow.createCell(0).setCellValue(course.getCid());
//            dataRow.createCell(1).setCellValue(course.getName());
//            dataRow.createCell(2).setCellValue(course.getPrice());
//            dataRowIndex++;
//        }

        for (int i = 0; i < deviceList.size(); i++) {
            Device device = deviceList.get(i);
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(device.getDevice_01());
            dataRow.createCell(1).setCellValue(device.getDevice_02());
            dataRow.createCell(2).setCellValue(device.getDevice_03());
            dataRow.createCell(3).setCellValue(device.getDevice_04());
            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
