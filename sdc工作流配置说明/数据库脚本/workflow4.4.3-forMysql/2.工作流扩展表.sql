
/* DROP SQL */
drop table JBPM4_BUSINESS cascade;
drop table JBPM4_GLOBAL_PARAM cascade;
drop table JBPM4_DEPLOY cascade;
drop table WORKFLOW_ARTICULATED_PROCESS cascade;
drop table WORKFLOW_AUTHORIZED_MANAGEMENT cascade;
drop table WORKFLOW_BEHAVIOR_MANAGEMENT cascade;



/* ��� JBPM4_BUSINESS */
create table JBPM4_BUSINESS
(
  ID                  VARCHAR(32) not null ,
  NAME                VARCHAR(255)         ,
  CODE                VARCHAR(50)          ,
  BUSI_DESC           VARCHAR(255)         ,
  OPERANT             VARCHAR(50)          comment '��action_config�����õ�action·��������ֵ��Ч',
  QUERY               VARCHAR(50)          comment '��xml���õ�action·��',
  BUSINESS_CLASS_NAME VARCHAR(255)
)
;
alter table JBPM4_BUSINESS
  add constraint PK_JBPM4_BUSINESS primary key (ID);

  
/* ��� JBPM4_GLOBAL_PARAM */
create table JBPM4_GLOBAL_PARAM
(
  ID            VARCHAR(32) not null  comment 'id',
  CODE          VARCHAR(32)           comment '����Ӣ������',
  NAME          VARCHAR(50)           comment '������������',
  DES           VARCHAR(256)          comment '��ע',
  STATUS        CHAR(1)            comment '����ɾ����ʶ(0,����ɾ����1������ɾ��)',
  VARIABLE_TYPE VARCHAR(32)           comment '��������'
)
;
alter table JBPM4_GLOBAL_PARAM comment '������ȫ�ֲ�������';
alter table JBPM4_GLOBAL_PARAM
  add constraint PK_GLOBAL_PARAM primary key (ID);
  

/* ��� JBPM4_DEPLOY */
create table JBPM4_DEPLOY
(
  ID            VARCHAR(32) not null  comment 'id',
  NAME          VARCHAR(255)          comment '��������',
  CODE          VARCHAR(50)           comment '���̱��',
  PDSTATUS      CHAR(1)               comment '����״̬��0:δ����;1:�ѷ���;2:�޸�δ����',
  PROCESSDEFINE LONGTEXT              comment '���̶��������',
  PDNAME        VARCHAR(30)           comment '��������XML������',
  PDVERSION     INTEGER               comment '���̶���İ汾',
  PDID          VARCHAR(255)          comment '���̶���ID',
  FLAG          CHAR(1)               comment '��ͣ��־��0:ֹͣ;1:����',
  STATUS        CHAR(1)               comment '�߼�ɾ����־��0:�߼�ɾ��;1:δɾ��',
  MODIFIERID    VARCHAR(32)           comment '�༭��ID',
  PROCESSTYPE   VARCHAR(20)           comment '�������͡�������:mainprocess;������:subprocess',
  AGENCYID      VARCHAR(32)           ,
  DEPARTMENTID  VARCHAR(32)           
)
;
alter table JBPM4_DEPLOY comment '�������ʱ����������';
alter table JBPM4_DEPLOY
  add constraint pk_jbpm4_deploy_id primary key (ID);
alter table JBPM4_DEPLOY
  add constraint check_id check ("ID" IS NOT NULL);



/* ��� WORKFLOW_ARTICULATED_PROCESS */
create table WORKFLOW_ARTICULATED_PROCESS
(
  ID                VARCHAR(32) not null  comment 'ID��Ψһ��ʶ',
  PROCESSDEFINITION VARCHAR(32) not null  comment '����ID',
  AGENCY            VARCHAR(32) not null  comment '��λID',
  BUSINESS          VARCHAR(32) not null  comment 'ҵ������ID',
  BUSINESSNAME      VARCHAR(255)          comment 'ҵ����������',
  OPERATOR          VARCHAR(32)           comment '������',
  OPERANT_TIME      DATETIME not null     comment '����ʱ��',
  FLAG              CHAR(1) not null      comment '״̬��ʶ',
  STATUS            CHAR(1) not null      comment '�߼�ɾ��ʶ',
  MODTIME           DATETIME not null     comment '�޸�ʱ��',
  COMMITERID        VARCHAR(32) not null  comment '������ID',
  MODIFIERID        VARCHAR(32) not null  comment '�޸���ID'
)
;
alter table WORKFLOW_ARTICULATED_PROCESS comment '���������̹ҽӱ�';


/* ��� WORKFLOW_AUTHORIZED_MANAGEMENT */
create table WORKFLOW_AUTHORIZED_MANAGEMENT
(
  ID               VARCHAR(32) not null  comment 'id',
  AUTHORIZER       VARCHAR(32) not null  comment '��Ȩ��ID',
  BUSINESSTYPE     VARCHAR(42) not null  comment 'ҵ������',
  AUTHORIZEDPERSON VARCHAR(32) not null  comment '����Ȩ��ID',
  AUTHORIZEDSTATE  CHAR(1) not null      comment '��Ȩ״̬0,δ��Ч,1,��Ч,2,����ʧЧ',
  STARTTIME        DATETIME not null     comment '��ʼʱ��',
  ENDTIME          DATETIME not null     comment '����ʱ��',
  FLAG             CHAR(1) not null      comment '��ͣ��ʶ',
  STATUS           CHAR(1) not null      comment '״̬��ʶ',
  MODTIME          DATETIME not null     comment '�޸�ʱ��',
  COMMITERID       VARCHAR(32) not null  comment '������ID',
  MODIFIERID       VARCHAR(32) not null  comment '�޸���ID'
)
;
alter table WORKFLOW_AUTHORIZED_MANAGEMENT comment '��Ȩ�����';
alter table WORKFLOW_AUTHORIZED_MANAGEMENT
  add constraint PK_AUTHORIZED_MANAGEMENT primary key (ID);


/* ��� WORKFLOW_BEHAVIOR_MANAGEMENT */
create table WORKFLOW_BEHAVIOR_MANAGEMENT
(
  ID        VARCHAR(32) not null   comment 'ID',
  NAME      VARCHAR(50) not null   comment '��Ϊ��',
  CODE      VARCHAR(32) not null   comment '��Ϊ���',
  CLASSPATH VARCHAR(100) not null  comment 'ʵ����򷽷���',
  EV_TYPE   VARCHAR(32)            comment '�¼�����'
)
;
alter table WORKFLOW_BEHAVIOR_MANAGEMENT comment '��Ϊ����';
alter table WORKFLOW_BEHAVIOR_MANAGEMENT
  add constraint PK_BEHAVIOR_MANAGEMENT primary key (ID);

  
/* ��� JBPM4_EXT_RULE �����߹���� */
create table JBPM4_EXT_RULE
(
  ID            VARCHAR(32) not null    comment 'Ψһ��ʶ',
  CODE          VARCHAR(32)             comment '��������߹�����',
  NAME          VARCHAR(50)             comment '��������߹�������',
  FLAG          CHAR(1) not null        comment '״̬��ʶ',
  STATUS        CHAR(1)                 comment '�Ƿ�ɾ��',
  AGENCY_ID     VARCHAR(32)             comment '����ID',
  DEPARTMENT_ID VARCHAR(32)             comment '����ID',
  CLASS_NAME    VARCHAR(255)            comment '�����߹���ʵ����',
  IS_EXCLUDE    VARCHAR(1) default '0'  comment '�Ƿ��ų��ύ��'
)
;
alter table JBPM4_EXT_RULE comment  '�����߹����';
alter table JBPM4_EXT_RULE add primary key (ID);


/* ��� JBPM4_EXT_RULE_DETAIL �����߹�����ϸ��  */
create table JBPM4_EXT_RULE_DETAIL
(
  ID               VARCHAR(32) not null  comment 'Ψһ��ʶ',
  PARTICIPANT_TYPE CHAR(1)               comment '��������:0,�û�;1,����;2,ְ��;3,����4.��λ',
  PARTICIPANT_ID   VARCHAR(32)           comment '������ID',
  RULE_ID          VARCHAR(32)           comment '�����߹���ID',
  IS_DYNAMIC       CHAR(1)               comment '�Ƿ�̬����:0,��;1,��',
  DYNAMIC_TYPE     VARCHAR(40)           comment '��̬����:fatherAgency,�ϼ�����'
)
;
alter table JBPM4_EXT_RULE_DETAIL comment  '�����߹�����ϸ��';
alter table JBPM4_EXT_RULE_DETAIL add primary key (ID);

