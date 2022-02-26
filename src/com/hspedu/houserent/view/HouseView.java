package com.hspedu.houserent.view;

import com.hspedu.houserent.domain.House;
import com.hspedu.houserent.service.HouseService;
import com.hspedu.houserent.utils.Utility;

/**
 * 1.显示界面
 * 2.接收用户的输入
 * 3.调用HouseView完成对房屋信息对各种操作
 */

public class HouseView {

    private boolean loop = true;//控制显示菜单
    private char key = ' ';//接收用户选择
    private HouseService houseservice = new HouseService(10);//设置数组对大小为10


    //根据id修改房屋信息
    public void update(){
        System.out.println("==========修改房屋信息==========");
        System.out.println("请选择待修改房屋的编号(-1退出):");
        int updateId = Utility.readInt();
        if (updateId == -1){
            System.out.println("你放弃了修改房屋信息");
            return;
        }
        //根据输入得到的updateId,查找对象
        House house = houseservice.findById(updateId);//返回的是引用类型
        if (house == null){
            System.out.println("===========你要修改的房屋信息不存在无法修改===========");
            return;
        }
        System.out.print("姓名("+ house.getName()+"): ");
            String name = Utility.readString(8,"");//如果用户直接回撤表示不修改该信息 默认返回空串
            if (!"".equals(name)){
                house.setName(name);
            }
        System.out.println("电话("+house.getPhone()+")");
            String phone = Utility.readString(12,"");
            if (!"".equals(phone)){
                house.setPhone(phone);
            }
        System.out.println("地址("+house.getAddress()+")");
            String address = Utility.readString(18,"");
            if (!"".equals(address)){
                house.setAddress(address);
            }
        System.out.println("租金("+house.getRent()+")");
            int rent = Utility.readInt(-1);
            if (rent != -1){
                house.setRent(rent);
            }
        System.out.println("状态("+house.getState()+")");
            String state = Utility.readString(3,"");
            if (!"".equals(state)){
                house.setState(state);
            }
        System.out.println("==========修改房屋信息成功==========");
    }

    //根据id查找房屋信息
    public void findHouse(){
        System.out.println("=========查找房屋信息=========");
        System.out.println("请输入要查找的id");
        int findId = Utility.readInt();
        House house = houseservice.findById(findId);
        if(house != null){
            System.out.println(house);
        }else {
            System.out.println("==========查找的房屋信息不存在==========");
        }
    }
    //完成退出确认
    public void exit() {
        //这里我们使用Utility提供方法，完成确认
        char c = Utility.readConfirmSelection();
        if (c == 'Y'){
            loop = false;
        }
    }

    //编写delHouse();接收输入的id，Service的del方法
    public void delHouse(){
        System.out.println("==========删除房屋信息==========");
        System.out.println("请输入待删除房屋的编号(-1退出)：");
        int delId = Utility.readInt();
        if(delId == -1){
            System.out.println("你已经放弃了删除房屋信息");
            return;//结束一个方法 break是结束一个循环
        }
        char choice = Utility.readConfirmSelection();//小写的y可以转换为大写的Y，可以用一个条件就可以搞定
        //上面的方法 本身就有循环判断的逻辑，必须输入Y/N
        if (choice == 'Y'){//真的要删除了
            if (houseservice.del(delId)){
                System.out.println("===========删除房屋信息成功============");
            }else{
                System.out.println("==========房屋编号不存在，删除失败==========");
            }
        }else{
            System.out.println("===========放弃删除房屋信息==========");
        }
    }

    //编写addHouse()接收输入，创建house对象，调用add方法
    public void addHouse(){
        System.out.println("==========房屋列表==========");
        System.out.println("姓名：");
        String name = Utility.readString(8);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("地址：");
        String address = Utility.readString(16);
        System.out.println("月租：");
        int rent = Utility.readInt();
        System.out.println("状态：");
        String state = Utility.readString(3);
        //创建一个新的House对象，注意id是系统分配的，用户不能输入
        House newHouse = new House(0,name,phone,address,rent,state);
        if (houseservice.add(newHouse)){
            System.out.println("==========添加房屋成功==========");
        }else{
            System.out.println("==========添加房屋失败==========");
        }
    }


    //编写listHouses()显示房屋列表
    public void listHouses(){
        System.out.println("==========房屋列表==========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseservice.list();//得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null){
                //如果为null 就不要再显示了
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("==========房屋列表显示完毕==========");
    }
    //显示一个主菜单
    public void mainMenu(){
        do {
            System.out.println("\n==========房屋出租系统菜单==========");
            System.out.println("\t\t 1 新 增 房 源");
            System.out.println("\t\t 2 查 找 房 屋");
            System.out.println("\t\t 3 删 除 房 屋 信 息");
            System.out.println("\t\t 4 修 改 房 屋 信 息");
            System.out.println("\t\t 5 房 屋 列 表");
            System.out.println("\t\t 6 退      出");
            System.out.println("请输入你对选择(1-6): ");
            key = Utility.readChar();
            switch (key) {
                case '1' :
                   addHouse();
                    break;
                case '2' :
                    findHouse();
                    break;
                case '3' :
                    delHouse();
                    break;
                case '4' :
                    update();
                    break;
                case '5' :
                   listHouses();
                    break;
                case '6' :
                    exit();
                    loop = false;
                    break;
            }
        }while(loop);
    }
}