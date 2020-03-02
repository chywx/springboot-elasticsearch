package cn.chendahai.entity;

import java.util.Date;
import lombok.Data;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/3/2 0002
 */
@Data
public class Match {

    private Integer id;

    private String name;

    private Integer age;

    private Date bir = new Date();

    private Long scheduled = System.currentTimeMillis();


}
