package com.birtdemo.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.birtreport.servlet.BirtEngineFactory;

public interface ReportProcessor {

	BirtEngineFactory getBitEngineFactory();
	
	void shutDownBirtEngine();

	boolean initilizeBirtEngine();

	void processReport(HttpServletRequest request, HttpServletResponse response);

}
