package com.birtdemo.common.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.birtdemo.common.service.ReportProcessor;
import com.birtreport.servlet.BirtEngineFactory;
import com.birtreport.util.ReportRenderer;

public class ReportProcessorImpl implements ReportProcessor{

	private BirtEngineFactory birtEngineFactory; 
	private ReportRenderer reportRenderer;
	private static ReportProcessor reportProcessor =null; 
	
	@Override
	public boolean initilizeBirtEngine(){
		boolean isInitialized =true;
		reportRenderer = new ReportRenderer();
		reportRenderer.setBirtEngine( this.getBitEngineFactory().getEngine() );
		
		System.out.println("Bit Engine Successfully Started.");
		
		return isInitialized;
	}
	
	/**
	 * Annotated with @ bean and will create BirtEngineFactory bean.
	 * @return
	 */
	@Override
	public BirtEngineFactory getBitEngineFactory(){
		
		birtEngineFactory = BirtEngineFactory.getBirtEngineFactory() ;
		//uncomment to use logging
		//birtEngineFactory.setLogLevel( Level.FINEST);
		//birt engine logs will be created under this directory.
		//currently this line is commented
		//birtEngineFactory.setLogDirectory( new File("E:/WorkSpaces/PracticeWorkspace/BirtIntegration/birtlogs"));
		
	  return birtEngineFactory; 
	}
	
	@Override
	public void shutDownBirtEngine(){
		birtEngineFactory.destroy();
		
	}
	
	@Override
	public void processReport(HttpServletRequest request,
	    HttpServletResponse response) {
		reportRenderer.processReportDesignDocAndRenderReport(request, response);
	}
	
}
