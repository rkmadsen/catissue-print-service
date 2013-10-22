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
 * <p>Title: PrintWebServiceFactory Class>
 * <p>Description:	PrintWebServiceFactory is a class that returns the object
 * of client for which print method is invoked. 
 * as per the domain objects.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Amit Doshi
 * @version 1.00
 * Created on Nov 1, 2007
 */

package edu.wustl.catissuecore.webservice.client;

import edu.wustl.webservice.catissuecore.print.PrintServiceClient;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

public class PrintWebServiceFactory {
	
	private static Log log = LogFactory.getLog(PrintWebServiceFactory.class);

	public static PrintServiceClient getPrintServiceClientObject() 
	{

		PrintServiceClient printObj=null;			
		String classname="edu.wustl.catissuecore.webservice.client.WashUPrintServiceClient";
		log.info(classname);
		try 
			{
				printObj = (PrintServiceClient)Class.forName(classname).newInstance();
			} catch (InstantiationException e) {

				e.printStackTrace();
				
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}


		return printObj;

	}
}
