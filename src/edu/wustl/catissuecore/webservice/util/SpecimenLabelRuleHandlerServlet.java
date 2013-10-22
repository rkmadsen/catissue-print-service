/*L
 *  Copyright Washington University in St. Louis
 *  Copyright SemanticBits
 *  Copyright Persistent Systems
 *  Copyright Krishagni
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/catissue-print-service/LICENSE.txt for details.
 */

package edu.wustl.catissuecore.webservice.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * Referenced classes of package edu.wustl.catissuecore.webservice.util: SpecimenLabelRuleHandler
 *            
 */

public class  SpecimenLabelRuleHandlerServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2536272325135641171L;

	public SpecimenLabelRuleHandlerServlet() {

	}
	
	/**
	 * method to initialize SpecimenLabelRuleHandler
	 */
	public void init(ServletConfig config) throws ServletException
	{
		SpecimenLabelRuleHandler.init();
	} 
}
