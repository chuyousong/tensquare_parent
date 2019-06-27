package com.tensquare.spit;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class controller {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;
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

    // 根据上级Id查询吐槽列表
    @GetMapping(value = "/comment/{parentid}/{page}/{size}")
    public  Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size ){
        Page<Spit> pageList = spitService.findByParentid(parentid, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent() ) );
    }

    @PutMapping(value = "/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable String spitId){
        String userid="111";
        if(redisTemplate.opsForValue().get("updateThumbup_"+userid) != null){
            return new Result(false,StatusCode.ERROR,"不能重复点赞");
        }
        spitService.updateThumbup(spitId);
        redisTemplate.opsForValue().set("updateThumbup_"+userid,1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }


}
