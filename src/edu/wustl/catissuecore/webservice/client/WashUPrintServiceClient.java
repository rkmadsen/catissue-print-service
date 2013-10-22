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
 * <p>Title: WashUPrintServiceClient Class>
 * <p>Description:	WashUPrintServiceClient is a class that is default client of
 * the print web service  
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Amit Doshi
 * @version 1.00
 * Created on Nov 1, 2007
 */
package edu.wustl.catissuecore.webservice.client;

import java.util.LinkedHashMap;

import edu.wustl.catissuecore.webservice.util.WashuLabelFileWriter;
import edu.wustl.webservice.catissuecore.print.PrintServiceClient;
import edu.wustl.webservice.catissuecore.print.PrintXMLParser;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

public class WashUPrintServiceClient implements PrintServiceClient{


	/*To implement the PrintService interface 
	 * this class define the actual functionality   
	 * of web method
	 */
	PrintXMLParser pxp = new PrintXMLParser();
	
	private static Log log = LogFactory.getLog(WashUPrintServiceClient.class);

	public String print(String xmlFormat)
	{
		try 
		{
			log.info("In WashUPrintServiceClient");
			LinkedHashMap objMap = pxp.getPrintMap(xmlFormat);
			printDomainObject(objMap);
			return "Successed";

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			log.info("Failed *************");
			log.info(e.getMessage());
			return e.getMessage();
		}
	}

	
	void printDomainObject(LinkedHashMap obj) throws Exception {
		log.info("In WashUPrintServiceClient.printDomainObject() --------------------");
		new WashuLabelFileWriter().createFile(obj);
		log.info("------------------------------");
	}
}