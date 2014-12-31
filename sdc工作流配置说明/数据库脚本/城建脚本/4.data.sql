
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


/* demo���ݿ��SYS_EMPLOYEE��SYS_WORKFLOW_TABLE */
create table SYS_WORKFLOW_TABLE
(
  ID                   VARCHAR(32) not null  comment 'Ψһ��ʶ',
  FLAG                 VARCHAR(1) not null   comment '��ͣ��ʶ',
  STATUS               VARCHAR(1) not null   comment '�Ƿ�ɾ��',
  MODTIME              datetime not null     comment '����ʱ��',
  MODIFIERID           VARCHAR(32)           comment '�ύ��ID',
  COMMITERID           VARCHAR(32) not null  comment '������ID',
  EXPENSEACCOUNTNUMBER VARCHAR(32) not null  comment '���ݱ�� ',
  EXPENSEACCOUNTDATE   VARCHAR(20) not null  comment '��������',
  EXPENSEACCOUNTAMOUNT INTEGER               comment '���ݽ��',
  DEPARTMENT           VARCHAR(50)           comment 'Ա��������λ',
  INPUTPERSON          VARCHAR(50) not null  comment '¼����',
  REIMBURSEMENTREASON  VARCHAR(100) not null comment '����',
  EXPENSEACCOUNTSTATUE VARCHAR(1) not null   comment '����״̬ 0,δ�ύ:1,������:2,��������:3,�쳣��ֹ:4,�˻�',
  ASSIGNEE             VARCHAR(50)           comment '������',
  CREATETIME           VARCHAR(20)           comment '�ύʱ��',
  BUSINESSTYPEID       VARCHAR(32) not null  comment 'ҵ������ID',
  PROCESSNAMEID        VARCHAR(32) not null  comment '��������ID',
  PROCESSINSTANCESID   VARCHAR(32)           comment '����ʵ��ID',
  PROCESSID            VARCHAR(32) not null  comment '����ID',
  ANAME1               VARCHAR(50)           comment '��չ������1',
  AVALUE1              VARCHAR(50)           comment '��չ����ֵ1',
  ANAME2               VARCHAR(50)           comment '��չ������2',
  AVALUE2              VARCHAR(50)           comment '��չ����ֵ2',
  AVALUE3              VARCHAR(50)           comment '��չ����ֵ3',
  AVALUE4              VARCHAR(50)           comment '��չ����ֵ4',
  ANAME3               VARCHAR(50)           comment '��չ������3',
  ANAME4               VARCHAR(50)           comment '��չ������4',
  MODEL                VARCHAR(50)           comment '�ͺ�',
  AMOUNT               VARCHAR(50)           comment '����',
  MODIFYNUMBER         INTEGER               comment '�޸Ĵ���',
  MODIFYDEPARTMENT     VARCHAR(50)           comment '�ύ�˲���'
)
;
alter table SYS_WORKFLOW_TABLE comment 'ҵ���---DEMO';

/* ҵ�����ͱ� jbpm4_business */
insert into jbpm4_business (ID, NAME, CODE, BUSI_DESC, OPERANT, QUERY, BUSINESS_CLASS_NAME)
values ('00000000000000000001280737111848', '�ɹ�����', '101', '�ɹ�����', '', '/workflow/queryDetailbuynote.do', '');

insert into jbpm4_business (ID, NAME, CODE, BUSI_DESC, OPERANT, QUERY, BUSINESS_CLASS_NAME)
values ('00000000000000000001280796338783', '��ٲ���', '202', '��ٲ���', '', '/workflow/queryDetailleave.do', '');

insert into jbpm4_business (ID, NAME, CODE, BUSI_DESC, OPERANT, QUERY, BUSINESS_CLASS_NAME)
values ('00000000000000000001280796338784', '��������', '303', '��������', '', '/workflow/queryDetailexpens.do', '');


/* �����߹��� JBPM4_EXT_RULE,JBPM4_EXT_RULE_DETAIL */
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69352580001', '0001', '���ž���', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69ba0980003', '0002', '���ܸ���', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69cc0f30005', '0003', '��ܸ���', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69d86820007', '0004', '������Ա', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69dd9c60009', '0005', '�����ܼ�', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b34a6924a0134a69e2c15000b', '0006', '�ܾ���', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b357eff5601357f0549080011', '0007', '�ͻ�����', '1', '1', null, null, null);
insert into JBPM4_EXT_RULE (ID, CODE, NAME, FLAG, STATUS, AGENCY_ID, DEPARTMENT_ID, CLASS_NAME)
values ('8a98c13b35c28c560135c2c447c40010', '0008', '����������', '1', '1', null, null, 'com.toft.widgets.workflow.participantrule.impl.MyParticipantRuleImpl');

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