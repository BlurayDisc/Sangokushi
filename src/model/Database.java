/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.GameParameters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RuN
 * @version 1.5
 * @since 03/09/2014
 * 
 */  

// 势力编号
// -----------------------
// 1 曹操
// 2 刘备
// 3 孙权
// 4 刘表
// 5 袁绍
// 6 刘焉 liuyan
// 7 马腾
// 8 公孙瓒
// 9 吕布
// 10 董卓
// 11 张角
// 12 司马懿
// 13 何进
// 14 孟获
// 15 张济
// 16 袁术
// 17 张鲁
// 18 公孙度
// 19 陶谦
// 20 孔融
// 21 金旋
// 22 韩玄
// 23 刘度
// 24 赵范
// 25 韩馥 hanfu
// 26 丁原
// 27 刘虞 liuyu
// 28 刘繇 liuyao
// 29 严白虎
// 30 王朗

// 城市编号
// ------------------------
// 1 襄平
// 2 北平
// 3 蓟
// 4 晋阳
// 5 邺
// 6 南皮
// 7 平原
// 8 北海
// 9 洛阳
// 10 许昌
// 11 陈留
// 12 濮阳
// 13 小沛
// 14 下邳
// 15 汝南
// 16 寿春
// 17 宛
// 18 西凉
// 19 天水
// 20 汉中
// 21 长安
// 22 庐江
// 23 建业
// 24 吴
// 25 柴桑
// 26 会稽
// 27 新野
// 28 襄阳
// 29 上庸
// 30 江夏
// 31 江陵
// 32 武陵
// 33 长沙
// 34 零陵
// 35 桂阳
// 36 梓潼
// 37 成都
// 38 江州
// 39 永安
// 40 云南

public class Database implements GameParameters
{
    private List<Character> characterList;
    private List<City> cityList;
    private List<Force> forceList;
    
    
    // 武将 按编号

    /**
     * 阿会喃
     */
    protected final Character ahuinan; // 1

    /**
     * 伊籍
     */
    protected final Character yiji;

    /**
     * 尹赏
     */
    protected final Character yinshang;

    /**
     * 尹默
     */
    protected final Character yinmo;
    
    protected final Character yujin;
    protected final Character weiguan;
    protected final Character yuanyin;
    protected final Character yuanxi;
    protected final Character yanxing;
    protected final Character yanrou;
    protected final Character yuanshu;
    protected final Character yuanshang;
    protected final Character yuanshao;
    protected final Character yanxiang;
    protected final Character yuantan;
    protected final Character yanpu;
    protected final Character wangwei;
    protected final Character wangyun;
    protected final Character wangkang;
    protected final Character wangcan;
    protected final Character wangxiu;
    protected final Character wangsu;
    protected final Character wangjun;
    protected final Character wangshuang;
    protected final Character wangchang;
    protected final Character wangping;
    protected final Character wangfu;
    protected final Character wangmen;
    protected final Character wangling;
    protected final Character wanglei;
    protected final Character wanglang;
    protected final Character wenhui;
    protected final Character heyan;
    protected final Character kuaiyue;
    protected final Character kuailiang;
    protected final Character jiahua;
    protected final Character jiakui;
    protected final Character heyi;
    protected final Character huaxin;
    protected final Character jiaxu;
    protected final Character guojia;
    protected final Character ehuan;
    protected final Character guosi;
    protected final Character yuejiu;
    protected final Character huojun;
    protected final Character haozhao;
    protected final Character lejin;
    protected final Character guotu;
    protected final Character haomeng;
    protected final Character guoyouzhi;
    protected final Character huoyi;       // 50
    protected final Character guohuai;
    protected final Character xiahouwei;
    protected final Character xiahouyuan;
    protected final Character xiahouen;
    protected final Character xiahouhe;
    protected final Character xiahouhui;
    protected final Character xiahouxuan;
    protected final Character xiahoushang;
    protected final Character xiahoude;
    protected final Character xiahoudun;
    protected final Character xiahouba;
    protected final Character xiahoumao;
    protected final Character jiachong;
    protected final Character hejin;
    protected final Character huaxiong;
    protected final Character hanyin;
    protected final Character guanyu;
    protected final Character huanjie;
    protected final Character guanhai;
    protected final Character guanqiujian;
    protected final Character hanxuan;
    protected final Character hanhao;
    protected final Character guanxing;
    protected final Character guansuo;
    protected final Character hansui;
    protected final Character haosong;
    protected final Character guanjing;
    protected final Character hanxian;
    protected final Character kanze;
    protected final Character characterhanzhong;
    protected final Character handang;
    protected final Character hande;
    protected final Character ganning;
    protected final Character hanfu;
    protected final Character guanping;
    protected final Character jianyong;
    protected final Character yanliang;
    protected final Character weiyan;
    protected final Character quyi;
    protected final Character xizhicai;
    protected final Character weixu;
    protected final Character weiyou;
    protected final Character niujin;
    protected final Character niufu;
    protected final Character jiangwei;
    protected final Character gongzhi;
    protected final Character qiaorui;
    protected final Character fandu;
    protected final Character xugong;
    protected final Character xujing;       // 100
    protected final Character xuchu;
    protected final Character xuyou;
    protected final Character jiling;
    protected final Character jinyi;
    protected final Character jinhuansanjie;
    protected final Character jinxuan;
    protected final Character yufan;
    protected final Character xingdaorong;
    protected final Character yanyan;
    protected final Character yangang;
    protected final Character yanjun;
    protected final Character yanzheng;
    protected final Character yanbaihu;
    protected final Character yanyu;
    protected final Character yanyi;
    protected final Character huanggai;
    protected final Character gaogan;
    protected final Character huangquan;
    protected final Character gaorou;
    protected final Character gaoshun;
    protected final Character gaoxiang;
    protected final Character gaosheng;
    protected final Character houcheng;
    protected final Character houxuan;
    protected final Character guangzu;
    protected final Character gongsunyue;
    protected final Character gongsunyuan;
    protected final Character gongsungong;
    protected final Character gongsunkang;
    protected final Character gongsunzan;
    protected final Character gongsunxu;
    protected final Character gongsundu;
    protected final Character gongsunfan;
    protected final Character huangzhong;
    protected final Character gaoding;
    protected final Character gaopei;
    protected final Character huangpusong;
    protected final Character kongrong;
    protected final Character gaolan;
    protected final Character wujing;
    protected final Character hucheer;
    protected final Character huzhen;
    protected final Character wutugu;
    protected final Character huban;
    protected final Character wuban;
    protected final Character guyong;
    protected final Character wulan;
    protected final Character caihe;
    protected final Character caizhong;
    protected final Character caimao;       // 150
    protected final Character zerong;
    protected final Character simayi;
    protected final Character simayan;
    protected final Character simashi;
    protected final Character simazhao;
    protected final Character simafu;
    protected final Character simalang;
    protected final Character shamoke;
    protected final Character zhouxin;
    protected final Character zhouang;
    protected final Character zhouzhi;
    protected final Character zhoucang;
    protected final Character zhoutai;
    protected final Character zhoufang;
    protected final Character zhouyu;
    protected final Character zhuhuan;
    protected final Character zhuju;
    protected final Character zhurong;
    protected final Character zhujun;
    protected final Character zhuran;
    protected final Character zhuzhi;
    protected final Character zhubao;
    protected final Character zhuling;
    protected final Character xunyu;
    protected final Character chunyuqiong;
    protected final Character xunshen;
    protected final Character xunyou;
    protected final Character jiangwan;
    protected final Character zhonghui;
    protected final Character jianggan;
    protected final Character jiangyiqu;
    protected final Character jiangqin;
    protected final Character jiangji;
    protected final Character qiaozhou;
    protected final Character jiaochu;
    protected final Character xiangchong;
    protected final Character zhongyao;
    protected final Character xianglang;
    protected final Character xurong;
    protected final Character zhugeke;
    protected final Character zhugejin;
    protected final Character zhugejun;
    protected final Character zhugedan;
    protected final Character zhugeliang;
    protected final Character xuhuang;
    protected final Character xushu;
    protected final Character xusheng;
    protected final Character xumiao;
    protected final Character shenyi;
    protected final Character shengeng;     // 200
    protected final Character shenpei;
    protected final Character xinbi;
    protected final Character xinping;
    protected final Character qinbi;
    protected final Character qinlang;
    protected final Character jiagu;
    protected final Character dengdan;
    protected final Character chengyi;
    protected final Character chenggongying;
    protected final Character xinzong;
    protected final Character danjing;
    protected final Character quanzong;
    protected final Character caorui;
    protected final Character caoxiu;
    protected final Character songxian;
    protected final Character caoang;
    protected final Character caohong;
    protected final Character caochun;
    protected final Character caozhang;
    Character caozhi;
    Character caozhen;
    Character caoren;
    Character caoxing;
    Character caocao;
    Character caochong;
    Character jianba;
    Character caopei;
    Character caobao;
    Character jushou;
    Character sufei;
    Character zumao;
    Character sunhuan;      // 孙桓 huan
    Character sunjian;
    Character sunqian;      // 孙乾 qian
    Character sunquan;
    Character sunjiao;      // 孙皎 jiao
    Character sunce;
    Character sunshao;      // 孙韶 shao
    Character sunjing;
    Character sunzhong;
    Character sundeng;
    Character sunyu;
    Character sunyi;     // 孙翊 yi
    Character sunli;
    Character taishici;
    Character dailaidongzhu;
    Character dailing;
    Character duosidawang;
    Character tanxiong;
    Character zhangyu;      // 250
    
    Character caomao;
    // Character ;

    
    // 曹操武将
    
    Character caopi;
    Character chenqun;
    Character chentai;
    Character chengyu;
    Character dianwei;
    Character manchong;
    Character lidian;
    Character liuxun;
    Character lelin;
    Character caoyu;
    Character caoshuang;
    Character caofang;
    Character chengwu;
    Character dianman;
    Character caohuan;
    Character guoshi;
    Character caojie;
    Character bianshi;
    
    // 刘备武将
    Character huodai;
    private Character zhugeyun;
    private Character sunqi;
    private Character zhaoyun;
    protected final Character zhangfei;
    private Character zhangbao;
    private Character zhaolei;
    private Character dongyu;
    private Character donghe;
    private Character dengzhi;
    private Character maji;
    private Character maliang;
    private Character milan;
    private Character mifang;
    private Character fengxi;
    private Character fazheng;
    private Character pangtong;
    private Character liushen;
    Character liubei;
    private Character liuchan;
    private Character liufeng;
    private Character mihua;
    private Character zhaoguang;
    private Character zhangshao;
    private Character zhaotong;
    private Character huangchong;
    private Character xiahoushi;
    private Character guanyinping;
    private Character ganshi;
    private Character huangyueying;
    private Character zhangshi;
    private Character mishi;
    private Character baosanniang;

    // 孙权武将
    private Character beihua;
    private Character yanze;
    private Character zhugege;
    private Character sunheng;
    private Character sunxiao;
    private Character sunzhao;
    private Character chenwu;
    private Character chengpu;
    private Character dingfeng;
    private Character lukang;
    private Character luxun;
    private Character lingcao;
    private Character lingtong;
    private Character lvmeng;
    private Character lusu;
    private Character chenbiao;
    private Character dingfeng2;
    private Character xiaoqiao;
    private Character xushi;
    private Character sunshi;
    private Character sunshangxiang;
    private Character sunluyu;
    private Character sunluban;
    private Character daqiao;
    private Character bulianshi;
    
    protected final Character liubiao;
    protected final Character liuyan;
    protected final Character mateng;
    protected final Character lvbu;
    protected final Character dongzhuo;
    protected final Character zhangjiao;


    
    
    // 城市
    protected City xuchang;   // 许昌
    protected City luoyang;   // 洛阳
    protected City chenliu;    // 陈留
    protected City puyang;    // 濮阳
    protected City xiaopei;    // 小沛
    protected City xiapi;      // 下邳
    protected City runan;     // 汝南
    protected City shouchun;  // 寿春
    protected City wan;       // 宛
    protected final City yunnan;    // 云南
    protected City jiangzhou;  // 江州
    protected City chengdu;   //  成都
    protected City yongan;    // 永安
    protected City zitong;    // 梓潼
    protected City xiliang;     // 西凉
    protected City tianshui;    // 天水
    protected City hanzhong;     // 汉中
    protected City changan;      // 长安
    protected City xiangyang;     // 襄阳
    protected City shangyong;    // 上庸
    protected City jiangxia;       // 江夏
    protected City xinye;         // 新野
    protected City jiangling;      // 江陵
    protected City wuling;        // 武陵
    protected City changsha;      // 长沙
    protected City lingling;       // 零陵
    protected City guiyang;      // 桂阳
    protected City xiangping;     // 襄平
    protected City beiping;       // 北平
    protected City ji;             // 蓟
    protected City jinyang;       // 晋阳
    protected City ye;            // 邺
    protected City nanpi;         // 南皮
    protected City pingyuan;     // 平原
    protected City beihai;        //  北海
    protected City lujiang;       // 庐江
    protected City jianye;        // 建业
    protected City wu;          // 吴
    protected City chaisang;     // 柴桑
    protected City kuaiji;         // 会稽
    
    private Ability tiebi;
    private Ability qiangxi;
    
    // 势力
    Force caocaoForce;
    Force liubeiForce;
    Force sunquanForce;
    Force liubiaoForce;
    Force yuanshaoForce;
    Force liuyanForce;
    Force matengForce;
    Force gongsunzanForce;
    Force lvbuForce;
    Force dongzhuoForce;
    Force zhangjiaoForce;
    Force simayiForce;
    Force hejinForce;
    Force menghuoForce;
    Force zhangjiForce;
    Force yuanshuForce;
    Force zhangluForce;
    Force gongsunduForce;
    Force taoqianForce;
    Force kongrongForce;
    Force jinxuanForce;
    Force hanxuanForce;
    Force liuduForce;
    Force zhaofanForce;
    Force hanfuForce;
    Force dingyuanForce;
    Force liuyuForce;
    Force liuyaoForce;
    Force yanbaihuForce;
    Force wanglangForce;
    Force neutralForce;
    
    
    public Database() {
        // initCharacters
        // 名， 统帅， 武力， 治理， 政治
        ahuinan = new Character("阿会喃", 65, 74, 26, 33);      // 1
        yiji = new Character("伊籍", 29, 24, 80, 86);
        yinshang = new Character("尹赏");
        yinmo = new Character("尹默");
        yujin = new Character("于禁", 83, 78, 74, 57);
        weiguan = new Character("卫瓘");
        yuanyin = new Character("袁胤");
        yuanxi = new Character("袁熙");
        yanxing = new Character("阎行");
        yanrou = new Character("阎柔");
        yuanshu = new Character("袁术");       // 10
        yuanshang = new Character("袁尚");
        yuanshao = new Character("袁绍");
        yanxiang = new Character("阎象");
        yuantan = new Character("袁谭");
        yanpu = new Character("阎圃");
        wangwei = new Character("王威");
        wangyun = new Character("王允");
        wangkang = new Character("王伉");
        wangcan = new Character("王粲");
        wangxiu = new Character("王脩");       // 20
        wangsu = new Character("王肃");
        wangjun = new Character("王濬");
        wangshuang = new Character("王双");
        wangchang = new Character("王昶");
        wangping = new Character("王平");
        wangfu = new Character("王甫");
        wangmen = new Character("王门");
        wangling = new Character("王淩");
        wanglei = new Character("王累");
        wanglang = new Character("王朗");       // 30
        wenhui = new Character("温恢");
        heyan = new Character("何晏");
        kuaiyue = new Character("蒯越");
        kuailiang = new Character("蒯良");
        jiahua = new Character("贾华");
        jiakui = new Character("贾逵");
        heyi = new Character("何仪");
        huaxin = new Character("华歆");
        jiaxu = new Character("贾诩");
        guojia = new Character("郭嘉", 88, 15, 96, 84);     // 40
        ehuan = new Character("鄂焕");
        guosi = new Character("郭汜");
        yuejiu = new Character("乐就");
        huojun = new Character("霍峻");
        haozhao = new Character("郝昭");
        lejin = new Character("乐进", 80, 84, 56, 51);
        guotu = new Character("郭图");
        haomeng = new Character("郝萌");
        guoyouzhi = new Character("郭攸之");
        huoyi = new Character("霍弋");       // 50
        guohuai = new Character("郭淮", 87, 78, 81, 75);
        xiahouwei = new Character("夏侯威", 71, 73, 49, 57);
        xiahouyuan = new Character("夏侯渊", 90, 91, 68, 61);
        xiahouen = new Character("夏侯恩", 63, 72, 53, 45);
        xiahouhe = new Character("夏侯和", 48, 51, 71, 76);
        xiahouhui = new Character("夏侯惠", 50, 44, 76, 73);
        xiahouxuan = new Character("夏侯玄", 54, 39, 76, 92);
        xiahoushang = new Character("夏侯尚", 79, 72, 75, 65);
        xiahoude = new Character("夏侯德", 69, 73, 32, 40);
        xiahoudun = new Character("夏侯惇", 92, 90, 63, 70);        // 60
        xiahouba = new Character("夏侯霸", 79, 84, 77, 55);
        xiahoumao = new Character("夏侯楙", 20, 7, 4, 21);    //  字典 楙 mao
        jiachong = new Character("贾充");
        hejin = new Character("何进");
        huaxiong = new Character("华雄");
        hanyin = new Character("韩胤");
        guanyu = new Character("关羽", 97, 97, 79, 62);
        huanjie = new Character("桓阶");
        guanhai = new Character("管亥");
        guanqiujian = new Character("毌丘俭");      // 70
        hanxuan = new Character("韩玄");
        hanhao = new Character("韩浩");
        guanxing = new Character("关兴", 76, 86, 67, 58);
        guansuo = new Character("关索", 74, 88, 56, 47);
        hansui = new Character("韩遂");
        haosong = new Character("韩嵩");
        guanjing = new Character("关靖");
        hanxian = new Character("韩暹");
        kanze = new Character("阚泽");
        characterhanzhong = new Character("韩忠");      // 80
        handang = new Character("韩当");
        hande = new Character("韩德");
        ganning = new Character("甘宁", 93, 94, 76, 18);
        hanfu = new Character("韩馥");
        guanping = new Character("关平", 80, 82, 72, 60);
        jianyong = new Character("简雍");
        yanliang = new Character("颜良", 88, 94, 21, 23);
        weiyan = new Character("魏延", 85, 92, 72, 49);
        quyi = new Character("麴义", 82, 78, 51, 18);
        xizhicai = new Character("戏志才", 63, 7, 88, 73);      // 90
        weixu = new Character("魏续");
        weiyou = new Character("魏攸");
        niujin = new Character("牛金");
        niufu = new Character("牛辅");
        jiangwei = new Character("姜维", 92, 89, 90, 67);
        gongzhi = new Character("巩志");
        qiaorui = new Character("桥蕤");
        fandu = new Character("龚都");
        xugong = new Character("许贡");
        xujing = new Character("许靖");       // 100
        xuchu = new Character("许褚", 74, 96, 36, 20);
        xuyou = new Character("许攸");
        jiling = new Character("纪灵");
        jinyi = new Character("金褘");
        jinhuansanjie = new Character("金环三结");
        jinxuan = new Character("金旋");
        yufan = new Character("虞翻");
        xingdaorong = new Character("邢道荣");
        yanyan = new Character("严颜", 82, 83, 72, 67);
        yangang = new Character("严纲");        // 110
        yanjun = new Character("严畯");
        yanzheng = new Character("严政");
        yanbaihu = new Character("严白虎");
        yanyu = new Character("严舆");
        yanyi = new Character("严懿");
        huanggai = new Character("黄盖");
        gaogan = new Character("高干");
        huangquan = new Character("黄权");
        gaorou = new Character("高柔");
        gaoshun = new Character("高顺");        // 120
        gaoxiang = new Character("高翔");
        gaosheng = new Character("高昇");
        houcheng = new Character("侯成");
        houxuan = new Character("候选");
        guangzu = new Character("黄祖");
        gongsunyue = new Character("公孙越");
        gongsunyuan = new Character("公孙渊");
        gongsungong = new Character("公孙恭");
        gongsunkang = new Character("公孙康");
        gongsunzan = new Character("公孙瓒");       // 130
        gongsunxu = new Character("公孙续");
        gongsundu = new Character("公孙度");
        gongsunfan = new Character("公孙範");
        huangzhong = new Character("黄忠");
        gaoding = new Character("高定");
        gaopei = new Character("高沛");
        huangpusong = new Character("皇甫嵩");
        kongrong = new Character("孔融");
        gaolan = new Character("高览");
        wujing = new Character("吴景");     // 140
        hucheer = new Character("胡车兒");
        huzhen = new Character("胡轸");
        wutugu = new Character("兀突骨");
        huban = new Character("胡班");
        wuban = new Character("吴班");
        guyong = new Character("顾雍");
        wulan = new Character("吴兰");
        caihe = new Character("蔡和");
        caizhong = new Character("蔡中");
        caimao = new Character("蔡瑁");       // 150
        zerong = new Character("笮融");
        simayi = new Character("司马懿");
        simayan = new Character("司马炎");
        simashi = new Character("司马师");
        simazhao = new Character("司马昭");
        simafu = new Character("司马孚");
        simalang = new Character("司马朗");
        shamoke = new Character("沙摩柯");
        zhouxin = new Character("周昕");
        zhouang = new Character("周昂");        // 160
        zhouzhi = new Character("周旨");
        zhoucang = new Character("周仓");
        zhoutai = new Character("周泰");
        zhoufang = new Character("周鲂");
        zhouyu = new Character("周瑜", 95, 71, 96, 86);
        zhuhuan = new Character("朱桓");
        zhuju = new Character("朱据");
        zhurong = new Character("祝融");
        zhujun = new Character("朱儁");
        zhuran = new Character("朱然");     // 170
        zhuzhi = new Character("朱治");
        zhubao = new Character("朱褒");
        zhuling = new Character("朱灵");
        xunyu = new Character("荀彧", 62, 14, 95, 98);
        chunyuqiong = new Character("淳于琼");
        xunshen = new Character("荀谌");                    // 多音字 字：chen, 姓：shen
        xunyou = new Character("荀攸", 73, 26, 94, 88);
        jiangwan = new Character("蒋琬");
        zhonghui = new Character("鐘会", 82, 51, 91, 79);   //  字典 鐘会 zhonghui
        jianggan = new Character("蒋干");       // 180
        jiangyiqu = new Character("蒋义渠");
        jiangqin = new Character("蒋钦");
        jiangji = new Character("蒋济");
        qiaozhou = new Character("谯周");
        jiaochu = new Character("焦触");
        xiangchong = new Character("向宠");
        zhongyao = new Character("鐘繇", 70, 24, 77, 91);    // 字典 鐘繇 zhongyao
        xianglang = new Character("向朗");
        xurong = new Character("徐荣");
        zhugeke = new Character("诸葛恪");
        zhugejin = new Character("诸葛瑾");       // 190
        zhugejun = new Character("诸葛均");
        zhugedan = new Character("诸葛诞");
        zhugeliang = new Character("诸葛亮", 98, 38, 100, 95);
        xuhuang = new Character("徐晃", 91, 90, 78, 48);
        xushu = new Character("徐庶");
        xusheng = new Character("徐盛");
        xumiao = new Character("徐邈");
        shenyi = new Character("申仪");
        shengeng = new Character("申耽");     // 200
        shenpei = new Character("");
        xinbi = new Character("");
        xinping = new Character("");
        qinbi = new Character("");
        qinlang = new Character("");
        jiagu = new Character("");
        dengdan = new Character("");
        chengyi = new Character("");
        chenggongying = new Character("");
        xinzong = new Character("");
        danjing = new Character("");
        quanzong = new Character("");
        caorui = new Character("曹叡", 72, 52, 82, 82);     // 字典 叡rui
        caoxiu = new Character("曹休", 74, 75, 62, 56);
        songxian = new Character("");
        caoang = new Character("曹昂", 74, 67, 69, 68);
        caohong = new Character("曹洪", 79, 81, 44, 35);
        caochun = new Character("曹纯", 75, 71, 63, 35);
        caozhang = new Character("曹彰", 82, 90, 40, 35);
        caozhi = new Character("");
        caozhen = new Character("");
        caoren = new Character("");
        caoxing = new Character("");
        caocao = new Character("");
        caochong = new Character("");
        jianba = new Character("");
        caopei = new Character("");
        caobao = new Character("");
        jushou = new Character("");
        sufei = new Character("");
        zumao = new Character("");
        sunhuan = new Character("");      // 孙桓 huan
        sunjian = new Character("");
        sunqian = new Character("");      // 孙乾 qian
        sunquan = new Character("");
        sunjiao = new Character("");      // 孙皎 jiao
        sunce = new Character("");
        sunshao = new Character("");      // 孙韶 shao
        sunjing = new Character("");
        sunzhong = new Character("");
        sundeng = new Character("");
        sunyu = new Character("");
        sunyi = new Character("");     // 孙翊 yi
        sunli = new Character("");
        taishici = new Character("");
        dailaidongzhu = new Character("");
        dailing = new Character("");
        duosidawang = new Character("");
        tanxiong = new Character("");
        zhangyu = new Character("");      // 250
        
        // 曹操
        caozhi = new Character("曹植", 33, 22, 81, 70);
        caozhen = new Character("曹真", 81, 74, 70, 72);
        caoren = new Character("曹仁", 89, 86, 62, 46);
        caocao = new Character("曹操", 99, 72, 91, 94);
        caochong = new Character("曹冲", 31, 21, 85, 79);
        caopi = new Character("曹丕", 70, 71, 83, 86);
        chenqun = new Character("陈群", 32, 14, 75, 96);
        chentai = new Character("陈泰", 84, 77, 86, 78);
        chengyu = new Character("程昱", 70, 49, 90, 79);     // 字典 程昱 Yu
        dianwei = new Character("典韦", 72, 95, 35, 29);
        manchong = new Character("满宠", 84, 64, 82, 84);
        lidian = new Character("李典", 78, 77, 79, 74);
        liuxun = new Character("刘勋", 47, 63, 35, 16);
        lelin = new Character("乐綝", 73, 75, 43, 41);
        caoyu = new Character("曹宇", 50, 43, 37, 52);
        caoshuang = new Character("曹爽", 39, 33, 30, 66);
        caofang = new Character("曹芳", 20, 14, 27, 48);
        chengwu = new Character("程武", 56, 35, 75, 71);
        dianman = new Character("典满", 51, 73, 39, 26);
        caomao = new Character("曹髦", 54, 61, 59, 75);
        caohuan = new Character("曹奂", 30, 28, 30, 45);    // 字典 奂 huan
        guoshi = new Character("郭氏", 42, 4, 69, 64);
        caojie = new Character("曹节", 36, 2, 75, 77);
        bianshi = new Character("卞氏", 36, 24, 75, 76);     // 字典 卞 bian         
        
        liubei = new Character("刘备", 81, 77, 78, 80);
        zhangfei = new Character("张飞", 100, 100, 100, 100);
        sunquan = new Character("孙权", 80, 80, 80, 80);
        liubiao = new Character("刘表", 75, 75, 75, 75);
        liuyan = new Character("刘焉");
        mateng = new Character("马腾");
        lvbu = new Character("吕布");
        dongzhuo = new Character("董卓");
        zhangjiao = new Character("张角");
        
        // initCities
        yunnan = new City("云南");
        
        initialiseCharacters();
        initialiseAbilities();
        initialiseCities();
        initialiseForces();
    }
    
    private void initialiseCharacters() {
        // 全武将列表
        characterList = new ArrayList<>(100);
        characterList.add(guojia);
        characterList.add(lejin);
        characterList.add(guohuai);
        characterList.add(xiahouwei);
        characterList.add(xiahouyuan);
        characterList.add(xiahouen);
        characterList.add(xiahouhe);
        characterList.add(xiahouhui);
        characterList.add(xiahoushang);
        characterList.add(xiahoude);
        characterList.add(xiahoudun);
        characterList.add(xiahouba);
        characterList.add(xiahoumao);
        characterList.add(xizhicai);
        characterList.add(xuchu);
        characterList.add(xunyu);
        characterList.add(xunyou);
        characterList.add(zhonghui);
        characterList.add(zhongyao);
        characterList.add(xuhuang);
        characterList.add(caorui);
        characterList.add(caoxiu);
        characterList.add(caoang);
        characterList.add(caohong);
        characterList.add(caochun);
        characterList.add(caozhang);
        characterList.add(caozhi);
        characterList.add(caozhen);
        characterList.add(caoren);
        characterList.add(caocao);
        characterList.add(caochong);
        characterList.add(caopi);
        characterList.add(chenqun);
        characterList.add(chentai);
        characterList.add(chengyu);
        characterList.add(dianwei);
        characterList.add(manchong);
        characterList.add(lidian);
        characterList.add(liuxun);
        characterList.add(lejin);
        characterList.add(caoyu);
        characterList.add(caoshuang);
        characterList.add(caofang);
        characterList.add(chengwu);
        characterList.add(dianman);
        characterList.add(caomao);
        characterList.add(caohuan);
        characterList.add(xumiao);
        characterList.add(guoshi);
        characterList.add(caojie);
        characterList.add(bianshi);
        characterList.add(yujin);
        characterList.add(liubei);
        characterList.add(sunquan);
    }
    
    private void initialiseCities()
    {
        // city = new City(Name)
        // setNeighbours(北, 南, 西, 东)
        // setNeighbours(北, 南, 西, 东, 西北, 东北, 西南, 东南)
        
        // 川蜀
        yunnan.setMapCoordinate(64, 555);
        yunnan.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_JIANGZHOU, CITY_EMPTY, CITY_EMPTY);
        
        jiangzhou = new City("江州");
        jiangzhou.setMapCoordinate(166, 450);
        jiangzhou.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_CHENGDU, CITY_YONGAN, CITY_ZITONG, CITY_EMPTY, CITY_YUNNAN, CITY_EMPTY);
        
        chengdu = new City("成都");
        chengdu.setMapCoordinate(41, 459);
        chengdu.setNeighbours(CITY_ZITONG, CITY_EMPTY, CITY_EMPTY, CITY_JIANGZHOU);
        
        yongan = new City("永安");
        yongan.setMapCoordinate(242, 449);
        yongan.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_JIANGZHOU, CITY_JIANGLING, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_WULING);
        
        zitong = new City("梓潼");
        zitong.setMapCoordinate(47, 402);
        zitong.setNeighbours(CITY_HANZHONG, CITY_CHENGDU, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_JIANGZHOU);
        
        // 中原
        xuchang = new City("许昌");
        xuchang.setMapCoordinate(429, 355);
        xuchang.setNeighbours(CITY_PUYANG, CITY_EMPTY, CITY_WAN, CITY_EMPTY, CITY_LUOYANG, CITY_EMPTY, CITY_EMPTY, CITY_RUNAN);
        
        wan = new City("宛");
        wan.setMapCoordinate(362, 362);
        wan.setNeighbours(CITY_LUOYANG, CITY_EMPTY, CITY_EMPTY, CITY_XUCHANG, CITY_CHANGAN, CITY_EMPTY, CITY_EMPTY, CITY_XINYE);
        
        luoyang = new City("洛阳");
        luoyang.setMapCoordinate(345, 321);
        luoyang.setNeighbours(CITY_JINYANG, CITY_WAN, CITY_CHANGAN, CITY_CHENLIU, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_XUCHANG);
        
        chenliu = new City("陈留");
        chenliu.setMapCoordinate(456, 316);
        chenliu.setNeighbours(CITY_YE, CITY_XUCHANG, CITY_LUOYANG, CITY_XIAOPEI, CITY_EMPTY, CITY_PUYANG, CITY_EMPTY, CITY_EMPTY);
        
        puyang = new City("濮阳");
        puyang.setMapCoordinate(511, 286);
        puyang.setNeighbours(CITY_EMPTY, CITY_XIAOPEI, CITY_CHENLIU, CITY_EMPTY, CITY_YE, CITY_BEIHAI, CITY_EMPTY, CITY_EMPTY);
        
        xiaopei = new City("小沛");
        xiaopei.setMapCoordinate(556, 327);
        xiaopei.setNeighbours(CITY_PUYANG, CITY_SHOUCHUN, CITY_EMPTY, CITY_XIAPI, CITY_CHENLIU, CITY_EMPTY, CITY_RUNAN, CITY_EMPTY);
        
        xiapi = new City("下邳");
        xiapi.setMapCoordinate(607, 329);
        xiapi.setNeighbours(CITY_BEIHAI, CITY_EMPTY, CITY_XIAOPEI, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_SHOUCHUN, CITY_EMPTY);
        
        runan = new City("汝南");
        runan.setMapCoordinate(488, 388);
        runan.setNeighbours(CITY_XUCHANG, CITY_EMPTY, CITY_EMPTY, CITY_SHOUCHUN, CITY_EMPTY, CITY_XIAOPEI, CITY_XINYE, CITY_EMPTY);
        
        shouchun = new City("寿春");
        shouchun.setMapCoordinate(551, 397);
        shouchun.setNeighbours(CITY_XIAOPEI, CITY_EMPTY, CITY_RUNAN, CITY_EMPTY, CITY_EMPTY, CITY_XIAPI, CITY_LUJIANG, CITY_JIANYE);
        
        
        // 西凉
        xiliang = new City("西凉");
        xiliang.setMapCoordinate(47, 254);
        xiliang.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_TIANSHUI);
        
        tianshui = new City("天水");
        tianshui.setMapCoordinate(102, 316);
        tianshui.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_CHANGAN, CITY_XILIANG, CITY_EMPTY, CITY_EMPTY, CITY_HANZHONG);
        
        changan = new City("长安");
        changan.setMapCoordinate(236, 333);
        changan.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_TIANSHUI, CITY_LUOYANG, CITY_EMPTY, CITY_EMPTY, CITY_HANZHONG, CITY_WAN);
        
        hanzhong = new City("汉中");
        hanzhong.setMapCoordinate(96, 364);
        hanzhong.setNeighbours(CITY_EMPTY, CITY_ZITONG, CITY_EMPTY, CITY_SHANGYONG, CITY_TIANSHUI, CITY_CHANGAN, CITY_EMPTY, CITY_EMPTY);
        
        // 荆州
        xinye = new City("新野");
        xinye.setMapCoordinate(397, 395);
        xinye.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_WAN, CITY_RUNAN, CITY_XIANGYANG, CITY_JIANGXIA);
        
        jiangxia = new City("江夏");
        jiangxia.setMapCoordinate(426, 434);
        jiangxia.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_XIANGYANG, CITY_LUJIANG, CITY_XINYE, CITY_EMPTY, CITY_JIANGLING, CITY_CHAISANG);
        
        shangyong = new City("上庸");
        shangyong.setMapCoordinate(262, 382);
        shangyong.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_HANZHONG, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_XIANGYANG);
        
        jiangling = new City("江陵");
        jiangling.setMapCoordinate(331, 443);
        jiangling.setNeighbours(CITY_XIANGYANG, CITY_EMPTY, CITY_YONGAN, CITY_CHAISANG, CITY_EMPTY, CITY_JIANGXIA, CITY_WULING, CITY_CHANGSHA);
        
        wuling = new City("武陵");
        wuling.setMapCoordinate(257, 527);
        wuling.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_CHANGSHA, CITY_YONGAN, CITY_JIANGLING, CITY_EMPTY, CITY_LINGLING);
        
        lingling = new City("零陵");
        lingling.setMapCoordinate(336, 563);
        lingling.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_WULING, CITY_GUIYANG);
        
        guiyang = new City("桂阳");
        guiyang.setMapCoordinate(414, 568);
        guiyang.setNeighbours(CITY_CHANGSHA, CITY_EMPTY, CITY_LINGLING, CITY_EMPTY);
        
        changsha = new City("长沙");
        changsha.setMapCoordinate(394, 510);
        changsha.setNeighbours(CITY_EMPTY, CITY_GUIYANG, CITY_WULING, CITY_CHAISANG, CITY_JIANGLING, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY);
        
        xiangyang = new City("襄阳");
        xiangyang.setMapCoordinate(335, 405);
        xiangyang.setNeighbours(CITY_EMPTY, CITY_JIANGLING, CITY_EMPTY, CITY_JIANGXIA, CITY_SHANGYONG, CITY_XINYE, CITY_EMPTY, CITY_EMPTY);


        
        // 吴越
        chaisang = new City("柴桑");
        chaisang.setMapCoordinate(494, 501);
        chaisang.setNeighbours(CITY_LUJIANG, CITY_EMPTY, CITY_JIANGLING, CITY_KUAIJI, CITY_JIANGXIA, CITY_JIANYE, CITY_CHANGSHA, CITY_EMPTY);
        
        lujiang = new City("庐江");
        lujiang.setMapCoordinate(507, 437);
        lujiang.setNeighbours(CITY_EMPTY, CITY_CHAISANG, CITY_JIANGXIA, CITY_JIANYE, CITY_EMPTY, CITY_SHOUCHUN, CITY_EMPTY, CITY_EMPTY);
        
        jianye = new City("建业");
        jianye.setMapCoordinate(608, 433);
        jianye.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_LUJIANG, CITY_WU, CITY_SHOUCHUN, CITY_EMPTY, CITY_CHAISANG, CITY_EMPTY);
        
        wu = new City("吴");
        wu.setMapCoordinate(676, 400);
        wu.setNeighbours(CITY_EMPTY, CITY_KUAIJI, CITY_JIANYE, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY);
        
        kuaiji = new City("会稽");
        kuaiji.setMapCoordinate(650, 503);
        kuaiji.setNeighbours(CITY_WU, CITY_EMPTY, CITY_CHAISANG, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY);
        
        // 幽州
        xiangping = new City("襄平");
        xiangping.setMapCoordinate(758, 87);
        xiangping.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_BEIPING, CITY_EMPTY);
        
        beiping = new City("北平");
        beiping.setMapCoordinate(552, 152);
        beiping.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_JI, CITY_EMPTY, CITY_EMPTY, CITY_XIANGPING, CITY_NANPI, CITY_EMPTY);
        
        ji = new City("蓟");
        ji.setMapCoordinate(444, 152);
        ji.setNeighbours(CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_BEIPING, CITY_EMPTY, CITY_EMPTY, CITY_JINYANG, CITY_NANPI);
        
        jinyang = new City("晋阳");
        jinyang.setMapCoordinate(358, 238);
        jinyang.setNeighbours(CITY_EMPTY, CITY_LUOYANG, CITY_EMPTY, CITY_YE, CITY_EMPTY, CITY_JI, CITY_EMPTY, CITY_EMPTY);
        
        ye = new City("邺");
        ye.setMapCoordinate(440, 261);
        ye.setNeighbours(CITY_EMPTY, CITY_CHENLIU, CITY_JINYANG, CITY_PINGYUAN, CITY_EMPTY, CITY_NANPI, CITY_EMPTY, CITY_PUYANG);
        
        nanpi = new City("南皮");
        nanpi.setMapCoordinate(488, 207);
        nanpi.setNeighbours(CITY_EMPTY, CITY_PINGYUAN, CITY_EMPTY, CITY_EMPTY, CITY_JI, CITY_BEIPING, CITY_YE, CITY_BEIHAI);
        
        pingyuan = new City("平原");
        pingyuan.setMapCoordinate(500, 246);
        pingyuan.setNeighbours(CITY_NANPI, CITY_EMPTY, CITY_YE, CITY_BEIHAI, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY, CITY_EMPTY);
        
        beihai = new City("北海");
        beihai.setMapCoordinate(607, 248);
        beihai.setNeighbours(CITY_EMPTY, CITY_XIAPI, CITY_PINGYUAN, CITY_EMPTY, CITY_NANPI, CITY_EMPTY, CITY_PUYANG, CITY_EMPTY);
         
        // Initialise City List.
        cityList = new ArrayList<>(40);
        cityList.add(xiangping);
        cityList.add(beiping);
        cityList.add(ji);
        cityList.add(jinyang);
        cityList.add(ye);
        cityList.add(nanpi);
        cityList.add(pingyuan);
        cityList.add(beihai);
        cityList.add(luoyang);
        cityList.add(xuchang);      // 10
        cityList.add(chenliu);
        cityList.add(puyang);
        cityList.add(xiaopei);
        cityList.add(xiapi);
        cityList.add(runan);
        cityList.add(shouchun);
        cityList.add(wan);
        cityList.add(xiliang);
        cityList.add(tianshui);
        cityList.add(hanzhong);     // 20
        cityList.add(changan);
        cityList.add(lujiang);
        cityList.add(jianye);
        cityList.add(wu);
        cityList.add(chaisang);
        cityList.add(kuaiji);
        cityList.add(xinye);
        cityList.add(xiangyang);
        cityList.add(shangyong);
        cityList.add(jiangxia);       // 30
        cityList.add(jiangling);
        cityList.add(wuling);
        cityList.add(changsha);
        cityList.add(lingling);
        cityList.add(guiyang);
        cityList.add(zitong);
        cityList.add(chengdu);
        cityList.add(jiangzhou);
        cityList.add(yongan);
        cityList.add(yunnan);       // 40
     }
    
    private void initialiseForces() {
        caocaoForce = new Force("曹操");
        caocaoForce.setForceColor(23, 23, 138);
        liubeiForce = new Force("刘备");
        liubeiForce.setForceColor(34, 76, 0);
        sunquanForce = new Force("孙权");
        sunquanForce.setForceColor(130, 0, 24);
        liubiaoForce = new Force("刘表");
        liubiaoForce.setForceColor(19, 134, 134);
        yuanshaoForce = new Force("袁绍");
        yuanshaoForce.setForceColor(177, 177, 55);
        liuyanForce = new Force("刘焉");
        liuyanForce.setForceColor(23, 28, 79);
        matengForce = new Force("马腾");
        matengForce.setForceColor(105, 75, 30);
        gongsunzanForce = new Force("公孙瓒");
        gongsunzanForce.setForceColor(199, 76, 8);
        lvbuForce = new Force("吕布");
        lvbuForce.setForceColor(33, 33, 33);
        dongzhuoForce = new Force("董卓");
        dongzhuoForce.setForceColor(61, 61, 61);
        zhangjiaoForce = new Force("张角");
        zhangjiaoForce.setForceColor(183, 109, 19);
        simayiForce = new Force("司马懿");
        simayiForce.setForceColor(96, 139, 33);
        hejinForce = new Force("何进");
        hejinForce.setForceColor(123, 103, 53);
        menghuoForce = new Force("孟获");
        menghuoForce.setForceColor(206, 68, 68);
        zhangjiForce = new Force("张济");
        zhangjiForce.setForceColor(98, 23, 44);
        yuanshuForce = new Force("袁术");
        yuanshuForce.setForceColor(143, 89, 102);
        zhangluForce = new Force("张鲁");
        zhangluForce.setForceColor(100, 144, 92);
        gongsunduForce = new Force("公孙度");
        gongsunduForce.setForceColor(115, 95, 124);
        taoqianForce = new Force("陶谦");
        taoqianForce.setForceColor(155, 198, 100);
        kongrongForce = new Force("孔融");
        kongrongForce.setForceColor(139, 46, 86);
        jinxuanForce = new Force("金玄");
        jinxuanForce.setForceColor(92, 46, 18);
        hanxuanForce = new Force("韩玄");
        hanxuanForce.setForceColor(76, 0, 97);
        liuduForce = new Force("刘度");
        liuduForce.setForceColor(193, 121, 79);
        zhaofanForce = new Force("赵范");
        zhaofanForce.setForceColor(0, 79, 56);
        hanfuForce = new Force("韩馥");
        hanfuForce.setForceColor(101, 0, 14);
        dingyuanForce = new Force("丁原");
        dingyuanForce.setForceColor(94, 25, 76);
        liuyuForce = new Force("刘虞");
        liuyuForce.setForceColor(64, 13, 49);
        liuyaoForce = new Force("刘繇");
        liuyaoForce.setForceColor(0, 57, 101);
        yanbaihuForce = new Force("严白虎");
        yanbaihuForce.setForceColor(86, 76, 49);
        wanglangForce = new Force("王朗");
        wanglangForce.setForceColor(119, 119, 119);
        neutralForce = new Force("中立");
        neutralForce.setForceColor(230, 230, 230);
                
        forceList = new ArrayList<>(31);
        forceList.add(caocaoForce);         // 1
        forceList.add(liubeiForce);
        forceList.add(sunquanForce);
        forceList.add(liubiaoForce);
        forceList.add(yuanshaoForce);
        forceList.add(liuyanForce);
        forceList.add(matengForce);
        forceList.add(gongsunzanForce);
        forceList.add(lvbuForce);
        forceList.add(dongzhuoForce);       // 10
        forceList.add(zhangjiaoForce);
        forceList.add(simayiForce);
        forceList.add(hejinForce);
        forceList.add(menghuoForce);
        forceList.add(zhangjiForce);
        forceList.add(yuanshuForce);
        forceList.add(zhangluForce);
        forceList.add(gongsunduForce);
        forceList.add(taoqianForce);
        forceList.add(kongrongForce);       // 20
        forceList.add(jinxuanForce);
        forceList.add(hanxuanForce);
        forceList.add(liuduForce);
        forceList.add(zhaofanForce);
        forceList.add(hanfuForce);
        forceList.add(dingyuanForce);
        forceList.add(liuyuForce);
        forceList.add(liuyaoForce);
        forceList.add(yanbaihuForce);
        forceList.add(wanglangForce);       // 30
        forceList.add(neutralForce);
    }
    
    private void initialiseAbilities()
    {
        tiebi = new Ability("铁壁");
        tiebi.setDefenceBonus(250);
        tiebi.setCombatPowerBonus(20);
        
        qiangxi = new Ability("强袭");
        qiangxi.setDamageBonus(200);
        qiangxi.setUnitsKillCount(1000);
    }
    
    public List<City> getCityList()
    {
        return cityList;
    }
    
    public List<Character> getCharList()
    {
        return characterList;
    }
    
    public List<Force> getForceList()
    {
        return forceList;
    }
    
    public Character getCharacter(int value)
    {
        return characterList.get(value - 1);
    }
}
