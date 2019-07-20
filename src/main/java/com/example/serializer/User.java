package com.example.serializer;

import java.util.List;

/**
 * 描述:
 *
 * @author qianyue
 * @date 2019-07-20 上午11:01
 */
public class User {

    private String userName;
    private Integer age;
    private List<String> likeList;

    public List<String> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<String> likeList) {
        this.likeList = likeList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", likeList=" + likeList +
                '}';
    }
}