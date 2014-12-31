
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


/* 添加 JBPM4_DEPLOYMENT */
create table JBPM4_DEPLOYMENT
(
  DBID_      BIGINT not null  comment '部署标识',
  NAME_      LONGTEXT         comment '部署名称',
  TIMESTAMP_ BIGINT           comment '部署时间',
  STATE_     VARCHAR(255)     comment '部署状态'
)
;
alter table JBPM4_DEPLOYMENT comment '发布后的流程';
alter table JBPM4_DEPLOYMENT add primary key (DBID_);


/* 添加 JBPM4_DEPLOYPROP */
create table JBPM4_DEPLOYPROP
(
  DBID_       BIGINT not null comment '记录标识',
  DEPLOYMENT_ BIGINT          comment '部署标识',
  OBJNAME_    VARCHAR(255)    comment '流程定义名称',
  KEY_        VARCHAR(255)    comment '流程定义属性名称',
  STRINGVAL_  VARCHAR(255)    comment '流程定义属性名称的值',
  LONGVAL_    BIGINT          comment '流程定义属性名称的值'
)
;
alter table JBPM4_DEPLOYPROP comment '流程定义属性表';
alter table JBPM4_DEPLOYPROP add primary key (DBID_);
alter table JBPM4_DEPLOYPROP
  add constraint FK_DEPLPROP_DEPL foreign key (DEPLOYMENT_)
  references JBPM4_DEPLOYMENT (DBID_);


/* 添加 JBPM4_EXECUTION */
create table JBPM4_EXECUTION
(
  DBID_          BIGINT not null         comment '记录标识',
  CLASS_         VARCHAR(255) not null   comment '鉴别标识',
  DBVERSION_     INTEGER not null        comment '记录版本号',
  ACTIVITYNAME_  VARCHAR(255)            comment '节点名称',
  PROCDEFID_     VARCHAR(255)            comment '发布以后的流程定义标识',
  HASVARS_       BIT                     comment '拥有变量标识',
  NAME_          VARCHAR(255)            comment '可选的执行（流程实例）名称',
  KEY_           VARCHAR(255)            comment '执行（流程实例）key',
  ID_            VARCHAR(255)            comment '执行（流程实例）ID',
  STATE_         VARCHAR(255)            comment '执行（流程实例）状态',
  SUSPHISTSTATE_ VARCHAR(255)            comment '历史挂起状态',
  PRIORITY_      INTEGER                 comment '执行（流程实例）优先级',
  HISACTINST_    BIGINT                  comment '处于活动节点的引用',
  PARENT_        BIGINT                  comment '父执行',
  INSTANCE_      BIGINT                  comment '流程实例',
  SUPEREXEC_     BIGINT                  comment '父流程实例',
  SUBPROCINST_   BIGINT                  comment '子流程实例',
  PARENT_IDX_    INTEGER                 comment '当前执行之下所有并发执行的索引'
)
;
alter table JBPM4_EXECUTION comment '流程实例表';

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



/* 添加 JBPM4_HIST_PROCINST */
create table JBPM4_HIST_PROCINST
(
  DBID_        BIGINT not null     comment '记录标识',
  DBVERSION_   INTEGER not null    comment '记录版本号',
  ID_          VARCHAR(255)        comment '历史流程执行实例Id',
  PROCDEFID_   VARCHAR(255)        comment '流程实例Id',
  KEY_         VARCHAR(255)        comment '历史流程实例key',
  START_       DATETIME            comment '流程实例开始的时间',
  END_         DATETIME            comment '流程实例结束的时间',
  DURATION_    BIGINT              comment '流程实例运行所耗费的时间',
  STATE_       VARCHAR(255)        comment '流程实例状态',
  ENDACTIVITY_ VARCHAR(255)        comment '流程实例结束节点的名称',
  NEXTIDX_     INTEGER       
)
;
alter table JBPM4_HIST_PROCINST comment '流程任务实例历史表';
alter table JBPM4_HIST_PROCINST
  add primary key (DBID_);


/* 添加 JBPM4_HIST_TASK */
create table JBPM4_HIST_TASK
(
  DBID_             BIGINT not null     comment '记录标识',
  DBVERSION_        INTEGER not null    comment '记录版本号',
  EXECUTION_        VARCHAR(255)        comment '任务所对应的执行ID',
  OUTCOME_          VARCHAR(255)        comment '任务的流向',
  ASSIGNEE_         VARCHAR(255)        comment '任务处理人',
  PRIORITY_         INTEGER             comment '优先级',
  STATE_            VARCHAR(255)        comment '状态',
  CREATE_           DATETIME            comment '创建时间',
  END_              DATETIME            comment '结束时间',
  DURATION_         BIGINT              comment '任务运行时间',
  NEXTIDX_          INTEGER             ,
  URL_              VARCHAR(255)        comment '链接到业务数据的URL',
  AGENTID_          VARCHAR(255)        comment '任务代理人的标识',
  AGENCYID_         VARCHAR(255)        comment '机构标识',
  ASSIGNRULE_       VARCHAR(255)        comment '任务参与者规则',
  HASTENTEMPLATE_   VARCHAR(255)        comment '消息模板Code',
  BUSINESSTYPE_     VARCHAR(255)        comment '业务类型',
  NAME_             VARCHAR(255)        comment '历史任务的名称',
  ACTIVITY_NAME_    VARCHAR(255)        comment '节点的显示名称',
  SUBMITACTIVITY_   VARCHAR(255)        comment '提交节点标识',
  PARENTID_         VARCHAR(255)        comment '父任务标识',
  ABNORMAL_         VARCHAR(255)        comment '任务是否为正常结束',
  NEXTOPERATOR_     VARCHAR(255)        comment '下一级任务的处理人',
  SUPEREXECUTIONID_ VARCHAR(255)        comment '父流程的执行标识',
  BUSINESSNAME_     VARCHAR(255)        comment '业务类型名称',
  APPLICATIONUSER_  VARCHAR(255)        comment '单据提交人',
  BUSINESSRECORDID_ VARCHAR(255)        comment '业务单据编号',
  BUSINESSSN_       VARCHAR(255)        comment '业务类型编号',
  BUSINESSMONEY_    VARCHAR(255)        comment '业务单据金额',
  EXEID_            VARCHAR(255)        comment '任务所在流程的执行标识，主要是在子任务中进行标识',
  SUPERTASK_        BIGINT              comment '父历史任务',
  DEPARTMENTID_     VARCHAR(255)        ,
  WORKFLOW_OBJECT_  LONGBLOB            comment '扩展属性'
)
;
alter table JBPM4_HIST_TASK comment '流程任务实例历史表';
alter table JBPM4_HIST_TASK
  add primary key (DBID_);
alter table JBPM4_HIST_TASK
  add constraint FK_HSUPERT_SUB foreign key (SUPERTASK_)
  references JBPM4_HIST_TASK (DBID_);



/* 添加 JBPM4_HIST_ACTINST */
create table JBPM4_HIST_ACTINST
(
  DBID_          BIGINT not null         comment '记录标识',
  CLASS_         VARCHAR(255) not null   comment '鉴别标识',
  DBVERSION_     INTEGER not null        comment '记录版本号',
  HPROCI_        BIGINT                  comment '历史流程实例的引用',
  TYPE_          VARCHAR(255)            comment '节点类型',
  EXECUTION_     VARCHAR(255)            comment '执行ID',
  ACTIVITY_NAME_ VARCHAR(255)            comment '节点的ActivityName',
  START_         DATETIME                comment '节点的开始（创建）时间',
  END_           DATETIME                comment '节点的结束时间',
  DURATION_      BIGINT                  comment '节点从开始到结束的时间',
  TRANSITION_    VARCHAR(255)            comment '节点流向',
  NEXTIDX_       INTEGER                 ,
  HTASK_         BIGINT                  comment '所对应的历史任务节点标识'
)
;
alter table JBPM4_HIST_ACTINST comment '流程活动(节点)实例表';
alter table JBPM4_HIST_ACTINST add primary key (DBID_);
alter table JBPM4_HIST_ACTINST
  add constraint FK_HACTI_HPROCI foreign key (HPROCI_)
  references JBPM4_HIST_PROCINST (DBID_);
alter table JBPM4_HIST_ACTINST
  add constraint FK_HTI_HTASK foreign key (HTASK_)
  references JBPM4_HIST_TASK (DBID_);



/* 添加 JBPM4_HIST_VAR */
create table JBPM4_HIST_VAR
(
  DBID_        BIGINT not null   comment '记录标识',
  DBVERSION_   INTEGER not null  comment '记录版本号',
  PROCINSTID_  VARCHAR(255)      comment '流程实例ID',
  EXECUTIONID_ VARCHAR(255)      comment '流程执行ID',
  VARNAME_     VARCHAR(255)      comment '变量名称',
  VALUE_       VARCHAR(255)      comment '变量值',
  HPROCI_      BIGINT            comment '历史流程实例的引用',
  HTASK_       BIGINT            comment '历史任务的引用'
)
;
alter table JBPM4_HIST_VAR comment '流程变量历史表';
alter table JBPM4_HIST_VAR
  add primary key (DBID_);
alter table JBPM4_HIST_VAR
  add constraint FK_HVAR_HPROCI foreign key (HPROCI_)
  references JBPM4_HIST_PROCINST (DBID_);
alter table JBPM4_HIST_VAR
  add constraint FK_HVAR_HTASK foreign key (HTASK_)
  references JBPM4_HIST_TASK (DBID_);


/* 添加 JBPM4_HIST_DETAIL */
create table JBPM4_HIST_DETAIL
(
  DBID_       BIGINT not null        comment '记录标识',
  CLASS_      VARCHAR(255) not null  comment '鉴别标识',
  DBVERSION_  INTEGER not null       comment '记录版本号',
  USERID_     VARCHAR(255)           comment '用户标识',
  TIME_       DATETIME               comment '记录产生的时间',
  HPROCI_     BIGINT                 comment '历史流程实例引用',
  HPROCIIDX_  INTEGER                comment '历史流程实例索引',
  HACTI_      BIGINT                 comment '历史节点的引用',
  HACTIIDX_   INTEGER                comment '历史节点实例索引',
  HTASK_      BIGINT                 comment '历史任务的引用',
  HTASKIDX_   INTEGER                comment '历史任务实例索引',
  HVAR_       BIGINT                 comment '历史变量的引用',
  HVARIDX_    INTEGER                comment '历史变量实例索引',
  MESSAGE_    LONGTEXT               comment '任务的评论',
  OLD_INT_    INTEGER                comment '子类HistoryPriorityUpdateImpl原有的优先级',
  NEW_INT_    INTEGER                comment '子类HistoryPriorityUpdateImpl新的优先级',
  OLD_STR_    VARCHAR(255)           comment '子类HistoryTaskAssignmentImpl任务原有参与者',
  NEW_STR_    VARCHAR(255)           comment '子类HistoryTaskAssignmentImpl任务新的参与者',
  OLD_TIME_   DATETIME               comment '子类HistoryTaskDuedateUpdateImpl原有的运行时间',
  NEW_TIME_   DATETIME               comment '子类HistoryTaskDuedateUpdateImpl现在的支持时间',
  PARENT_     BIGINT                 ,
  PARENT_IDX_ INTEGER                 
)
;
alter table JBPM4_HIST_DETAIL comment '流程历史详细信息表';
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


/* 添加 JBPM4_ID_GROUP */
create table JBPM4_ID_GROUP
(
  DBID_      BIGINT not null   comment '记录标识',
  DBVERSION_ INTEGER not null  comment '记录版本号',
  ID_        VARCHAR(255)      comment '组ID',
  NAME_      VARCHAR(255)      comment '组名称',
  TYPE_      VARCHAR(255)      comment '组类型',
  PARENT_    BIGINT            comment '父组'
)
;
alter table JBPM4_ID_GROUP
  add primary key (DBID_);
alter table JBPM4_ID_GROUP
  add constraint FK_GROUP_PARENT foreign key (PARENT_)
  references JBPM4_ID_GROUP (DBID_);


/* 添加 JBPM4_ID_USER */
create table JBPM4_ID_USER
(
  DBID_          BIGINT not null   comment '记录标识',
  DBVERSION_     INTEGER not null  comment '记录版本号',
  ID_            VARCHAR(255)      comment '用户ID',
  PASSWORD_      VARCHAR(255)      comment '用户密码',
  GIVENNAME_     VARCHAR(255)      comment '用户名',
  FAMILYNAME_    VARCHAR(255)      comment '用户姓',
  BUSINESSEMAIL_ VARCHAR(255)      comment '用户Email'
)
;
alter table JBPM4_ID_USER
  add primary key (DBID_);


/* 添加 JBPM4_ID_MEMBERSHIP */
create table JBPM4_ID_MEMBERSHIP
(
  DBID_      BIGINT not null   comment '记录标识',
  DBVERSION_ INTEGER not null  comment '记录版本号',
  USER_      BIGINT            comment '用户引用',
  GROUP_     BIGINT            comment '组的引用',
  NAME_      VARCHAR(255)      comment '角色名称'
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


/* 添加 JBPM4_LOB */
create table JBPM4_LOB
(
  DBID_       BIGINT not null ,
  DBVERSION_  INTEGER not null,
  BLOB_VALUE_ LONGBLOB            ,
  DEPLOYMENT_ BIGINT          ,
  NAME_       LONGTEXT               
)
;
alter table JBPM4_LOB comment '流程定义存储表';
alter table JBPM4_LOB
  add primary key (DBID_);
alter table JBPM4_LOB
  add constraint FK_LOB_DEPLOYMENT foreign key (DEPLOYMENT_)
  references JBPM4_DEPLOYMENT (DBID_);


/* 添加 JBPM4_JOB */
create table JBPM4_JOB
(
  DBID_            BIGINT not null        comment '记录标识',
  CLASS_           VARCHAR(255) not null  comment '鉴别标识',
  DBVERSION_       INTEGER not null       comment '记录版本号',
  DUEDATE_         DATETIME               comment '运行时间',
  STATE_           VARCHAR(255)           comment '状态',
  ISEXCLUSIVE_     BIT                    comment '是否可并发执行标识',
  LOCKOWNER_       VARCHAR(255)           comment '定时器拥有者',
  LOCKEXPTIME_     DATETIME               comment '定时器过期时间',
  EXCEPTION_       LONGTEXT               comment '定时器执行时的异常',
  RETRIES_         INTEGER                comment '异常出现后所尝试执行的次数',
  PROCESSINSTANCE_ BIGINT                 comment '流程实例的引用',
  EXECUTION_       BIGINT                 comment '执行实例的引用',
  CFG_             BIGINT                 comment '流程文件的引用',
  SIGNAL_          VARCHAR(255)           comment '子类org.jbpm.pvm.internal.job.TimerImpl的signalName属性',
  EVENT_           VARCHAR(255)           comment '子类org.jbpm.pvm.internal.job.TimerImpl的eventName属性',
  REPEAT_          VARCHAR(255)           comment '子类org.jbpm.pvm.internal.job.TimerImpl的repeat属性'
)
;
alter table JBPM4_JOB comment '定时表，存放Timer（定义器）的定义信息';
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


/* 添加 JBPM4_SWIMLANE */
create table JBPM4_SWIMLANE
(
  DBID_      BIGINT not null  comment '记录标识',
  DBVERSION_ INTEGER not null comment '记录版本号',
  NAME_      VARCHAR(255)     comment '泳道的名称',
  ASSIGNEE_  VARCHAR(255)     comment '参与者',
  EXECUTION_ BIGINT           comment '执行（流程实例）的引用'
)
;
alter table JBPM4_SWIMLANE comment '泳道信息表';
alter table JBPM4_SWIMLANE
  add primary key (DBID_);
alter table JBPM4_SWIMLANE
  add constraint FK_SWIMLANE_EXEC foreign key (EXECUTION_)
  references JBPM4_EXECUTION (DBID_);


/* 添加 JBPM4_TASK */
create table JBPM4_TASK
(
  DBID_             BIGINT not null   comment '记录标识',
  CLASS_            CHAR(1) not null  comment '鉴别标识',
  DBVERSION_        INTEGER not null  comment '记录版本号',
  NAME_             VARCHAR(255)      comment '任务的名称',
  DESCR_            LONGTEXT          comment '任务描述',
  STATE_            VARCHAR(255)      comment '状态',
  SUSPHISTSTATE_    VARCHAR(255)      ,
  ASSIGNEE_         VARCHAR(255)      comment '参与者',
  FORM_             VARCHAR(255)      comment '表单的名称',
  URL_              VARCHAR(255)      comment '链接到业务数据的URL',
  AGENTID_          VARCHAR(255)      comment '任务代理人的标识',
  AGENCYID_         VARCHAR(255)      comment '机构标识',
  ASSIGNRULE_       VARCHAR(255)      comment '任务参与者规则',
  HASTENTEMPLATE_   VARCHAR(255)      comment '消息模板Code',
  BUSINESSTYPE_     VARCHAR(255)      comment '业务类型',
  DYNAMICTASK_      VARCHAR(255)      comment '标识任务是否为动态',
  HASTENWAY_        VARCHAR(255)      comment '任务的催办方式',
  SUBMITACTIVITY_   VARCHAR(255)      comment '提交节点标识',
  TASKTYPE_         VARCHAR(255)      comment '任务类型',
  BUSINESSNAME_     VARCHAR(255)      comment '业务类型名称',
  APPLICATIONUSER_  VARCHAR(255)      comment '提交人',
  BUSINESSRECORDID_ VARCHAR(255)      comment '业务单据编号',
  BUSINESSSN_       VARCHAR(255)      comment '业务编号',
  BUSINESSMONEY_    VARCHAR(255)      comment '单据金额',
  EXEID_            VARCHAR(255)      comment '执行标识，主要是在子任务中进行标识',
  PRIORITY_         INTEGER           comment '优先级',
  CREATE_           DATETIME          comment '创建时间',
  DUEDATE_          DATETIME          comment '运行时间',
  PROGRESS_         INTEGER           ,
  SIGNALLING_       BIT               ,
  EXECUTION_ID_     VARCHAR(255)      comment '执行的ID',
  ACTIVITY_NAME_    VARCHAR(255)      comment '任务的activityName',
  HASVARS_          BIT               comment '是否为变量标识',
  SUPERTASK_        BIGINT            comment '父任务的引用',
  EXECUTION_        BIGINT            comment '执行的引用',
  PROCINST_         BIGINT            comment '流程实例的引用',
  SWIMLANE_         BIGINT            comment '泳道的引用',
  TASKDEFNAME_      VARCHAR(255)      comment '任务定义的引用',
  DEPARTMENTID_     VARCHAR(255)      ,
  WORKFLOW_OBJECT_  LONGBLOB          comment '扩展属性'
)
;
alter table JBPM4_TASK comment '任务表';
alter table JBPM4_TASK
  add primary key (DBID_);
alter table JBPM4_TASK
  add constraint FK_TASK_SUPERTASK foreign key (SUPERTASK_)
  references JBPM4_TASK (DBID_);
alter table JBPM4_TASK
  add constraint FK_TASK_SWIML foreign key (SWIMLANE_)
  references JBPM4_SWIMLANE (DBID_);


/* 添加 JBPM4_PARTICIPATION */
create table JBPM4_PARTICIPATION
(
  DBID_      BIGINT not null   comment '记录标识',
  DBVERSION_ INTEGER not null  comment '记录版本号',
  GROUPID_   VARCHAR(255)      comment '组ID',
  USERID_    VARCHAR(255)      comment '用户ID',
  TYPE_      VARCHAR(255)      comment '参与者类型',
  STATE_     VARCHAR(255)      comment '标识参与者的状态',
  TASK_      BIGINT            comment '任务的引用',
  SWIMLANE_  BIGINT            comment '泳道的引用'
)
;
alter table JBPM4_PARTICIPATION comment '任务参与者表';
alter table JBPM4_PARTICIPATION
  add primary key (DBID_);
alter table JBPM4_PARTICIPATION
  add constraint FK_PART_SWIMLANE foreign key (SWIMLANE_)
  references JBPM4_SWIMLANE (DBID_);
alter table JBPM4_PARTICIPATION
  add constraint FK_PART_TASK foreign key (TASK_)
  references JBPM4_TASK (DBID_);


/* 添加 JBPM4_VARIABLE */
create table JBPM4_VARIABLE
(
  DBID_         BIGINT not null        comment '记录标识',
  CLASS_        VARCHAR(255) not null  comment '鉴别标识',
  DBVERSION_    INTEGER not null       comment '记录版本号',
  KEY_          VARCHAR(255)           comment 'key',
  CONVERTER_    VARCHAR(255)           comment '转换器的引用',
  HIST_         BIT                    comment '是否开启历史',
  EXECUTION_    BIGINT                 comment '执行的引用',
  TASK_         BIGINT                 comment '任务的引用',
  LOB_          BIGINT                 comment '子类org.jbpm.pvm.internal.type.variable.BlobVariable的引用',
  DATE_VALUE_   DATETIME               comment '子类org.jbpm.pvm.internal.type.variable.DateVariable的属性date',
  DOUBLE_VALUE_ double precision       comment '子类org.jbpm.pvm.internal.type.variable.DoubleVariable的属性',
  CLASSNAME_    VARCHAR(255)           comment '子类HibernateLongVariable和HibernateStringVariable的标识',
  LONG_VALUE_   BIGINT                 comment '子类HibernateLongVariable的属性',
  STRING_VALUE_ VARCHAR(255)           comment '子类HibernateStringVariable的属性',
  TEXT_VALUE_   LONGTEXT               comment '子类TextVariable的属性text',
  EXESYS_       BIGINT                 comment '执行（流程实例）的引用'
)
;
alter table JBPM4_VARIABLE comment '流程变量表';
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


/* 添加 JBPM4_PROPERTY */
create table JBPM4_PROPERTY
(
  KEY_     VARCHAR(255) not null,
  VERSION_ INTEGER not null,
  VALUE_   VARCHAR(255)
)
;
alter table JBPM4_PROPERTY
  add primary key (KEY_);
alter table JBPM4_HIST_TASK add  PROCINST_ BIGINT comment '流程实例ID';
alter table JBPM4_HIST_TASK add  SYSTEM_CODE_ VARCHAR(32) comment '系统标识';
alter table JBPM4_TASK add  SYSTEM_CODE_ VARCHAR(32) comment '系统标识';


/* 增加外键索引 */
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