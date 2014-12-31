/**
 * 
 */
package com.toft.widgets.workflow.db;

import org.apache.commons.lang.StringUtils;

import com.toft.widgets.workflow.utils.ConstValue;
import com.toft.widgets.workflow.vo.BehaviorManagement;
import com.toft.widgets.workflow.vo.FlowDesignerContext;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 18, 2009
 * 
 */

/** ************modify by lsy:调用别人给出的接口：这部分暂时不用********************* */
public class SQLProvider {

    /**
     * **********************modify by lsy :change jbpm_deploy to
     * jbpm4_deploy*************************************************
     */
    public static String selectAllWorkflow(FlowDesignerContext context) {
        StringBuffer sql = null;
        
		sql = findProcessDefBaseSql();
	    if (context != null) {
          if (StringUtils.isNotBlank(context.getAgency()))
              sql.append(" WHERE JD.AGENCYID='" + context.getAgency() + "' ");
        }
	    
	    sql.append("ORDER BY NAME ");
	    
		return sql.toString();
	}

    public static String insertWorkflow() {
        String sql = "insert into "
                + ConstValue.JBPM4_DEPLOY
                + " (ID,NAME,CODE,pdstatus,PROCESSDEFINE,PDNAME,PROCESSTYPE,PDVERSION,PDID,AGENCYID,DEPARTMENTID,FLAG,STATUS) values (?,?,?,0,?,?,?,0,NULL,?,?,'1','1')";
        return sql;
    }

    // 版本变化--insert
    public static String insertWorkflowByVersion() {
        String sql = "insert into "
                + ConstValue.JBPM4_DEPLOY
                + " (ID,NAME,CODE,pdstatus,PROCESSDEFINE,PDNAME,PROCESSTYPE,PDVERSION,PDID,AGENCYID,DEPARTMENTID,FLAG,STATUS) values (?,?,?,?,?,?,?,?,?,?,?,'1','1')";
        return sql;
    }

    // 版本变化--update
    public static String updateWorkflowByVersion() {
        String sql = "update " + ConstValue.JBPM4_DEPLOY + " set PDVERSION =?,PDID=? WHERE ID=?";
        return sql;
    }

    public static String updateWorkflow() {
        String sql = "update " + ConstValue.JBPM4_DEPLOY
                + " set NAME=?,CODE=?,pdstatus=?,PROCESSDEFINE=?,PDNAME=? where ID=?";
        return sql;
    }

    public static String selectWorkflowByID() {
        String sql = "select * from " + ConstValue.JBPM4_DEPLOY + " where ID=? and flag='1' and status='1'";
        return sql;
    }

    public static String selectWorkflowByCode(FlowDesignerContext context) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from " + ConstValue.JBPM4_DEPLOY + " where code=? and flag='1' and status='1' ");
        if (context != null) {
            if (context.getAgency() != null && !context.getAgency().equals(""))
                sql.append(" and AGENCYID='" + context.getAgency() + "' ");
            if (context.getDepartment() != null && !context.getDepartment().equals(""))
                sql.append(" and DEPARTMENTID='" + context.getDepartment() + "' ");
        }
        sql.append(" order by pdversion");
        return sql.toString();
    }

    public static String selectWorkflowByName(FlowDesignerContext context) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from " + ConstValue.JBPM4_DEPLOY
                + " where name=? and code<>? and flag='1' and status='1' ");
        if (context != null) {
            if (context.getAgency() != null && !context.getAgency().equals(""))
                sql.append(" and AGENCYID='" + context.getAgency() + "' ");
            if (context.getDepartment() != null && !context.getDepartment().equals(""))
                sql.append(" and DEPARTMENTID='" + context.getDepartment() + "' ");
        }
        return sql.toString();
    }

    public static String selectWorkflowByNameAndCode(FlowDesignerContext context) {
        StringBuffer sql = new StringBuffer();

        sql.append("select * from " + ConstValue.JBPM4_DEPLOY + " where name=? and code=? and flag='1' and status='1' ");// 去除name（对应用户自定义名称）的过滤
        // sql.append("select * from ConstValue.JBPM4_DEPLOY where code=? and flag='1' and status='1' ");

        if (context != null) {
            if (context.getAgency() != null && !context.getAgency().equals(""))
                sql.append(" and AGENCYID='" + context.getAgency() + "' ");
            if (context.getDepartment() != null && !context.getDepartment().equals(""))
                sql.append(" and DEPARTMENTID='" + context.getDepartment() + "' ");
        }
        return sql.toString();
    }

    public static String selectWorkflowByNameOnly(FlowDesignerContext context) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from " + ConstValue.JBPM4_DEPLOY + " where name=? and flag='1' and status='1' ");
        if (context != null) {
            if (context.getAgency() != null && !context.getAgency().equals(""))
                sql.append(" and AGENCYID='" + context.getAgency() + "' ");
            if (context.getDepartment() != null && !context.getDepartment().equals(""))
                sql.append(" and DEPARTMENTID='" + context.getDepartment() + "' ");
        }
        sql.append("order by pdversion");
        return sql.toString();
    }

    public static String deleteWorkflow(FlowDesignerContext context) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from " + ConstValue.JBPM4_DEPLOY + " where code=?");
        if (context != null) {
            if (context.getAgency() != null && !context.getAgency().equals(""))
                sql.append(" and AGENCYID='" + context.getAgency() + "' ");
            if (context.getDepartment() != null && !context.getDepartment().equals(""))
                sql.append(" and DEPARTMENTID='" + context.getDepartment() + "' ");
        }
        return sql.toString();
    }

    public static String isArticulated(FlowDesignerContext context) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from " + ConstValue.JBPM4_ARTICULATED_PROCESS + " where processdefinition=?");
        if (context != null) {
            if (context.getAgency() != null && !context.getAgency().equals(""))
                sql.append(" and AGENCYID='" + context.getAgency() + "' ");
            if (context.getDepartment() != null && !context.getDepartment().equals(""))
                sql.append(" and DEPARTMENTID='" + context.getDepartment() + "' ");
        }
        return sql.toString();
    }

    /**
     * ************************************************************************* ****
     */
    /**
     * @author lsy getProcessName
     */
    public static String getProcessName(FlowDesignerContext context) {
        StringBuffer sql = null;
        sql = findProcessDefBaseSql();
        
        sql.append(" WHERE JD.processType='subprocess' and JD.pdStatus='1' ");
        
        if (context != null) {
            if (StringUtils.isNotBlank(context.getAgency()))
                sql.append(" and JD.AGENCYID='" + context.getAgency() + "' ");
        }
          
        sql.append("ORDER BY NAME ");
        
        return sql.toString();
    }

    /**
     * 更新状态值 从0态--创建态更新到1态--发布态
     */
    public static String updateStatus() {
        String sql = "update " + ConstValue.JBPM4_DEPLOY + " set pdstatus = ? where id = ?";
        return sql;
    }

    /**
     * 根据事件类型获取记录
     * 
     * @return
     */
    public static String getBehaviorManagementList(String evType, String name) {
        String sql = "select ID,CODE,NAME,CLASSPATH,EV_TYPE from " + ConstValue.WORKFLOW_BEHAVIOR_MANAGEMENT
                + " where EV_Type=?";
        if (StringUtils.isNotEmpty(name)) {
            sql += " and name like ?";
        }
        return sql;
    }

    /**
     * 重复校验
     * 
     * @param behaviorManagement
     * @return
     */
    public static String getBehaviorManagementCount(BehaviorManagement behaviorManagement) {
        String id = behaviorManagement.getId();
        String evType = behaviorManagement.getEvType();
        String sql = "select count(ID) from " + ConstValue.WORKFLOW_BEHAVIOR_MANAGEMENT + " where name=? and EV_TYPE=?";
        if ("2".equals(evType)) {
            sql += " and CLASSPATH=?";
        }
        if (StringUtils.isNotEmpty(id)) {
            sql += " and id<>?";
        }
        return sql;
    }

    /**
     * 插入记录
     * 
     * @return
     */
    public static String insertBehaviorManagement() {
        String sql = "insert into " + ConstValue.WORKFLOW_BEHAVIOR_MANAGEMENT
                + "(ID,CODE,NAME,CLASSPATH,EV_TYPE) values(?,?,?,?,?)";
        return sql;
    }

    /**
     * 更新记录
     * 
     * @return
     */
    public static String updateBehaviorManagement() {
        String sql = "update " + ConstValue.WORKFLOW_BEHAVIOR_MANAGEMENT
                + " set CODE=?,NAME=?,CLASSPATH=?,EV_TYPE=? where ID=?";
        return sql;
    }

    /**
     * 删除记录
     * 
     * @return
     */
    public static String deleteBehaviorManagement() {
        String sql = "delete from " + ConstValue.WORKFLOW_BEHAVIOR_MANAGEMENT + " where id=?";
        return sql;
    }

    // 去掉oracle的特征，兼容db2,mysql add by chenshuqing on 20130528
    private static StringBuffer findProcessDefBaseSql(){
        
        StringBuffer sql = new StringBuffer();    
        sql.append("SELECT "); 
        sql.append("     jd.id as ID,jd.code as CODE,jd.name as NAME,jd.pdversion,jd.pdid,jd.pdstatus as PDSTATUS,");
        sql.append("     jd.processType as PROCESSTYPE,jd.pdName,jd.processDefine,jd.agencyId");
        sql.append(" FROM");
        sql.append("    (SELECT T.ID,");
        sql.append("    T.CODE,");
        sql.append("    T.NAME,");
        sql.append("    T.PDSTATUS,");
        sql.append("    T.PDVERSION,");
        sql.append("    T.PROCESSTYPE,");
        sql.append("    T.PDID,");
        sql.append("    T.PDNAME,");
        sql.append("    T.PROCESSDEFINE,");
        sql.append("    T.AGENCYID ");
        sql.append("    FROM "+ConstValue.JBPM4_DEPLOY+" T,");
        sql.append("     (SELECT TEMP1.ID AS ID");
        sql.append("       FROM (SELECT T3.ID AS ID, T3.CODE AS CODE");
        sql.append("               FROM "+ConstValue.JBPM4_DEPLOY+" T3");
        sql.append("              WHERE (T3.PDVERSION = 0 OR T3.PDVERSION IS NULL)");
        sql.append("                AND T3.CODE IS NOT NULL AND T3.STATUS = 1) TEMP1");
        sql.append("     UNION");
        sql.append("     SELECT TEMP2.ID AS ID");
        sql.append("       FROM (SELECT T1.ID AS ID, T1.CODE AS CODE");
        sql.append("               FROM "+ConstValue.JBPM4_DEPLOY+" T1,");
        sql.append("                    (SELECT CODE AS CODE, MAX(PDVERSION) AS VERSION");
        sql.append("                       FROM "+ConstValue.JBPM4_DEPLOY);
        sql.append("                      GROUP BY CODE) T2");
        sql.append("              WHERE T1.CODE = T2.CODE");
        sql.append("                AND T1.PDVERSION = T2.VERSION");
        sql.append("                AND T1.STATUS = 1");
        sql.append("                AND T1.CODE NOT IN");
        sql.append("                    (SELECT CODE");
        sql.append("                       FROM (SELECT T3.ID AS ID, T3.CODE AS CODE");
        sql.append("                               FROM "+ConstValue.JBPM4_DEPLOY+" T3");
        sql.append("                              WHERE (T3.PDVERSION = 0 OR");
        sql.append("                                    T3.PDVERSION IS NULL)");
        sql.append("                                AND T3.CODE IS NOT NULL AND T3.STATUS = 1) temp4 )) TEMP2) T3 ");
        sql.append("WHERE T.ID = T3.ID) JD ");
        
        return sql;
    }
}
