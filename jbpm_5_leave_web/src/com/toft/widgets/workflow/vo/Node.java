/**
 * 
 */
package com.toft.widgets.workflow.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Element;

import com.toft.widgets.workflow.utils.ArrayUtils;
import com.toft.widgets.workflow.utils.ConstValue;
import com.toft.widgets.workflow.utils.PropertiesUtils;
import com.toft.widgets.workflow.vo.javanode.ClassField;
import com.toft.widgets.workflow.vo.javanode.JavaNode;
import com.toft.widgets.workflow.vo.javanode.MethodParameter;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * 
 */
public class Node {

    private String id = "";
    private String name = "";
    private String activityName = "";
    private String identifyType = "";
    private String type = "";
    private String expr = "";
    private int x;// modify by lsy: float => int
    private int y;// modify by lsy: float => int
    private int width;
    private int height;
    private Object[] goIn;
    private Object[] goOut;
    private Object[] actions;
    private Object[] tasks;
    private Object[] variables;
    private String missHandle = "";

    /** **************************************** */
    private SubProcess subProcess = new SubProcess();// 子流程名称

    private String assignee;// 用于提交节点的指派人

    private String maxSatiefy;// 用于聚合节点最大满足条件

    private String countersignType = "all";// 鹿鹏：追加会签节点的满足类型
    private String countersignValue = "";// 鹿鹏：追加会签节点的满足值
    private boolean countersignAlldo = true;// 鹿鹏：追加自动终止未完成工作项
    /* 增加任务节点“直接跳跃”和“自由跳跃” add by liangleilei at 2013-05-14 */
    private boolean directJump = false;
    private boolean freeJump = false;

    // 超时处理--状态节点时使用
    public TimeOutAction timeoutAction = new TimeOutAction();// 超时处理类
    public String timeout;
    public List timeoutParam = new ArrayList();// 超时类的参数

    // 用于事件处理
    public Object[] events;
    // 用于自由节点的类名
    public FreeNodeClass freeNodeClass = new FreeNodeClass();

    private List subParamInList = new ArrayList();// 保存子流程节点的入参
    private List subParamOutList = new ArrayList();// 保存子流程节点的出参

    /** ****************************************** */

    private Foreach foreachs = new Foreach();

    public Foreach getForeachs() {
        return foreachs;
    }

    public void setForeachs(Foreach foreachs) {
        this.foreachs = foreachs;
    }

    private JavaNode javanode = new JavaNode();

    public JavaNode getJavanode() {
        return javanode;
    }

    public void setJavanode(JavaNode javanode) {
        this.javanode = javanode;
    }

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

    public void toModelXML(Element element) throws Exception {
        Element node = element.addElement("node");
        node.addAttribute("name", this.name);
        /** ********************lsy-start************************** */
        node.addAttribute("g", this.getG());
        /** ********************lsy-end************************* */
        if (this.goOut != null)
            for (int i = 0; i < this.goOut.length; i++) {
                Transition transition = (Transition) this.goOut[i];
                transition.toModelXML(node);
            }
    }

    public void toFlowXML(Element element) throws Exception {
        // 创建根节点,创建活动节点
        Element node = element.addElement(this.type);
        node.addAttribute("name", this.name);
        node.addAttribute("activityName", this.activityName);
        node.addAttribute("identifyType", this.identifyType);// 用于前台的判断
        node.addAttribute("missHandle", this.missHandle);// ??????
        // 鹿鹏：修改业务逻辑，去除事件处理(老的2011.04.19前)--2011.04.19 再次改造“多事件”
        if (this.events != null && this.events.length > 0) {
            for (int i = 0; i < this.events.length; i++) {
                EventFW eventfw = (EventFW) this.events[i];
                eventfw.toEventXML(node);
            }
        }

        node.addAttribute("g", this.getG());

        // 子流程的参数
        if ("sub-process".equals(this.type)) {
            node.addAttribute("sub-process-key", this.subProcess.getSubProcessCode());
            node.addAttribute("sub-process-key-name", this.subProcess.getSubProcessName());
            //增加子流程默认出度 by liangleilei at 2013-05-15
            String outcome = "";
            //如果只有一个出度则直接填写流向
            if (this.goOut.length == 1) {
                outcome = ((Transition) this.goOut[0]).getName();
            }
            String agreeTransition = "";
            String refuseTransition = "";
            //如果有两个出度则根据流向同意拒绝拼出流向表达式（暂时这样解决）
            if (this.goOut.length == 2) {
                for (int i = 0; i < this.goOut.length; i++) {
                    Transition transition = (Transition) this.goOut[i];
                    String pass = transition.getPass();
                    if ("true".equals(pass)) {
                        agreeTransition = transition.getName();
                    } else {
                        refuseTransition = transition.getName();
             }
                }
                outcome="#{commentType=='同意'?'"+agreeTransition+"':'"+refuseTransition+"'}";
            }
            node.addAttribute("outcome", outcome);
            // 入参
            Iterator iterParamIn = this.subParamInList.iterator();
            while (iterParamIn.hasNext()) {
                Map paramIn = (Map) iterParamIn.next();
                if (paramIn != null && paramIn.get("name") != null && paramIn.get("value") != null) {
                    Element paramInElement = node.addElement("parameter-in");
                    paramInElement.addAttribute("var", paramIn.get("code") != null ? paramIn.get("code").toString()
                            : paramIn.get("name").toString());// name
                    paramInElement.addAttribute("subvar", paramIn.get("value").toString());
                }
            }
            // 出参
            Iterator iterParamOut = this.subParamOutList.iterator();
            while (iterParamOut.hasNext()) {
                Map paramOut = (Map) iterParamOut.next();
                if (paramOut != null && paramOut.get("name") != null && paramOut.get("value") != null) {
                    Element paramOutElement = node.addElement("parameter-out");
                    paramOutElement.addAttribute("var", paramOut.get("code") != null ? paramOut.get("code").toString()
                            : paramOut.get("name").toString());// name
                    paramOutElement.addAttribute("subvar", paramOut.get("value").toString());
                }
            }
        }

        /** ***********************JOIN节点最大满足条件********************************* */
        if ("join".equals(this.type) && this.maxSatiefy != null && !"".equals(this.maxSatiefy)) {
            node.addAttribute("multiplicity", this.maxSatiefy);
        }
        // foreach节点xml封装
        if ("foreach".equals(this.type) && this.foreachs != null) {
            // var="department" in="#{departments}"
            node.addAttribute("var", this.foreachs.getVars());
            node.addAttribute("in", this.foreachs.getCollections());
        }

        if ("java".equals(this.type) && this.javanode != null) {
            node.addAttribute("class", javanode.getClasspath());
            node.addAttribute("method", javanode.getMethod());
            node.addAttribute("continue", javanode.getContinued());
            node.addAttribute("var", javanode.getVars());
            for (int i = 0; i < javanode.getFields().length; i++) {
                ClassField field = javanode.getFields()[i];
                Element el_f = node.addElement("field");
                el_f.addAttribute("name", field.getName());
                Element el_f_n = el_f.addElement(field.getType().toLowerCase());
                el_f_n.addAttribute("value", field.getValue());
            }
            for (int i = 0; i < javanode.getParameters().length; i++) {
                MethodParameter parameter = javanode.getParameters()[i];
                Element el_a = node.addElement("arg");
                Element el_a_n = el_a.addElement(parameter.getType().toLowerCase());
                el_a_n.addAttribute("value", parameter.getValue());
            }
        }

        // 鹿鹏：追加 会签节点的3个属性：通过类型，通过值，是否自动终止未完成项
        if (ConstValue.COUNTERSIGNTYPE.equals(this.identifyType)) {
            node.addAttribute("countersignType", this.countersignType);
            node.addAttribute("countersignValue", this.countersignValue);
            node.addAttribute("countersignAlldo", this.countersignAlldo + "");
        }

        if (ConstValue.TASKNODETYPE.equals(this.identifyType)) {
            node.addAttribute("directJump", this.isDirectJump() + "");
            node.addAttribute("freeJump", this.isFreeJump() + "");
        }

        /**
         * ******************CUSTOM节点的CLASS属性*********************************** ******
         */
        if ("custom".equals(this.type)) {
            node.addAttribute("class", "com.toft.widgets.workflow.handler.CustomNodeCounter");// 待修改
        }
        /**
         * *******************modify by lsy -提交节点独有属性，指定处理人 2009-12-8***************************
         */
        if (ConstValue.COMMITNODETYPE.equals(this.identifyType)) {
            node.addAttribute("assignee", "#{submituser}");
            node.addAttribute("directJump", this.isDirectJump() + "");
            node.addAttribute("freeJump", this.isFreeJump() + "");
        }
        /** ********************************************** */
        if ("decision".equals(this.type) && this.goOut != null && this.goOut.length > 0) {
            String expr = "";
            String defaultTransition = "";
            Transition transition;
            Transition[] transitions = new Transition[this.goOut.length];
            int k = 0;
            for (int i = 0; i < this.goOut.length; i++) {
                transition = (Transition) this.goOut[i];
                if (transition.getDescription() == null || transition.getDescription().trim().equals("")) {
                    defaultTransition = transition.getName();
                    continue;
                }
                transitions[k] = transition;
                k++;
            }
            transitions = (Transition[]) ArrayUtils.arrayReduceLength(transitions, this.goOut.length - k);
            for (int j = transitions.length - 1; j >= 0; j--) {
                if (transitions[j] == null)
                    continue;
                transition = transitions[j];
                String str = transition.getDescription() + "?'" + transition.getName() + "'";
                if (j == transitions.length - 1) {
                    str += (":'" + defaultTransition + "'");
                }
                if (j != 0)
                    expr = ":(" + str + expr + ")";
                else
                    expr = str + expr;
            }
            expr = ("#{" + expr + "}").trim();
            node.addAttribute("expr", expr);
        }
        if (this.tasks != null && this.tasks.length > 0)
            for (int j = 0; j < this.tasks.length; j++) {
                Task task = (Task) this.tasks[j];
                // add by lsy 判断条件 提交节点没有下一级的属性 2009-12-09
                task.toXML(node, this.identifyType);

            }
        if (this.goOut != null && this.goOut.length > 0)
            for (int i = 0; i < this.goOut.length; i++) {
                Transition transition = (Transition) this.goOut[i];
                transition.toFlowXML(node);
            }

        if (this.variables != null && this.variables.length > 0) {
            Element variablesElement = node.addElement("flow-variables");
            for (int k = 0; k < this.variables.length; k++) {
                Variable variable = (Variable) this.variables[k];
                variable.toXML(variablesElement);
            }
        }
    }

    public Node toNode(Element defineElement) throws Exception {
        this.id = defineElement.attributeValue("id");
        this.name = defineElement.attributeValue("name");
        this.activityName = defineElement.attributeValue("activityName");
        this.identifyType = defineElement.attributeValue("identifyType");
        this.expr = defineElement.attributeValue("expr");
        this.missHandle = defineElement.attributeValue("missHandle");
        // add by lsy : freeNode--class
        this.freeNodeClass.setFreeNodeClassCode(defineElement.attributeValue("class"));
        // add by lsy :sub-process-key
        this.subProcess.setSubProcessCode(defineElement.attributeValue("sub-process-key"));
        this.subProcess.setSubProcessName(defineElement.attributeValue("sub-process-key-name"));
        this.subProcess.setOutcome(defineElement.attributeValue("outcome"));
        this.type = defineElement.getName();
        /**
         * ********************************子流程的属性处理***************************** *********
         */
        if (this.name.indexOf("sub-process") != -1) {
            // in
            if (defineElement.element("parameter-in") != null) {
                Iterator itemparminElement = defineElement.elementIterator("parameter-in");
                while (itemparminElement.hasNext()) {
                    Map tempMap = new HashMap();
                    Element paramIn = (Element) itemparminElement.next();
                    tempMap.put("name", paramIn.attributeValue("var"));
                    tempMap.put("value", paramIn.attributeValue("subvar"));
                    this.subParamInList.add(tempMap);
                }
            }
            // out
            if (defineElement.element("parameter-out") != null) {
                Iterator itemparmoutElement = defineElement.elementIterator("parameter-out");
                while (itemparmoutElement.hasNext()) {
                    Map tempMap = new HashMap();
                    Element paramOut = (Element) itemparmoutElement.next();
                    tempMap.put("name", paramOut.attributeValue("var"));
                    tempMap.put("value", paramOut.attributeValue("subvar"));
                    this.subParamOutList.add(tempMap);
                }
            }
        }
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
        /**
         * ********************状态节点的超时处理-解析************************** 2011.04.21 鹿鹏：去除超时处理，将此作为事件进行处理解析 Iterator
         * iterNodeEventTimeOut = defineElement.elementIterator("on"); while (iterNodeEventTimeOut.hasNext()) { Element
         * elementNodeTOOn = (Element) iterNodeEventTimeOut.next(); if
         * ("timeout".equals(elementNodeTOOn.attributeValue("event"))) { Element elementTimeOutTimer = (Element)
         * elementNodeTOOn .element("timer"); this.timeout = elementTimeOutTimer.attributeValue("duedate"); Element
         * elementTimeOutActionEL = (Element) elementNodeTOOn .element("event-listener"); if (elementTimeOutActionEL !=
         * null) { this.timeoutAction .setTimeoutActionCode(elementTimeOutActionEL .attributeValue("class")); Iterator
         * iterTimeOutField = elementTimeOutActionEL .elementIterator("field"); while (iterTimeOutField.hasNext()) {
         * Element iterField = (Element) iterTimeOutField.next(); if ("timeoutName".equals(iterField
         * .attributeValue("name"))) { this.timeoutAction.setTimeoutActionName(iterField
         * .element("string").attributeValue("value")); } else { Iterator iterTimeOut = iterField.element("list")
         * .elementIterator("string"); while (iterTimeOut.hasNext()) { Map mapObj = new HashMap(); Element
         * elementTimeOutParam = (Element) iterTimeOut .next(); String value = elementTimeOutParam
         * .attributeValue("value"); if (value != null) { String arrValue[] = value.split("="); mapObj.put("name",
         * arrValue[0]); mapObj.put("value", arrValue[1]); this.timeoutParam.add(mapObj); } } } } } } }
         */
        // join节点的最大满足条件
        this.maxSatiefy = defineElement.attributeValue("multiplicity");
        this.countersignType = defineElement.attributeValue("countersignType");
        this.countersignValue = defineElement.attributeValue("countersignValue");
        this.countersignAlldo = (new Boolean(defineElement.attributeValue("countersignAlldo")).booleanValue());
        /* 增加任务节点“直接跳跃”和“自由跳跃” add by liangleilei at 2013-05-14 */
        this.directJump = (new Boolean(defineElement.attributeValue("directJump")).booleanValue());
        this.freeJump = (new Boolean(defineElement.attributeValue("freeJump")).booleanValue());

        // 追加foreach节点node封装
        this.foreachs.setVars(defineElement.attributeValue("var"));
        this.foreachs.setCollections(defineElement.attributeValue("in"));

        /*
         * <java name="greet" class="org.jbpm.examples.java.JohnDoe" method="hello" continue="async" expr="#{hand}"
         * var="answer" g="96,16,83,52"> <field name="state"><string value="fine"/></field> <arg><string
         * value="Hi, how are you?"/></arg> <transition to="shake hand" /> </java>
         */
        javanode.setClasspath(defineElement.attributeValue("class"));
        javanode.setContinued(defineElement.attributeValue("continue"));
        javanode.setMethod(defineElement.attributeValue("method"));
        javanode.setVars(defineElement.attributeValue("var"));
        List fields = defineElement.elements("field");
        ClassField cf = null;
        ClassField[] cfs = new ClassField[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            cf = new ClassField();
            cf.setName(((Element) fields.get(i)).attributeValue("name"));
            cf.setType(((Element) ((Element) fields.get(i)).elements().get(0)).getName());
            cf.setValue(((Element) ((Element) fields.get(i)).elements().get(0)).attributeValue("value"));
            cfs[i] = cf;
        }
        javanode.setFields(cfs);

        List parameters = defineElement.elements("arg");
        MethodParameter mp = null;
        MethodParameter[] mps = new MethodParameter[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            mp = new MethodParameter();
            mp.setIndex(i);
            mp.setType(((Element) ((Element) parameters.get(i)).elements().get(0)).getName());
            mp.setValue(((Element) ((Element) parameters.get(i)).elements().get(0)).attributeValue("value"));
            mps[i] = mp;
        }
        javanode.setParameters(mps);

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
            // 处理task

            // 处理swimline
            if (!"".equals(defineElement.attributeValue("swimlineSet"))) {
                task.setSwimlineSetter(defineElement.attributeValue("swimlineSet"));
            } else {
                task.setSwimlineSetter("");
            }

            if (!"".equals(defineElement.attributeValue("swimlineGet"))) {
                task.setSwimlineGetter(defineElement.attributeValue("swimlineGet"));
            } else {
                task.setSwimlineGetter("");
            }

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

    public List getSubParamInList() {
        return subParamInList;
    }

    public void setSubParamInList(List subParamInList) {
        this.subParamInList = subParamInList;
    }

    public List getSubParamOutList() {
        return subParamOutList;
    }

    public void setSubParamOutList(List subParamOutList) {
        this.subParamOutList = subParamOutList;
    }

    public SubProcess getSubProcess() {
        return subProcess;
    }

    public void setSubProcess(SubProcess subProcess) {
        this.subProcess = subProcess;
    }

    public FreeNodeClass getFreeNodeClass() {
        return freeNodeClass;
    }

    public void setFreeNodeClass(FreeNodeClass freeNodeClass) {
        this.freeNodeClass = freeNodeClass;
    }

    public TimeOutAction getTimeoutAction() {
        return timeoutAction;
    }

    public void setTimeoutAction(TimeOutAction timeoutAction) {
        this.timeoutAction = timeoutAction;
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
