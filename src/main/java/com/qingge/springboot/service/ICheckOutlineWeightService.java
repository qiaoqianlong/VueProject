package com.qingge.springboot.service;

import com.qingge.springboot.entity.CheckOutlineWeight;
import com.baomidou.mybatisplus.extension.service.IService;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
public interface ICheckOutlineWeightService extends IService<CheckOutlineWeight> {

    List<CheckOutlineWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId);
}
