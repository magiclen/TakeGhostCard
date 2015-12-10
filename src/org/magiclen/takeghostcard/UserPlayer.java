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

import java.util.Scanner;

/**
 * 使用者玩家。繼承Player。
 *
 * @see Player
 * @see StringClass
 * @see Talk
 * @see Tools
 * @author Magic Len
 */
public class UserPlayer extends Player {

    // -----物件常數-----
    private final Scanner sc = new Scanner(System.in);

    // -----物件變數-----
    private int resistCount = 3; //可用的抗抽牌次數
    private int randomCount = 3; //可用的洗牌次數
    private int forceGiveGhostCard = 1; //可用的送鬼牌次數

    // -----建構子-----
    /**
     * 建構子。設定使用者玩家的名稱。
     *
     * @param inputName 傳入使用者玩家的名稱
     */
    public UserPlayer(final String inputName) {
	super(inputName); //設定名稱
    }

    // -----物件方法-----
    /**
     * 抽走另一位玩家的一張牌。
     *
     * @param player 傳入另一位玩家
     */
    @Override
    public void pickAcard(final Player player) {
	final int num = player.getPile().size(); //取得對象牌數
	System.out.printf(StringClass.SHOW_USER_PILE, getPile().size(), getPile());
	Tools.delay(1000); //延遲一秒
	System.out.printf(StringClass.TARGET_CARD, player.getName(), num, num);
	int pickIndex = Tools.typeInteger(1, num) - 1; //指定索引
	final int flag = player.bePicked(pickIndex); //對象被抽牌
	if (flag == 0) { //如果抽牌被駁回
	    System.out.print(StringClass.WANTCHANGE);
	    if (Tools.typeYorN()) { //若輸入y，要換牌
		talk(Talk.AGREECHANGE);
		System.out.printf(StringClass.TARGET_CARD, player.getName(), num, num);
		pickIndex = Tools.typeInteger(1, num, pickIndex) - 1; //指定索引
	    } else { //若不要換牌
		talk(Talk.DISAGREECHANGE);
	    }
	} else if (flag == 1) { //如果抽牌被重洗
	    talk(Talk.PICKASKRANDOM);
	    System.out.printf(StringClass.RANDOMCHANGE, num);
	    pickIndex = Tools.typeInteger(1, num) - 1; //再次指定索引
	} else if (flag == 3) { //如果被送鬼牌
	    pickIndex = player.getPile().isExist(new Card()); //找出鬼牌位置
	}

	final Card c = getAnotherPlayerCard(player, pickIndex);
	System.out.printf(StringClass.SHOW_GET_WHAT_CARD, c);
	Tools.delay(1000); //延遲一秒
	removeCouple(); //消除對牌
    }

    /**
     * 輸出語句。
     *
     * @param sentence 傳入Talk特徵
     */
    @Override
    public void talk(final Talk sentence) {
	switch (sentence) {
	    case AGREECHANGE: //答應換牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.AGREE_CHANGE));
		break;
	    case DISAGREECHANGE: //不答應換牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.DISAGREE_CHANGE));
		break;
	    case PICKASKRANDOM: //洗牌重抽
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.PICK_ASK_RANDOM));
		break;
	    case GETTEDACARD: //被抽牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.GETTED_A_CARD));
		break;
	    case RESIST: //拒絕被抽
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.TAKE_RESIST));
		break;
	    case ASKRANDOM: //要求洗牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.ASK_RANDOM));
		break;
	    case FORCEGHOSTCARD: //送鬼牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.FORCE_GHOSTCARD));
		break;
	    case ALLDOWN: //丟完牌
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.PILE_EMPTY));
		break;
	    case LOSER: //輸家遺言
		System.out.printf(StringClass.SHOW_TALK_NAME_SENTENCE, getName(), Tools.getStringArrayRandom(StringClass.LOSER_TALK));
		break;
	}
	Tools.delay(1000); //延遲一秒
    }

    /**
     * 被另一位玩家抽一張牌。
     *
     * @param index 傳入被挑中的牌序
     * @return 傳回是否成功被抽牌。0為否且可不換；1為否不可不換；2為是。3為直接讓對方抽到鬼牌。
     */
    @Override
    public int bePicked(final int index) {
	System.out.printf(StringClass.SHOW_USER_PILE, getPile().size(), getPile());
	System.out.printf(StringClass.SHOW_COMPUTER_GET, getPile().getCard(index));
	if (resistCount + randomCount == 0) { //耍詐次數用盡
	    if (resistCount + randomCount == 0 && getPile().size() == 1) { //如果沒耍詐次數，也只剩一張牌
		System.out.print(StringClass.USER_TREAK_OVER_ONLY);

	    } else { //如果沒耍詐次數，但有超過一張牌
		System.out.print(StringClass.USER_TREAK_OVER);
	    }
	    Tools.delay(1000); //延遲一秒
	} else { //尚有耍詐次數
	    if (getPile().size() == 1) { //有耍詐次數，但沒牌了
		System.out.print(StringClass.USER_ONLY_LEAST_ONE);
		Tools.delay(1000); //延遲一秒
	    } else { //可以耍詐
		System.out.print(StringClass.USER_TREAK);
		if (Tools.typeYorN()) { //若輸入y，要耍詐
		    System.out.printf(StringClass.USER_TREAK_MENU, resistCount, randomCount, forceGiveGhostCard);
		    int treak = Tools.typeInteger(1, 3);
		    if (treak == 1 && resistCount > 0) { //勸說對方換張牌抽
			talk(Talk.RESIST);
			resistCount--;
			return 0;
		    } else if (treak == 2 && randomCount > 0) { //強制洗牌讓對方重抽
			talk(Talk.ASKRANDOM);
			getPile().random(); //洗牌
			randomCount--;
			return 1;
		    } else if (treak == 3 && forceGiveGhostCard > 0) { //直接送鬼牌
			if (getPile().hasGhostCard()) { //如果有鬼牌
			    talk(Talk.FORCEGHOSTCARD);
			    forceGiveGhostCard--;
			    return 3;
			} else { //如果根本沒鬼牌
			    System.out.print(StringClass.SHOW_NO_GHOST_CARD);
			    return 2;
			}
		    }
		    System.out.print(StringClass.SHOW_TREAK_LEAK);
		}
	    }
	}

	talk(Talk.GETTEDACARD);
	return 2;
    }
}
