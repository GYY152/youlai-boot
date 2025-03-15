package com.youlai.boot.utils.poi;

import lombok.Data;

import java.util.Date;

/**
 * @author gyy
 * @since 2025/3/15 20:25
 */
@Data
public class Student {
    private String name;
    private int age;
    private Date birthDate;

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", birthDate=" + birthDate + "}";
    }

}
