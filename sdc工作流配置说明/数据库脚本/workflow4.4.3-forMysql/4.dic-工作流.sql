insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86e9018000d', 'Workflow_Duty', '职责字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_Duty', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc888eb470025', 'Workflow_RefModule', '模块类型', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_RefModule', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc84ef3b50001', 'Workflow_Agency', '单位字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_Agency', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88bc08a0029', 'Workflow_UseAgenDept', '参与者用户字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_UseAgenDept', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88e8f160031', 'Workflow_UseAuth', '授权字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_UseAuth', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc85146450005', 'Workflow_Branch', '部门字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_Branch', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc84450012bc845664b0002', 'Workflow_Post', '岗位字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_Post', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86470d80005', 'Workflow_Busprocess', '流程业务字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_Busprocess', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8705d930011', 'Workflow_EmpBusiness', '员工字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_EmpBusiness', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8624c7a0001', 'Workflow_Business', '挂接业务字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_Business', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87e1dc30017', 'Workflow_JbpmAgency', '挂接单位字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_JbpmAgency', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87fdd83001b', 'Workflow_JbpmProDef', '挂接流程字典', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_JbpmProDef', '', '1', '1');

insert into sys_dic_config (ID, DIC_NAME, NAME, DIC_TYPE, CACHE_TYPE, I18N, SQL_ID, JAVA_CLASS, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88227680021', 'Workflow_RefEvent', '事件类型', 'dicSqlUtils', 'noCache', '', 'Dictionary.Workflow_RefEvent', '', '1', '1');



insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86ee2f5000e', '8a98c2f92bc8619e012bc86e9018000d', '职责ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc875ed3f0015', '8a98c2f92bc8619e012bc8705d930011', '单位编号', 'AGENCY_ID', 'false', '3', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87ecdfb0018', '8a98c2f92bc8619e012bc87e1dc30017', '单位ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc882d1a50022', '8a98c2f92bc8619e012bc88227680021', '事件类型编号', 'CODE', 'true', '0', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc883227f0023', '8a98c2f92bc8619e012bc88227680021', '码表类型ID', 'REFERENCEID', 'false', '1', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88463340024', '8a98c2f92bc8619e012bc88227680021', '事件类型名称', 'LABLENAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc889e0340026', '8a98c2f92bc8619e012bc888eb470025', '模块类型编号', 'CODE', 'true', '0', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84450012bc846742d0003', '8a98c2f92bc84450012bc845664b0002', '岗位ID', 'ID', 'true', '0', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84450012bc846d6990004', '8a98c2f92bc84450012bc845664b0002', '岗位名称', 'NAME', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc84f6e6a0002', '8a98c2f92bc84e59012bc84ef3b50001', '单位ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc84fb1b80003', '8a98c2f92bc84e59012bc84ef3b50001', '单位编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc84ff15c0004', '8a98c2f92bc84e59012bc84ef3b50001', '单位名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc851b5b00006', '8a98c2f92bc84e59012bc85146450005', '部门ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc85200ec0007', '8a98c2f92bc84e59012bc85146450005', '部门编号', 'BRANCH_CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc84e59012bc85244680008', '8a98c2f92bc84e59012bc85146450005', '部门名称', 'BRANCH_NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88a2d640027', '8a98c2f92bc8619e012bc888eb470025', '模块类型名称', 'LABLENAME', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88a9abd0028', '8a98c2f92bc8619e012bc888eb470025', '码表类型IDID', 'REFERENCEID', 'false', '2', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88c294e002a', '8a98c2f92bc8619e012bc88bc08a0029', '用户ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88c56f3002b', '8a98c2f92bc8619e012bc88bc08a0029', '用户编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88c8a72002c', '8a98c2f92bc8619e012bc88bc08a0029', '用户名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88efedf0032', '8a98c2f92bc8619e012bc88e8f160031', '授权用户ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88f53520033', '8a98c2f92bc8619e012bc88e8f160031', '授权用户编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88f90660034', '8a98c2f92bc8619e012bc88e8f160031', '授权用户名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86327690002', '8a98c2f92bc8619e012bc8624c7a0001', '业务ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8636c0e0003', '8a98c2f92bc8619e012bc8624c7a0001', '业务编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc863a1df0004', '8a98c2f92bc8619e012bc8624c7a0001', '业务名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8650a100006', '8a98c2f92bc8619e012bc86470d80005', '流程业务ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86709e1000c', '8a98c2f92bc8619e012bc86470d80005', '单位编号', 'AGENCY', 'false', '6', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86566130007', '8a98c2f92bc8619e012bc86470d80005', '流程业务编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc865b3ef0008', '8a98c2f92bc8619e012bc86470d80005', '流程业务名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86618300009', '8a98c2f92bc8619e012bc86470d80005', '流程编号', 'PROCESSDEFINITION', 'false', '3', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8668b64000a', '8a98c2f92bc8619e012bc86470d80005', '流程定义编号', 'PDID', 'false', '4', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc866c914000b', '8a98c2f92bc8619e012bc86470d80005', '流程名称', 'PDN', 'false', '5', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86f2cda000f', '8a98c2f92bc8619e012bc86e9018000d', '职责编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc86f7db30010', '8a98c2f92bc8619e012bc86e9018000d', '职责名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87133040012', '8a98c2f92bc8619e012bc8705d930011', '员工ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87176900013', '8a98c2f92bc8619e012bc8705d930011', '员工编号', 'EMPLOYEE_CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc871e1480014', '8a98c2f92bc8619e012bc8705d930011', '员工姓名', 'EMPLOYEE_NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8763c530016', '8a98c2f92bc8619e012bc8705d930011', '单位名称', 'AGENCY_NAME', 'false', '4', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87eff970019', '8a98c2f92bc8619e012bc87e1dc30017', '单位编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc87f349d001a', '8a98c2f92bc8619e012bc87e1dc30017', '单位名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc8804780001c', '8a98c2f92bc8619e012bc87fdd83001b', '流程ID', 'ID', 'false', '0', '0', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88087b1001d', '8a98c2f92bc8619e012bc87fdd83001b', '流程编号', 'CODE', 'true', '1', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc880b8b0001e', '8a98c2f92bc8619e012bc87fdd83001b', '流程名称', 'NAME', 'true', '2', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc881014d001f', '8a98c2f92bc8619e012bc87fdd83001b', '流程版本', 'PDVERSION', 'false', '3', '100', '1', '1');

insert into sys_dic_column_config (ID, DIC_ID, TITLE, FIELD, IS_QUERY_CONDICTION, SHOW_ORDER, WIDTH, FLAG, STATUS)
values ('8a98c2f92bc8619e012bc88147490020', '8a98c2f92bc8619e012bc87fdd83001b', '流程实例ID', 'PDID', 'false', '4', '100', '1', '1');

commit;