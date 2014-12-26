/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi.data;

/**
 *
 * @author RuN
 */

// 城市编号
// ------------------------
// 0 空
// 1 襄平 xiangping
// 2 北平
// 3 蓟     ji
// 4 晋阳 jinyang
// 5 邺     ye
// 6 南皮
// 7 平原
// 8 北海
// 9 洛阳
// 10 许昌
// 11 陈留
// 12 濮阳 puyang
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
// 36 梓潼 zitong
// 37 成都
// 38 江州
// 39 永安
// 40 云南

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

// 兵科编号
// ----------------
// 1 剑
// 2 枪
// 3 弓
// 4 骑
// 5 长剑
// 6 戟
// 7 弩
// 8 重骑
// 9 大盾
// 10 弓骑
// 11 战车
// 12 藤甲
// 13 虎豹骑
// 14 白马骑
// 15 西凉铁骑

// 方向编号
// ------------------
// 1 north
// 2 south
// 3 west
// 4 east
// 5 northWest
// 6 northEast
// 7 southWest
// 8 southEast


public interface GameParameters
{ 
    int CITY_EMPTY = 0;
    int CITY_XIANGPING = 1;
    int CITY_BEIPING = 2;
    int CITY_JI = 3;
    int CITY_JINYANG = 4;
    int CITY_YE = 5;
    int CITY_NANPI = 6;
    int CITY_PINGYUAN = 7;
    int CITY_BEIHAI = 8;
    int CITY_LUOYANG = 9;
    int CITY_XUCHANG = 10;
    int CITY_CHENLIU = 11;
    int CITY_PUYANG= 12;
    int CITY_XIAOPEI = 13;
    int CITY_XIAPI = 14;
    int CITY_RUNAN = 15;
    int CITY_SHOUCHUN = 16;
    int CITY_WAN = 17;
    int CITY_XILIANG = 18;
    int CITY_TIANSHUI = 19;
    int CITY_HANZHONG = 20;
    int CITY_CHANGAN = 21;
    int CITY_LUJIANG = 22;
    int CITY_JIANYE = 23;
    int CITY_WU = 24;
    int CITY_CHAISANG = 25;
    int CITY_KUAIJI = 26;
    int CITY_XINYE = 27;
    int CITY_XIANGYANG = 28;
    int CITY_SHANGYONG = 29;
    int CITY_JIANGXIA = 30;
    int CITY_JIANGLING = 31;
    int CITY_WULING = 32;
    int CITY_CHANGSHA = 33;
    int CITY_LINGLING = 34;
    int CITY_GUIYANG = 35;
    int CITY_ZITONG = 36;
    int CITY_CHENGDU = 37;
    int CITY_JIANGZHOU = 38;
    int CITY_YONGAN = 39;
    int CITY_YUNNAN = 40;
    
    int FORCE_CAOCAO = 1;
    int FORCE_LIUBEI = 2;
    int FORCE_SUNQUAN = 3;
    int FORCE_LIUBIAO = 4;
    int FORCE_YUANSHAO = 5;
    int FORCE_LIUYAN = 6;
    int FORCE_MATENG = 7;
    int FORCE_GONGSUNZAN = 8;
    int FORCE_LVBU = 9;
    int FORCE_DONGZHUO = 10;
    int FORCE_ZHANGJIAO= 11;
    int FORCE_SIMAYI = 12;
    int FORCE_HEJIN = 13;
    int FORCE_MENGHUO = 14;
    int FORCE_ZHANGJI = 15;
    int FORCE_YUANSHU = 16;
    int FORCE_ZHANGLU = 17;
    int FORCE_GONGSUNDU = 18;
    int FORCE_TAOQIAN = 19;
    int FORCE_KONGRONG = 20;
    int FORCE_JINXUAN = 21;
    int FORCE_HANXUAN = 22;
    int FORCE_LIUDU = 23;
    int FORCE_ZHAOFAN = 24;
    int FORCE_HANFU = 25;
    int FORCE_DINGYUAN = 26;
    int FORCE_LIUYU = 27;
    int FORCE_LIUYAO = 28;
    int FORCE_YANBAIHU = 29;
    int FORCE_WANGLANG = 30;
    
    int UNIT_TYPE_SWORDMAN = 1;
    int UNIT_TYPE_SPEARMAN = 2;
    int UNIT_TYPE_ARCHER = 3;
    int UNIT_TYPE_CAVALRY = 4;
    int UNIT_TYPE_LONGSWORD = 5;
    int UNIT_TYPE_HALBERD = 6;
    int UNIT_TYPE_CROSSBOW = 7;
    int UNIT_TYPE_HEAVY_CAVALRY = 8;
    int UNIT_TYPE_SHIELD = 9;
    int UNIT_TYPE_CAVALRY_ARCHER = 10;
    int UNIT_TYPE_RATTAN_ARMOUR = 11;
    int UNIT_TYPE_CHARIOT = 12;
    int UNIT_TYPE_TIGER_CAVALRY = 13;
    int UNIT_TYPE_WHITE_CALVARY = 14;
    int UNIT_TYPE_TURK_CALVARY = 15;
    
    int DIRECTION_NORTH = 1;
    int DIRECTION_SOUTH = 2;
    int DIRECTION_WEST = 3;
    int DIRECTION_EAST = 4;
    int DIRECTION_NORTHWEST = 5;
    int DIRECTION_NORTHEAST = 6;
    int DIRECTION_SOUTHWEST = 7;
    int DIRECTION_SOUTHEAST = 8;
    
    int MENU_BUTTON_UNSELECTED = 0;
    int MENU_BUTTON_BATTLE = 1;
    int MENU_BUTTON_BUILD = 2;
    int MENU_BUTTON_MILITARY = 3;
    int MENU_BUTTON_POLITICS = 4;
    int MENU_BUTTON_PERSONELS = 5;
    int MENU_BUTTON_EXIT = 6;
}
