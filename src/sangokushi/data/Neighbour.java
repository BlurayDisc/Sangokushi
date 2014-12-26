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
public enum Neighbour {
    EMPTY(0),
    XIANGPING(1),
    BEIPING(2),
    JI(3),
    JINYANG(4),
    YE(5),
    NANPI(6),
    PINGYUAN(0),
    BEIHAI(0),
    LUOYANG(0),
    XUCHANG(0),
    CHENLIU(0),
    PUYANG(0),
    XIAOPEI(0),
    XIAPI(0),
    RUNAN(0),
    SHOUCHUN(0),
    WAN(0),
    XILIANG(0),
    TIANSHUI(0),
    HANZHONG(0),
    CHANGAN(0),
    LUJIANG(0),
    JIANYE(0),
    WU(0),
    CHAISANG(0),
    KUAIJI(0),
    XINYE(0),
    XIANGYANG(0),
    SHANGYONG(0),
    JIANGXIA(30),
    JIANGLING(31),
    WULING(32),
    CHANGSHA(33),
    LINGLING(34),
    GUIYANG(35),
    ZITONG(36),
    CHENGDU(37),
    JIANGZHOU(38),
    YONGAN(39),
    YUNNAN(40);
    
    private int value = 0;
    
    private Neighbour(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
