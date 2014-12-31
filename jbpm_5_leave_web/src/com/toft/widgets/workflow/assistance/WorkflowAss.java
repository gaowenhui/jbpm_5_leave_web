/**
 * 
 */
package com.toft.widgets.workflow.assistance;

import java.io.File;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import com.toft.widgets.workflow.db.SQLProvider;
import com.toft.widgets.workflow.utils.CommonValue;
import com.toft.widgets.workflow.utils.ConstValue;
import com.toft.widgets.workflow.utils.CreateRandomId;
import com.toft.widgets.workflow.utils.FileUtil;
import com.toft.widgets.workflow.utils.StringUtil;
import com.toft.widgets.workflow.utils.TableConstants;
import com.toft.widgets.workflow.utils.XMLUtil;
import com.toft.widgets.workflow.utils.ZipCompressor;
import com.toft.widgets.workflow.vo.FlowDesignerContext;
import com.toft.widgets.workflow.vo.HastenTemplate;
import com.toft.widgets.workflow.vo.Node;
import com.toft.widgets.workflow.vo.Task;
import com.toft.widgets.workflow.vo.Workflow;

/**
 * 流程辅助类
 *  
 * @author cswang mail to : <cswang@isoftstone.com>
 * @version 1.0.0  2012-8-21 下午1:46:09 
 * 
 * @author chenshuqing
 * @version 4.4.3  2012-8-21 下午1:46:09  checkstyle并修订
 *
 */
public class WorkflowAss extends BaseAss {

    private final Logger logger = Logger.getLogger(WorkflowAss.class);

    private CommonValue convalue = CommonValue.getInstance();// 接收输出/入XML的路径

    public static WorkflowAss ass;

    /**
     * 获取实例
     *
     * @return WorkflowAss 实例
     */
    public static WorkflowAss getInstance() {
        if (ass == null){
            ass = new WorkflowAss();
        }
        return ass;
    }

    /**
     * 保存流程
     *
     * @param arrTemp 参数
     * @return        流程实例
     * @throws Exception  应用异常
     */
    public Workflow saveWorkflow(Object[] arrTemp) throws Exception {
        Workflow wf = null;
        wf = isWorkflowExistByNameAndCode(arrTemp);
        if (wf == null){
            return insertWorkflow(arrTemp);
        }else {
            arrTemp[0] = wf;
            return updateWorkflow(arrTemp);
        }
    }

    /**
     * 查询返回所有流程
     * 
     * @param arrTemp 参数
     * @return Object[]  流程实例
     */
    public Object[] getWorkflowArray(Object[] arrTemp) {
        final FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
        Connection conn = null;
        conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        final List workflowList = new ArrayList();
        String statusTemp = null;
        ResultSet rs = null;
        try {
            Clob clob = null;
            ps = conn.prepareStatement(SQLProvider.selectAllWorkflow(context));
            rs = ps.executeQuery();
            Map workflow = null;
            while (rs.next()) {
                workflow = new HashMap();
                workflow.put("id", rs.getString("id".toUpperCase()));
                workflow.put("name", rs.getString("name".toUpperCase()));
                workflow.put("code", rs.getString("code".toUpperCase()));
                workflow.put("pdversion", rs.getString("pdversion".toUpperCase()));
                statusTemp = rs.getString("pdstatus".toUpperCase());
                if ("0".equals(statusTemp)) {
                    workflow.put("pdstatus", "未发布");
                }
                if ("1".equals(statusTemp)) {
                    workflow.put("pdstatus", "已发布");
                }
                if ("2".equals(statusTemp)) {
                    workflow.put("pdstatus", "修改未发布");
                }
                clob = rs.getClob("PROCESSDEFINE");
                final String tempstr = StringUtil.clobToString(clob);
                if (!"".equals(tempstr)) {
                    workflow.put("processDefine", tempstr);
                }

                workflowList.add(workflow);
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return workflowList.toArray();
    }

    /**
     * 
     * 2011.04.21 鹿鹏 标注：此方法暂无使用
     *
     * @param workflow   流程定义实体
     * @param filePath   保存路径
     * @return           执行是否成功
     * @throws Exception  应用异常
     */
    public boolean writeWorkflow(Workflow workflow, String filePath) throws Exception {
        boolean writeDefine = false;
        boolean writeDiagram = false;
        final Document define = XMLUtil.createDefineXML(workflow);
        writeDefine = XMLUtil.writeXML(define, filePath + File.separator + "processdefinition.xml");
        final Document diagram = XMLUtil.createDiagramXML(workflow);
        writeDiagram = XMLUtil.writeXML(diagram, filePath + File.separator + "gpd.xml");
        if (writeDefine && writeDiagram){
            return true;
        }
        return false;
    }

    /**
     * 插入流程
     * 
     * @param arrTemp  参数
     * @return         流程实体
     * 
     * @throws Exception 应用异常
     */
    public Workflow insertWorkflow(Object[] arrTemp) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        Workflow workflow = null;
        try {
            final FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
            conn = getConnection(context.getConParam());
            workflow = (Workflow) arrTemp[0];
            final String id = CreateRandomId.createId_Thirty();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(SQLProvider.insertWorkflow());
            ps.setString(1, id);
            ps.setString(2, workflow.getActivityName());
            final int columnNumber3 = 3;
            ps.setString(columnNumber3, workflow.getName());
            if (workflow.getProcessDefine() == null || "".equals(workflow.getProcessDefine())) {
                workflow.setId(id);
                final Document doc = XMLUtil.createDefineXML(workflow);
                doc.setXMLEncoding("UTF-8");
                workflow.setProcessDefine(doc.asXML());
            }
            
            final int columnNumber4 = 4;
            ps.setString(columnNumber4, "");
            
            // 流程定义文件名
            final int columnNumber5 = 5;
            ps.setString(columnNumber5, workflow.getName() + ".jpdl" + ".xml");
            
            // 流程定义类型（主流程-子流程）
            final int columnNumber6 = 6;
            ps.setString(columnNumber6, workflow.getProcessType());
            final int columnNumber7 = 7;
            ps.setString(columnNumber7, context.getAgency());
            final int columnNumber8 = 8;
            ps.setString(columnNumber8, context.getDepartment());
            ps.execute();
            StringUtil.stringToClob(conn, workflow.getProcessDefine(), workflow.getId());
            updateVariableStatus(workflow, conn);
            updateMessageTemplateStatus(workflow, conn);
            conn.commit();
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            rollBackConnection(conn);
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
            throw e;
        } finally {
            closePrepareStatement(ps);
            closeConnection(conn);
        }

        return workflow;
    }

    /**
     * 更新流程 ，如果为状态为1（已发布）则将状态值改为2（修改后未发布）
     * 
     * @param arrTemp 参数 
     * @return        流程实体
     * @throws Exception  应用异常
     */
    public Workflow updateWorkflow(Object[] arrTemp) throws Exception {
        final FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        final Connection conn = getConnection(context.getConParam());
        Workflow workflow = (Workflow) arrTemp[0];
        PreparedStatement ps = null;
        final Map map = getWorkflowState(arrTemp);
        final String states = map.get("pdstatus").toString();
        final int version = Integer.parseInt(map.get("pdversion").toString());
        try {
            conn.setAutoCommit(false);
            if (version == 0) {
                ps = conn.prepareStatement(SQLProvider.updateWorkflow());
                workflow = processAddId(workflow, "0");
                ps.setString(1, workflow.getActivityName());
                ps.setString(2, workflow.getName());
                final int columnNumber3 = 3;
                if ("1".equals(states)){
                    ps.setString(columnNumber3, "2");
                }else{
                    ps.setString(columnNumber3, states);
                }
                final int columnNumber4 = 4;
                ps.setString(columnNumber4, "");
                
                // 新加入的字段
                final int columnNumber5 = 5;
                ps.setString(columnNumber5, workflow.getName() + ".jpdl" + ".xml");
                final int columnNumber6 = 6;
                ps.setString(columnNumber6, workflow.getId());
            } else {
                ps = conn.prepareStatement(SQLProvider.insertWorkflowByVersion());
                workflow = processAddId(workflow, "1");
                ps.setString(1, workflow.getId());
                ps.setString(2, workflow.getActivityName());
                ps.setString(3, workflow.getName());
                if (states.equals("1")) {
                    ps.setString(4, "2");
                    ps.setString(9, null);// 如果状态为0或2时 流程Id为空
                } else {
                    ps.setString(4, states);
                    ps.setString(9, map.get("pdid").toString());// 如果状态为1时，流程Id
                }

                ps.setString(5, "");
                ps.setString(6, workflow.getName() + ".jpdl" + ".xml");
                ps.setString(7, workflow.getProcessType());
                ps.setInt(8, 0);
                ps.setString(10, context.getAgency());
                ps.setString(11, context.getDepartment());
            }
            ps.execute();
            /**
             * *********************************更新之后获得版本最高的做打开操作****************
             * *********************************
             */
            StringUtil.stringToClob(conn, workflow.getProcessDefine(), workflow.getId());
            updateVariableStatus(workflow, conn);
            updateMessageTemplateStatus(workflow, conn);
            conn.commit();
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            rollBackConnection(conn);
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
            throw e;
        }
        return workflow;
    }

    /**
     * 查询流程中是否有ID值，如果为更新流程则ID值为已知直接生成XML 如果为新建流程则要重新生成ID值，再生成XML pattern 0 ===新增
     * 1 ===更新
     * 
     * @param workflow
     * @return
     */
    public Workflow processAddId(Workflow workflow, String pattern) throws Exception {
        if ("1".equals(pattern)) {
            String id = CreateRandomId.createId_Thirty();
            workflow.setId(id);
        }
        Document doc = XMLUtil.createDefineXML(workflow);
        workflow.setProcessDefine(doc.asXML());
        return workflow;
    }

    /**
     * 根据流程的名称打开该流程
     * 
     * @param fileName
     * @return
     */
    public Workflow openWorkflow(Object[] arrTemp) {
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        ResultSet rs = null;
        Workflow workflow = new Workflow();
        /**
         * modify by lsy reason:根据后台的要求，生成与过程名同名的xml文件 delete: '+
         * File.separator+ ConstValue.DEFINENAME'
         */
        try {
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByID());
            ps.setString(1, arrTemp[0].toString());
            rs = ps.executeQuery();
            Clob clob = null;
            while (rs.next()) {
                clob = rs.getClob("PROCESSDEFINE");// 取出流程的开始节点，从数据库中取出
                workflow.setPdStatus(rs.getString("PDSTATUS"));
            }
            if (clob == null) {
            } else {
                String tempstr = StringUtil.clobToString(clob);
                if ("".equals(tempstr))
                    return workflow;
                Document tempDoc = StringUtil.stringToDoc(tempstr);// 类型转换
                workflow.toWorkflow(tempDoc.getRootElement());
            }

            closePrepareStatement(ps);
            closeConnection(conn);
            return workflow;
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return workflow;
    }

    /**
     * 删除工作流
     * 
     * @param fileName
     * @return
     */
    public String deleteWorkflow(Object[] arrTemp) {
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Connection conn = getConnection(context.getConParam());
        Map param = (Map) arrTemp[0];
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQLProvider.isArticulated(context));
            ps.setString(1, (String) param.get("id"));
            rs = ps.executeQuery();
            while (rs.next()) {
                return "0";
            }
            ps = conn.prepareStatement(SQLProvider.deleteWorkflow(context));
            ps.setString(1, (String) param.get("code"));
            ps.execute();
            closePrepareStatement(ps);
            closeConnection(conn);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
    }

    /**
     * 导出工作流
     * 
     * @param fileName
     * @return
     */
    public String exportWorkflow(Object[] arrTemp) {
        if (arrTemp[0] == null)
            return null;
        Object[] obs = (Object[]) arrTemp[0];
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        String fileName = CreateRandomId.createExport();
        String exportPath = convalue.getExportFilesPath() + File.separator + fileName;

        logger.info("--------------------Zip File to dir：  " + exportPath);

        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        String ids = "";
        Map map = null;
        for (int i = 0; i < obs.length; i++) {
            map = (Map) obs[i];
            ids += "'" + map.get("id") + "',";
        }

        try {
            String sql = "select * from "+TableConstants.JBPM4_DEPLOY+" where flag='1' and status='1'";
            if ("".equals(ids))
                return null;
            ids = ids.substring(0, ids.length() - 1);
            sql += " and id in (" + ids + ")";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            List list = new ArrayList();

            Clob clob = null;
            String code = null;
            String name = null;

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    if ("PROCESSDEFINE".equals(rsmd.getColumnName(i)) && !"".equals(rsmd.getColumnName(i)) && null != rsmd.getColumnName(i)) {
                        map = new HashMap();
                        clob = rs.getClob("PROCESSDEFINE");
                        code = rs.getString("CODE");
                        name = rs.getString("NAME");
                        String tempstr = StringUtil.clobToString(clob);
                        map.put("code", code);
                        map.put("body", tempstr);
                        map.put("name", name);
                        list.add(map);
                    }
                }
            }
            closePrepareStatement(ps);
            closeConnection(conn);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    map = (Map) list.get(i);
                    Document tempDoc = StringUtil.stringToDoc(((String) map.get("body")).trim());
                    XMLUtil.writeXML(tempDoc, exportPath + File.separatorChar + ((String) map.get("name")) + ".jpdl.xml");
                }
            }

            ZipCompressor.zip(exportPath, exportPath + ".zip");
            FileUtil.deleteDirectory(exportPath);

        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            logger.info("-----------------export error start----------------------");
            e.printStackTrace();
            logger.info("-----------------export error end----------------------");
        }
        return fileName + ".zip";
    }

    // 导出工作流后将生成的临时文件删除的方法
    public void deleteTempFile(Object[] arrTemp) {
        boolean result = false;
        String exportPath = convalue.getExportFilesPath() + File.separator + arrTemp[0].toString();
        result = FileUtil.delete(exportPath);
        if (result)
            System.out.println("success");
        else
            System.out.println("fild");
    }

    public void deleteTempFiles(Object[] arrTemp) {
        for (int i = 0; i < ((Object[]) arrTemp[0]).length; i++) {
            boolean result = false;
            String exportPath = convalue.getExportFilesPath() + File.separator + ((Object[]) arrTemp[0])[i].toString();
            result = FileUtil.delete(exportPath);
            if (result)
                System.out.println("success");
            else
                System.out.println("fild");
        }
    }

    public String isWorkflowExist(Object[] arrTemp) {
        Connection conn = null;
        PreparedStatement ps = null;
        String id = "";
        try {
            FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
            conn = getConnection(context.getConParam());
            Workflow workflow = (Workflow) arrTemp[0];
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByID());
            ps.setString(1, workflow.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("ID");
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return id;
    }

    // modify by lsy : return id==>return map
    public Map getWorkflowState(Object[] arrTemp) {
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Workflow workflow = (Workflow) arrTemp[0];
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        Map map = new HashMap();
        try {
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByCode(context));
            ps.setString(1, workflow.getName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put("pdstatus", rs.getString("pdstatus".toUpperCase()));
                map.put("pdversion", new Integer(rs.getInt("pdversion".toUpperCase())));
                map.put("pdid", rs.getString("pdid".toUpperCase()));
                if (rs.getInt("pdversion".toUpperCase()) == 0) {
                    break;
                }
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return map;
    }

    /** *****************add by lsy:getProcessName************* */
    /**
     * 查询返回子流程的名称的集合
     * 
     * @author lsy
     * @return List
     */
    public List selectProcessName(Object[] arrTemp) {
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
        List processName = new ArrayList();
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQLProvider.getProcessName(context));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map map = new HashMap();
                map.put("name", rs.getString("name"));// "activityname".toUpperCase()
                map.put("code", rs.getString("code"));// name
                map.put("workflow", toWorkflow(rs));
                processName.add(map);
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return processName;

    }

    public Workflow toWorkflow(ResultSet rs) throws Exception {
        Clob clob = null;
        Workflow workflow = new Workflow();
        clob = rs.getClob("PROCESSDEFINE");
        String tempstr = StringUtil.clobToString(clob);
        if (!"".equals(tempstr)) {
            Document tempDoc = StringUtil.stringToDoc(tempstr);// 类型转换
            workflow.toWorkflow(tempDoc.getRootElement());
        }
        return workflow;
    }
    //鹿鹏：打开时，如果节点中包含子流程，就会调用此方法获取子流程的参数变量
    public Object[] getSubProParams(Object[] arrTemp){
        Object[] wfParams = null;
        String code = (String) arrTemp[0];
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        boolean b = false;
        try {
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByCode(context));
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            Clob clob = null;
            Workflow workflow = new Workflow();
            while (rs.next()) {                
                clob = rs.getClob("PROCESSDEFINE");
                String tempstr = StringUtil.clobToString(clob);
                if (!"".equals(tempstr)) {
                    Document tempDoc = StringUtil.stringToDoc(tempstr);// 类型转换
                    workflow.toWorkflow(tempDoc.getRootElement());
                }
            }
            if(workflow!=null){
                wfParams = workflow.getWfParams();
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return wfParams;
    }

    public boolean isWorkflowExistByCode(Object[] arrTemp) {
        String code = (String) arrTemp[0];
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        boolean b = false;
        try {
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByCode(context));
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                b = true;
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return b;
    }

    public boolean isWorkflowExistByName(Object[] arrTemp) {
        Workflow workflow = (Workflow) arrTemp[0];
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        boolean b = false;
        try {
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByName(context));
            ps.setString(1, workflow.getActivityName());
            ps.setString(2, workflow.getName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                b = true;
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return b;
    }

    public Workflow isWorkflowExistByNameAndCode(Object[] arrTemp) {
        Workflow workflow = (Workflow) arrTemp[0];
        FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
        Connection conn = getConnection(context.getConParam());
        PreparedStatement ps = null;
        boolean b = false;
        try {
            ps = conn.prepareStatement(SQLProvider.selectWorkflowByNameAndCode(context));
            ps.setString(1, workflow.getActivityName());
            ps.setString(2, workflow.getName());
            // ps.setString(1, workflow.getName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                b = true;
                workflow.setName(rs.getString("CODE"));
            }
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(conn);
        } catch (Exception e) {
            closePrepareStatement(ps);
            closeConnection(conn);
            e.printStackTrace();
        }
        return b ? workflow : null;
    }

    public Workflow importWorkflow(Map param) throws Exception {
        if (param == null || param.size() < 1)
            throw new Exception("null parameters");
        String xml = (String) param.get("design");
        FlowDesignerContext context = (FlowDesignerContext) param.get("context");
        if (xml == null || context == null || "".equals(xml))
            throw new Exception("null parameters");
        Workflow workflow = new Workflow();
        try {
            Document tempDoc = StringUtil.stringToDoc(xml);
            workflow.toWorkflow(tempDoc.getRootElement());
        } catch (Exception e) {
            throw e;
        }
        Connection conn = null;
        List list = null;
        try {
            conn = getConnection(context.getConParam());
            list = selectWorkflowByName(workflow.getActivityName(), context, conn);
            closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection(conn);
            throw e;
        }
        Workflow result = null;
        if (list != null && list.size() > 0) {
            Map map = (Map) list.get(0);
            int version = ((Integer) map.get("version")).intValue();
            if (version == 0)
                workflow.setId((String) map.get("id"));
            workflow.setName((String) map.get("code"));
            Object[] obs = { workflow, context };
            result = updateWorkflow(obs);

        } else {
            workflow.setName(CreateRandomId.createPD());
            workflow.setId(CreateRandomId.createId_Thirty());
            Object[] obs = { workflow, context };
            result = insertWorkflow(obs);

        }
        return result;

    }

    public void importWorkflow(List flows) throws Exception {
        if (flows == null || flows.size() < 1)
            throw new Exception("null parameters");
        Connection conn = null;
        for (int i = 0; i < flows.size(); i++) {
            Map params = (Map) flows.get(i);
            if (params == null)
                continue;
            Workflow flow = (Workflow) params.get("design");
            FlowDesignerContext context = (FlowDesignerContext) params.get("context");
            if (flow == null || context == null)
                continue;
            List list = null;
            try {
                if (conn == null || conn.isClosed()) {
                    conn = getConnection(context.getConParam());
                    conn.setAutoCommit(false);
                }
                list = selectWorkflowByName(flow.getActivityName(), context, conn);
                closeConnection(conn);
            } catch (Exception e) {
                closeConnection(conn);
                e.printStackTrace();
                throw e;
            }
            if (list != null && list.size() > 0) {
                Map map = (Map) list.get(0);
                int version = ((Integer) map.get("vsrsion")).intValue();
                if (version == 0) {
                    flow.setName((String) map.get("code"));
                    Object[] obs = { flow, context };
                    updateWorkflow(obs);
                } else {

                }
            } else {
                flow.setName(CreateRandomId.createPD());
                flow.setId(CreateRandomId.createId_Thirty());
                Object[] obs = { flow, context };
                insertWorkflow(obs);
            }
        }
        conn.commit();
        closeConnection(conn);
    }

    private List selectWorkflowByName(String flowName, FlowDesignerContext context, Connection conn) throws Exception {
        PreparedStatement ps = conn.prepareStatement(SQLProvider.selectWorkflowByNameOnly(context));
        ps.setString(1, flowName);
        ResultSet rs = ps.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            Map map = new HashMap();
            map.put("code", rs.getString("CODE"));
            map.put("id", rs.getString("ID"));
            map.put("status", rs.getString("PDSTATUS"));
            map.put("version", new Integer(rs.getInt("PDVERSION")));
            list.add(map);
        }
        closePrepareStatement(ps);
        closeResultSet(rs);
        return list;
    }

    private void updateVariableStatus(Workflow workflow, Connection conn) throws Exception {
        if (workflow.getWfParams() != null && workflow.getWfParams().length > 0 && conn != null && !conn.isClosed()) {
            String sql = "update "+ConstValue.JBPM4_GLOBAL_PARAM+" set status=1 where code in ";
            Map param = null;
            String codes = "";
            for (int i = 0; i < workflow.getWfParams().length; i++) {
                param = (Map) workflow.getWfParams()[i];
                if (i == 0)
                    codes = "'" + param.get("code") + "'";
                else
                    codes = codes + ",'" + param.get("code") + "'";
            }
            if ("".equals(codes))
                return;
            sql = sql + "(" + codes + ")";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();
        }
    }

    private void updateMessageTemplateStatus(Workflow workflow, Connection conn) throws Exception {
        if (workflow.getNodes() != null && workflow.getNodes().length > 0 && conn != null && !conn.isClosed()) {
            List list = new ArrayList();
            Node node = null;
            Task task = null;
            for (int j = 0; j < workflow.getNodes().length; j++) {
                node = (Node) workflow.getNodes()[j];
                if (node.getTasks() != null && node.getTasks().length > 0)
                    for (int k = 0; k < node.getTasks().length; k++) {
                        task = (Task) node.getTasks()[k];
                        if (task.getHastenTemplate() != null)
                            list.add(task.getHastenTemplate());
                    }
            }
            if (list != null && list.size() > 0) {
                String sql = "update "+ConstValue.SYS_MESSAGE_TEMPLATE+" set status=1 where code in ";
                String codes = "";
                HastenTemplate template = null;
                for (int i = 0; i < list.size(); i++) {
                    template = (HastenTemplate) list.get(i);
                    if ("".equals(template.getHastenTemplateCode())) {
                        continue;
                    }
                    codes += "'" + template.getHastenTemplateCode() + "',";
                }
                if ("".equals(codes))
                    return;
                sql = sql + "(" + codes.substring(0, codes.length() - 1) + ")";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
                ps.close();
            }
        }
    }
}