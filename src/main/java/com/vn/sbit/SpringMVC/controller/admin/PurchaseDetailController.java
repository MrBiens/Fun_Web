package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailCSV;
import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.service.PurchaseDetailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/purchase-detail")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PurchaseDetailController {
    private static final Logger log = LoggerFactory.getLogger(PurchaseDetailController.class);
    PurchaseDetailService purchaseDetailService;
    ProductSupplierRepository productSupplierRepository;


    //get All PurchaseInvoiceDetail
    @GetMapping("/home")
    public String home(Model model){
        List<PurchaseDetailResponse> list_purchasedetail=purchaseDetailService.getAll();
        model.addAttribute("list_purchasedetail",list_purchasedetail);
        return "admin/purchasedetail/index";
    }

    @GetMapping("/index/{purchaseId}")
    public String getPurchase(@PathVariable("purchaseId")Long purchaseInvoiceId,Model model){
        List<PurchaseDetailResponse> list=purchaseDetailService.getPurchaseInvoiceDetailsByPurchaseInvoiceId(purchaseInvoiceId);
        model.addAttribute("list",list);

        model.addAttribute("purchaseInvoiceId",purchaseInvoiceId);

        Double totalAmount = purchaseDetailService.calculateTotalAmountByInvoiceId(purchaseInvoiceId);
        model.addAttribute("totalAmount",totalAmount);

        int totalQuantity = purchaseDetailService.calculateTotalQuantityByInvoiceId(purchaseInvoiceId);
        model.addAttribute("totalQuantity",totalQuantity);

        return "admin/purchasedetail/detail";
    }

    @GetMapping("/add/{purchaseId}")
    public String add(@PathVariable("purchaseId")Long purchaseId, Model model){
        PurchaseDetailRequest request = new PurchaseDetailRequest();
        request.setPurchaseInvoiceId(purchaseId);

        model.addAttribute("request",request);

        model.addAttribute("purchaseId",purchaseId); // xem đó là hóa đơn nào - who supplier

        List<ProductSupplier> productSupllier = productSupplierRepository.findAll();
        model.addAttribute("list_productSupplier",productSupllier); // lấy productsupplier xem user nhập product của supplier nào -> trả về productSupplierId

        return "admin/purchasedetail/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("request") PurchaseDetailRequest request, Model model, RedirectAttributes redirectAttributes){
        PurchaseDetailResponse response = purchaseDetailService.create(request);

        Long purchaseId = request.getPurchaseInvoiceId();
        redirectAttributes.addAttribute("purchaseId", purchaseId); // thêm purchaseId vào đường dẫn
        return "redirect:/admin/purchase-detail/index/{purchaseId}";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        PurchaseInvoiceDetail purchaseInvoiceDetail = purchaseDetailService.getById(id); // đã check
        
        model.addAttribute("purchaseDetailId",id); // id purchaseDetail

        PurchaseDetailUpdateRequest request = new PurchaseDetailUpdateRequest();

        request.setPurchaseInvoiceId(purchaseInvoiceDetail.getId());

        model.addAttribute("request",request);

        List<ProductSupplier> productSupllier = productSupplierRepository.findAll();
        model.addAttribute("list_productSupplier",productSupllier); // lấy productsupplier xem user nhập product của supplier nào -> trả về productSupplierId

        return "admin/purchasedetail/edit";

    }



    @PostMapping("/edit")
    public String update(@RequestParam("purchaseDetailId")Long id,@ModelAttribute("request")PurchaseDetailUpdateRequest request,RedirectAttributes redirectAttributes){
        if(request != null){
            purchaseDetailService.updateById(id,request);
            Long purchaseId = purchaseDetailService.getById(id).getPurchaseInvoice().getId();
            redirectAttributes.addAttribute("purchaseId", purchaseId); // thêm purchaseId vào đường dẫn
            return "redirect:/admin/purchase-detail/index/{purchaseId}";
        }else{
            return "admin/purchasedetail/edit";
        }

    }




    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        Long purchaseId = purchaseDetailService.getById(id).getPurchaseInvoice().getId();
        purchaseDetailService.deleteById(id);
        redirectAttributes.addAttribute("purchaseId", purchaseId); // thêm purchaseId vào đường dẫn
        return "redirect:/admin/purchase-detail/index/{purchaseId}";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String fileName="purchaseDetail"+currentDateTime+".csv";
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename="+fileName;

        response.setHeader(headerKey,headerValue);

        List<PurchaseDetailResponse> detailList = purchaseDetailService.getAll();

        List<PurchaseDetailCSV> detailCSVList = detailList.stream()
                .map(PurchaseDetailCSV::new)
                .toList();
        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

//        String [] csvHeader = {"Mã chi tiết hóa đơn","Mã hóa đơn","Mã sản phẩm nhà ","Số lượng","Đơn giá","Tổng tiền","Số tiền đã thanh toán"}; // loi font text

        String [] csvHeader = {"id","purchase_invoice_id","product_supplier_id","quantity","purchase_price","total_price","debt"};
        String [] nameMapping = {"id","purchaseInvoiceId","productSupplierId","quantity","purchasePrice","totalPrice","debt"}; //PurchaseDetailCSV request

        csvBeanWriter.writeHeader(csvHeader);
        for (PurchaseDetailCSV detailCSV : detailCSVList) {
            csvBeanWriter.write(detailCSV, nameMapping);
        }
        csvBeanWriter.close();
    }

}
