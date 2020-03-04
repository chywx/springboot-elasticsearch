package cn.chendahai.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/3/2 0002
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;

    private String name;

    private Integer age;

    private Date scheduledDate = new Date();

    private String birthday;

    private Long scheduledLong = System.currentTimeMillis();

    private Map<String,Son> sonMap;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public static List<Person> people = new ArrayList<Person>() {
        {
            add(new Person(111, "dong zaizi", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(112, "fand ong", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(113, "dong", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(114, "dong", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(115, "wu guan jia", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(116, "ge ge", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(117, "jia jia", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
            add(new Person(118, "xiao ge", getRandomAge(), new Date(), dateFormat.format(new Date()), System.currentTimeMillis(), null));
        }
    };

    public static Integer getRandomAge() {
        return (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        System.out.println(people);
    }


}
