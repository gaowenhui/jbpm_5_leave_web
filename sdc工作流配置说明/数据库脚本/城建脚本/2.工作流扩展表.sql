
/* DROP SQL */
drop table JBPM4_BUSINESS cascade;
drop table JBPM4_GLOBAL_PARAM cascade;
drop table JBPM4_DEPLOY cascade;
drop table WORKFLOW_ARTICULATED_PROCESS cascade;
drop table WORKFLOW_AUTHORIZED_MANAGEMENT cascade;
drop table WORKFLOW_BEHAVIOR_MANAGEMENT cascade;



/* 添加 JBPM4_BUSINESS */
create table JBPM4_BUSINESS
(
  ID                  VARCHAR(32) not null ,
  NAME                VARCHAR(255)         ,
  CODE                VARCHAR(50)          ,
  BUSI_DESC           VARCHAR(255)         ,
  OPERANT             VARCHAR(50)          comment '在action_config中配置的action路径，返回值无效',
  QUERY               VARCHAR(50)          comment '在xml配置的action路径',
  BUSINESS_CLASS_NAME VARCHAR(255)
)
;
alter table JBPM4_BUSINESS
  add constraint PK_JBPM4_BUSINESS primary key (ID);

  
/* 添加 JBPM4_GLOBAL_PARAM */
create table JBPM4_GLOBAL_PARAM
(
  ID            VARCHAR(32) not null  comment 'id',
  CODE          VARCHAR(32)           comment '变量英文名字',
  NAME          VARCHAR(50)           comment '变量中文名字',
  DES           VARCHAR(256)          comment '备注',
  STATUS        CHAR(1)            comment '关联删除标识(0,可以删除；1，不可删除)',
  VARIABLE_TYPE VARCHAR(32)           comment '变量类型'
)
;
alter table JBPM4_GLOBAL_PARAM comment '工作流全局参数设置';
alter table JBPM4_GLOBAL_PARAM
  add constraint PK_GLOBAL_PARAM primary key (ID);
  

/* 添加 JBPM4_DEPLOY */
create table JBPM4_DEPLOY
(
  ID            VARCHAR(32) not null  comment 'id',
  NAME          VARCHAR(255)          comment '流程名称',
  CODE          VARCHAR(50)           comment '流程编号',
  PDSTATUS      CHAR(1)               comment '流程状态。0:未发布;1:已发布;2:修改未发布',
  PROCESSDEFINE LONGTEXT              comment '流程定义的内容',
  PDNAME        VARCHAR(30)           comment '流程生成XML的名称',
  PDVERSION     INTEGER               comment '流程定义的版本',
  PDID          VARCHAR(255)          comment '流程定义ID',
  FLAG          CHAR(1)               comment '启停标志。0:停止;1:启用',
  STATUS        CHAR(1)               comment '逻辑删除标志。0:逻辑删除;1:未删除',
  MODIFIERID    VARCHAR(32)           comment '编辑者ID',
  PROCESSTYPE   VARCHAR(20)           comment '流程类型。主流程:mainprocess;子流程:subprocess',
  AGENCYID      VARCHAR(32)           ,
  DEPARTMENTID  VARCHAR(32)           
)
;
alter table JBPM4_DEPLOY comment '流程设计时操作的内容';
alter table JBPM4_DEPLOY
  add constraint pk_jbpm4_deploy_id primary key (ID);
alter table JBPM4_DEPLOY
  add constraint check_id check ("ID" IS NOT NULL);



/* 添加 WORKFLOW_ARTICULATED_PROCESS */
create table WORKFLOW_ARTICULATED_PROCESS
(
  ID                VARCHAR(32) not null  comment 'ID，唯一标识',
  PROCESSDEFINITION VARCHAR(32) not null  comment '流程ID',
  AGENCY            VARCHAR(32) not null  comment '单位ID',
  BUSINESS          VARCHAR(32) not null  comment '业务类型ID',
  BUSINESSNAME      VARCHAR(255)          comment '业务类型名称',
  OPERATOR          VARCHAR(32)           comment '操作人',
  OPERANT_TIME      DATETIME not null     comment '操作时间',
  FLAG              CHAR(1) not null      comment '状态标识',
  STATUS            CHAR(1) not null      comment '逻辑删标识',
  MODTIME           DATETIME not null     comment '修改时间',
  COMMITERID        VARCHAR(32) not null  comment '创建人ID',
  MODIFIERID        VARCHAR(32) not null  comment '修改人ID'
)
;
alter table WORKFLOW_ARTICULATED_PROCESS comment '工作流流程挂接表';


/* 添加 WORKFLOW_AUTHORIZED_MANAGEMENT */
create table WORKFLOW_AUTHORIZED_MANAGEMENT
(
  ID               VARCHAR(32) not null  comment 'id',
  AUTHORIZER       VARCHAR(32) not null  comment '授权人ID',
  BUSINESSTYPE     VARCHAR(42) not null  comment '业务类型',
  AUTHORIZEDPERSON VARCHAR(32) not null  comment '被授权人ID',
  AUTHORIZEDSTATE  CHAR(1) not null      comment '授权状态0,未生效,1,生效,2,过期失效',
  STARTTIME        DATETIME not null     comment '开始时间',
  ENDTIME          DATETIME not null     comment '结束时间',
  FLAG             CHAR(1) not null      comment '启停标识',
  STATUS           CHAR(1) not null      comment '状态标识',
  MODTIME          DATETIME not null     comment '修改时间',
  COMMITERID       VARCHAR(32) not null  comment '创建人ID',
  MODIFIERID       VARCHAR(32) not null  comment '修改人ID'
)
;
alter table WORKFLOW_AUTHORIZED_MANAGEMENT comment '授权管理表';
alter table WORKFLOW_AUTHORIZED_MANAGEMENT
  add constraint PK_AUTHORIZED_MANAGEMENT primary key (ID);


/* 添加 WORKFLOW_BEHAVIOR_MANAGEMENT */
create table WORKFLOW_BEHAVIOR_MANAGEMENT
(
  ID        VARCHAR(32) not null   comment 'ID',
  NAME      VARCHAR(50) not null   comment '行为名',
  CODE      VARCHAR(32) not null   comment '行为编号',
  CLASSPATH VARCHAR(100) not null  comment '实现类或方法名',
  EV_TYPE   VARCHAR(32)            comment '事件类型'
)
;
alter table WORKFLOW_BEHAVIOR_MANAGEMENT comment '行为设置';
alter table WORKFLOW_BEHAVIOR_MANAGEMENT
  add constraint PK_BEHAVIOR_MANAGEMENT primary key (ID);

  
/* 添加 JBPM4_EXT_RULE 参与者规则表 */
create table JBPM4_EXT_RULE
(
  ID            VARCHAR(32) not null    comment '唯一标识',
  CODE          VARCHAR(32)             comment '任务参与者规则编号',
  NAME          VARCHAR(50)             comment '任务参与者规则名称',
  FLAG          CHAR(1) not null        comment '状态标识',
  STATUS        CHAR(1)                 comment '是否删除',
  AGENCY_ID     VARCHAR(32)             comment '机构ID',
  DEPARTMENT_ID VARCHAR(32)             comment '部门ID',
  CLASS_NAME    VARCHAR(255)            comment '参与者规则实现类',
  IS_EXCLUDE    VARCHAR(1) default '0'  comment '是否排除提交人'
)
;
alter table JBPM4_EXT_RULE comment  '参与者规则表';
alter table JBPM4_EXT_RULE add primary key (ID);


/* 添加 JBPM4_EXT_RULE_DETAIL 参与者规则明细表  */
create table JBPM4_EXT_RULE_DETAIL
(
  ID               VARCHAR(32) not null  comment '唯一标识',
  PARTICIPANT_TYPE CHAR(1)               comment '规则类型:0,用户;1,机构;2,职责;3,部门4.岗位',
  PARTICIPANT_ID   VARCHAR(32)           comment '参与者ID',
  RULE_ID          VARCHAR(32)           comment '参与者规则ID',
  IS_DYNAMIC       CHAR(1)               comment '是否动态策略:0,否;1,是',
  DYNAMIC_TYPE     VARCHAR(40)           comment '动态类型:fatherAgency,上级机构'
)
;
alter table JBPM4_EXT_RULE_DETAIL comment  '参与者规则明细表';
alter table JBPM4_EXT_RULE_DETAIL add primary key (ID);

/* mysql视图和oracle、db2不同，不能包含子查询 */
create view V_JBPM4_EXT_TODOTASKS 
    as 
    select 
        t.dbid_ as id, 
        t.create_ as startTime, 
        t.agencyid_ as agencyId,
        t.businesstype_ as businessType, 
        t.activity_name_ as nodeName, 
        u.id as assignee, 
        t.businessname_ as businessName, 
        t.applicationuser_ as submitUserCode,
        t.businessrecordid_ as billId, 
        t.businesssn_ as billCode, 
        t.businessmoney_ as money, 
        t.exeId_ as processId, 
        t.state_ as status, 
        t.url_ as businessUrl, 
        t.agentid_ as agentId 
    from 
        jbpm4_task t, 
        jbpm4_participation p,  
        sys_user u 
    where 
        p.task_ = t.dbid_ 
        and p.userid_=u.id  
        and (t.submitactivity_ != 'refuseToSubmitActivity' or t.submitactivity_ is null) 
    union select 
        t.dbid_ as id, 
        t.create_ as startTime, 
        t.agencyid_ as agencyId, 
        t.businesstype_ as businessType, 
        t.activity_name_ as nodeName, 
        t.assignee_ as assignee, 
        t.businessname_ as businessName,
        t.applicationuser_ as submitUserCode, 
        t.businessrecordid_ as billId, 
        t.businesssn_ as businessTypeCode, 
        t.businessmoney_ as money, 
        t.exeId_ as processId,
        t.state_ as status, t.url_ as businessUrl, 
        t.agentid_ as agentId 
    from 
        jbpm4_task t 
    where 
        (t.submitactivity_ != 'refuseToSubmitActivity' or t.submitactivity_ is null) and t.assignee_ is not null;

/* 已办辅助视图（mysql视图里面不能包含子查询的解决办法） */
create view V_JBPM4_EXT_HISTTASK
    as 
    select
        max(ht1.dbid_) as dbid_, 
        ht1.businessrecordid_, 
        ht1.assignee_ 
    from 
        jbpm4_hist_task ht1 
    where 
        ht1.state_ = 'completed' 
        and (ht1.submitactivity_ != 'refuseToSubmitActivity' or ht1.submitactivity_ is null) 
    group by 
        ht1.businessrecordid_, ht1.assignee_;
        
create view V_JBPM4_EXT_ALREADYDOTASKS 
    as 
    select 
        t.dbid_ as id, 
        t.create_ as startTime,
        t.end_ as endTime, 
        t.duration_ as duration, 
        t.agencyid_ as agencyId, 
        t.businesstype_ as businessType, 
        t.name_ as nodeName, 
        t.businessname_ as businessName,
        t.applicationuser_ as submitUserCode, 
        t.businessrecordid_ as billId, 
        t.businesssn_ as billCode, 
        t.businessmoney_ as money, 
        t.exeid_ as processId, 
        t.url_ as businessUrl, 
        t.assignee_ as assignee 
    from 
        jbpm4_hist_task t, V_JBPM4_EXT_HISTTASK ht 
    where 
        t.dbid_ = ht.dbid_;