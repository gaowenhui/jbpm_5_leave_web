package com.toft.widgets.workflow.test;

import java.io.FileNotFoundException;

import com.toft.widgets.workflow.utils.FileReadUtil;

public class FileUploadServletTest {
	public boolean toTest() {
		FileReadUtil fru = new FileReadUtil();
		String str = "";
		try {
			str = fru
					.ReadFile("C:\\Documents and Settings\\admin\\桌面\\processdefinition.xml");
			// Workflow workflow = XMLUtil.readXmlString(str);
			// WorkflowAss wa = new WorkflowAss();
			boolean result = true;// wa.insertWorkflow(workflow);
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		FileUploadServletTest ft = new FileUploadServletTest();
		boolean result = ft.toTest();
		if (result) {
			System.out.print("insert success.");
		} else if (result) {
			System.out.print("insert error");
		}
	}
}
