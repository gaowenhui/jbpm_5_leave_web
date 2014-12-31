
/* 系统码表 */
insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000027', '事件类型', 'EV_TYPE', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000028', '模块类型', 'MU_TYPE', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000029', '任务意见类型', 'WF_TASK', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000030', '变量类型', 'variable', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001251339507832', '0002', '1', '0002', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf004', '自由节点的外部触发类', '', '1000028', '', '自由节点的外部触发类', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961743', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf001', '工作流URL', '', '1000028', '', '工作流URL', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961744', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf002', '超时类', '', '1000028', '', '超时类', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260779487751', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf003', '工作流', '', '1000028', '', '工作流', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001263540942969', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, '1', '同意', '', '1000029', '', '同意', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001264670408766', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, '3', '拒绝', '', '1000029', '', '拒绝', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001263540942971', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, '2', '弃权', '', '1000029', '', '弃权', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961741', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'start', '进入事件', '', '1000027', '', '进入事件', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961742', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'end', '离开事件', '', '1000027', '', '离开事件', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129159', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'String', '字符串(String)', '', '1000030', '', 'String', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129161', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Float', '单精度浮点(Float)', '', '1000030', '', 'Float', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129162', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Double', '双精度浮点(Double)', '', '1000030', '', 'Double', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129164', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Long', '长整型(Long)', '', '1000030', '', 'Long', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129157', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Date', '日期(Date)', '', '1000030', '', 'Date', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129158', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Boolean', '布尔型(Boolean)', '', '1000030', '', 'Boolean', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129160', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Integer', '整数(Integer)', '', '1000030', '', 'Integer', '', '');


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

/* 邮件功能 */
insert into sys_message_sender_implement (ID, NAME, CLASS, FLAG, STATUS, MODTIME, MODIFIERID, COMMITERID)
values ('00000000000000000001280997671618', '邮件', 'com.toft.widgets.sys.workflow.message.messagesender.MessageSenderJavaMailImpl', '1', '1', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '0000', '0000');

insert into sys_message_template (ID, CODE, NAME, TITLE, TEXT, FLAG, STATUS, MODTIME, MODIFIERID, COMMITERID)
values ('1', '001', '模板1', '请审批', '您收到据号:{businessSN},业务类型:{businessName};    单据据金额:￥{money};请您尽快审批!', '1', '1', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '0001', '1');

/* 动态消息 工作流实现类 */
insert into sys_dynamic_message_provider (ID, PROVIDER_CLASS, NAME, DESCRIPTION)
values ('002', 'com.toft.widgets.workflow.web.message.DynamicMessageProviderImpl', '状态消息实现类(工作流)', '工作流使用');

/* 动态消息(待办任务配置) */
insert into sys_dynamic_message (ID, TITLE_MODEL, TEXT_MODEL, SQL, NAME, URL_MODEL, PROVIDER_CLASS_ID)
values ('1264847115441', '待办任务(To-do Task)', '您收到单据号:{businessSN},机构名称:{agencyName},业务类型:{businessName};  单据金额:￥{money};请您尽快审批!','select w.businessid, w.AGENCY_NAME, w.BUSINESSNAME from view_jbpm_query_waiting w where w.actor =''{loginName}''', '待办任务(To-do Task)', '/workflow/toDoTask.do', '002');

/* 2012/08/22 by chenshuqing sdc关联设置   */
/* 删除用户、单位、职责、岗位的校验（确保参与者规则、流程挂接数据完整）*/
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000386683381531079', '/sys/user.ajax', '用户删除', null);
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000897982394010348', '/sys/agency.ajax', '单位删除', null);
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000386683381531078', '/sys/duty.ajax', '职责删除', null);
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000669484993325065', '/sys/setAgency.ajax', '岗位删除', null);
  
  
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000897982394010349', '/sys/agency.ajax', 'com.toft.widgets.workflow.web.ext.ArticulatedProcessRelevance', '单位删除');
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000669484993325064', '/sys/duty.ajax', 'com.toft.widgets.workflow.web.ext.ParticipantRuleRelevance', '职责删除');
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000669484993325063', '/sys/user.ajax', 'com.toft.widgets.workflow.web.ext.ParticipantRuleRelevance', '用户删除');
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000669484993325066', '/sys/setAgency.ajax', 'com.toft.widgets.workflow.web.ext.ParticipantRuleRelevance', '岗位删除');
  
  commit;