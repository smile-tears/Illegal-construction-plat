insert into user (id,username,password,name) 
SELECT replace(uuid(),'-','') as id,'admin' as username,'36e9740c2a79ea71e1493eaaea4003fa' as password,'系统管理员' as name
from dual where not EXISTS (select 1 from user where username='admin');

insert into subcompany (id,subcompanyname,supsubcompanyid) 
SELECT replace(uuid(),'-','') as id,'根目录' as subcompanyname,'0' as supsubcompanyid 
from dual where not EXISTS (select 1 from subcompany where supsubcompanyid='0');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '1' id,'指挥中心' menuName, '/' path , 'index' name, 'BasicLayout' component, '/firstPage' redirect, 
'{ title: "指挥中心" }' meta, '0' supMenuid,0 visible,1 showOrder
from dual where not EXISTS (select 1 from menu where id='1');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '2' id,'首页' menuName ,'/firstPage' path , 'FullScreen' name, 'teamWork/FullScreen' component, null redirect, 
'{ title: "首页", icon: "line-chart", keepAlive: false, permission: [ "dashboard" ] }' meta, '1' supMenuid,0 visible,2 showOrder
from dual where not EXISTS (select 1 from menu where id='2');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '3' id,'应用维护子系统' menuName, '/appMaintainSystem' path , 'appMaintainSystem' name, 'PageView' component, null redirect, 
'{ title: "应用维护子系统", icon: "setting", permission: ["dashboard"] }' meta, '1' supMenuid,0 visible,3 showOrder
from dual where not EXISTS (select 1 from menu where id='3');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '4' id,'网格管理' menuName, '/appMaintainSystem/gridcommunity' path , 'gridcommunity' name, 'appMaintainSystem/gridcommunity/GridCommunityList' component, null redirect, 
'{ title: "网格管理", icon: "solution", keepAlive: true, permission: [ "support" ] }' meta, '3' supMenuid,0 visible,4 showOrder
from dual where not EXISTS (select 1 from menu where id='4');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '5' id,'公司管理' menuName, '/appMaintainSystem/companyManage' path , 'companyManage' name, 'appMaintainSystem/companymanage/CompanyManageList' component, null redirect, 
'{ title: "公司管理", icon: "solution", keepAlive: true, permission: [ "support" ] }' meta, '3' supMenuid,0 visible,5 showOrder
from dual where not EXISTS (select 1 from menu where id='5');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '6' id,'基础数据子系统' menuName ,'/baseDataSystem' path , 'baseDataSystem' name, 'PageView' component, null redirect, 
'{ title: "基础数据子系统", icon: "team", permission: ["dashboard"] }' meta, '1' supMenuid,0 visible,6 showOrder
from dual where not EXISTS (select 1 from menu where id='6');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '7' id,'用户管理' menuName, '/baseDataSystem/user' path , 'User' name, 'appMaintainSystem/UserView' component, null redirect, 
'{ title: "用户管理", icon: "user", keepAlive: true }' meta, '6' supMenuid,0 visible,7 showOrder
from dual where not EXISTS (select 1 from menu where id='7');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '8' id, '部门管理' menuName,'/baseDataSystem/department' path , 'Department' name, 'appMaintainSystem/DepartmentView' component, null redirect, 
'{ title: "部门管理", icon: "team", keepAlive: true }' meta, '6' supMenuid,0 visible,8 showOrder
from dual where not EXISTS (select 1 from menu where id='8');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '9' id, '角色管理' menuName,'/baseDataSystem/role' path , 'role' name, 'appMaintainSystem/role/RoleList' component, null redirect, 
'{ title: "角色管理", icon: "solution", keepAlive: true }' meta, '6' supMenuid,0 visible,9 showOrder
from dual where not EXISTS (select 1 from menu where id='9');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '10' id,'菜单管理' menuName, '/baseDataSystem/menu' path , 'MenuView' name, 'appMaintainSystem/MenuView' component, null redirect, 
'{ title: "菜单管理", icon: "solution", keepAlive: true }' meta, '6' supMenuid,0 visible,10 showOrder
from dual where not EXISTS (select 1 from menu where id='10');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '11' id, '菜单分配' menuName,'/baseDataSystem/auth' path , 'auth' name, 'appMaintainSystem/MenuAuthView' component, null redirect, 
'{ title: "菜单分配", icon: "solution", keepAlive: true }' meta, '6' supMenuid,0 visible,11 showOrder
from dual where not EXISTS (select 1 from menu where id='11');

insert into menu (id,menuName,path,name,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '12' id, '监督受理子系统' menuName,'/superviseAcceptSystem' path , 'superviseAcceptSystem' name, 'PageView' component, null redirect, 
'{ title: "监督受理子系统", icon: "eye"}' meta, '1' supMenuid,0 visible,12 showOrder
from dual where not EXISTS (select 1 from menu where id='12');

insert into menu (id,menuName,path,name,props,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '13' id, '案件上报' menuName,'/superviseAcceptSystem/caseReport' path , 'caseReport' name, '{page: "0"}' props, 'appMaintainSystem/case/ReportList' component, null redirect, 
'{ title: "案件上报", icon: "solution"}' meta, '12' supMenuid,0 visible,13 showOrder
from dual where not EXISTS (select 1 from menu where id='13');

insert into menu (id,menuName,path,name,props,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '14' id,'案件处置' menuName, '/superviseAcceptSystem/caseHandle' path , 'caseHandle' name, '{page: "1"}' props, 'appMaintainSystem/case/ReportList' component, null redirect, 
'{ title: "案件处置", icon: "solution"}' meta, '12' supMenuid,0 visible,14 showOrder
from dual where not EXISTS (select 1 from menu where id='14');

insert into menu (id,menuName,path,name,props,component,redirect,meta,supMenuid,visible,showOrder) 
SELECT '15' id,'案件查询' menuName, '/superviseAcceptSystem/caseSearch' path , 'caseSearch' name, '{page: "2"}' props, 'appMaintainSystem/case/ReportList' component, null redirect, 
'{ title: "案件查询", icon: "solution"}' meta, '12' supMenuid,0 visible,15 showOrder
from dual where not EXISTS (select 1 from menu where id='15');