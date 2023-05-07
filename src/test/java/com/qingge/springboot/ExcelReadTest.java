package com.qingge.springboot;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExcelReadTest {
    String PATH = "C:\\Users\\wano\\Desktop\\毕设资料\\技术Demo\\Excel读写\\myExcel\\src\\main\\resources\\static\\testData\\";

    @Test
    public void simpleRead() {
        // 读取文件
        // 创建ExcelReaderBuilder实例
        ExcelReaderBuilder readerBuilder = EasyExcel.read();
        // 获取文件对象
        readerBuilder.file(PATH+"课程体系.xlsx");
        // 指定sheet
        readerBuilder.sheet();
        // 自动关闭输入流
        readerBuilder.autoCloseStream(true);
        // 设置文件格式
        readerBuilder.excelType(ExcelTypeEnum.XLSX);
        // 注册监听器进行数据解析
        readerBuilder.registerReadListener(new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                // 一行数据读取完成后进行回调， 一行数据封装成一个对象后回调
                System.out.println(o);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                // 文件读取完毕日志
                System.out.println("文件读取完毕!");
            }
        });
        // 构建读取器
        ExcelReader reader = readerBuilder.build();
        // 读取数据
        reader.readAll();
        // 读取完毕
        reader.finish();
    }
}
