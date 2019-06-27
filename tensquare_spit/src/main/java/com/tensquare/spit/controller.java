package com.tensquare.spit;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class controller {

    @Autowired
    private SpitService spitService;

    // 查询所有的数据
    @GetMapping
    public Result findAll(){
        return  new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    //根据id查询
    @GetMapping(value = "/{spitId}")
    public Result findById(@PathVariable  String spitId){
          return  new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    // 新增
    @PostMapping
    public  Result Add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    // 修改
    @PutMapping(value = "/{spitId}")
    public Result update(@RequestBody Spit spit,@PathVariable String spitId){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    // 删除
    @DeleteMapping(value = "/{spitId}")
    public  Result deleteById(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return  new Result(true,StatusCode.OK,"删除成功");
    }


}
