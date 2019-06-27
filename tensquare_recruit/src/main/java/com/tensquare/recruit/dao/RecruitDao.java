package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    // 根据状态查询
	      public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String states);
//    根据最新职位列表
           public List<Recruit> findTop12ByStateOrderByCreatetimeDesc(String states);
}
