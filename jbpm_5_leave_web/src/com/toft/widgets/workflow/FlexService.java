
package com.toft.widgets.workflow;

import java.util.List;
import java.util.Map;
import com.toft.widgets.workflow.assistance.MessageAss;
import com.toft.widgets.workflow.assistance.NodeAss;
import com.toft.widgets.workflow.assistance.ParamtersAss;
import com.toft.widgets.workflow.assistance.TaskAss;
import com.toft.widgets.workflow.assistance.WorkflowAss;
import com.toft.widgets.workflow.assistance.WorkflowConfigAss;
import com.toft.widgets.workflow.utils.CheckExperssionUtil;
import com.toft.widgets.workflow.utils.ClassUtil;
import com.toft.widgets.workflow.vo.Workflow;
import com.toft.widgets.workflow.vo.javainfo.EveryJavaNodeClazz;

/**
 * 流程设计器服务类
 *  
 * @author cswang mail to : <cswang@isoftstone.com>
 * @version 1.0.0  2012-8-21 下午1:24:07 
 * 
 * @author chenshuqing
 * @version 4.4.3  2012-8-21 下午1:24:07  checkstyle并修订 
 *
 */
public class FlexService {

    // add by lsy 用于动态规则查询操作的对象
    private WorkflowAss workflowAss;
    private MessageAss messageAss;
    private TaskAss taskAss;
    private NodeAss nodeAss;
    private ParamtersAss paramtersAss;
    private WorkflowConfigAss configAss;

    /**
     * 构造函数
     */
    public FlexService() {
        try {
            workflowAss = WorkflowAss.getInstance();
            messageAss = MessageAss.getInstance();
            taskAss = TaskAss.getInstance();
            nodeAss = NodeAss.getInstance();
            paramtersAss = ParamtersAss.getInstance();
            configAss = WorkflowConfigAss.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取初始化数组
     *
     * @param arrTemp 参数
     * @return  集合
     */
    public List getInitArray(Object[] arrTemp) {
    
        // 调后台，取得其它的值
        return messageAss.getInitArray(arrTemp);
    }

    /**
     * 查询返回所有流程
     * 
     * @param arrTemp 设计器上下文
     * 
     * @return Object[] 流程定义数组
     */
    public Object[] getWorkflowArray(Object[] arrTemp) {
        return workflowAss.getWorkflowArray(arrTemp);
    }

    /**
     * 打开流程
     *
     * @param arrTemp   设计器上下文
     * @return          流程实体
     */
    public Workflow openWorkflow(Object[] arrTemp) {
        return workflowAss.openWorkflow(arrTemp);

    }

    /**
     * 保存流程 
     *
     * @param arrTemp  流程定义参数
     * @return         流程实体
     */
    public Workflow saveWorkflow(Object[] arrTemp) {
        Workflow wf = null;
        try {
            wf = workflowAss.saveWorkflow(arrTemp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wf;
    }

    /**
     * 复制流程
     *
     * @param arrTemp  流程参数
     * @return         流程实体
     */
    public Workflow copyWorkflow(Object[] arrTemp) {
        final List param = (List) arrTemp[0];
        final String oldflowid = (String) param.get(0);
        final String name = (String) param.get(1);
        final String activityName = (String) param.get(2);
        final Object context = arrTemp[1];
        Workflow tempwf = new Workflow();
        // 加入判断，如果上一个流程为空则不再取值
        if (oldflowid != null) {
            final Object[] obj = new Object[2];
            obj[0] = oldflowid;
            obj[1] = context;
            tempwf = workflowAss.openWorkflow(obj);
        }
        tempwf.setName(name);
        tempwf.setActivityName(activityName);
        final Object[] tosaveObj = new Object[2];
        tosaveObj[0] = tempwf;
        tosaveObj[1] = context;
        tempwf = null;
        try {
            tempwf = workflowAss.insertWorkflow(tosaveObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempwf;
    }

    /**
     * 保存
     * 
     * @param params 业务参数
     * @return       执行是否成功
     */
    public String saveBehavor(Object[] params) {
        return configAss.saveBehaviorManagement(params);
    }
    
    /**
     * 获取列表
     * @param params 参数
     * @return       列表集合
     */
    public List loadBehavor(Object[] params) {
        return configAss.getBehaviorManagementList(params);
    }
    
    /**
     * 删除
     * @param params   参数
     * @return         执行是否成功
     */
    public String deleteBehavior(Object[] params){
        return configAss.deleteBehaviorManagement(params);
    }

    /**
     * 删除流程
     * 
     * @param arrTemp   参数
     * @return          执行是否成功
     */
    public String deleteWorkflow(Object[] arrTemp) {
        String result = "";
        result = workflowAss.deleteWorkflow(arrTemp);
        return result;
    }

    /**
     * 导出流程
     * 
     * @param arrTemp  参数 
     * @return         执行是否成功
     */
    public String exportWorkflow(Object[] arrTemp) {
        String result = null;
        result = workflowAss.exportWorkflow(arrTemp);
        return result;
    }

    /**
     * 导出流程后执行的方法，以删除临时文件
     * 
     * @param arrTemp 参数
     */
    public void deleteTempFile(Object[] arrTemp) {
        workflowAss.deleteTempFile(arrTemp);
    }

    /**
     * 导出流程后执行的方法，以删除临时文件，多文件
     * 
     * @param arrTemp 参数
     */
    public void deleteTempFiles(Object[] arrTemp) {
        workflowAss.deleteTempFiles(arrTemp);
    }

    /**
     * 查询出process name的值，以填充子流程节点的前台下拉列表
     * 
     * @author lsy
     * 
     * @param arrTemp 参数
     * @return List 名称集合
     */
    public List selectProcessName(Object[] arrTemp) {
        return workflowAss.selectProcessName(arrTemp);

    }

    /**
     * 表达式验证
     * 
     * @param arrTemp 参数
     * @return        是否符合验证标准
     */
    public boolean checkExpr(Object[] arrTemp) {
        return CheckExperssionUtil.testLogic(arrTemp[0].toString());
    }

    /**
     * 查询出taskAssignAllSimpleRule的值，以便任务节点前台选择任务参与者规则列表的查询操作
     * 
     * @param arrTemp 参数
     * 
     * @author lsy
     * @return List
     * @throws DBException
     */
    public List getTaskAssAllSimpleRuleList(Object[] arrTemp) {
        return taskAss.getTaskAssAllSimpleRuleList(arrTemp);
    }

    /**
     * 查询出taskAssignAllDYNRule的值，以便动态节点的前台选择动态任务参与者规则列表的查询操作
     * 
     * @param arrTemp 参数
     * 
     * @author lsy
     * @return List
     * @throws DBException
     */
    public List getTaskAssAllDynList(Object[] arrTemp) {
        return taskAss.getTaskAssAllDynList(arrTemp);
    }

    /**
     * 查询出taskDynamicRule的值，以便前台的选择动态规则列表的查询操作
     * 
     * @param arrTemp 参数
     * @return List
     * @throws DBException
     * 
     */
    public List getTaskDycRuleList(Object[] arrTemp) {
        return nodeAss.getTaskDycRuleList(arrTemp);
    }

    /**
     * 查询消息处理方式MessageService
     * 
     * @param arrTemp 参数
     * 
     * @return List
     * @throws Exception
     */
    public List getMessagePatternList(Object[] arrTemp) {
        return messageAss.getMessagePatternList(arrTemp);
    }

    /**
     * 查询会签节点的参与者规则
     * 
     * @param arrTemp 参数
     * 
     * @return List
     * @throws Exception
     */
    public List getCounterSignAllPostRuleList(Object[] arrTemp) {
        return taskAss.getCounterSignAllPostRuleList(arrTemp);
    }

    /**
     * 查询所有消息模板的信息
     * 
     * @param arrTemp 参数
     * 
     * @return List
     * @throws DBException
     */
    public List getMessageTemplateList(Object[] arrTemp) {
        return messageAss.getMessageTemplateList(arrTemp);
    }

    /**
     * 查询所有的参数列表,另行调试
     * 
     * @param arrTemp 参数
     * @return List   参数列表
     */
    public List getParamsList(Object[] arrTemp) {
        return paramtersAss.getParamsList(arrTemp);
    }

    /**
     * 配置
     *
     * @param ob  参数
     * @return    配置map
     */
    public Map getConfig(Object ob) {
        return paramtersAss.getConfig();
    }

    /**
     * 判断流程是否存在
     *
     * @param arrTemp 参数
     * @return        判断结果
     */
    public boolean isFlowExist(Object[] arrTemp) {
        return workflowAss.isWorkflowExistByName(arrTemp);
    }

    /**
     * 鹿鹏：打开时，如果节点中包含子流程，就会调用此方法获取子流程的参数变量
     *
     * @param arrTemp  参数
     * @return         子流程参数数组
     */
    public Object[] getSubWorkflowBySubProcessCode(Object[] arrTemp) {
        return workflowAss.getSubProParams(arrTemp);
    }

    /**
     * 鹿鹏：获取类信息，组装EveryJavaNodeClazz
     *
     * @param arrTemp   参数
     * @return EveryJavaNodeClazz  java节点实现类
     * @throws Exception           一般异常
     */
    public EveryJavaNodeClazz getEveryJavaNodeClazz(Object[] arrTemp) throws Exception {
        final String className = (String) arrTemp[0];
        final EveryJavaNodeClazz result = new EveryJavaNodeClazz();
        result.setClasspath(className);
        try {
            final List methods = ClassUtil.getMethods(className);
            final List classField = ClassUtil.getClassField(className);
            result.setMethods(methods);
            result.setFields(classField);
        } catch (Exception e) {
            throw new Exception("获取\"" + className + "\"信息失败");
        }
        return result;
    }

    /**
     * 事件实现类校验
     *
     * @param arrTemp  参数
     * @return         校验结果
     */
    public boolean checkClass(Object[] arrTemp) {
        boolean result = true;
        try {
            this.getClass().getClassLoader().loadClass(String.valueOf(arrTemp[0]));
        } catch (ClassNotFoundException e) {
            // 出现异常表示未加载到
            result = false;
        }
        return result;
    }
}
