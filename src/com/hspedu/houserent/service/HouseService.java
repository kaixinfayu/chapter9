package com.hspedu.houserent.service;

import com.hspedu.houserent.domain.House;

/**
 * 1.定义House[]，保存House对象
 * 2.相应HouseView的调用
 * 3.完成对房屋信息对各种操作（crud == 增删改查）
 */
public class HouseService {
    private House[] houses;//保存House对象
    private int houseNums = 1;//记录当前有多少个房屋信息
    private int idCounter = 1;//记录当前到id增长到哪个值了



    public HouseService(int size) {
        //new houses
        houses = new House[size];//当创建HouseService对象当时候，指定数组当大小
        //为了配合测试列表，这里初始化一个House对象
        houses[0] = new House(1,"jack","112","海淀区",2000,"未出租");
    }

    //find()，返回House对象或者null
    public House findById(int findId){
        //遍历数组
        for (int i = 0; i < houseNums; i++) {
            if (findId == houseNums){
                return houses[i];
            }
        }
        return null;
    }

    //del方法，删除掉一个房屋信息
    public boolean del(int delId) {

        //应当先找到要删除的房屋信息对应的下标
        //强调：一定要搞清楚 下标和房屋的编号不是一回事
        int index = -1;
        for (int i = 0; i <houseNums;i++){
            if (delId == houses[i].getId()){//要删除的房屋对应的id，再数组下标为i的元素
                index = i;//使用index记录下i

            }
        }
        if (index == -1){//说明delId再数组中不存在
            return false;

        }
        //如果找到
        //思路分析：
        for (int i = index; i < houseNums - 1 ; i++) {
            houses[i] = houses[i + 1];

        }
        houses[--houseNums] = null;//把当前存在的房屋信息的最后一个设置为null,不是说把数组最后一个置空

        return true;

    }

    //add();添加新对象 返回boolean
    public boolean add(House newHouse){
        //判断是否还可以继续添加 我们暂时不考虑数组扩容的问题
        if (houseNums == houses.length){
            System.out.println("数组已满，不能再添加了");
            return false;
        }
        //把newHouse对象加入到
        houses[houseNums++] = newHouse;//先给值再自增
        //我们需要设计一个id自增长到机制
        newHouse.setId(++idCounter);
        return true;
    }

    //list方法，返回house
    public House[] list(){
        return houses;
    }
}
