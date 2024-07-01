package com.tarifftales.test.dto;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class ExcelFileDTO {
	
	    private String carrier;
	    private String origin;
	    private String destination;
	    private String via;
	    private int frequency;
	    private int transitTime;
	    private String mode;
	    private String commodity;
	    private String flightNo;
	    private String etd;
	    private String eta;
	    private String currency;
	    private int minValue;
	    private int nRate;
	    private int tax;
	    private int fortyFive;
	    private int hundred;
	    private int twoFifty;
	    private int threeHundred;
	    private int fiveHundred;
	    private int oneThousand;
	    ArrayList<Integer> valueList;
}
