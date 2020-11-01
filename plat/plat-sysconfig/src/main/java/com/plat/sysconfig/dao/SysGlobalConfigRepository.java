package com.plat.sysconfig.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.sysconfig.entity.SysGlobalConfig;

@Repository
public interface SysGlobalConfigRepository extends JpaRepository<SysGlobalConfig,String> {

}
