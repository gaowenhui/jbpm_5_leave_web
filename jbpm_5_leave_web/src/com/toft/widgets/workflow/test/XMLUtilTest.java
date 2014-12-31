/**
 * 
 */
package com.toft.widgets.workflow.test;

import junit.framework.TestCase;

import org.dom4j.Document;

import com.toft.widgets.workflow.utils.XMLUtil;
import com.toft.widgets.workflow.vo.Workflow;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 17, 2009
 * 
 */
public class XMLUtilTest extends TestCase {

	protected void setUp() throws Exception {

	}

	public void testXMLUtil() throws Exception {
		Workflow workflow = new Workflow();
		workflow = XMLUtil.readXml("D:\\simple", null);
		Document define = XMLUtil.createDefineXML(workflow);
		XMLUtil.writeXML(define, "d:\\test\\processdefinition.xml");
		Document diagram = XMLUtil.createDiagramXML(workflow);
		XMLUtil.writeXML(diagram, "d:\\test\\gpd.xml");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
