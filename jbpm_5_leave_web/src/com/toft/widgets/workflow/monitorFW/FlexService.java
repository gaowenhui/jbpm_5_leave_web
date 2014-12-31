
package com.toft.widgets.workflow.monitorFW;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;

import com.toft.widgets.workflow.factory.impl.ServiceFactoryImpl;
import com.toft.widgets.workflow.model.ProcessActivity;
import com.toft.widgets.workflow.monitorFW.utils.StringUtil;
import com.toft.widgets.workflow.monitorFW.vo.Node;
import com.toft.widgets.workflow.monitorFW.vo.Transition;
import com.toft.widgets.workflow.monitorFW.vo.Workflow;
import com.toft.widgets.workflow.utils.ConstValue;

/**
 * 流程跟踪service
 * 
 * 分别查询目标流程已经完成的节点实例和运行中的任务实例，节点实例完成时，设置为
 * 流程跟踪的完成状态，运行状态设置为运行状态。已经完成的节点实例，查询出度轨迹，
 * 设置transition，并设置状态为流程跟踪的完成状态。总体思想，先根据完成的节点实
 * 例，设置流程定义中对应的node状态；然后根据完成节点实例的轨迹，设置流程定义中
 * 对应的transition连线的状态。
 * 
 * 有两个特殊考虑的地方，
 * <p>流程的开始节点，在流程实例化后，就完成了，所以要默认设置为完成。
 * <p>连接开始节点和提交节点的transition在流程启动后，就处于完成状态，所以要默认设置。
 * 
 * @author cswang mail to : <cswang@isoftstone.com>
 * @version 1.0.0  2012-8-21 下午1:07:30 
 * 
 * @author chenshuqing
 * @version 4.4.3  2012-8-21 下午1:07:30  checkstyle并修订
 *
 */
public class FlexService {

	private final Log log = LogFactory.getLog(FlexService.class);
	
	/**
	 * 用来解析查看工作流程信息的方法 2010-2-8
	 * 将流程运行的数据解析，并返给flex客户端显示
	 *
	 * @param pdId   流程实例ID
	 * @return       返给flex客户端的值，list包含流程定义实体和流程任务实例集合
	 */
	public Object[] viewWorkFlow(String pdId) {

		List listWF = new ArrayList();
		try {
			
			String temp = ServiceFactoryImpl.getServiceFactory().getMonitorWorkflowService().getProcessDefinitionByProcessInstanceId(pdId);
			
			Document tempDoc = StringUtil.stringToDoc(temp);// 类型转换
			Workflow workflow = new Workflow();
			workflow.toWorkflow(tempDoc.getRootElement());
			workflow.setActive(!ServiceFactoryImpl.getServiceFactory().getMonitorWorkflowService().isEndedByProcessInstanceId(pdId));
			
			// 流程定义实体
			listWF.add(workflow);

			Object obj = ServiceFactoryImpl.getServiceFactory().getMonitorWorkflowService().getProcessInfoByProcessInstanceId(pdId);
//			List processActivitys = service.getProcessInfoByProcessInstanceId(pdId);
			List processActivitys = (List)obj;
			
			// 节点状态判断与赋值
			ProcessActivity pa = null;
			Node node = null;
			for (int i = 0; i < processActivitys.size(); i++) {
			    pa = (ProcessActivity) processActivitys.get(i);
			    
				for (int j = 0; j < workflow.getNodes().length; j++) {
					node = (Node) workflow.getNodes()[j];
					
					if(ConstValue.STARTNODETYPE.equals(node.getIdentifyType())){ // 开始节点特殊判断
						log.debug(">>>=== startNode : " + node.getName());
						node.setIsPass("processed");
					}
					
				    if (pa.getActivityType().equalsIgnoreCase(node.getName())) {
				    	log.debug(">>>=== node : " + node.getName());
					    node.setIsPass(pa.getActivityStatus());
					}
				}
			}
			
			// 节点出度transition判断
			Transition transition = null;
			String startNode = null;
			String endNode = null;
			String[] transitions = null;
			for (int i = 0; i < processActivitys.size(); i++) {
				pa = (ProcessActivity) processActivitys.get(i);
				log.debug(">>>=== transition node : " + pa.getActivityType());			
				for (int j = 0; j < workflow.getTransitions().length; j++) {
					transition = (Transition) workflow.getTransitions()[j];	
					startNode = transition.getStartNode().getIdentifyType(); // 开始节点类型
					endNode = transition.getEndNode().getIdentifyType(); // 结束节点
					
					if(ConstValue.STARTNODETYPE.equalsIgnoreCase(startNode) 
				    		&& ConstValue.COMMITNODETYPE.equalsIgnoreCase(endNode)){ // 开始-提交节点连线特殊判断
						log.debug(">>>=== startNodeToCommitNodeTransition : " + transition.getName());
				    	transition.setIsPass("processed");
				    	continue;
				    }
					
					// 以节点的出度transition为线索
					if(pa.getTransition()!=null){
						transitions = pa.getTransition().split(",");
						for(int num = 0; transitions!=null && num<transitions.length ;num++){
							if(transitions[num].equalsIgnoreCase(transition.getName())){
								log.debug(">>>=== transition : " + transition.getName());
								transition.setIsPass("processed");
							    break;
							}
						}	
					}
			    }
			}

			// 流程实例的节点集合
			listWF.add(processActivitys); 
		} catch (Exception e) {
		    log.error(e);
		}

		return listWF.toArray();
	}
}