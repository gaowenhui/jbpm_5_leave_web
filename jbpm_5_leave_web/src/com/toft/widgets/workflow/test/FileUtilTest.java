package com.toft.widgets.workflow.test;

import junit.framework.TestCase;

import com.toft.widgets.workflow.utils.CommonValue;
import com.toft.widgets.workflow.utils.FileUtil;

public class FileUtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testFileUtil(){
		String filePath = CommonValue.getInstance().getFlowFilesPath()+ "\\"+"test";
		FileUtil.delete(filePath);
		
		
	}
	
//	public void testxxx(){
////		FileUploaded f = new FileUploaded();
//			System.out.println("url is" + f.getURL());
//	}

}
