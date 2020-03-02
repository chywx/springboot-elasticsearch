package cn.chendahai.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Match {

    private Integer id;

    private String name;

    private Integer age;

    private Date scheduledDate = new Date();

    private String birthday;

    private Long scheduledLong = System.currentTimeMillis();

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static Integer randomAge = (int) (Math.random() * 100);


    public static List<Match> matches = new ArrayList<Match>() {
        {
            add(new Match(101, "dongzaizi", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(102, "fandong", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(103, "dong", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(104, "dong", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(105, "wuguanjia", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(106, "gege", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(107, "jiajia", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
            add(new Match(108, "xiaoge", randomAge, new Date(), dateFormat.format(new Date()), System.currentTimeMillis()));
        }
    };

    public static void main(String[] args) {
        System.out.println(matches);
    }


}
