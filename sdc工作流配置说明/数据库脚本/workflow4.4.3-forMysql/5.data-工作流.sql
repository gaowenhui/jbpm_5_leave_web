
/* ϵͳ��� */
insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000027', '�¼�����', 'EV_TYPE', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000028', 'ģ������', 'MU_TYPE', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000029', '�����������', 'WF_TASK', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference (ID, NAME, CODE, TYPE, FLAG, STATUS, DESCRIPTION, COMMITERID, MODIFIERID, MODTIME, VERSION, ISINHERITED)
values ('1000030', '��������', 'variable', '1', '1', '1', '', '0001', '0001', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), 1, '0');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001251339507832', '0002', '1', '0002', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf004', '���ɽڵ���ⲿ������', '', '1000028', '', '���ɽڵ���ⲿ������', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961743', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf001', '������URL', '', '1000028', '', '������URL', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961744', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf002', '��ʱ��', '', '1000028', '', '��ʱ��', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260779487751', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'wf003', '������', '', '1000028', '', '������', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001263540942969', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, '1', 'ͬ��', '', '1000029', '', 'ͬ��', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001264670408766', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, '3', '�ܾ�', '', '1000029', '', '�ܾ�', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001263540942971', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, '2', '��Ȩ', '', '1000029', '', '��Ȩ', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961741', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'start', '�����¼�', '', '1000027', '', '�����¼�', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('00000000000000000001260321961742', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'end', '�뿪�¼�', '', '1000027', '', '�뿪�¼�', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129159', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'String', '�ַ���(String)', '', '1000030', '', 'String', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129161', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Float', '�����ȸ���(Float)', '', '1000030', '', 'Float', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129162', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Double', '˫���ȸ���(Double)', '', '1000030', '', 'Double', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129164', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Long', '������(Long)', '', '1000030', '', 'Long', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129157', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Date', '����(Date)', '', '1000030', '', 'Date', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129158', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Boolean', '������(Boolean)', '', '1000030', '', 'Boolean', '', '');

insert into sys_reference_value (ID, COMMITERID, FLAG, MODIFIERID, MODTIME, STATUS, VERSION, CODE, DESCRIPTION, INNERCODE, REFERENCEID, CURRENTVALUE, LABLENAME, PARENTID, REFERENCECODE)
values ('1270704129160', '0000', '1', '0000', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '1', null, 'Integer', '����(Integer)', '', '1000030', '', 'Integer', '', '');


/* ȫ�ֱ��� */
insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114053', 'money', '���', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114056', 'businessRecordId', '���ݱ�ʶ', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114057', 'businessName', 'ҵ������', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114058', 'businessSN', '���ݱ��', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114054', 'businessType', 'ҵ������', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001285821114055', 'submituser', '�ύ��', '', '1', 'String');

insert into jbpm4_global_param (ID, CODE, NAME, DES, STATUS, VARIABLE_TYPE)
values ('00000000000000000001291880568838', 'businessUrl', 'ҵ��URL', '', '1', 'String');

/* �ʼ����� */
insert into sys_message_sender_implement (ID, NAME, CLASS, FLAG, STATUS, MODTIME, MODIFIERID, COMMITERID)
values ('00000000000000000001280997671618', '�ʼ�', 'com.toft.widgets.sys.workflow.message.messagesender.MessageSenderJavaMailImpl', '1', '1', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '0000', '0000');

insert into sys_message_template (ID, CODE, NAME, TITLE, TEXT, FLAG, STATUS, MODTIME, MODIFIERID, COMMITERID)
values ('1', '001', 'ģ��1', '������', '���յ��ݺ�:{businessSN},ҵ������:{businessName};    ���ݾݽ��:��{money};������������!', '1', '1', str_to_date('12-03-2010 11:22:51', '%d-%m-%Y %H:%i:%s'), '0001', '1');

/* ��̬��Ϣ ������ʵ���� */
insert into sys_dynamic_message_provider (ID, PROVIDER_CLASS, NAME, DESCRIPTION)
values ('002', 'com.toft.widgets.workflow.web.message.DynamicMessageProviderImpl', '״̬��Ϣʵ����(������)', '������ʹ��');

/* ��̬��Ϣ(������������) */
insert into sys_dynamic_message (ID, TITLE_MODEL, TEXT_MODEL, SQL, NAME, URL_MODEL, PROVIDER_CLASS_ID)
values ('1264847115441', '��������(To-do Task)', '���յ����ݺ�:{businessSN},��������:{agencyName},ҵ������:{businessName};  ���ݽ��:��{money};������������!','select w.businessid, w.AGENCY_NAME, w.BUSINESSNAME from view_jbpm_query_waiting w where w.actor =''{loginName}''', '��������(To-do Task)', '/workflow/toDoTask.do', '002');

/* 2012/08/22 by chenshuqing sdc��������   */
/* ɾ���û�����λ��ְ�𡢸�λ��У�飨ȷ�������߹������̹ҽ�����������*/
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000386683381531079', '/sys/user.ajax', '�û�ɾ��', null);
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000897982394010348', '/sys/agency.ajax', '��λɾ��', null);
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000386683381531078', '/sys/duty.ajax', 'ְ��ɾ��', null);
  insert into SYS_TABLE_OPDATA (ID, OPERATE_URL, NAME, EXT1)
  values ('00000000000000000669484993325065', '/sys/setAgency.ajax', '��λɾ��', null);
  
  
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000897982394010349', '/sys/agency.ajax', 'com.toft.widgets.workflow.web.ext.ArticulatedProcessRelevance', '��λɾ��');
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000669484993325064', '/sys/duty.ajax', 'com.toft.widgets.workflow.web.ext.ParticipantRuleRelevance', 'ְ��ɾ��');
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000669484993325063', '/sys/user.ajax', 'com.toft.widgets.workflow.web.ext.ParticipantRuleRelevance', '�û�ɾ��');
  insert into SYS_TABLE_RELEVANCE (ID, OPERATE_URL, OPERATE_CLASS, NAME)
  values ('00000000000000000669484993325066', '/sys/setAgency.ajax', 'com.toft.widgets.workflow.web.ext.ParticipantRuleRelevance', '��λɾ��');
  
  commit;