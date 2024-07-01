package com.tarifftales.test.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tarifftales.test.dto.FormData;
import com.tarifftales.test.dto.TenantMAWBRPDTO;
import com.tarifftales.test.entity.TenantMAWBRP;
import com.tarifftales.test.service.TenantService;



@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class HomeController {

   @Autowired
   TenantService tenantService;
	
    @PostMapping("/saveform")
    public TenantMAWBRP saveTenantMAWBRP(@RequestParam("file") MultipartFile file,@RequestBody TenantMAWBRPDTO tenantMAWBRPDTO) {
    	TenantMAWBRP tenantMAWBRP = tenantService.saveTenatFormData(tenantMAWBRPDTO);
    	return tenantMAWBRP;
    }
    
   
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam String rateType,
    @RequestParam String vendorType,
    @RequestParam String carrierName,
    @RequestParam String validFrom,
    @RequestParam String validTo,@RequestParam("file") MultipartFile file) throws IOException, JsonProcessingException {
    	
   FormData formData = new FormData();
   
   formData.setCarrierName(carrierName);
   formData.setRateType(rateType);
   formData.setVendorType(vendorType);
   formData.setValidTo(validTo);
   formData.setValidFrom(validFrom);    	
    	tenantService.uploadFileData(file,formData);
    	return "Success";
    }

}
