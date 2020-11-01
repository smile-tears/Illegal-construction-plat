insert into user (id,username,password,name) 
SELECT replace(uuid(),'-','') as id,'admin' as username,'36e9740c2a79ea71e1493eaaea4003fa' as password,'系统管理员' as name
from dual where not EXISTS (select 1 from user where username='admin');

insert into menu (id,menuName,supMenuid,visible) 
SELECT replace(uuid(),'-','') as id,'根目录' as menuname,'0' as supMenuid,0 as visible 
from dual where not EXISTS (select 1 from menu where supMenuid='0');

insert into subcompany (id,subcompanyname,supsubcompanyid) 
SELECT replace(uuid(),'-','') as id,'根目录' as subcompanyname,'0' as supsubcompanyid 
from dual where not EXISTS (select 1 from subcompany where supsubcompanyid='0');

insert into questiontype (id,typename,pid) 
SELECT replace(uuid(),'-','') as id,'根目录' as typename,'0' as pid 
from dual where not EXISTS (select 1 from questiontype where pid='0');

insert into gridcommunity (id,gridname,pid) 
SELECT replace(uuid(),'-','') as id,'根目录' as gridname,'0' as pid 
from dual where not EXISTS (select 1 from gridcommunity where pid='0');

insert into sample (id,sampleName,supSampleId) 
SELECT replace(uuid(),'-','') as id,'根目录' as sampleName,'0' as supSampleId 
from dual where not EXISTS (select 1 from sample where supSampleId='0');

CREATE TABLE if not EXISTS `test_orders` (  
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `orderNo` varchar(25) NOT NULL DEFAULT '',  
  `orderName` char(10) NOT NULL DEFAULT '',  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;