package com.scqzy.base.controller;

import com.scqzy.base.pojo.Label;
import com.scqzy.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/13 12:55
 */
@RestController
@RequestMapping("label")
@CrossOrigin
public class BaseController {

    @Autowired
    private LabelService labelService;

    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "success", labelService.findAll());
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId) {
        return new Result("查询成功", labelService.findById(labelId));
    }

    @PostMapping()
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result("添加成功");
    }

    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        labelService.update(labelId, label);
        return new Result("修改成功");
    }

    @DeleteMapping("/{labelId}")
    public Result delete(@PathVariable("labelId") String labelId) {
        labelService.delete(labelId);
        return new Result("删除成功");
    }

    @PostMapping("/search")
    public Result search(Label label) {
        return new Result("查询成功", labelService.search(label));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchByPage(Label label, @PathVariable int page, @PathVariable int size) {
        return new Result("查询成功", labelService.searchByPage(label,page,size));
    }
}
