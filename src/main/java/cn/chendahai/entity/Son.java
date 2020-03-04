package cn.chendahai.entity;

import lombok.Data;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/3/3 0003
 */
@Data
public class Son {

    private String name;

    private Integer age;

    public Son() {
    }

    public Son(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
