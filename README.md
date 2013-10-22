Welcome to the caTissue Print Service Project!
==============================================

The caTissue Print Service provides the framework for printing labels for specimens, specimen collection groups and containers
from the [caTissue] (https://github.com/NCIP/catissue-core) application.

The caTissue Print Service is distributed under the BSD 3-Clause License.
Please see the NOTICE and LICENSE files for details.

You will find more details about the caTissue Print Service project in the following links:
 * [caTissue Documentation] (https://wiki.nci.nih.gov/x/uJT9AQ)
 * [Code Repository] (https://github.com/NCIP/catissue-print-service)
 
Please join us in further developing and improving the caTissue Print Service project.

Introduction
------------

caTissue Suite 1.1 and greater has the framework to print labels for specimen, specimen collection group and containers. Each center can customize the framework and implement print web service to meet their requirements. This might be specific to the printer model they use and format of the specimen labels. 

We have implemented the caTissue Print Web service for Washington University (WashU) for Brady printer. This document describes how to set up this web service with caTissue to print specimen labels based on format specified by WashU.

Integration of web service with caTissue
----------------------------------------

High level requirements of WashU are as below:

1. Information to be printed for specimen labels should be configurable per class and type of specimen, type of label. The type of label could be Cap+Side(used for vials) or Slide. 
2. WashU uses multiple printers at various labs. In order to decide the printer to forward the request to, the workstation IP should be configurable. 

Based on these requirements, the parameters that decide the label format to be printed are configured using file **print_rules.xls** having the below parameters:

<table border="1" cellspacing="0" cellpadding="3">
	<tr>
		<th>Parameter</th> <th>Definition</th>
	</tr>
	<tr>
		<td>Specimen Class</td> <td>Class of the specimen <br> Permissible Values:  Molecular, Fluid, Cell, Tissue</td>
	</tr>
	<tr>
		<td>Specimen Type</td> <td>Type of the specimen <br> Permissible Values: ‘Any’ or  any specimen type of the class specified in same row</td>
	</tr>
	<tr>
		<td>Label Type</td> <td>This could be Cap+ Slide or side. This parameter and the specimen type define the information that needs to go in the label. This will help user to configure based on label physical dimensions.</td>
	</tr>
	<tr>
		<td>Data on the label</td> <td>Information that needs to be included in the label based on the specimen class/type and label type</td>
	</tr>
	<tr>
		<td>Printer</td> <td>Name of the printer where the request has to be forwarded to.</td>
	</tr>
	<tr>
		<td>Workstation IP</td> <td>IP address of the machine from where the print request will come and this decides to which printer the request has to be forwarded to.</td>
	</tr>
</table>

###Below are some example entries for the **print_rules.xls** file:

<table border="1" cellspacing="0" cellpadding="3">
	<tr>
		<th>Specimen Class</th> <th>Specimen type</th> <th>Label type</th> <th>Data on the label</th> <th>Printer</th> <th>Workstation IP</th>
	</tr>
	<tr>
		<td>Molecular</td> <td>Any</td> <td>Cap + Slide</td> <td>Specimen Label, Specimen type, Concentration</td> <td>Molecular</td> <td>10.39.153.173</td>
	</tr>
	<tr>
		<td>Fluid</td> <td>Any</td> <td>Cap + Slide</td> <td>Specimen label, Specimen type</td> <td>Histology </td> <td>10.39.153.173</td>
	</tr>
	<tr>
		<td>Cell</td> <td>Cryopreserved Cells</td> <td>Cap + Slide</td> <td>Specimen label</td> <td>Histology </td> <td>10.39.153.173</td>
	</tr>
</table>


##Web service deployment:
Print web service has to be deployed on a server which has access to printer Make the following changes in that JBoss. The folder where the JBoss is deployed will be referred to as JBOSS-HOME

1.	Create three folders inside JBOSS-HOME/bin
	* JBOSS-HOME/bin/print/printer
	* JBOSS-HOME/bin/print/printlabel
	* JBOSS-HOME/bin/print/printrules

2.	Place print_rules.xls inside JBOSS-HOME/bin/print/printrules. Note:  After any change in print_rules.xls post deployment, server should be restarted.
3.	Unzip the PrintWebService.zip and go to unzipped folder through command line and execute “ant build_war”.
4.	Copy war from unzipped folder to JBOSS-HOME/server/default/deploy/
5.	Start print web service JBoss server.
6.	For accessing this web service from caTissue application, make the following change in caTissue application JBoss make sure to cofigure https or http for your environment:

	File: JBOSS-HOME/server/default/catissuecore-properties/PrintServiceImplementor.properties
    printWebServiceEndPoint= http(s)://<ipaddress:portNo>/caTissuePrintWebService/Print?wsdl> 
	This is the URL of Print Web Service, where IP address and port is of the server where print web service is deployed.

7.	Restart caTissue Suite application JBoss server.
 
###Output:
The labels to be printed are written to .cmd files will be created for each specimen in JBOSS-HOME/bin/print/printer. The brady printer has to be configured to read this folder and pick up the .cmd files to print the labels.

###Things to remember:

1.	The web service can be configured to run under https by configuring the web.xml CONFIDENTIAL descriptor and deploying the configuration to require secure transport. The web.xml configuration is copied from a local config directory.

2.	When labels are printed from specimen collection group (SCG) page or from collection protocol based specimen entry page, labels for all specimens (collected or pending) under that SCG will be printed. 
	
3. If using SSL for web services set the truststore path for the JVM on startup of jboss with -D args. # Truststore settings for JVM on the client web app

	*JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStore=/path/to/truststore -Djavax.net.ssl.trustStorePassword=password" 
