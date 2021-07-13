package com.scqzy.base.service;

import com.scqzy.base.dao.LabelDao;
import com.scqzy.base.pojo.Label;
import entity.PageResult;
import exception.NoNecessaryFieldException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/13 13:14
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).orElse(new Label());
    }

    public void save(Label label) {
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }

    public void update(String labelId, Label label) {
        if (StringUtils.isBlank(labelId)) {
            throw new NoNecessaryFieldException("labelId missed");
        }
        label.setId(labelId);
        labelDao.save(label);
    }

    public void delete(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> search(Label label) {
        return labelDao.findAll(createSpecification(label));
    }

    private Specification<Label> createSpecification(Label label) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(label.getLabelName())) {
                Predicate labelName = cb.like(root.get("labelName").as(String.class), "%" + label.getLabelName() + "%");
                list.add(labelName);
            }
            if (StringUtils.isNotBlank(label.getRecommend())) {
                Predicate recommend = cb.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%");
                list.add(recommend);
            }
            if (StringUtils.isNotBlank(label.getState())) {
                Predicate state = cb.like(root.get("state").as(String.class), "%" + label.getState() + "%");
                list.add(state);
            }
            return cb.and(list.toArray(new Predicate[0]));
        };
    }

    public PageResult<Label> searchByPage(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Label> labels = labelDao.findAll(createSpecification(label), pageable);
        return new PageResult<>(labels.getTotalElements(),labels.getContent(),labels.getTotalPages());
    }
}
