package com.toft.widgets.workflow.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageTest {

	public static void main(String[] args){
		List ll = new ArrayList();
		 try {
			ll = null;//mess.getSupportMessageSenders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Iterator it = ll.iterator();
		 while(it.hasNext()){
//			 Object ob = it.next();
			
		 }
	}
}
