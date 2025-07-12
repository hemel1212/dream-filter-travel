package com.example.travel.travel.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportService {

    @Autowired
    private DataSource dataSource;

    public byte[] generateReportPdf() throws Exception {
        // Load the .jrxml file
        ClassPathResource resource = new ClassPathResource("reports/report.jrxml");
        InputStream inputStream = resource.getInputStream();

        // Compile the report
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // Set parameters if needed
        Map<String, Object> parameters = new HashMap<>();

        try (Connection connection = dataSource.getConnection()) {
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // Export to PDF as byte[]
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }
}
