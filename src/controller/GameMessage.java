/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *
 * @author RuN
 */
public class GameMessage
{
    private String caocaoDesc;
    private String liubeiDesc;
    private String sunquanDesc;
    private String liubiaoDesc;
    
    private String militaryButtonStatue;
    private String humanResourceButtonStatue;
    private String politicsButtonStatue;
    private String rewardButtonStatue;
    private String armyButtonStatue;
    private String settingsButtonStatue;
    private String charactersButtonStatue;
    private String intelButtonStatue;
    private String strategyReportButtonStatue;
    private String strategyButtonStatue;
    private String procceedButtonStatue;
    private String currentString;
    private String previousString = "状态";
    
    private String cityNameXuChang;
    
    public GameMessage()
    {
        initialiseButtonTooltips();
        initialiseCityNames();
        initForceDesc();
    }
    
    private void initForceDesc()
    {
          caocaoDesc = "领地为许昌，部下以曹家一族及夏侯一族为中心。拥有郭嘉、荀彧、典韦\n"
                     + "等丰富的文武两道人才。由于领地约在大陆的中央地带，且可使用的手下\n"
                     + "也充足，因此往哪方面扩张势力可能才是重要之事。若能以速攻方式压制\n"
                     + "中原的话，则可说大势底定。";
        
           liubeiDesc = "领地为江陵，麾下有关羽、张飞、赵云、诸葛亮、庞统、姜维等许多顶尖\n"
                     + "的文武人才。首先应活用这些优秀的人才压制荆南再说。不须与许昌或柴\n"
                     + "桑的孙权硬碰硬，按照史实，固守益州地盘也许就是迈向天下之道。 ";
           
        sunquanDesc = "领地为柴桑。麾下以孙家一族为中心，拥有许多优秀的人才。特别是周瑜\n"
                     + "、鲁肃、吕蒙、陆逊、陆抗等历代都都督齐聚一堂，可说是压轴之作。若\n"
                     + "能适切地运用拿手的水军，则称霸扬州指日可待。在曹操或刘备势力增强\n"
                     + "前能稳固住自己地盘的话，则应可以一口气取得天下。";
        
          liubiaoDesc = "领地为襄阳。麾下虽为数不多，但却取得文武面上的均衡。文官有蒯良、\n"
                     + "蒯越，武将有蔡瑁、文聘。势力周边有许多像新野的空白地域，因此压制\n" 
                     + "该地应为最优先之策。此外，也可与江陵的刘备结盟以防背后遭人攻击。 ";
    }
    
    
    private void initialiseButtonTooltips()
    {
        // 5 White Buttons
        militaryButtonStatue = "进行出征或输送。";
        humanResourceButtonStatue = "进行武将的移动。";
        politicsButtonStatue = "派遣使者去其他势力外交。";
        rewardButtonStatue = "进行队伍将的赏罚。 ";
        armyButtonStatue = "进行委任军团的编制及命令。 ";
        
        // 4 green Buttons;
        settingsButtonStatue = "设置游戏选项";
        charactersButtonStatue = "查看己方势力武将";
        intelButtonStatue = "查看情报";
        strategyReportButtonStatue = "查看秘策状况";
        
        // Game Buttons
        strategyButtonStatue = "开发秘籍。 ";
        procceedButtonStatue = "结束命令， 并进行这次回合 ";
    }
    
    private void initialiseCityNames()
    {
        cityNameXuChang = "许昌";
    }
    
    public String getCaocaoDesc()
    {
        return caocaoDesc;
    }
    
    public String getLiubeiDesc()
    {
        return liubeiDesc;
    }
    
    public String getSunquanDesc()
    {
        return sunquanDesc;
    }
    
    public String getLiubiaoDesc()
    {
        return liubiaoDesc;
    }
    
    public String getCityNameXuChang()
    {
        return cityNameXuChang;
    }

    public String getMilitaryButtonStatue()
    {
        return militaryButtonStatue;
    }

    public String getHumanResourceButtonStatue()
    {
        return humanResourceButtonStatue;
    }

    public String getPoliticsButtonStatue()
    {
        return politicsButtonStatue;
    }

    public String getRewardButtonStatue()
    {
        return rewardButtonStatue;
    }

    public String getArmyButtonStatue()
    {
        return armyButtonStatue;
    }

    public String getSettingsButtonStatue()
    {
        return settingsButtonStatue;
    }

    public String getCharactersButtonStatue()
    {
        return charactersButtonStatue;
    }

    public String getIntelButtonStatue()
    {
        return intelButtonStatue;
    }

    public String getStrategyReportButtonStatue()
    {
        return strategyReportButtonStatue;
    }

    public String getStrategyButtonStatue()
    {
        return strategyButtonStatue;
    }

    public String getProcceedButtonStatue()
    {
        return procceedButtonStatue;
    }

    public String getCurrentString()
    {
        return currentString;
    }

    public String getPreviousString()
    {
        return previousString;
    }
}
