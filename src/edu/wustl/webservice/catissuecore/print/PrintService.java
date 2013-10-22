/*L
 *  Copyright Washington University in St. Louis
 *  Copyright SemanticBits
 *  Copyright Persistent Systems
 *  Copyright Krishagni
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/catissue-print-service/LICENSE.txt for details.
 */

/**
 * <p>Title: PrintServiceImpl Class>
 * <p>Description:	PrintServiceImpl is a class which implements the PrintService 
 * interface and print  method. 
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Amit Doshi
 * @version 1.00
 * Created on Nov 1, 2007
 */
package edu.wustl.webservice.catissuecore.print;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.wustl.catissuecore.webservice.client.PrintWebServiceFactory;


@WebService (serviceName="PrintWebService")

public class PrintService 
{	
	private Log log = LogFactory.getLog(PrintService.class);
	
	@WebMethod	
	public String print(String xmlFormat)
	{
		
		log.info(xmlFormat);
		PrintServiceClient printserviceClientObj=PrintWebServiceFactory.getPrintServiceClientObject();
		
		String msg=printserviceClientObj.print(xmlFormat);
		
		return msg;
	}
	
}
