package com.scqzy.spit.controller;

import com.scqzy.spit.pojo.Spit;
import com.scqzy.spit.service.SpitService;
import com.scqzy.entity.Result;
import com.scqzy.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/14 19:31
 */
@RestController
@CrossOrigin
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "success", spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable("spitId") String spitId) {
        return new Result("查询成功", spitService.findById(spitId));
    }

    @PostMapping()
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result("添加成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable("spitId") String spitId, @RequestBody Spit spit) {
        spitService.update(spitId, spit);
        return new Result("修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable("spitId") String spitId) {
        spitService.deleteById(spitId);
        return new Result("删除成功");
    }

    @GetMapping("comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        return new Result(spitService.findByParentid(parentid, page, size));
    }

    @PutMapping("thumbup/{spitid}")
    public Result thumbup(@PathVariable String spitid) {
        return spitService.thumbup(spitid);
    }
}
