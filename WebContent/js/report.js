
/**
 * This js contains two method 
 * 1)generateReport(reportName) : Requesting to load the report content in div.
 * 2)downloadReport(format): Downloading the current report in given format.
 */


//this will hold the currently loaded report name.
currentReportName="";
currentPageNumber =1;
paginationFlag=false;
	/**
	 * This method is responsible for loading the reports in the report div.
	 * @param localReportName
	 */
	function generateReport(reportName) {
	
		//	here relative url is given if relative url is not working try giving full url
		var reporturl ="/BirtDemo/loadReport?ReportName="+reportName+"&ReportFormat=html"+"&pageNumber="+currentPageNumber;
		
		$("#reportData").html("Loading...<br><img src='/BirtDemo/images/loading.gif' align='middle' >");
		
        $('#reportData').load(reporturl ,function(response, status, xhr) {
        	
          if (status == "error") {
		    var msg = "Sorry but there was an error getting details ! ";
			$("#reportData").html(msg + xhr.status + " " + xhr.statusText);
		  }
	    });
        
        currentReportName=reportName;
        //once you set pagination skip pagination next time
        if(currentReportName =="books_report.rptdesign" && paginationFlag==false){
        	setPagePagination();
        	paginationFlag=true;
        }
	}
	
	/**
	 * Download report function
	 * 
	 * @param format
	 */
	function downloadReport(format){
		
		if(currentReportName==""){
			alert("Please Select the report.");
			return;
		}
		//here relative url is given if relative url is not working try giving full url
		var reporturl ="/BirtDemo/loadReport?ReportName="+currentReportName+"&ReportFormat="+format;
		window.location.href = reporturl;
		
	}
	
	function setPagePagination(){
		
		var reporturl ="/BirtDemo/getPageCount";

		$.ajax({
		  type : 'GET',//GET Or POST 
		  url  : reporturl,
		  cache: false, //get fresh copy of details.html instead of cahced one
		 
		  success: function(response, textStatus, jqXHR){
		        alert("Total number pagination pages are :"+response);
		        var pageCount = response;
		        var paginationHtml="";
	
		        //create pagincation html
		        for(var i=1;i<=pageCount;i++){
		        	paginationHtml = paginationHtml +"   <b><a href='#' >"+i+"</a></b>" +" |";
		        }
		        
		        $("#paginationDiv").html("pagination -->"+paginationHtml);
		        
				$("#paginationDiv a").click(function(){
					alert("---"+$(this).text()+"---");
					currentPageNumber =$(this).text();
					
					generateReport(currentReportName);
				});

		  }		 
		});
	}
	
