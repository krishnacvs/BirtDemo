package com.birtreport.servlet;

import java.io.File;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;


/**
*
*Factory class for the instance of the {@link IReportEngine report engine}.
*Responsible for creating IReportEngine and also destroying the engine. 
*
*/
public class BirtEngineFactory {  

	private IReportEngine birtEngine ;	
	private File _resolvedDirectory ;
	private java.util.logging.Level logLevel ;
	private static BirtEngineFactory birtEngineFactory;
	
	
	private BirtEngineFactory(){
		
	}
	
	public static BirtEngineFactory getBirtEngineFactory(){
		if(birtEngineFactory == null){
			birtEngineFactory = new BirtEngineFactory();
		}
		return birtEngineFactory;
	}
	
	/**
	 * Destroy birt engine.
	 * and shut down platform
	 * 
	 * call this method while destroying your application context.
	 */
	public void destroy() {
		
		try{
			
		birtEngine.destroy();
		Platform.shutdown() ;
		System.out.println("Engine successfully destroyed and platform is shutdown!!");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set log level
	 * @param ll
	 */
	public void setLogLevel(  java.util.logging.Level  ll){
		this.logLevel = ll ;
	}

	/**
	 * Setting log file.
	 * if log directory is not present.It will be created
	 * @param f
	 */
	public void setLogDirectory ( java.io.File f ){
		
		//TODO: if  puts wrong path please validate that here.
		if(!f.exists()){
			f.mkdirs();
		}
		this._resolvedDirectory = f; 
	}

	/**
	 * Factory method for birt engine.
	 */
	public IReportEngine getEngine(){ 

		if(birtEngine !=null){
			
		}
		
		EngineConfig config = getEngineConfig();
		
		try {
			Platform.startup( config );
		}
		catch ( BirtException e ) {
			e.printStackTrace();
		}

		IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
		IReportEngine be = factory.createReportEngine( config );
		this.birtEngine = be ;
		
		return be ;
	}

	/**
	 * Set  engine configuration like logs and other things as per the need
	 * @return
	 */
	public EngineConfig getEngineConfig(){
		EngineConfig config = new EngineConfig();
		return config;
	}
	
	
	public Class<?> getObjectType() {
		return IReportEngine.class;
	}
}
