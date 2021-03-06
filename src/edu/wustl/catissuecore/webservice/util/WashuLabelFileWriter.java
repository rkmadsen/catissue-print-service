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
 * 
 */
package edu.wustl.catissuecore.webservice.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

/**
 * File to write the Label file. This will create file as per Brady Printer
 * Specifications.
 * 
 * @author prafull_kadam
 */
public class WashuLabelFileWriter 
{
	/**
	 * To create file with the given information in the map as per Brady printer
	 * specification.
	 * 
	 * @param map
	 * @throws IOException
	 */

	private Log log = LogFactory.getLog(WashuLabelFileWriter.class);
	
	public void createFile(LinkedHashMap map) throws IOException {
		String printDirectory = System.getProperty("jboss.home.dir") + "/print/" + PrinterPropertyHandler.getValue("printer.directory");
		checkDirectory(printDirectory);
		BufferedWriter fileWriter = null;
		int counter = 1;
		Set<String> keySet = map.keySet();
		List<String> sortedList = new ArrayList<String>(keySet);
		Collections.sort(sortedList, new KeySetComparator());
		for (String key : sortedList) {
			HashMap<String, String> object = (HashMap<String, String>) map.get(key);
			String fileName = printDirectory + "/" + getFileName()+counter+ ".cmd";
			FileWriter file = new FileWriter(fileName);
			fileWriter = new BufferedWriter(file);
			log.info("Writing label file in directory:" + fileName);
			String cmdFileData = SpecimenLabelRuleHandler.getFileContentForSpecimen(object);
			if(cmdFileData != null && !cmdFileData.equals(""))
			{
				fileWriter.write(cmdFileData);
			}
			counter++;
			fileWriter.close();
		}
	}

	/**
	 * Method to create directory with specified path if doesn't exists
	 * @param printDirectory
	 */
	private void checkDirectory(String printDirectory) 
	{
		File dir = new File(printDirectory);
		if(dir==null || !dir.exists())
		{
			log.info("Directory does not exists! Creating directory "+printDirectory);
			dir.mkdir();
		}
	}

	/**
	 * To create & write data present in the map to specified file.
	 * 
	 * @param fileName
	 *            the Name of the file with full path.
	 * @throws IOException
	 */
	private void writeDataInFile(String fileName, LinkedHashMap map)
	throws IOException {
		BufferedWriter fileWriter = null;
		FileWriter file = new FileWriter(fileName);
		fileWriter = new BufferedWriter(file);
		String labelFile = PrinterPropertyHandler.getValue("printer.labelFile");

		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			HashMap<String, String> object = (HashMap<String, String>) map
			.get(key);

			fileWriter.write("\nLABELNAME=" + labelFile + "\n");
			Set<String> labelVariableKeys = object.keySet();
			for (String labelKey : labelVariableKeys) {
				String value = object.get(labelKey);
				fileWriter.write(labelKey + "=\"" + value + "\"\n");
			}
		}
		fileWriter.close();
	}

	/**
	 * To get the unique filename, that will not clash to other file in case of
	 * simultanious access.
	 * 
	 * @return The name of the file to be created.
	 */
	private String getFileName() {
		String fileName = "p" + System.currentTimeMillis();

		// To generate unique file name for file.
		while (true) {
			// TODO code to create unique filename, so that simlutaneous access
			// to this method will not return same filename.
			break;
		}
		return fileName;
	}
}
