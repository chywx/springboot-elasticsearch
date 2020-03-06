package cn.chendahai.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/3/2 0002
 */
public class Person {

    private Integer id;

    private String name;

    private Integer age;

    private Date scheduledDate = new Date();

    private String birthday;

    private List<Son> sonList;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public static List<Person> people = new ArrayList<Person>() {
        {
            add(new Person(111, "dong zai zi", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(112, "fan dong", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(113, "dong dong", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(114, "dong", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(115, "wu guan jia", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(116, "ge ge", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(117, "jia ye", getRandomAge(), new Date(), dateFormat.format(new Date())));
            add(new Person(118, "xiao ge", getRandomAge(), new Date(), dateFormat.format(new Date())));
        }
    };

    public static Integer getRandomAge() {
        return (int) (Math.random() * 100);
    }

    public Person() {
    }

    public Person(Integer id, String name, Integer age, Date scheduledDate, String birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.scheduledDate = scheduledDate;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Son> getSonList() {
        return sonList;
    }

    public void setSonList(List<Son> sonList) {
        this.sonList = sonList;
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(DateFormat dateFormat) {
        Person.dateFormat = dateFormat;
    }

    public static List<Person> getPeople() {
        return people;
    }

    public static void setPeople(List<Person> people) {
        Person.people = people;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", scheduledDate=" + scheduledDate +
            ", birthday='" + birthday + '\'' +
            ", sonList=" + sonList +
            '}';
    }

    public static void main(String[] args) {
        System.out.println(people);
    }


}
