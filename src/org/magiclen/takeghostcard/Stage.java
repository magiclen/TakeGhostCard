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
 * 舞台。為遊戲主體。
 *
 * @see StringClass
 * @see ComputerPlayer
 * @see UserPlayer
 * @see Tools
 * @see Player
 * @see Talk
 * @author Magic Len
 */
public class Stage {

    /**
     * 開始遊戲。
     *
     * @param p 以陣列的形式傳入玩家。
     * @return 如果要繼續遊戲，傳回true；否則傳回false
     */
    public static boolean play(final Player p[]) {
	final int number = p.length; //取得玩家總數
	System.out.print(StringClass.START_GAME);
	for (int i = 0; i < number; i++) {
	    if (p[i] instanceof UserPlayer) {
		System.out.print("「您」");
	    }
	    System.out.print(p[i].getName());
	    if (i != number - 1) {
		System.out.print("、");
	    } else {
		System.out.printf(StringClass.START_GAME_Player, number);
	    }
	}
	Tools.delay(2500); //延遲兩秒半
	p[0].talk(Talk.DEALCARD); //由第一位玩家發牌
	System.out.print(StringClass.SHOW_COMPUTER_DEALING_CARD);
	Tools.delay(2000); //延遲兩秒
	final Pile fullPile = Pile.createFullPile(true); //建立含有鬼牌的牌組
	fullPile.random(); //洗牌
	for (int i = 0; !fullPile.isEmpty(); i++) {
	    p[i % number].getPile().add(fullPile.takeTopCard()); //分牌
	}
	p[0].talk(Talk.DEALCARDFINISH); //第一位玩家發完牌

	//消除成對牌
	for (int i = 0; i < number; i++) {
	    p[i].removeCouple();
	}

	//輪流抽牌
	int sum = Player.getCardCounts(p); //儲存玩家手牌總數
	int stage = 0; //儲存回合數
	for (int i = 0; sum != 1; i++) {
	    if ((i / number) + 1 != stage) {
		stage = (i / number) + 1;
		System.out.printf(StringClass.SHOW_GAME_STAGE, stage);
	    }
	    Player current = p[i % number]; //設定目前玩家
	    if (current.getPile().isEmpty()) { //如果該玩家已經沒有牌了
		if (!current.isAllDone()) { //如果此玩家剛丟完牌
		    System.out.println(); //換行
		    current.talk(Talk.ALLDOWN);
		    current.setAllDone();
		}
		continue; //跳過該玩家
	    }
	    int j = i + 1; //儲存抽牌對象編號
	    while (true) { //搜尋抽牌對象
		if (!p[j % number].getPile().isEmpty()) { //若對象有牌
		    j %= number;
		    break;
		}
		j++;
	    }
	    Player target = p[j]; //設定抽牌對象
	    System.out.printf(StringClass.SHOW_PLAYING_PLAYER, current.getName(), target.getName());
	    Tools.delay(1500); //延遲一秒半
	    current.pickAcard(target); //抽牌

	    sum = Player.getCardCounts(p); //儲存玩家手牌總數
	}
	final int l = Player.findGhostCard(p); //找出鬼牌在哪

	System.out.printf(StringClass.SHOW_GAME_OVER, stage);
	Tools.delay(2000); //延遲兩秒
	System.out.printf(StringClass.SHOW_GAME_OVER_CONTINUE, p[l].getName());
	Tools.delay(2000); //延遲兩秒
	p[l].talk(Talk.LOSER);
	Tools.delay(1000); //延遲一秒

	return exit(p[0]);
    }

    /**
     * 關閉舞台。電腦玩家跟你說掰掰。
     *
     * @param player 玩家
     * @return 傳回true或false到play()方法
     */
    private static boolean exit(final Player player) {
	System.out.print(StringClass.SHOW_GAME_EXIT);
	if (Tools.typeYorN()) { //若輸入y
	    return true; //傳回true。
	} else { //若輸入n
	    player.talk(Talk.BYE);
	    return false; //傳回false，跳出menu的迴圈
	}
    }
}
