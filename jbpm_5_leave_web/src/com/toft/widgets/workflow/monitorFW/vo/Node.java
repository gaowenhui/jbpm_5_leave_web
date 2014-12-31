/**
 * 
 */
package com.toft.widgets.workflow.monitorFW.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.toft.widgets.workflow.monitorFW.utils.ConstValue;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * 
 */
public class Node {

    private String id;
    private String name;
    private String activityName;
    private String identifyType;
    private String type;
    private String expr;
    private int x;// modify by lsy: float => int
    private int y;// modify by lsy: float => int
    private int width;
    private int height;
    private Object[] goIn;
    private Object[] goOut;
    private Object[] actions;
    private Object[] tasks;
    private Object[] variables;
    private String missHandle;

    /** **************************************** */
    private String processName;// 子流程名称

    private String assignee;// 用于提交节点的指派人

    private String maxSatiefy;// 用于聚合节点最大满足条件
    private String countersignType = "all";// 鹿鹏：追加会签节点的满足类型
    private String countersignValue = "";// 鹿鹏：追加会签节点的满足值
    private boolean countersignAlldo = true;// 鹿鹏：追加自动终止未完成工作项
    /* 增加任务节点“直接跳跃”和“自由跳跃” add by liangleilei at 2013-05-14 */
    private boolean directJump = false;
    private boolean freeJump = false;

    // 超时处理--状态节点时使用
    public String timeoutAction;// 超时处理类
    public String timeout;
    public List timeoutParam = new ArrayList();// 超时类的参数

    // 用于事件处理
    public Object[] events;
    // 用于自由节点的类名
    public String freeNodeClassName;

    public String isPass;

    // 鹿鹏：监控根本不看，故不做封装
    /*
     * private Foreach foreachs = new Foreach(); public Foreach getForeachs() { return foreachs; } public void
     * setForeachs(Foreach foreachs) { this.foreachs = foreachs; }
     */

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        if (this.type == ConstValue.JOINNODE || this.type == ConstValue.FORKNODE)
            width = ConstValue.NODEWIDTHTWO;
        else
            width = ConstValue.NODEWIDTHONE;
        return width;
    }

    /**
     * @param width
     *            the width to set
     */
    public void setWidth(int width) {

        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        if (this.type == ConstValue.JOINNODE || this.type == ConstValue.FORKNODE)
            height = ConstValue.NODEHEIGHTTWO;
        else
            height = ConstValue.NODEHEIGHTONE;
        return height;
    }

    /**
     * @param height
     *            the height to set
     */
    public void setHeight(int height) {

        this.height = height;
    }

    /**
     * @return the goIn
     */
    public Object[] getGoIn() {
        return goIn;
    }

    /**
     * @param goIn
     *            the goIn to set
     */
    public void setGoIn(Object[] goIn) {
        this.goIn = goIn;
    }

    /**
     * @return the goOut
     */
    public Object[] getGoOut() {
        return goOut;
    }

    /**
     * @param goOut
     *            the goOut to set
     */
    public void setGoOut(Object[] goOut) {
        this.goOut = goOut;
    }

    /**
     * @return the actions
     */
    public Object[] getActions() {
        return actions;
    }

    /**
     * @param actions
     *            the actions to set
     */
    public void setActions(Object[] actions) {
        this.actions = actions;
    }

    /**
     * @return the tasks
     */
    public Object[] getTasks() {
        return tasks;
    }

    /**
     * @param tasks
     *            the tasks to set
     */
    public void setTasks(Object[] tasks) {
        this.tasks = tasks;
    }

    public Node toNode(Element defineElement) throws Exception {
        this.id = defineElement.attributeValue("id");
        this.name = defineElement.attributeValue("name");
        this.activityName = defineElement.attributeValue("activityName");
        this.identifyType = defineElement.attributeValue("identifyType");
        this.expr = defineElement.attributeValue("expr");
        this.missHandle = defineElement.attributeValue("missHandle");
        this.type = defineElement.getName();

        /** ***************************事件处理*********************************** */
        List events = new ArrayList();
        for (Iterator it = defineElement.elements().iterator(); it.hasNext();) {
            Element defineEventElement = (Element) it.next();
            if (defineEventElement.getName().equals("on")) {
                EventFW eventfw = new EventFW();
                eventfw.toEvent(defineEventElement);
                events.add(eventfw);
            }
        }
        this.events = events.toArray();
        
        // join节点的最大满足条件
        this.maxSatiefy = defineElement.attributeValue("multiplicity");
        this.countersignType = defineElement.attributeValue("countersignType");
        this.countersignValue = defineElement.attributeValue("countersignValue");
        this.countersignAlldo = (new Boolean(defineElement.attributeValue("countersignAlldo")).booleanValue());
        /*增加任务节点“直接跳跃”和“自由跳跃” add by liangleilei at 2013-05-14 */
        this.directJump = (new Boolean(defineElement.attributeValue("directJump")).booleanValue());
        this.freeJump = (new Boolean(defineElement.attributeValue("freeJump")).booleanValue());

        // 鹿鹏：监控根本不看，故不做封装
        // this.foreachs.setVars(defineElement.attributeValue("var"));
        // this.foreachs.setCollections(defineElement.attributeValue("in"));

        // 将取出的x值进行转换
        // add by lsy :增加G结点 格式 [x,y,width,heigh],此时取到x,y用于绘制图形

        if (!"".equals(defineElement.attributeValue("g")) && null != defineElement.attributeValue("g")) {
            String temptoNode[] = defineElement.attributeValue("g").split(",");

            this.x = Integer.parseInt(temptoNode[0]);
            // 将取出的y值进行转换
            this.y = Integer.parseInt(temptoNode[1]);
        } else {
            this.x = 0;
            this.y = 0;
        }
        List defineTransitions = defineElement.elements("transition");
        List outs = new ArrayList();
        for (Iterator it = defineTransitions.iterator(); it.hasNext();) {
            Element defineTransition = (Element) it.next();
            Transition transition = new Transition();
            outs.add(transition.toTransition(defineTransition));
            transition.setStartNode(this);
            it.remove();
        }
        this.goOut = outs.toArray();

        List taskElements = defineElement.elements("assignment-handler");
        List tasks = new ArrayList();
        for (Iterator it = taskElements.iterator(); it.hasNext();) {
            Element taskElement = (Element) it.next();
            Task task = new Task();
            tasks.add(task.toTask(taskElement, defineElement));
        }
        this.tasks = tasks.toArray();

        Element variables = defineElement.element("flow-variables");
        if (variables != null) {
            List variablesElements = variables.elements("flow-variable");
            List variableList = new ArrayList();
            for (Iterator it = variablesElements.iterator(); it.hasNext();) {
                Element variableElement = (Element) it.next();
                Variable variable = new Variable();
                variableList.add(variable.toVariable(variableElement));
            }
            this.variables = variableList.toArray();
        }
        return this;
    }

    /**
     * @return the activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName
     *            the activityName to set
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public Object[] getVariables() {
        return variables;
    }

    public void setVariables(Object[] variables) {
        this.variables = variables;
    }

    public String getMissHandle() {
        return missHandle;
    }

    public void setMissHandle(String missHandle) {
        this.missHandle = missHandle;
    }

    /** *******************lsy-start************************ */
    public String getG() {
        return String.valueOf(this.x) + "," + String.valueOf(this.y) + "," + String.valueOf(this.getWidth()) + ","
                + String.valueOf(this.getHeight());
    }

    /** ******************lsy-end**************************** */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getMaxSatiefy() {
        return maxSatiefy;
    }

    public void setMaxSatiefy(String maxSatiefy) {
        this.maxSatiefy = maxSatiefy;
    }

    public String getTimeoutAction() {
        return timeoutAction;
    }

    public void setTimeoutAction(String timeoutAction) {
        this.timeoutAction = timeoutAction;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public List getTimeoutParam() {
        return timeoutParam;
    }

    public void setTimeoutParam(List timeoutParam) {
        this.timeoutParam = timeoutParam;
    }

    public Object[] getEvents() {
        return events;
    }

    public void setEvents(Object[] events) {
        this.events = events;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public String getFreeNodeClassName() {
        return freeNodeClassName;
    }

    public void setFreeNodeClassName(String freeNodeClassName) {
        this.freeNodeClassName = freeNodeClassName;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getCountersignType() {
        return countersignType;
    }

    public void setCountersignType(String countersignType) {
        this.countersignType = countersignType;
    }

    public String getCountersignValue() {
        return countersignValue;
    }

    public void setCountersignValue(String countersignValue) {
        this.countersignValue = countersignValue;
    }

    public boolean isCountersignAlldo() {
        return countersignAlldo;
    }

    public void setCountersignAlldo(boolean countersignAlldo) {
        this.countersignAlldo = countersignAlldo;
    }

    public boolean isDirectJump() {
        return directJump;
    }

    public void setDirectJump(boolean directJump) {
        this.directJump = directJump;
    }

    public boolean isFreeJump() {
        return freeJump;
    }

    public void setFreeJump(boolean freeJump) {
        this.freeJump = freeJump;
    }

}
