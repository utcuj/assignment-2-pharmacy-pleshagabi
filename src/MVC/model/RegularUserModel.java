package MVC.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by plesha on 07-Apr-18.
 */
@XStreamAlias("model.RegularUserModel")
public class RegularUserModel {
    @XStreamAlias("id")
    private  int id;
    @XStreamAlias("name")
    private String name;
    @XStreamAlias("age")
    private int age;
    @XStreamAlias("username")
    private String username;
    @XStreamAlias("password")
    private String password;


    public RegularUserModel(){

    }
    public RegularUserModel( int id, String name, int age, String username, String password ){
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
