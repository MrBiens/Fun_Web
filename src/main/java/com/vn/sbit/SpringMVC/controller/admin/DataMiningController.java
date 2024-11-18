package com.vn.sbit.SpringMVC.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/admin/datamining")
public class DataMiningController {
    @GetMapping("/home")
    public String home(){
        return "admin/analyze/index";
    }

    @PostMapping("/analyze")
    public String analyzeCsv(@RequestParam("file") MultipartFile file, Model model) throws Exception {
        // Bước 1: Lưu file CSV vào thư mục tạm
        File csvFile = new File("temp.csv");
        try (FileOutputStream fos = new FileOutputStream(csvFile)) {
            fos.write(file.getBytes());
        }

        // Bước 2: Chuyển CSV sang định dạng Instances (Weka)
        CSVLoader loader = new CSVLoader();
        loader.setSource(csvFile);
        Instances data = loader.getDataSet();

        // Bước 3: Áp dụng thuật toán K-Means
        SimpleKMeans kMeans = new SimpleKMeans();
        kMeans.setNumClusters(3); // Số cụm tùy chỉnh
        kMeans.buildClusterer(data);

        StringBuilder result = new StringBuilder("Kết quả phân cụm:\n");
        for (int i = 0; i < data.numInstances(); i++) {
            int cluster = kMeans.clusterInstance(data.instance(i));
            result.append("Dòng ").append(i + 1).append(" thuộc cụm: ").append(cluster).append("\n");
        }

        // Xóa file tạm
        csvFile.delete();

        model.addAttribute("analysisResult", result.toString());
        return "admin/analyze/result"; // Trả về tên file HTML trong thư m ục templates
    }
}
