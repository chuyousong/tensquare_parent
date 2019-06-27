package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Optional;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    // 查询全部记录
    public List<Spit> findAll(){
        return  spitDao.findAll();
    }

    // 根据主键查询实体
    public Spit findById(String spitId){
        Spit spit = spitDao.findById(spitId).get();
        return  spit;
    }

    // 新增
    public  void  add(Spit spit){
        spit.set_id(idWorker.nextId()+"");// 主键值
        spitDao.save(spit);
    }

    // 修改
    public  void  update(Spit spit){
        spitDao.save(spit);
    }

    // 删除
    public void deleteById(String spitId){
        spitDao.deleteById(spitId);
    }






}
