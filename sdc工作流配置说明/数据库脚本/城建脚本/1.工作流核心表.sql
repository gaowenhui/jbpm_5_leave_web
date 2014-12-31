
/* DROP SQL */
drop table JBPM4_DEPLOYMENT cascade;
drop table JBPM4_DEPLOYPROP cascade;
drop table JBPM4_EXECUTION cascade;
drop table JBPM4_HIST_PROCINST cascade;
drop table JBPM4_HIST_TASK cascade;
drop table JBPM4_HIST_ACTINST cascade;
drop table JBPM4_HIST_VAR cascade;
drop table JBPM4_HIST_DETAIL cascade;
drop table JBPM4_ID_GROUP cascade;
drop table JBPM4_ID_GROUP cascade;
drop table JBPM4_ID_USER cascade;
drop table JBPM4_ID_MEMBERSHIP cascade;
drop table JBPM4_JOB cascade;
drop table JBPM4_SWIMLANE cascade;
drop table JBPM4_TASK cascade;
drop table JBPM4_PARTICIPATION cascade;
drop table JBPM4_VARIABLE cascade;
drop table JBPM4_PROPERTY cascade;
drop table JBPM4_LOB cascade;


/* ��� JBPM4_DEPLOYMENT */
create table JBPM4_DEPLOYMENT
(
  DBID_      BIGINT not null  comment '�����ʶ',
  NAME_      LONGTEXT         comment '��������',
  TIMESTAMP_ BIGINT           comment '����ʱ��',
  STATE_     VARCHAR(255)     comment '����״̬'
)
;
alter table JBPM4_DEPLOYMENT comment '�����������';
alter table JBPM4_DEPLOYMENT add primary key (DBID_);


/* ��� JBPM4_DEPLOYPROP */
create table JBPM4_DEPLOYPROP
(
  DBID_       BIGINT not null comment '��¼��ʶ',
  DEPLOYMENT_ BIGINT          comment '�����ʶ',
  OBJNAME_    VARCHAR(255)    comment '���̶�������',
  KEY_        VARCHAR(255)    comment '���̶�����������',
  STRINGVAL_  VARCHAR(255)    comment '���̶����������Ƶ�ֵ',
  LONGVAL_    BIGINT          comment '���̶����������Ƶ�ֵ'
)
;
alter table JBPM4_DEPLOYPROP comment '���̶������Ա�';
alter table JBPM4_DEPLOYPROP add primary key (DBID_);
alter table JBPM4_DEPLOYPROP
  add constraint FK_DEPLPROP_DEPL foreign key (DEPLOYMENT_)
  references JBPM4_DEPLOYMENT (DBID_);


/* ��� JBPM4_EXECUTION */
create table JBPM4_EXECUTION
(
  DBID_          BIGINT not null         comment '��¼��ʶ',
  CLASS_         VARCHAR(255) not null   comment '�����ʶ',
  DBVERSION_     INTEGER not null        comment '��¼�汾��',
  ACTIVITYNAME_  VARCHAR(255)            comment '�ڵ�����',
  PROCDEFID_     VARCHAR(255)            comment '�����Ժ�����̶����ʶ',
  HASVARS_       BIT                     comment 'ӵ�б�����ʶ',
  NAME_          VARCHAR(255)            comment '��ѡ��ִ�У�����ʵ��������',
  KEY_           VARCHAR(255)            comment 'ִ�У�����ʵ����key',
  ID_            VARCHAR(255)            comment 'ִ�У�����ʵ����ID',
  STATE_         VARCHAR(255)            comment 'ִ�У�����ʵ����״̬',
  SUSPHISTSTATE_ VARCHAR(255)            comment '��ʷ����״̬',
  PRIORITY_      INTEGER                 comment 'ִ�У�����ʵ�������ȼ�',
  HISACTINST_    BIGINT                  comment '���ڻ�ڵ������',
  PARENT_        BIGINT                  comment '��ִ��',
  INSTANCE_      BIGINT                  comment '����ʵ��',
  SUPEREXEC_     BIGINT                  comment '������ʵ��',
  SUBPROCINST_   BIGINT                  comment '������ʵ��',
  PARENT_IDX_    INTEGER                 comment '��ǰִ��֮�����в���ִ�е�����'
)
;
alter table JBPM4_EXECUTION comment '����ʵ����';

alter table JBPM4_EXECUTION
  add primary key (DBID_);
alter table JBPM4_EXECUTION
  add unique (ID_);
alter table JBPM4_EXECUTION
  add constraint FK_EXEC_INSTANCE foreign key (INSTANCE_)
  references JBPM4_EXECUTION (DBID_);
alter table JBPM4_EXECUTION
  add constraint FK_EXEC_PARENT foreign key (PARENT_)
  references JBPM4_EXECUTION (DBID_);
alter table JBPM4_EXECUTION
  add constraint FK_EXEC_SUBPI foreign key (SUBPROCINST_)
  references JBPM4_EXECUTION (DBID_);
alter table JBPM4_EXECUTION
  add constraint FK_EXEC_SUPEREXEC foreign key (SUPEREXEC_)
  references JBPM4_EXECUTION (DBID_);



/* ��� JBPM4_HIST_PROCINST */
create table JBPM4_HIST_PROCINST
(
  DBID_        BIGINT not null     comment '��¼��ʶ',
  DBVERSION_   INTEGER not null    comment '��¼�汾��',
  ID_          VARCHAR(255)        comment '��ʷ����ִ��ʵ��Id',
  PROCDEFID_   VARCHAR(255)        comment '����ʵ��Id',
  KEY_         VARCHAR(255)        comment '��ʷ����ʵ��key',
  START_       DATETIME            comment '����ʵ����ʼ��ʱ��',
  END_         DATETIME            comment '����ʵ��������ʱ��',
  DURATION_    BIGINT              comment '����ʵ���������ķѵ�ʱ��',
  STATE_       VARCHAR(255)        comment '����ʵ��״̬',
  ENDACTIVITY_ VARCHAR(255)        comment '����ʵ�������ڵ������',
  NEXTIDX_     INTEGER       
)
;
alter table JBPM4_HIST_PROCINST comment '��������ʵ����ʷ��';
alter table JBPM4_HIST_PROCINST
  add primary key (DBID_);


/* ��� JBPM4_HIST_TASK */
create table JBPM4_HIST_TASK
(
  DBID_             BIGINT not null     comment '��¼��ʶ',
  DBVERSION_        INTEGER not null    comment '��¼�汾��',
  EXECUTION_        VARCHAR(255)        comment '��������Ӧ��ִ��ID',
  OUTCOME_          VARCHAR(255)        comment '���������',
  ASSIGNEE_         VARCHAR(255)        comment '��������',
  PRIORITY_         INTEGER             comment '���ȼ�',
  STATE_            VARCHAR(255)        comment '״̬',
  CREATE_           DATETIME            comment '����ʱ��',
  END_              DATETIME            comment '����ʱ��',
  DURATION_         BIGINT              comment '��������ʱ��',
  NEXTIDX_          INTEGER             ,
  URL_              VARCHAR(255)        comment '���ӵ�ҵ�����ݵ�URL',
  AGENTID_          VARCHAR(255)        comment '��������˵ı�ʶ',
  AGENCYID_         VARCHAR(255)        comment '������ʶ',
  ASSIGNRULE_       VARCHAR(255)        comment '��������߹���',
  HASTENTEMPLATE_   VARCHAR(255)        comment '��Ϣģ��Code',
  BUSINESSTYPE_     VARCHAR(255)        comment 'ҵ������',
  NAME_             VARCHAR(255)        comment '��ʷ���������',
  ACTIVITY_NAME_    VARCHAR(255)        comment '�ڵ����ʾ����',
  SUBMITACTIVITY_   VARCHAR(255)        comment '�ύ�ڵ��ʶ',
  PARENTID_         VARCHAR(255)        comment '�������ʶ',
  ABNORMAL_         VARCHAR(255)        comment '�����Ƿ�Ϊ��������',
  NEXTOPERATOR_     VARCHAR(255)        comment '��һ������Ĵ�����',
  SUPEREXECUTIONID_ VARCHAR(255)        comment '�����̵�ִ�б�ʶ',
  BUSINESSNAME_     VARCHAR(255)        comment 'ҵ����������',
  APPLICATIONUSER_  VARCHAR(255)        comment '�����ύ��',
  BUSINESSRECORDID_ VARCHAR(255)        comment 'ҵ�񵥾ݱ��',
  BUSINESSSN_       VARCHAR(255)        comment 'ҵ�����ͱ��',
  BUSINESSMONEY_    VARCHAR(255)        comment 'ҵ�񵥾ݽ��',
  EXEID_            VARCHAR(255)        comment '�����������̵�ִ�б�ʶ����Ҫ�����������н��б�ʶ',
  SUPERTASK_        BIGINT              comment '����ʷ����',
  DEPARTMENTID_     VARCHAR(255)        ,
  WORKFLOW_OBJECT_  LONGBLOB            comment '��չ����'
)
;
alter table JBPM4_HIST_TASK comment '��������ʵ����ʷ��';
alter table JBPM4_HIST_TASK
  add primary key (DBID_);
alter table JBPM4_HIST_TASK
  add constraint FK_HSUPERT_SUB foreign key (SUPERTASK_)
  references JBPM4_HIST_TASK (DBID_);



/* ��� JBPM4_HIST_ACTINST */
create table JBPM4_HIST_ACTINST
(
  DBID_          BIGINT not null         comment '��¼��ʶ',
  CLASS_         VARCHAR(255) not null   comment '�����ʶ',
  DBVERSION_     INTEGER not null        comment '��¼�汾��',
  HPROCI_        BIGINT                  comment '��ʷ����ʵ��������',
  TYPE_          VARCHAR(255)            comment '�ڵ�����',
  EXECUTION_     VARCHAR(255)            comment 'ִ��ID',
  ACTIVITY_NAME_ VARCHAR(255)            comment '�ڵ��ActivityName',
  START_         DATETIME                comment '�ڵ�Ŀ�ʼ��������ʱ��',
  END_           DATETIME                comment '�ڵ�Ľ���ʱ��',
  DURATION_      BIGINT                  comment '�ڵ�ӿ�ʼ��������ʱ��',
  TRANSITION_    VARCHAR(255)            comment '�ڵ�����',
  NEXTIDX_       INTEGER                 ,
  HTASK_         BIGINT                  comment '����Ӧ����ʷ����ڵ��ʶ'
)
;
alter table JBPM4_HIST_ACTINST comment '���̻(�ڵ�)ʵ����';
alter table JBPM4_HIST_ACTINST add primary key (DBID_);
alter table JBPM4_HIST_ACTINST
  add constraint FK_HACTI_HPROCI foreign key (HPROCI_)
  references JBPM4_HIST_PROCINST (DBID_);
alter table JBPM4_HIST_ACTINST
  add constraint FK_HTI_HTASK foreign key (HTASK_)
  references JBPM4_HIST_TASK (DBID_);



/* ��� JBPM4_HIST_VAR */
create table JBPM4_HIST_VAR
(
  DBID_        BIGINT not null   comment '��¼��ʶ',
  DBVERSION_   INTEGER not null  comment '��¼�汾��',
  PROCINSTID_  VARCHAR(255)      comment '����ʵ��ID',
  EXECUTIONID_ VARCHAR(255)      comment '����ִ��ID',
  VARNAME_     VARCHAR(255)      comment '��������',
  VALUE_       VARCHAR(255)      comment '����ֵ',
  HPROCI_      BIGINT            comment '��ʷ����ʵ��������',
  HTASK_       BIGINT            comment '��ʷ���������'
)
;
alter table JBPM4_HIST_VAR comment '���̱�����ʷ��';
alter table JBPM4_HIST_VAR
  add primary key (DBID_);
alter table JBPM4_HIST_VAR
  add constraint FK_HVAR_HPROCI foreign key (HPROCI_)
  references JBPM4_HIST_PROCINST (DBID_);
alter table JBPM4_HIST_VAR
  add constraint FK_HVAR_HTASK foreign key (HTASK_)
  references JBPM4_HIST_TASK (DBID_);


/* ��� JBPM4_HIST_DETAIL */
create table JBPM4_HIST_DETAIL
(
  DBID_       BIGINT not null        comment '��¼��ʶ',
  CLASS_      VARCHAR(255) not null  comment '�����ʶ',
  DBVERSION_  INTEGER not null       comment '��¼�汾��',
  USERID_     VARCHAR(255)           comment '�û���ʶ',
  TIME_       DATETIME               comment '��¼������ʱ��',
  HPROCI_     BIGINT                 comment '��ʷ����ʵ������',
  HPROCIIDX_  INTEGER                comment '��ʷ����ʵ������',
  HACTI_      BIGINT                 comment '��ʷ�ڵ������',
  HACTIIDX_   INTEGER                comment '��ʷ�ڵ�ʵ������',
  HTASK_      BIGINT                 comment '��ʷ���������',
  HTASKIDX_   INTEGER                comment '��ʷ����ʵ������',
  HVAR_       BIGINT                 comment '��ʷ����������',
  HVARIDX_    INTEGER                comment '��ʷ����ʵ������',
  MESSAGE_    LONGTEXT               comment '���������',
  OLD_INT_    INTEGER                comment '����HistoryPriorityUpdateImplԭ�е����ȼ�',
  NEW_INT_    INTEGER                comment '����HistoryPriorityUpdateImpl�µ����ȼ�',
  OLD_STR_    VARCHAR(255)           comment '����HistoryTaskAssignmentImpl����ԭ�в�����',
  NEW_STR_    VARCHAR(255)           comment '����HistoryTaskAssignmentImpl�����µĲ�����',
  OLD_TIME_   DATETIME               comment '����HistoryTaskDuedateUpdateImplԭ�е�����ʱ��',
  NEW_TIME_   DATETIME               comment '����HistoryTaskDuedateUpdateImpl���ڵ�֧��ʱ��',
  PARENT_     BIGINT                 ,
  PARENT_IDX_ INTEGER                 
)
;
alter table JBPM4_HIST_DETAIL comment '������ʷ��ϸ��Ϣ��';
alter table JBPM4_HIST_DETAIL
  add primary key (DBID_);
alter table JBPM4_HIST_DETAIL
  add constraint FK_HDETAIL_HACTI foreign key (HACTI_)
  references JBPM4_HIST_ACTINST (DBID_);
alter table JBPM4_HIST_DETAIL
  add constraint FK_HDETAIL_HPROCI foreign key (HPROCI_)
  references JBPM4_HIST_PROCINST (DBID_);
alter table JBPM4_HIST_DETAIL
  add constraint FK_HDETAIL_HTASK foreign key (HTASK_)
  references JBPM4_HIST_TASK (DBID_);
alter table JBPM4_HIST_DETAIL
  add constraint FK_HDETAIL_HVAR foreign key (HVAR_)
  references JBPM4_HIST_VAR (DBID_);
create index IDX_HDET_HVAR on JBPM4_HIST_DETAIL (HVAR_);
create index IDX_HDET_HTASK on JBPM4_HIST_DETAIL (HTASK_);
create index IDX_HSUPERT_SUB on JBPM4_HIST_TASK (SUPERTASK_);


/* ��� JBPM4_ID_GROUP */
create table JBPM4_ID_GROUP
(
  DBID_      BIGINT not null   comment '��¼��ʶ',
  DBVERSION_ INTEGER not null  comment '��¼�汾��',
  ID_        VARCHAR(255)      comment '��ID',
  NAME_      VARCHAR(255)      comment '������',
  TYPE_      VARCHAR(255)      comment '������',
  PARENT_    BIGINT            comment '����'
)
;
alter table JBPM4_ID_GROUP
  add primary key (DBID_);
alter table JBPM4_ID_GROUP
  add constraint FK_GROUP_PARENT foreign key (PARENT_)
  references JBPM4_ID_GROUP (DBID_);


/* ��� JBPM4_ID_USER */
create table JBPM4_ID_USER
(
  DBID_          BIGINT not null   comment '��¼��ʶ',
  DBVERSION_     INTEGER not null  comment '��¼�汾��',
  ID_            VARCHAR(255)      comment '�û�ID',
  PASSWORD_      VARCHAR(255)      comment '�û�����',
  GIVENNAME_     VARCHAR(255)      comment '�û���',
  FAMILYNAME_    VARCHAR(255)      comment '�û���',
  BUSINESSEMAIL_ VARCHAR(255)      comment '�û�Email'
)
;
alter table JBPM4_ID_USER
  add primary key (DBID_);


/* ��� JBPM4_ID_MEMBERSHIP */
create table JBPM4_ID_MEMBERSHIP
(
  DBID_      BIGINT not null   comment '��¼��ʶ',
  DBVERSION_ INTEGER not null  comment '��¼�汾��',
  USER_      BIGINT            comment '�û�����',
  GROUP_     BIGINT            comment '�������',
  NAME_      VARCHAR(255)      comment '��ɫ����'
)
;
alter table JBPM4_ID_MEMBERSHIP
  add primary key (DBID_);
alter table JBPM4_ID_MEMBERSHIP
  add constraint FK_MEM_GROUP foreign key (GROUP_)
  references JBPM4_ID_GROUP (DBID_);
alter table JBPM4_ID_MEMBERSHIP
  add constraint FK_MEM_USER foreign key (USER_)
  references JBPM4_ID_USER (DBID_);


/* ��� JBPM4_LOB */
create table JBPM4_LOB
(
  DBID_       BIGINT not null ,
  DBVERSION_  INTEGER not null,
  BLOB_VALUE_ LONGBLOB            ,
  DEPLOYMENT_ BIGINT          ,
  NAME_       LONGTEXT               
)
;
alter table JBPM4_LOB comment '���̶���洢��';
alter table JBPM4_LOB
  add primary key (DBID_);
alter table JBPM4_LOB
  add constraint FK_LOB_DEPLOYMENT foreign key (DEPLOYMENT_)
  references JBPM4_DEPLOYMENT (DBID_);


/* ��� JBPM4_JOB */
create table JBPM4_JOB
(
  DBID_            BIGINT not null        comment '��¼��ʶ',
  CLASS_           VARCHAR(255) not null  comment '�����ʶ',
  DBVERSION_       INTEGER not null       comment '��¼�汾��',
  DUEDATE_         DATETIME               comment '����ʱ��',
  STATE_           VARCHAR(255)           comment '״̬',
  ISEXCLUSIVE_     BIT                    comment '�Ƿ�ɲ���ִ�б�ʶ',
  LOCKOWNER_       VARCHAR(255)           comment '��ʱ��ӵ����',
  LOCKEXPTIME_     DATETIME               comment '��ʱ������ʱ��',
  EXCEPTION_       LONGTEXT               comment '��ʱ��ִ��ʱ���쳣',
  RETRIES_         INTEGER                comment '�쳣���ֺ�������ִ�еĴ���',
  PROCESSINSTANCE_ BIGINT                 comment '����ʵ��������',
  EXECUTION_       BIGINT                 comment 'ִ��ʵ��������',
  CFG_             BIGINT                 comment '�����ļ�������',
  SIGNAL_          VARCHAR(255)           comment '����org.jbpm.pvm.internal.job.TimerImpl��signalName����',
  EVENT_           VARCHAR(255)           comment '����org.jbpm.pvm.internal.job.TimerImpl��eventName����',
  REPEAT_          VARCHAR(255)           comment '����org.jbpm.pvm.internal.job.TimerImpl��repeat����'
)
;
alter table JBPM4_JOB comment '��ʱ�����Timer�����������Ķ�����Ϣ';
alter table JBPM4_JOB
  add primary key (DBID_);
alter table JBPM4_JOB
  add constraint FK_JOB_CFG foreign key (CFG_)
  references JBPM4_LOB (DBID_);
create index IDX_JOBDUEDATE on JBPM4_JOB (DUEDATE_);
create index IDX_JOBLOCKEXP on JBPM4_JOB (LOCKEXPTIME_);
create index IDX_JOBRETRIES on JBPM4_JOB (RETRIES_);
create index IDX_JOB_CFG on JBPM4_JOB (CFG_);
create index IDX_JOB_EXE on JBPM4_JOB (EXECUTION_);
create index IDX_JOB_PRINST on JBPM4_JOB (PROCESSINSTANCE_);


/* ��� JBPM4_SWIMLANE */
create table JBPM4_SWIMLANE
(
  DBID_      BIGINT not null  comment '��¼��ʶ',
  DBVERSION_ INTEGER not null comment '��¼�汾��',
  NAME_      VARCHAR(255)     comment 'Ӿ��������',
  ASSIGNEE_  VARCHAR(255)     comment '������',
  EXECUTION_ BIGINT           comment 'ִ�У�����ʵ����������'
)
;
alter table JBPM4_SWIMLANE comment 'Ӿ����Ϣ��';
alter table JBPM4_SWIMLANE
  add primary key (DBID_);
alter table JBPM4_SWIMLANE
  add constraint FK_SWIMLANE_EXEC foreign key (EXECUTION_)
  references JBPM4_EXECUTION (DBID_);


/* ��� JBPM4_TASK */
create table JBPM4_TASK
(
  DBID_             BIGINT not null   comment '��¼��ʶ',
  CLASS_            CHAR(1) not null  comment '�����ʶ',
  DBVERSION_        INTEGER not null  comment '��¼�汾��',
  NAME_             VARCHAR(255)      comment '���������',
  DESCR_            LONGTEXT          comment '��������',
  STATE_            VARCHAR(255)      comment '״̬',
  SUSPHISTSTATE_    VARCHAR(255)      ,
  ASSIGNEE_         VARCHAR(255)      comment '������',
  FORM_             VARCHAR(255)      comment '��������',
  URL_              VARCHAR(255)      comment '���ӵ�ҵ�����ݵ�URL',
  AGENTID_          VARCHAR(255)      comment '��������˵ı�ʶ',
  AGENCYID_         VARCHAR(255)      comment '������ʶ',
  ASSIGNRULE_       VARCHAR(255)      comment '��������߹���',
  HASTENTEMPLATE_   VARCHAR(255)      comment '��Ϣģ��Code',
  BUSINESSTYPE_     VARCHAR(255)      comment 'ҵ������',
  DYNAMICTASK_      VARCHAR(255)      comment '��ʶ�����Ƿ�Ϊ��̬',
  HASTENWAY_        VARCHAR(255)      comment '����Ĵ߰췽ʽ',
  SUBMITACTIVITY_   VARCHAR(255)      comment '�ύ�ڵ��ʶ',
  TASKTYPE_         VARCHAR(255)      comment '��������',
  BUSINESSNAME_     VARCHAR(255)      comment 'ҵ����������',
  APPLICATIONUSER_  VARCHAR(255)      comment '�ύ��',
  BUSINESSRECORDID_ VARCHAR(255)      comment 'ҵ�񵥾ݱ��',
  BUSINESSSN_       VARCHAR(255)      comment 'ҵ����',
  BUSINESSMONEY_    VARCHAR(255)      comment '���ݽ��',
  EXEID_            VARCHAR(255)      comment 'ִ�б�ʶ����Ҫ�����������н��б�ʶ',
  PRIORITY_         INTEGER           comment '���ȼ�',
  CREATE_           DATETIME          comment '����ʱ��',
  DUEDATE_          DATETIME          comment '����ʱ��',
  PROGRESS_         INTEGER           ,
  SIGNALLING_       BIT               ,
  EXECUTION_ID_     VARCHAR(255)      comment 'ִ�е�ID',
  ACTIVITY_NAME_    VARCHAR(255)      comment '�����activityName',
  HASVARS_          BIT               comment '�Ƿ�Ϊ������ʶ',
  SUPERTASK_        BIGINT            comment '�����������',
  EXECUTION_        BIGINT            comment 'ִ�е�����',
  PROCINST_         BIGINT            comment '����ʵ��������',
  SWIMLANE_         BIGINT            comment 'Ӿ��������',
  TASKDEFNAME_      VARCHAR(255)      comment '�����������',
  DEPARTMENTID_     VARCHAR(255)      ,
  WORKFLOW_OBJECT_  LONGBLOB          comment '��չ����'
)
;
alter table JBPM4_TASK comment '�����';
alter table JBPM4_TASK
  add primary key (DBID_);
alter table JBPM4_TASK
  add constraint FK_TASK_SUPERTASK foreign key (SUPERTASK_)
  references JBPM4_TASK (DBID_);
alter table JBPM4_TASK
  add constraint FK_TASK_SWIML foreign key (SWIMLANE_)
  references JBPM4_SWIMLANE (DBID_);


/* ��� JBPM4_PARTICIPATION */
create table JBPM4_PARTICIPATION
(
  DBID_      BIGINT not null   comment '��¼��ʶ',
  DBVERSION_ INTEGER not null  comment '��¼�汾��',
  GROUPID_   VARCHAR(255)      comment '��ID',
  USERID_    VARCHAR(255)      comment '�û�ID',
  TYPE_      VARCHAR(255)      comment '����������',
  STATE_     VARCHAR(255)      comment '��ʶ�����ߵ�״̬',
  TASK_      BIGINT            comment '���������',
  SWIMLANE_  BIGINT            comment 'Ӿ��������'
)
;
alter table JBPM4_PARTICIPATION comment '��������߱�';
alter table JBPM4_PARTICIPATION
  add primary key (DBID_);
alter table JBPM4_PARTICIPATION
  add constraint FK_PART_SWIMLANE foreign key (SWIMLANE_)
  references JBPM4_SWIMLANE (DBID_);
alter table JBPM4_PARTICIPATION
  add constraint FK_PART_TASK foreign key (TASK_)
  references JBPM4_TASK (DBID_);


/* ��� JBPM4_VARIABLE */
create table JBPM4_VARIABLE
(
  DBID_         BIGINT not null        comment '��¼��ʶ',
  CLASS_        VARCHAR(255) not null  comment '�����ʶ',
  DBVERSION_    INTEGER not null       comment '��¼�汾��',
  KEY_          VARCHAR(255)           comment 'key',
  CONVERTER_    VARCHAR(255)           comment 'ת����������',
  HIST_         BIT                    comment '�Ƿ�����ʷ',
  EXECUTION_    BIGINT                 comment 'ִ�е�����',
  TASK_         BIGINT                 comment '���������',
  LOB_          BIGINT                 comment '����org.jbpm.pvm.internal.type.variable.BlobVariable������',
  DATE_VALUE_   DATETIME               comment '����org.jbpm.pvm.internal.type.variable.DateVariable������date',
  DOUBLE_VALUE_ double precision       comment '����org.jbpm.pvm.internal.type.variable.DoubleVariable������',
  CLASSNAME_    VARCHAR(255)           comment '����HibernateLongVariable��HibernateStringVariable�ı�ʶ',
  LONG_VALUE_   BIGINT                 comment '����HibernateLongVariable������',
  STRING_VALUE_ VARCHAR(255)           comment '����HibernateStringVariable������',
  TEXT_VALUE_   LONGTEXT               comment '����TextVariable������text',
  EXESYS_       BIGINT                 comment 'ִ�У�����ʵ����������'
)
;
alter table JBPM4_VARIABLE comment '���̱�����';
alter table JBPM4_VARIABLE
  add primary key (DBID_);
alter table JBPM4_VARIABLE
  add constraint FK_VAR_EXECUTION foreign key (EXECUTION_)
  references JBPM4_EXECUTION (DBID_);
alter table JBPM4_VARIABLE
  add constraint FK_VAR_EXESYS foreign key (EXESYS_)
  references JBPM4_EXECUTION (DBID_);
alter table JBPM4_VARIABLE
  add constraint FK_VAR_LOB foreign key (LOB_)
  references JBPM4_LOB (DBID_);
alter table JBPM4_VARIABLE
  add constraint FK_VAR_TASK foreign key (TASK_)
  references JBPM4_TASK (DBID_);
create index IDX_VAR_EXECUTION on JBPM4_VARIABLE (EXECUTION_);
create index IDX_VAR_EXESYS on JBPM4_VARIABLE (EXESYS_);
create index IDX_VAR_LOB on JBPM4_VARIABLE (LOB_);
create index IDX_VAR_TASK on JBPM4_VARIABLE (TASK_);


/* ��� JBPM4_PROPERTY */
create table JBPM4_PROPERTY
(
  KEY_     VARCHAR(255) not null,
  VERSION_ INTEGER not null,
  VALUE_   VARCHAR(255)
)
;
alter table JBPM4_PROPERTY
  add primary key (KEY_);
alter table JBPM4_HIST_TASK add  PROCINST_ BIGINT comment '����ʵ��ID';
alter table JBPM4_HIST_TASK add  SYSTEM_CODE_ VARCHAR(32) comment 'ϵͳ��ʶ';
alter table JBPM4_TASK add  SYSTEM_CODE_ VARCHAR(32) comment 'ϵͳ��ʶ';


/* ����������� */
CREATE INDEX IND_FK_TASK_SUPERTASK ON JBPM4_TASK (SUPERTASK_);
CREATE INDEX IND_FK_TASK_SWIML ON JBPM4_TASK (SWIMLANE_);

CREATE INDEX IND_FK_PART_TASK ON JBPM4_PARTICIPATION (TASK_);
CREATE INDEX IND_FK_PART_SWIMLANE ON JBPM4_PARTICIPATION (SWIMLANE_);

CREATE INDEX IND_FK_EXEC_INSTANCE ON JBPM4_EXECUTION (INSTANCE_);
CREATE INDEX IND_FK_EXEC_PARENT ON JBPM4_EXECUTION (PARENT_);
CREATE INDEX IND_FK_EXEC_SUBPI ON JBPM4_EXECUTION (SUBPROCINST_);
CREATE INDEX IND_FK_EXEC_SUPEREXEC ON JBPM4_EXECUTION (SUPEREXEC_);

CREATE INDEX IDX_DEPLPROP_DEPL on JBPM4_DEPLOYPROP (DEPLOYMENT_);

CREATE INDEX IDX_HACTI_HPROCI on JBPM4_HIST_ACTINST (HPROCI_);
CREATE INDEX IDX_HTI_HTASK on JBPM4_HIST_ACTINST (HTASK_);

CREATE INDEX IDX_HDET_HACTI on JBPM4_HIST_DETAIL (HACTI_);
CREATE INDEX IDX_HDET_HPROCI on JBPM4_HIST_DETAIL (HPROCI_);

CREATE INDEX IDX_HVAR_HPROCI on JBPM4_HIST_VAR (HPROCI_);
CREATE INDEX IDX_HVAR_HTASK on JBPM4_HIST_VAR (HTASK_);

CREATE INDEX IDX_GROUP_PARENT on JBPM4_ID_GROUP (PARENT_);   

CREATE INDEX IDX_MEM_USER on JBPM4_ID_MEMBERSHIP (USER_);

CREATE INDEX IDX_LOB_DEPLOYMENT on JBPM4_LOB (DEPLOYMENT_);

CREATE INDEX IDX_SWIMLANE_EXEC on JBPM4_SWIMLANE (EXECUTION_); 