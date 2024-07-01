package com.tarifftales.test.service;

import org.springframework.web.multipart.MultipartFile;

import com.tarifftales.test.dto.FormData;
import com.tarifftales.test.dto.TenantMAWBRPDTO;
import com.tarifftales.test.entity.TenantMAWBFreightRP;
import com.tarifftales.test.entity.TenantMAWBRP;

public interface TenantService {
	
	public TenantMAWBRP saveTenatFormData(TenantMAWBRPDTO tenantMAWBRPDTO);
	
	public TenantMAWBFreightRP uploadFileData(MultipartFile multipartFile, FormData formData );

}
