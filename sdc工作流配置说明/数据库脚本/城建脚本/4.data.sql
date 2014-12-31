
/* 全局变量 */
insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114053', 'money', '金额', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114056', 'businessRecordId', '单据标识', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114057', 'businessName', '业务名称', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114058', 'businessSN', '单据编号', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114054', 'businessType', '业务类型', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114055', 'submituser', '提交人', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001291880568838', 'businessUrl', '业务URL', '', '1', 'String');


/* demo数据库表：SYS_EMPLOYEE、SYS_WORKFLOW_TABLE */
create table SYS_WORKFLOW_TABLE
(
  ID                   VARCHAR(32) not null  comment '唯一标识',
  FLAG                 VARCHAR(1) not null   comment '启停标识',
  STATUS               VARCHAR(1) not null   comment '是否删除',
  MODTIME              datetime not null     comment '操作时间',
  MODIFIERID           VARCHAR(32)           comment '提交人ID',
  COMMITERID           VARCHAR(32) not null  comment '创建人ID',
  EXPENSEACCOUNTNUMBER VARCHAR(32) not null  comment '单据编号 ',
  EXPENSEACCOUNTDATE   VARCHAR(20) not null  comment '申请日期',
  EXPENSEACCOUNTAMOUNT INTEGER               comment '单据金额',
  DEPARTMENT           VARCHAR(50)           comment '员工所属单位',
  INPUTPERSON          VARCHAR(50) not null  comment '录入人',
  REIMBURSEMENTREASON  VARCHAR(100) not null comment '事由',
  EXPENSEACCOUNTSTATUE VARCHAR(1) not null   comment '单据状态 0,未提交:1,审批中:2,审批结束:3,异常终止:4,退回',
  ASSIGNEE             VARCHAR(50)           comment '负责人',
  CREATETIME           VARCHAR(20)           comment '提交时间',
  BUSINESSTYPEID       VARCHAR(32) not null  comment '业务类型ID',
  PROCESSNAMEID        VARCHAR(32) not null  comment '流程名称ID',
  PROCESSINSTANCESID   VARCHAR(32)           comment '流程实例ID',
  PROCESSID            VARCHAR(32) not null  comment '流程ID',
  ANAME1               VARCHAR(50)           comment '扩展属性名1',
  AVALUE1              VARCHAR(50)           comment '扩展属性值1',
  ANAME2               VARCHAR(50)           comment '扩展属性名2',
  AVALUE2              VARCHAR(50)           comment '扩展属性值2',
  AVALUE3              VARCHAR(50)           comment '扩展属性值3',
  AVALUE4              VARCHAR(50)           comment '扩展属性值4',
  ANAME3               VARCHAR(50)           comment '扩展属性名3',
  ANAME4               VARCHAR(50)           comment '扩展属性名4',
  MODEL                VARCHAR(50)           comment '型号',
  AMOUNT               VARCHAR(50)           comment '数量',
  MODIFYNUMBER         INTEGER               comment '修改次数',
  MODIFYDEPARTMENT     VARCHAR(50)           comment '提交人部门'
)
;
alter table SYS_WORKFLOW_TABLE comment '业务表---DEMO';

/* 业务类型表 jbpm4_business */
insert into jbpm4_business (ID, NAME, CODE, BUSI_DESC, OPERANT, QUERY, BUSINESS_CLASS_NAME)
values ('00000000000000000001280737111848', '采购测试', '101', '采购测试', '', '/workflow/queryDetailbuynote.do', '');

insert into jbpm4_business (ID, NAME, CODE, BUSI_DESC, OPERANT, QUERY, BUSINESS_CLASS_NAME)
values ('00000000000000000001280796338783', '请假测试', '202', '请假测试', '', '/workflow/queryDetailleave.do', '');

insert into jbpm4_business (ID, NAME, CODE, BUSI_DESC, OPERANT, QUERY, BUSINESS_CLASS_NAME)
values ('00000000000000000001280796338784', '报销测试', '303', '报销测试', '', '/workflow/queryDetailexpens.do', '');


/* 参与者规则 JBPM4_EXT_RULE,JBPM4_EXT_RULE_DETAIL */
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69352580001', '0001', '部门经理', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69ba0980003', '0002', '主管副总', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69cc0f30005', '0003', '监管副总', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69d86820007', '0004', '财务人员', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69dd9c60009', '0005', '财务总监', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69e2c15000b', '0006', '总经理', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b357eff5601357f0549080011', '0007', '客户经理', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b35c28c560135c2c447c40010', '0008', '流程启动者', '1', '1', null, null, 'com.toft.widgets.workflow.participantrule.impl.MyParticipantRuleImpl');

insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b34a6924a0134a69b18800002', '2', '1318470302167', '8a98c13b34a6924a0134a69352580001', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b34a6924a0134a69c18d60004', '2', '1318470302168', '8a98c13b34a6924a0134a69ba0980003', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b34a6924a0134a69d22e40006', '2', '1318470302169', '8a98c13b34a6924a0134a69cc0f30005', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b34a6924a0134a69daae90008', '2', '1318470302170', '8a98c13b34a6924a0134a69d86820007', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b34a6924a0134a69dfc0b000a', '2', '1318470302171', '8a98c13b34a6924a0134a69dd9c60009', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b34a6924a0134a69f0faa000c', '2', '1318470302172', '8a98c13b34a6924a0134a69e2c15000b', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b357eff5601357f0574c00012', '2', '1329275557501', '8a98c13b357eff5601357f0549080011', '1', 'AGENCY');
insert into JBPM4_EXT_RULE_DETAIL (ID, PARTICIPANT_TYPE, PARTICIPANT_ID, RULE_ID, IS_DYNAMIC, DYNAMIC_TYPE)
values ('8a98c13b35c28c560135c2c467f50011', '0', '006', '8a98c13b35c28c560135c2c447c40010', '9', '9');

commit;