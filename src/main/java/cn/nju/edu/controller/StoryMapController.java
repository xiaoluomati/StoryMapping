package cn.nju.edu.controller;

import cn.nju.edu.service.StoryMapService;
import cn.nju.edu.util.ExcelHelper;
import cn.nju.edu.vo.StoryMapVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@RestController
public class StoryMapController {


    @Autowired
    StoryMapService storyMapService;

    @GetMapping("/getStoryMapList")
    public List<StoryMapVo> getStoryMapList(@RequestParam(value="userId", defaultValue="1") int userId) {
        return storyMapService.getStoryMapList(userId);
    }

    @PostMapping("/addStoryMap")
    public void addStoryMap(StoryMapVo storyMapVo){
        storyMapService.addStoryMap(storyMapVo);
    }

    @PostMapping("/updateStoryMap")
    public void updateStoryMap(StoryMapVo storyMapVo){
        storyMapService.updateStoryMap(storyMapVo);
    }

    @PostMapping("/deleteStoryMap")
    public void deleteStoryMap(StoryMapVo storyMapVo){
        storyMapService.deleteStoryMap(storyMapVo);
    }

//    @GetMapping("/exportExcel")
//    public void exportExcel(@RequestParam(value="storyId", defaultValue="1") int storyId){
//        storyMapService.exportExcel(storyId);
//    }

    @GetMapping(value = "/exportExcel")
    public ResponseEntity<Resource> exportExcel(@RequestParam(value="storyId", defaultValue="1") int storyId){
        storyMapService.exportExcel(storyId);
        ByteArrayOutputStream bos = null;
        String filename = "storyMap.xlsx";
        try {
            ExcelHelper excelHelper = new ExcelHelper();
            File file = new File("src/main/resources/Excel/storyMap.xlsx");
            Workbook workbook = excelHelper.openFile(file);
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            workbook.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("charset", "utf-8");
            //设置下载文件名
            filename = URLEncoder.encode(filename, "UTF-8");
            headers.add("Content-Disposition", "attachment;filename=\"" + filename + "\"");

            Resource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));

            return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);

        } catch (IOException e) {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }
}

