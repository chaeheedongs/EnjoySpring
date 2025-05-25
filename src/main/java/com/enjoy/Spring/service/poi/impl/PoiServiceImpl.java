package com.enjoy.Spring.service.poi.impl;

import com.enjoy.Spring.controller.PoiExcel;
import com.enjoy.Spring.service.poi.PoiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class PoiServiceImpl implements PoiService {

    @Override
    public void upload(final MultipartFile file) {
        if (file.isEmpty()) {
            return;
        }

        List<PoiExcel> userExcels = new ArrayList<>();

        final String originalFileName = file.getOriginalFilename();
        final String fileName = file.getName();
        final long fileSize = file.getSize();

        log.info( "originalFileName = {}, fileName = {}, fileSize = {}", originalFileName, fileName, fileSize);
        InputStream inputStream = null;
        Workbook workBook = null;
        try {
            inputStream = file.getInputStream();
            workBook = new XSSFWorkbook(inputStream);
            final Sheet sheet = workBook.getSheetAt(0);
            final int sheetSize = sheet.getLastRowNum();
            log.info( "sheetSize = {}", sheetSize);

            final Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                final Row row = rows.next();
                if (row.getRowNum() == 0) {
                    continue;
                }

                final PoiExcel userExcel = PoiExcel.of()
                                                   .name(row.getCell(0)
                                                          .getStringCellValue())
                                                   .address(row.getCell(1)
                                                             .getStringCellValue())
                                                   .etc(row.getCell(2)
                                                         .getStringCellValue())
                                                   .build();
                userExcels.add(userExcel);

                /* cell 하나 하나 확인 */
//                final Iterator<Cell> cells = row.cellIterator();
//                while (cells.hasNext()) {
//                    final Cell cell = cells.next();
//                    final String cellValue = cell.getStringCellValue();
//                    log.info("rowNum = {}, cellValue = {}", row.getRowNum(), cellValue);
//                }
            }
        }
        catch (Exception e) {
            log.error("### Poi Upload Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                workBook.close();
                inputStream.close();
            }
            catch (Exception e) {
                log.error("### Poi Stream Close Exception: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        log.info("userExcels = {}", userExcels);
    }

    @Override
    public void uploadFiles(final MultipartFile[] files) {
        if (files == null) {
            return;
        }

        List<PoiExcel> userExcels = new ArrayList<>();
        InputStream inputStream = null;
        Workbook workBook = null;
        try {
            for (MultipartFile file : files) {
                final String originalFileName = file.getOriginalFilename();
                final String fileName = file.getName();
                final long fileSize = file.getSize();

                log.info( "originalFileName = {}, fileName = {}, fileSize = {}", originalFileName, fileName, fileSize);

                inputStream = file.getInputStream();
                workBook = new XSSFWorkbook(inputStream);
                final Sheet sheet = workBook.getSheetAt(0);
                final int sheetSize = sheet.getLastRowNum();
                log.info( "sheetSize = {}", sheetSize);

                final Iterator<Row> rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    final Row row = rows.next();
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    final PoiExcel userExcel = PoiExcel.of()
                                                       .name(row.getCell(0)
                                                                .getStringCellValue())
                                                       .address(row.getCell(1)
                                                                   .getStringCellValue())
                                                       .etc(row.getCell(2)
                                                               .getStringCellValue())
                                                       .build();
                    userExcels.add(userExcel);

                    /* cell 하나 하나 확인 */
    //                final Iterator<Cell> cells = row.cellIterator();
    //                while (cells.hasNext()) {
    //                    final Cell cell = cells.next();
    //                    final String cellValue = cell.getStringCellValue();
    //                    log.info("rowNum = {}, cellValue = {}", row.getRowNum(), cellValue);
    //                }
                }
            }
        }
        catch (Exception e) {
            log.error("### Poi Upload Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                workBook.close();
                inputStream.close();
            }
            catch (Exception e) {
                log.error("### Poi Stream Close Exception: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        log.info("userExcels = {}", userExcels);
    }

    @Override
    public void download(HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        Workbook workBook = null;
        try {
            outputStream = response.getOutputStream();
            workBook = new XSSFWorkbook();

            Sheet sheet = workBook.createSheet("test");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("결과1");
            header.createCell(1).setCellValue("결과2");
            header.createCell(2).setCellValue("결과3");

            final List<PoiExcel> poiExcels = this.getPoiExcels();
            for (int rowIndex = 1; rowIndex <= poiExcels.size(); rowIndex++) {
                final PoiExcel poiExcel = poiExcels.get(rowIndex - 1);
                final Row row = sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(poiExcel.getName());
                row.createCell(1).setCellValue(poiExcel.getAddress());
                row.createCell(2).setCellValue(poiExcel.getEtc());
            }

            final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            final String fileName = "엑셀_다운로드_테스트_" + now;
            final String encodeedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + encodeedFileName + ".xlsx");

            workBook.write(outputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                workBook.close();
                outputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<PoiExcel> getPoiExcels() {
        final PoiExcel data1 = PoiExcel.of()
                                       .name("aaab")
                                       .address("bbbb")
                                       .etc("cccc")
                                       .build();
        final PoiExcel data2 = PoiExcel.of()
                                       .name("dddd")
                                       .address("eeee")
                                       .etc("ffff")
                                       .build();
        final PoiExcel data3 = PoiExcel.of()
                                       .name("gggg")
                                       .address("hhhh")
                                       .etc("iiii")
                                       .build();
        return List.of(data1, data2, data3);
    }
}
