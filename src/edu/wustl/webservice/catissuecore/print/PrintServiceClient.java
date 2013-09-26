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
 * <p>Title: PrintServiceClient Class>
 * <p>Description:	PrintServiceClient is an Interface which is implemented by all 
 * clients of the PrintWebService. 
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Amit Doshi
 * @version 1.00
 * Created on Nov 1, 2007
 */
package edu.wustl.webservice.catissuecore.print;

public interface PrintServiceClient {

	public String print(String xmlFormat);
}
