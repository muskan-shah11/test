package com.tarifftales.test.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tarifftales.test.dto.ExcelFileDTO;
import com.tarifftales.test.dto.FormData;
import com.tarifftales.test.dto.TenantMAWBRPDTO;
import com.tarifftales.test.entity.TenantMAWBFreightRP;
import com.tarifftales.test.entity.TenantMAWBFreightRPRate;
import com.tarifftales.test.entity.TenantMAWBRP;
import com.tarifftales.test.repository.TenantMAWBFreightRPRepository;
import com.tarifftales.test.repository.TenantMAWBRPRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TenantServiceImpl implements TenantService  {
	
    @Autowired
    private TenantMAWBRPRepository tenantMAWBRPRepository;

    @Autowired
    private TenantMAWBFreightRPRepository tenantMAWBFreightRPRepository;

	@Override
	public TenantMAWBRP saveTenatFormData(TenantMAWBRPDTO tenantMAWBRPDTO) {
		TenantMAWBRP tenantMAWBRP = null;
		try {
			if(tenantMAWBRPDTO != null) {
				tenantMAWBRP = tenantMAWBRPDTOToEntity(tenantMAWBRPDTO);
				tenantMAWBRPRepository.save(tenantMAWBRP);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			//return "Failed to save entity tenantMAWBRP " + e.getMessage().toString();
		}
		return tenantMAWBRP;
	}

	private TenantMAWBRP tenantMAWBRPDTOToEntity(TenantMAWBRPDTO tenantMAWBRPDTO) {

		TenantMAWBRP tenantMAWBRP = new TenantMAWBRP();
		
		tenantMAWBRP.setActive(tenantMAWBRPDTO.getActive());
		tenantMAWBRP.setCarrierName(tenantMAWBRPDTO.getCarrierName());
		tenantMAWBRP.setCreatedBy(null);
		tenantMAWBRP.setCreatedOn(new Date());
		tenantMAWBRP.setModifiedBy(null);
		tenantMAWBRP.setModifiedOn(new Date());
		tenantMAWBRP.setRateType(tenantMAWBRPDTO.getRateType());
		tenantMAWBRP.setValidFrom(tenantMAWBRPDTO.getValidFrom());
		tenantMAWBRP.setValidTo(tenantMAWBRPDTO.getValidTo());
		
		return tenantMAWBRP;
	}
	
	@Override
	public TenantMAWBFreightRP uploadFileData(MultipartFile multipartFile, FormData formData) {
	   	try{
    	    Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream()); // Or HSSFWorkbook for XLS
    	    Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
    	    Iterator<Row> rowIterator = sheet.iterator();
    	    
    	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = dateFormat.parse(formData.getValidFrom());
            Date dateTo = dateFormat.parse(formData.getValidTo());
    	    
    	    TenantMAWBRP tenantMAWBRP =  new TenantMAWBRP();
    	    
//    	    tenantMAWBRP.setChargeId(""+Math.random());
    	    tenantMAWBRP.setRateType(formData.getRateType());
    	    tenantMAWBRP.setActive(true);
    	    tenantMAWBRP.setCarrierName(formData.getCarrierName());
    	    tenantMAWBRP.setValidFrom(dateFrom);
    	    tenantMAWBRP.setValidTo(dateTo);
    	    tenantMAWBRP.setCreatedOn(new Date());
    	    List<TenantMAWBFreightRP> TenantMAWBFreightRPList = new ArrayList<>();
    	   
    	    List<String> headerList = new ArrayList<>();
    		ExcelFileDTO excelFileDTO =null;
    	    
//    	    for (Row row : sheet) 
    		 while(rowIterator.hasNext())
    	    
    	    {
    			 
    			   Row row = rowIterator.next();
    			   if(isRowEmpty( row))
      			 {
      				 break;
      			 }
    			 
    	    	if(row == null) {
    	    		continue;
    	    	}
    	    	if(row.getRowNum() == 0) {
    	    		// for weight slab only
    	    		for(int cell=14; cell<=19; cell++) {
    	    			System.out.println(cell);
    	    			headerList.add(""+row.getCell(cell).getNumericCellValue());
    	    		}
    	    		continue;
    	    	}
    	    		
    	    	TenantMAWBFreightRP entity = new TenantMAWBFreightRP();
    	    	 List<TenantMAWBFreightRPRate> rateList = new ArrayList<>();
    	    	 excelFileDTO = setExcelFileDTO(row);
    	    	
        	    StringTokenizer tokenizer = new StringTokenizer(excelFileDTO.getEta());
        	    String etd = excelFileDTO.getEtd();
        	    
        	    
        	    // ETA and ETD extraction code
        	    String etdFrom = "";
	            String etdTo = "";
        	    
        	    if(etd.toLowerCase().contains("to") || etd.toLowerCase().contains("-")) {
        	    	// get 1st token before "to" and second token after "to"
        	    	
        	    	// Split the input string into an array of words
        	        String[] words = etd.split(" ");
        	        
        	        // Find the index of the word "to"
        	        int toIndex = -1;
        	        for (int i = 0; i < words.length; i++) {
        	            if ("to".equals(words[i].toLowerCase()) || "-".equals(words[i].toLowerCase()) ) {
        	                toIndex = i;
        	                break;
        	            }
        	        }

        	        // Extract the first word before "to" and the first word after "to"
        	        if (toIndex != -1 && toIndex >= 1 && toIndex < words.length - 1) {
        	             etdFrom = words[toIndex - 1];
        	             etdTo = words[toIndex + 1];

        	        } else {
        	            System.out.println("Not enough words before or after 'to' or 'to' not found");
        	        }
        	    }
        	    else {
        	    	 if(etd.toLowerCase().contains(":") || etd.toLowerCase().contains(".")) {
        	    		 if(etd.toLowerCase().contains("."))
        	    			etd = etd.replace(".", ":");
        	    		 
        	    		 
        	    		 etdFrom = etd;
        	    		 etdTo = etd;
        	    		 
        	    	 }
        	    }
        	    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .appendOptional(DateTimeFormatter.ofPattern("H:mm"))
                        .appendOptional(DateTimeFormatter.ofPattern("HH:mm"))
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT);
        	    
        	    entity.setEtdFrom(LocalTime.parse(etdFrom, formatter));
        	    entity.setEtdTo(LocalTime.parse(etdTo, formatter));
        	    System.out.println(entity.getEtdFrom()+"-------------------"+entity.getEtdTo());
        	    
        	    // Etraction for flight name and code
        	    
        	    processInputString(excelFileDTO.getFlightNo(),entity);
        	    
        	    
        	    System.out.println("########################### Flight : "+entity.getApplicableFlights()+ "      "+entity.getExceptionFlights());
        	
        	    entity.setActive(true);
        	    entity.setCargoMode(excelFileDTO.getMode());
        	    entity.setCargoType(null);
        	    entity.setCarrierName(excelFileDTO.getCarrier());
        	    entity.setCreatedBy("SYSTEM");
        	    entity.setCurrency(excelFileDTO.getCurrency());
        	    entity.setDestPort(excelFileDTO.getDestination());
        	    entity.setEta(null);
        	    entity.setFrequency(excelFileDTO.getFrequency());
        	    entity.setMinVal(excelFileDTO.getMinValue());
        	    entity.setModifiedBy(null);
        	    entity.setNRate(excelFileDTO.getNRate());
        	    entity.setOriginPort(excelFileDTO.getOrigin());
        	    entity.setTax(excelFileDTO.getTax());
          //   entity.setTenantMAWBRP(null);
        	    entity.setTransitTime(excelFileDTO.getTransitTime());
        	    entity.setViaPort(excelFileDTO.getVia());
        	    entity.setCreatedOn(new Date());
//        	    tenantMAWBFreightRPRepository.save(entity);
        	    
        	    
//        	    rateList
        	    Map<String, Integer> rateValuewithSlab = new HashMap<>();
        	    
//        	    rateValuewithSlab.put(excelFileDTO.get, null);
        	    for(String header : headerList) {
            	    rateValuewithSlab.put((header), excelFileDTO.getFortyFive());
            	    
        	    }
        	    
        	    System.out.println(rateValuewithSlab);
        	    
        	    
        	    int count=0;
        	 for(String val : headerList) {
        		 TenantMAWBFreightRPRate rate = new TenantMAWBFreightRPRate();
        		 rate.setCurrencyCd(excelFileDTO.getCurrency());
        		 rate.setWeightSlabType(val);
        		 rate.setFreightValue(excelFileDTO.getValueList().get(count));
        		 rate.setCreatedOn(new Date());
        		 count++;
        		 rateList.add(rate);
        	 }
        	 entity.setFreightRPRateList(rateList);	
//        	 entity.setChargeId(tenantMAWBRP.getChargeId());// setting the rate list in FreightRp
        	 TenantMAWBFreightRPList.add(entity);            // adding  FreightRp in List
        	 
        	    System.out.println(headerList+"  "+excelFileDTO.getValueList());
    	    }
    	    
    	   // tenantMAWBRP.setFreightRPList(TenantMAWBFreightRPList);
    	    tenantMAWBRP.setTenantMAWBFreightRPs(TenantMAWBFreightRPList);
    	    tenantMAWBRPRepository.save(tenantMAWBRP);
    	 } catch (IOException e) {
    	    e.printStackTrace();
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private ExcelFileDTO setExcelFileDTO(Row row) {
		
	    ExcelFileDTO excelFileDTO = new ExcelFileDTO();
		excelFileDTO.setCarrier(row.getCell(0).getStringCellValue());
	    excelFileDTO.setOrigin(row.getCell(1).getStringCellValue());
	    excelFileDTO.setDestination(row.getCell(2).getStringCellValue());
	    excelFileDTO.setVia(row.getCell(3).getStringCellValue());
	    excelFileDTO.setFrequency((int) row.getCell(4).getNumericCellValue());
	    excelFileDTO.setTransitTime((int) row.getCell(5).getNumericCellValue());
	    excelFileDTO.setMode(row.getCell(6).getStringCellValue());
	    excelFileDTO.setCommodity(row.getCell(7).getStringCellValue());
	    excelFileDTO.setFlightNo(row.getCell(8).getStringCellValue());
	      
	    // taking String
	    excelFileDTO.setEtd(row.getCell(9).getStringCellValue());
	    
	    excelFileDTO.setEta(row.getCell(10).getStringCellValue());
	    excelFileDTO.setCurrency(row.getCell(11).getStringCellValue());
	    excelFileDTO.setMinValue((int) row.getCell(12).getNumericCellValue());
	    excelFileDTO.setNRate((int) row.getCell(13).getNumericCellValue());
	    
	    // fright_rp_rate data
	    ArrayList<Integer> valueList = new ArrayList<>();
	   
	    for(int i=14;i<=19;i++) {
	    	valueList.add((int) row.getCell(i).getNumericCellValue());
	    }
	    excelFileDTO.setValueList(valueList);
//	    excelFileDTO.setFortyFive((int) row.getCell(14).getNumericCellValue());
//	    excelFileDTO.setHundred((int) row.getCell(15).getNumericCellValue());
//	    excelFileDTO.setTwoFifty((int) row.getCell(16).getNumericCellValue());
//	    excelFileDTO.setFiveHundred((int) row.getCell(17).getNumericCellValue());
//	    excelFileDTO.setOneThousand((int) row.getCell(18).getNumericCellValue());
//	    excelFileDTO.setTax((int) row.getCell(19).getNumericCellValue());
	    
	    excelFileDTO.setTax((int) row.getCell(20).getNumericCellValue());
		
		return excelFileDTO;
	}
	
	 private static void processInputString(String input,TenantMAWBFreightRP entity) {
	        String applicableFlights = null;
	        String exceptionFlights = null;

	        if (input.contains("All flights")) {
	            applicableFlights = input;
	        } else if (input.contains("except")) {
	            applicableFlights = "All flights";
	            exceptionFlights = extractFlightCodes(input.substring(input.indexOf("except") + 6));
	        } else if (input.contains("Only on")) {
	            applicableFlights = extractFlightCodes(input.substring(input.indexOf("Only on") + 8));
	        }
	        else {
	        	entity.setApplicableFlights(input);
	        }

	        if(applicableFlights !=null) {
	        	entity.setApplicableFlights(applicableFlights);
	        }
	        if(exceptionFlights != null) {
	        	entity.setExceptionFlights(exceptionFlights);
	        }
	        
	        System.out.println("Applicable Flights: " + applicableFlights);
	        System.out.println("Exception Flights: " + exceptionFlights);
	        System.out.println();
	    }
	 
	 private static boolean isRowEmpty(Row row) {
		 if (row == null) {
		 return true;
		 }
		 if (row.getLastCellNum() <= 0) {
		 return true;
		 }
		 for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
		 Cell cell = row.getCell(c);
		 if (cell != null )
		 return false;
		 }
		 return true;
		 }

	    private static String extractFlightCodes(String flightCodeString) {
	        return Arrays.stream(flightCodeString.split("[,&and]+"))
	                .map(String::trim)
	                .reduce((code1, code2) -> code1 + " " + code2)
	                .orElse("");
	    }
	


}
