package com.youlai.boot.utils.poi;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gyy
 * @since 2025/3/15 20:26
 */
public class ExcelUtilsTest {
    @Test
    public void testReadExcel() {
        try {
            // 假设学生Excel文件路径为：students.xlsx
            File excelFile = new File("D:\\git-project\\vehicle\\doc\\test.xlsx");

            // 读取数据并转化为Student对象
            List<Student> students = ExcelUtils.readExcel(excelFile, Student.class);

            // 验证读取的数据是否正确
            assertNotNull(students);
            assertEquals(2, students.size());

            // 第一个学生数据验证
            Student student1 = students.get(0);
            assertEquals("张三", student1.getName());
            assertEquals(20, student1.getAge());
            assertNotNull(student1.getBirthDate());

            // 第二个学生数据验证
            Student student2 = students.get(1);
            assertEquals("李四", student2.getName());
            assertEquals(22, student2.getAge());
            assertNotNull(student2.getBirthDate());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
