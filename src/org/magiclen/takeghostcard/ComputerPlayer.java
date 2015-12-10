/*
 *
 * Copyright 2015 magiclen.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.magiclen.takeghostcard;

/**
 * 電腦玩家。繼承Player。
 *
 * @see Player
 * @see StringClass
 * @see Talk
 * @see Tools
 * @author Magic Len
 */
public class ComputerPlayer extends Player {

    // -----類別方法-----
    /**
     * 產生數個電腦玩家的名稱
     *
     * @param number 傳入要產生的數量
     * @return 傳回產生出來的電腦玩家名稱陣列
     */
    public static String[] createComputerPlayerNames(final int number) {
	try {
	    final String name[] = new String[number]; //宣告字串陣列
	    name[0] = Tools.getStringArrayRandom(StringClass.COMPUTER_NAMES); //先產生第一個名稱
	    lb1: //外迴圈標籤
	    for (int i = 1; i < number; i++) { //產生第二個到最後一個的名稱
		name[i] = Tools.getStringArrayRandom(StringClass.COMPUTER_NAMES); //亂數產生名稱
		for (int j = 0; j < i; j++) { //搜尋重複項目
		    if (name[i].equals(name[j])) { //若找到相等的
			i--; //i值減1，讓下一次的for迴圈執行時繼續建立目前的玩家名稱。
			continue lb1; //繼續執行lb1迴圈
		    }
		}
	    }
	    return name; //傳回產生完成的名稱字串陣列
	} catch (final Exception e) { //如果產生過程中出現例外
	    System.err.println(e.getMessage()); //顯示錯誤訊息
	    return null; //回傳null
	}
    }

    // -----物件變數-----
    private int inverseCounter = 8; //強制送鬼牌機率倒數的倒數器

    // -----建構子-----
    /**
     * 建構子。設定電腦玩家的名稱。
     *
     * @param inputName 電腦玩家的名稱
     */
    public ComputerPlayer(final String inputName) {
	super(inputName); //設定名稱
    }

    // -----物件方法-----
    /**
     * 隨機抽走另一位玩家的一張牌。
     *
     * @param player 傳入另一位玩家
     */
    @Override //覆寫
    public void pickAcard(final Player player) {
	talk(Talk.GETACARD); //說要抽牌
	int randChoose = Tools.getRandom(0, player.getPile().size()); //以亂數抽牌
	final int flag = player.bePicked(randChoose); //對象被抽牌，取得抽牌時的事件項目
	if (flag == 0) { //如果抽牌被駁回
	    int rnd = (int) (Math.random() * 4); //4分之一的機率決定是否要換牌
	    if (rnd != 0) { //不換牌
		talk(Talk.DISAGREECHANGE); //說不換牌
		inverseSituation(); //增加逆轉局勢的機會
	    } else { //換牌
		talk(Talk.AGREECHANGE); //說要換牌
		inverseSituation(); //增加逆轉局勢的機會
		inverseSituation(); //增加逆轉局勢的機會
		int tmp = Tools.getRandom(0, player.getPile().size()); //亂數抽牌
		while (tmp == randChoose) { //如果亂數值和上次亂數相等
		    tmp = Tools.getRandom(0, player.getPile().size()); //再次亂數抽牌
		}
		randChoose = tmp; //確定有換到牌
	    }
	} else if (flag == 1) { //如果抽牌被重洗
	    talk(Talk.PICKASKRANDOM); //對方洗完牌後說要抽牌
	    inverseSituation(); //增加逆轉局勢的機會
	    randChoose = Tools.getRandom(0, player.getPile().size()); //再次亂數抽牌
	} else if (flag == 3) { //如果被送鬼牌
	    randChoose = player.getPile().isExist(new Card()); //找出鬼牌位置
	    inverseSituation(); //增加逆轉局勢的機會，機率連三加
	    inverseSituation(); //增加逆轉局勢的機會
	    inverseSituation(); //增加逆轉局勢的機會
	}
	final Card c = getAnotherPlayerCard(player, randChoose); //取得要抽的牌
	if (player instanceof UserPlayer) { //如果抽牌對象是玩家
	    System.out.printf(StringClass.SHOW_COMPUTER_GETTED, c); //顯示電腦抽到的牌
	    Tools.delay(1000); //延遲一秒
	}
	if (c.isGhostCard()) { //如果抽到鬼牌
	    final int rnd = (int) (Math.random() * 3); //3分之一的機率不小心透漏自己抽到鬼牌
	    if (rnd == 0) {
		talk(Talk.PICKGHOSTCARD); //透漏自己抽到鬼牌
		inverseSituation(); //增加逆轉局勢的機會
	    }
	    inverseSituation(); //增加逆轉局勢的機會
	}
	removeCouple(); //消除對牌
    }

    /**
     * 被另一位玩家抽一張牌。
     *
     * @param index 傳入被挑中的牌序
     * @return 傳回是否成功被抽牌。0為否且可不換；1為否不可不換；2為是。3為直接讓對方抽到鬼牌。
     */
    @Override //覆寫
    public int bePicked(final int index) {
	int rnd = (int) (Math.random() * 4); //4分之一的機率決定是否要讓對方換張牌抽(有好有壞的心機)
	if (rnd == 0 && getPile().size() > 1) { //如果命中機率且玩家擁有的牌大於一張
	    talk(Talk.RESIST); //勸對方換張牌抽
	    return 0; //傳回0
	} else {
	    if (getPile().getCard(index).isGhostCard()) { //如果要被抽走的是鬼牌
		rnd = (int) (Math.random() * 3); //3分之一的機率決定是否要不小心笑出來
		if (rnd == 0) {
		    talk(Talk.BEPICKGHOSTCARD); //不小心透漏出自己鬼牌被抽走
		}
	    } else { //如果被抽走的不是鬼牌
		if (getPile().size() > 1 && getPile().hasGhostCard()) { //如果牌大於一張，且有鬼牌
		    rnd = (int) (Math.random() * 8); //4分之一的機率決定是否要重新洗牌。又再4分之一的機率決定是否要讓對方換張牌抽。
		    if (rnd == 0 || rnd == 1) {
			talk(Talk.ASKRANDOM); //說要洗牌
			getPile().random(); //洗牌
			inverseSituation(); //增加逆轉局勢的機會
			return 1; //傳回1
		    } else if (rnd == 2 || rnd == 3) {
			talk(Talk.RESIST); //勸對方換張牌抽
			inverseSituation(); //增加逆轉局勢的機會
			return 0; //傳回0
		    } else if (rnd == 7 || (int) (Math.random() * inverseCounter) == 0) { //8分之一的基本機率加上大於等於8分之一的機率直接送給對方鬼牌。
			talk(Talk.FORCEGHOSTCARD); //說要強制給對方鬼牌
			inverseCounter = 4; //回復成原本機率的兩倍
			return 3; //傳回3
		    }
		    if ((int) (Math.random() * (inverseCounter + 8)) == 0) { //最後再加小於等於1/8的機率強制給對方鬼牌
			talk(Talk.FORCEGHOSTCARD); //說要強制給對方鬼牌
			inverseCounter = 6; //回復成原本機率的一點五倍
			return 3; //傳回3
		    }
		}
	    }
	}
	talk(Talk.GETTEDACARD); //把牌拱手讓人
	return 2; //讓它被抽走吧
    }

    /**
     * 輸出語句。
     *
     * @param sentence 傳入Talk特徵
     */
    @Override //覆寫
    public void talk(final Talk sentence) {
	switch (sentence) {
	    case DEALCARD: //發牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.COMPUTER_DEAL_CARD));
		break;
	    case DEALCARDFINISH: //抽完牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.COMPUTER_DEAL_CARD_FINISH));
		break;
	    case ALLDOWN: //丟完牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.PILE_EMPTY));
		break;
	    case GETACARD: //抽牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.GET_A_CARD));
		break;
	    case GETTEDACARD: //被抽牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.GETTED_A_CARD));
		break;
	    case RESIST: //拒絕被抽
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.TAKE_RESIST));
		break;
	    case AGREECHANGE: //答應換牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.AGREE_CHANGE));
		break;
	    case DISAGREECHANGE: //不答應換牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.DISAGREE_CHANGE));
		break;
	    case PICKGHOSTCARD: //抽到鬼牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.PICK_GHOST_CARD));
		break;
	    case BEPICKGHOSTCARD: //鬼牌被抽走
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.BE_PICK_GHOST_CARD));
		break;
	    case ASKRANDOM: //要求洗牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.ASK_RANDOM));
		break;
	    case PICKASKRANDOM: //洗牌重抽
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.PICK_ASK_RANDOM));
		break;
	    case LOSER: //輸家遺言
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.LOSER_TALK));
		break;
	    case BYE: //說掰掰
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.COMPUTER_BYE));
		break;
	    case FORCEGHOSTCARD: //送鬼牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.FORCE_GHOSTCARD));
		break;
	}
	Tools.delay(1000); //延遲一秒
    }

    /**
     * 增加逆轉局勢的機會。
     */
    private void inverseSituation() {
	if (inverseCounter > 1) {
	    inverseCounter--;
	}
    }
}
