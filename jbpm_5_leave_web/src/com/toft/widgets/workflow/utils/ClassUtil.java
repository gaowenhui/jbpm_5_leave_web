package com.toft.widgets.workflow.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassUtil {
		
	/***
	 * 获取classpath的方法List<Map<String,Object>>集合
	 * method_name 标识方法名称
	 * parameter_types 标识方法参数类型字符数组，按先后顺序0~n
	 * @param classpath 类完整路径
	 * @return
	 * @throws Exception
	 */
	public static List getMethods(String classpath) throws Exception{
		List result = null;
		try{
			Class clazz = Class.forName(classpath);
			Method[] methods = clazz.getMethods();
			Map temp = null;
			for(int i=0;i<methods.length;i++){
				if(result==null){
					result = new ArrayList();
				}
				temp = new HashMap();
				
				temp.put("method_name", methods[i].getName());
				Class[] types = methods[i].getParameterTypes();
				List parameter_types = new ArrayList();
				Map parameter = null;
				for(int j=0;j<types.length;j++){
					parameter = new HashMap();
					parameter.put("arg_index", String.valueOf(j));
					String type=types[j].getName();
					if(type.indexOf("String")!=-1){
						type="string";
					}
					parameter.put("arg_type", type);
					parameter_types.add(parameter);
				}
				temp.put("parameter_types", parameter_types);
				result.add(temp);
			}
		}catch(Exception e){
			System.out.println("com.toft.widgets.workflow.utils.ClassUtil 获取方法集合异常");
			e.printStackTrace();			
			throw e;			
		}
		return result;
	}
	
	/***
	 * 获取classpath的属性集合List<Map<String,String>>
	 * field_name 标识属性名称
	 * filed_type 标识属性类型
	 * @param classpath 类完整路径
	 * @return
	 * @throws Exception
	 */
	public static List getClassField(String classpath) throws Exception{
		List result = null;
		try{
			Class clazz = Class.forName(classpath);
			Field[] fields = clazz.getFields();
			Map map = null;
			result = new ArrayList();
			for(int i=0;i<fields.length;i++){
				map = new HashMap();
				map.put("field_name", fields[i].getName());
				map.put("field_type", fields[i].getType().getName());
				result.add(map);
			}
		}catch(Exception e){
			System.out.println("com.toft.widgets.workflow.utils.ClassUtil  获取类属性异常");
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	public static String validateClassPath(String classpath) throws Exception{
		    String result="exist";
			try {
				Class clazz = Class.forName(classpath);
			} catch (ClassNotFoundException e) {
				result="noExist";
				e.printStackTrace();
			}
			return result;
	}
	
	
	public static void main(String[] args) {
		
		String classpath = "com.toft.widgets.workflow.vo.Node";
		try {
			List methods = ClassUtil.getMethods(classpath);
			List classField = ClassUtil.getClassField(classpath);
			
			for(int i=0;i<methods.size();i++){
				Map map = (Map)methods.get(i);
				System.out.println(map.get("method_name")+":");
				List temp = (List)map.get("parameter_types");
				for(int j=0;j<temp.size();j++){
					Map m = (Map)temp.get(j);
					System.out.println("     "+m.get("arg_index")+"<-->"+m.get("arg_type"));
				}
			}
			System.out.println("--------------------------");
			for(int i=0;i<classField.size();i++){
				Map map = (Map)classField.get(i);
				System.out.println(map.get("field_name")+"<-->"+map.get("field_type"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
