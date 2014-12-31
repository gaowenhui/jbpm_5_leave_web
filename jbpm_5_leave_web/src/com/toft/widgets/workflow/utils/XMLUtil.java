/**
 * 
 */
package com.toft.widgets.workflow.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.toft.widgets.workflow.factory.ServiceFactory;
import com.toft.widgets.workflow.factory.impl.ServiceFactoryImpl;
import com.toft.widgets.workflow.vo.Workflow;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * 
 */
public class XMLUtil {

	public static Workflow readXml(String filePath, String filePathName) {

		if (!FileUtil.isFileExist(filePath)) {
			System.out.println("file not exist!");
			return null;
		} else {
			SAXReader reader = new SAXReader();
			Document defineDocument = null;

			try {
				defineDocument = reader.read(new File(filePathName));

			} catch (DocumentException e) {
				System.out.println("read files error!");
				e.printStackTrace();
			}
			if (defineDocument != null) {
				Element defineRoot = defineDocument.getRootElement();

				Workflow workflow = new Workflow();

				try {
					workflow.toWorkflow(defineRoot);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return workflow;
			} else if (defineDocument != null) {
				Element defineRoot = defineDocument.getRootElement();
				Workflow workflow = new Workflow();
				try {
					workflow.toWorkflow(defineRoot);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return workflow;
			}
		}
		return null;
	}

	/**
	 * 传入XML读取的String生成新的workflow对象
	 * 
	 * @param xml
	 * @return workflow：WorkFlow
	 * @throws Exception
	 */
	public static Workflow readXmlString(String xml) throws Exception {
		Workflow workflow = null;
		if (xml != null && !"".equals(xml)) {
			Document tempDoc = StringUtil.stringToDoc(xml);// 类型转换
			try {
				workflow = new Workflow();
				workflow.toWorkflow(tempDoc.getRootElement());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (workflow.getId() == null || "".equals(workflow.getId()))
				workflow.setId(CreateRandomId.createId_Thirty());
		}
		return workflow;

	}

	public static Document createDefineXML(Workflow workflow) throws Exception {
		Document defineDocument = DocumentHelper.createDocument();
		if (workflow != null) {
			// 传入最外层节点的值 process
			Element process_definition = defineDocument.addElement("process",
					"http://jbpm.org/4.0/jpdl");
			process_definition.addAttribute("name", workflow.getName());
			process_definition.addAttribute("id", workflow.getId());
			workflow.toFlowXML(process_definition);// 把dom4j的element对象传入下一个流程
		}
		return defineDocument;
	}

	public static Document createDiagramXML(Workflow workflow) throws Exception {
		Document diagramDocument = DocumentHelper.createDocument();
		Element process_diagram = diagramDocument.addElement("process-diagram");
		process_diagram.addAttribute("name", workflow.getName());
		workflow.toModelXML(process_diagram);
		return diagramDocument;
	}

	public static boolean writeXML(Document document, String filePath) {
		FileUtil.createFilePath(filePath);
		FileOutputStream fos = null;
		XMLWriter flowWriter = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			fos = new FileOutputStream(filePath);
			flowWriter = new XMLWriter(fos, format);
			flowWriter.write(document);
			fos.close();
			flowWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (fos != null)
					fos.close();
				if (flowWriter != null)
					flowWriter.close();
				FileUtil.rollBack(filePath);
				return false;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public int getJoinActivityMultiplicity(String processInstanceId,
			String activityName) {

		// InputStream is =
		// this.getServiceFactory().getWorkflowQueryService().getProcessDefinitionByProcessInstanceIdDup(processInstanceId);
		String processDefinitionContent = this.getServiceFactory()
				.getWorkflowQueryService()
				.getProcessDefinitionByProcessInstanceId(processInstanceId);
		InputStream is = new StringBufferInputStream(processDefinitionContent);
		String multiplicity = "";

		try {
			if (null != is) {
				SAXReader reader = new SAXReader();
				// Document document = reader.read(is);
				Document document = DocumentHelper
						.parseText(processDefinitionContent);
				Element root = document.getRootElement();
				for (Iterator iter = root.elementIterator("join"); iter
						.hasNext();) {
					Element element = (Element) iter.next();
					if (element.attribute("name").getValue().indexOf("join") != -1) {
						multiplicity = element.attribute("multiplicity")
								.getValue();
					}
				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		if (!"".equals(multiplicity)) {
			return Integer.parseInt(multiplicity);
		}

		return -1;

	}

	/**
	 * 获取指定流程中join节点的multiplicity属性值
	 * 
	 * @param processDefinitionContent
	 *            流程定义内容
	 * @param activityName
	 *            节点的属性(当流程定义中存在多个join节点时，可以用于准确匹配join节点)
	 * @return join节点的multiplicity属性值
	 */
	public int getJoinActivityMultiplicityDup(String processDefinitionContent,
			String activityName) {

		String multiplicity = "";

		try {
			if (null != processDefinitionContent
					&& !"".equals(processDefinitionContent)) {
				Document document = DocumentHelper
						.parseText(processDefinitionContent);
				Element root = document.getRootElement();
				for (Iterator iter = root.elementIterator("join"); iter
						.hasNext();) {
					Element element = (Element) iter.next();
					/*
					 * if(element.attribute("ids").getValue().indexOf("join") !=
					 * -1) { //当流程定义中区分各节点的属性改为ids后，使用该判断
					 *  }
					 */
					if (element.attribute("name").getValue().indexOf("join") != -1) {
						multiplicity = (element.attribute("multiplicity") == null ? ""
								: element.attribute("multiplicity").getValue());
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		if (!"".equals(multiplicity)) {
			return Integer.parseInt(multiplicity);
		}

		return -1;

	}

	private ServiceFactory getServiceFactory() {
		return ServiceFactoryImpl.getServiceFactory();
	}
}
