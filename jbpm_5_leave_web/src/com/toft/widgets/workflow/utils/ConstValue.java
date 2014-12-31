/**
 * 
 */
package com.toft.widgets.workflow.utils;

/**
 * 声明静态关键字及赋值
 * 
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 16, 2009
 * 
 */
public class ConstValue {

	public static final String STARTNODE = "start-state";
	public static final String ENDNODE = "end-state";
	public static final String STATENODE = "state";
	public static final String FORKNODE = "fork";
	public static final String JOINNODE = "join";
	public static final String DECISIONNODE = "decision";
	public static final String NORMALNODE = "node";
	public static final String TASKNODE = "task-node";
	public static final String TRANSITION = "transition";

	public static final String STARTNODETYPE = "start_type";
	public static final String ENDNODETYPE = "end-type";
	public static final String FORKNODETYPE = "fork-type";
	public static final String JOINNODETYPE = "join-type";
	public static final String DECISIONNODETYPE = "decision-type";
	public static final String TASKNODETYPE = "task-type";
	public static final String TASKDYNAMICNODETYPE = "dynamic-type";
	public static final String COMMITNODETYPE = "commit-type";
	public static final String COUNTERSIGNTYPE = "counter-type";
	public static final String FREENODETYPE = "state-type";
	public static final String TRANSITIONTYPE = "transition";
	public static final String SUBPROCESSTYPE = "subprocess-type";

	public static final int NODEWIDTHONE = 140;
	public static final int NODEHEIGHTONE = 40;
	public static final int NODEWIDTHTWO = 200;
	public static final int NODEHEIGHTTWO = 25;

	public static final String DEFINENAME = "processdefinition.xml";
	public static final String DIAGRAMNAME = "gpd.xml";
	
	//表名
	public static final String JBPM4_DEPLOY="jbpm4_deploy"; 
	public static final String JBPM4_ARTICULATED_PROCESS="workflow_articulated_process";
	public static final String JBPM4_GLOBAL_PARAM="jbpm4_global_param";
	public static final String JBPM4_BUSINESS_URL="jbpm4_business_url";
	public static final String SYS_MESSAGE_TEMPLATE="sys_message_template";
	public static final String WORKFLOW_BEHAVIOR_MANAGEMENT="workflow_behavior_management";

}
