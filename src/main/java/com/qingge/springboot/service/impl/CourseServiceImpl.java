package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.controller.dto.CourseReviewDTO;
import com.qingge.springboot.entity.CheckOutlineWeight;
import com.qingge.springboot.entity.Course;
import com.qingge.springboot.mapper.CourseMapper;
import com.qingge.springboot.mapper.CourseScheduleMapper;
import com.qingge.springboot.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private CourseMapper courseMapper;


    @Resource
    private CourseScheduleMapper courseScheduleMapper;

    @Override
    public Page<Course> findPage(Page<Course> page, String name, String type) {
        return courseMapper.findPage(page, name, type);
    }

    @Override
    public List<CourseReviewDTO> coursesByTeacherId(Integer teacherId) {
        // 根据 教师id 获取所有 课程ids
        List<Integer> courseIds = courseScheduleMapper.selectByTeacherId(teacherId);
        // 获取课程ids数组中不重复的元素
        List<Integer> newCourseIds = courseIds.stream().distinct().collect(Collectors.toList());
        // 根据 课程ids 获取 教师的所有课程
        List<CourseReviewDTO> teacherCourses = new ArrayList<>();
        for (Integer id: newCourseIds) {
            CourseReviewDTO courseReviewDTO = new CourseReviewDTO();
            courseReviewDTO.setCourseId(id);
            courseReviewDTO.setCourseName(courseMapper.getCourseNameById(id));
            courseReviewDTO.setStatus(courseScheduleMapper.getStatusByIds(teacherId,id));
            teacherCourses.add(courseReviewDTO);
        }
        return teacherCourses;
    }

    @Override
    public List<String> findCourseTypes() {
        return courseMapper.findCourseTypes();
    }



}
