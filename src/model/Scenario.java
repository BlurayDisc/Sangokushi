/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RuN
 */
public class Scenario extends Database
{    
    int year;
    
    public Scenario()
    {
        super();
    }
        
    public void initScenario1()
    {
        year = 251;
        // 载入英雄集结
        
        //   * 银两
        //   * 粮草
        //   * 势力初始城市
        //   * 城市兵力
        //   * 城市人口
        //   * 城市武将名单
        
        
        // 曹操势力
        caocaoForce.setGold(1000);
        caocaoForce.setFood(20000);
        caocaoForce.add(xuchang);
        xuchang.setSoldiers(20000);
        xuchang.setPopulation(50);
        xuchang.add(guojia);
        xuchang.add(lejin);
        xuchang.add(guohuai);
        xuchang.add(xiahouwei);
        xuchang.add(xiahouyuan);
        xuchang.add(xiahouen);
        xuchang.add(xiahouhe);
        xuchang.add(xiahouhui);
        xuchang.add(xiahoushang);
        xuchang.add(xiahoude);
        xuchang.add(xiahoudun);
        xuchang.add(xiahouba);
        xuchang.add(xiahoumao);
        xuchang.add(xizhicai);
        xuchang.add(xuchu);
        xuchang.add(xunyu);
        xuchang.add(xunyou);
        xuchang.add(zhonghui);
        xuchang.add(zhongyao);
        xuchang.add(xuhuang);
        xuchang.add(caorui);
        xuchang.add(caoxiu);
        xuchang.add(caoang);
        xuchang.add(caohong);
        xuchang.add(caochun);
        xuchang.add(caozhang);
        xuchang.add(caozhi);
        xuchang.add(caozhen);
        xuchang.add(caoren);
        xuchang.add(caocao);
        xuchang.add(caochong);
        xuchang.add(caopi);
        xuchang.add(chenqun);
        xuchang.add(chentai);
        xuchang.add(chengyu);
        xuchang.add(dianwei);
        xuchang.add(manchong);
        xuchang.add(lidian);
        xuchang.add(liuxun);
        xuchang.add(lejin);
        xuchang.add(caoyu);
        xuchang.add(caoshuang);
        xuchang.add(caofang);
        xuchang.add(chengwu);
        xuchang.add(dianman);
        xuchang.add(caomao);
        xuchang.add(caohuan);
        xuchang.add(guoshi);
        xuchang.add(caojie);
        xuchang.add(bianshi);
        xuchang.add(yujin);
        
        xuchang.addU(xiahouwei);
        
        // 刘备势力        
        liubeiForce.setGold(500);
        liubeiForce.setFood(15000);
        liubeiForce.add(jiangling);
        jiangling.setSoldiers(10000);
        jiangling.setPopulation(30);
        jiangling.add(liubei);
        
        // 孙权势力
        sunquanForce.setGold(1000);
        sunquanForce.setFood(20000);
        sunquanForce.add(lujiang);
        lujiang.setSoldiers(15000);
        lujiang.setPopulation(50);
        lujiang.add(sunquan);
        
        // 刘表势力
        liubiaoForce.setGold(1500);
        liubiaoForce.setFood(20000);
        liubiaoForce.add(xiangyang);
        xiangyang.setSoldiers(12000);
        xiangyang.setPopulation(100);
        xiangyang.add(liubiao);
        
        // 袁绍势力
        yuanshaoForce.add(nanpi);
        nanpi.add(yuanshao);
        
        // 刘焉势力
        liuyanForce.add(chengdu);
        chengdu.add(liuyan);
        
        // 马腾势力
        matengForce.add(xiliang);
        xiliang.add(mateng);
        
        // 公孙瓒势力
        gongsunzanForce.add(beiping);
        beiping.add(gongsunzan);
        
        // 吕布势力
        lvbuForce.add(puyang);
        puyang.add(lvbu);
        
        // 董卓势力
        dongzhuoForce.add(tianshui);
        tianshui.add(dongzhuo);
        
        // 张角势力
        zhangjiaoForce.add(pingyuan);
        pingyuan.add(zhangjiao);
        
        // 司马懿势力
        simayiForce.add(changan);
        changan.add(simayi);
        
        // 何进势力
        hejinForce.add(luoyang);
        luoyang.add(hejin);
        
        // 14 孟获势力
        menghuoForce.add(yunnan);
        
        // 15 张济势力
        zhangjiForce.add(wan);
       
        // 16 袁术势力
        yuanshuForce.add(shouchun);
        
        // 17 张鲁势力
        zhangluForce.add(hanzhong);
        // 18 公孙度势力
        gongsunduForce.add(xiangping);
        // 19 陶谦势力
        taoqianForce.add(xiapi);
        // 20 孔融势力
        kongrongForce.add(beihai);
        // 21 金旋势力
        jinxuanForce.add(wuling);
        // 22 韩玄势力
        hanxuanForce.add(changsha);
        // 23 刘度势力
        liuduForce.add(lingling);
        // 24 赵范势力
        zhaofanForce.add(guiyang);
        // 25 韩馥势力
        hanfuForce.add(ye);
        // 26 丁原势力
        dingyuanForce.add(jinyang);
        // 27 刘虞势力
        liuyuForce.add(ji);
        // 28 刘繇势力
        liuyaoForce.add(jianye);
        // 29 严白虎势力
        yanbaihuForce.add(wu);
        wu.setPopulation(30);
        // 30 王朗势力
        wanglangForce.add(kuaiji);
        kuaiji.setPopulation(30);
        
        // 31 中立势力
        neutralForce.add(xinye);
        
        neutralForce.add(shangyong);
        
        neutralForce.add(jiangxia);
        
        neutralForce.add(chaisang);
        chaisang.setPopulation(40);
        
        neutralForce.add(chenliu);
        
        neutralForce.add(runan);
        
        neutralForce.add(xiaopei);
        
        neutralForce.add(yongan);
        
        neutralForce.add(jiangzhou);
        
        neutralForce.add(zitong);
        
    }
    
    public void initScenario2()
    {
        year = 200;
            
        // 载入群雄割据
        getForceList().clear();
        getForceList().add(caocaoForce);
        getForceList().add(liubeiForce);
        getForceList().add(neutralForce);
        
        neutralForce.getCityList().addAll(getCityList());
        neutralForce.getCityList().remove(xuchang);
        neutralForce.getCityList().remove(chenliu);
        neutralForce.getCityList().remove(runan);
        neutralForce.getCityList().remove(puyang);
        neutralForce.getCityList().remove(xiapi);
        neutralForce.getCityList().remove(xiaopei);
        
        caocaoForce.add(xuchang);
        caocaoForce.add(chenliu);
        caocaoForce.add(runan);
        caocaoForce.add(puyang);
        
        liubeiForce.add(xiapi);
        liubeiForce.add(xiaopei);
    }
    
    public int getYear()
    {
        return year;
    }
}
