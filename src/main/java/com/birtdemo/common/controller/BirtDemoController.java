package com.birtdemo.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.birtdemo.common.service.ReportProcessor;

public class BirtDemoController extends AbstractController {
	private ReportProcessor reportProcessor;
	
	public void setReportProcessor(ReportProcessor reportProcessor) {
		this.reportProcessor = reportProcessor;
	}

	public void  init(){
		reportProcessor.initilizeBirtEngine();
		System.out.println("Engine Initilized!!");
	}

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		reportProcessor.processReport(request, response);
		return  null;
	}

}