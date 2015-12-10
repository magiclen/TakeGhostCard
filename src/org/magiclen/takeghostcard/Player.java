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
 * 玩家抽象類別，被ComputerPlayer、UserPlayer繼承。
 *
 * @see StringClass
 * @see ComputerPlayer
 * @see UserPlayer
 * @see Tools
 * @author Magic Len
 */
public abstract class Player {

    // -----類別方法-----
    /**
     * 取得所有玩家撲克牌的總數。
     *
     * @param player 傳入一堆玩家(Player陣列)。
     * @return 傳回所有玩家的撲克牌數量。
     */
    public static int getCardCounts(final Player[] player) {
	int sum = 0;
	for (final Player p : player) {
	    sum += p.getPile().size();
	}
	return sum;
    }

    /**
     * 找出鬼牌在哪位玩家的手上。
     *
     * @param player 傳入一堆玩家(Player陣列)。
     * @return 傳回擁有鬼牌的玩家索引值，若找不到則傳回-1。
     */
    public static int findGhostCard(final Player[] player) {
	for (int i = 0; i < player.length; i++) {
	    if (player[i].getPile().isEmpty()) {
		continue;
	    }
	    if (player[i].getPile().hasGhostCard()) {
		return i;
	    }
	}
	return -1;
    }

    // -----物件變數-----
    private String name; //玩家名稱
    private final Pile pile = new Pile(); //牌組
    private boolean allDone = false; //牌是否都丟完了

    // -----建構子-----
    /**
     * 建構子。設定玩家名稱。
     *
     * @param inputName 傳入玩家的名稱。
     */
    public Player(final String inputName) {
	setName(inputName); //設定玩家名稱
    }

    //-------------------------共同類別方法-------------------------
    /**
     * 取得玩家名稱。
     *
     * @return 傳回玩家名稱。
     */
    public final String getName() {
	return name;
    }

    /**
     * 設定玩家名稱。
     *
     * @param inputName 傳入名稱
     */
    public final void setName(final String inputName) {
	name = inputName;
    }

    /**
     * 取得玩家所擁有的Pile(牌組)。
     *
     * @return 傳回玩家牌組。
     */
    public final Pile getPile() {
	return pile;
    }

    /**
     * 消除數字成對的牌。
     *
     * @return 以牌組的形式傳回被丟棄的牌。
     */
    public final Pile removeCouple() {
	final Pile removeTrash = new Pile(); //建立新牌組
	if (getPile().size() > 1) {
	    for (int i = 0; i < getPile().size(); i++) { //搜尋數字成對的牌
		final int target_index = getPile().isExist(getPile().getCard(i).getNumber(), i + 1); //取得另一張數字一樣的牌之索引
		if (target_index >= 0) { //如果索引值大於零，表示有找到。
		    removeTrash.add(getPile().getCard(target_index)); //將目前的牌加入removeTrash中
		    removeTrash.add(getPile().getCard(i)); //將找到的牌加入removeTrash中
		    getPile().remove(target_index); //從原牌組中移除目前的牌
		    getPile().remove(i); //從原牌組中移除找到的牌
		    i--; //挑整目前的牌的索引
		}
	    }
	}
	if (removeTrash.size() > 0) { //如果有找到成對的牌
	    System.out.printf(StringClass.SHOW_CARD_REMOVED, getName(), removeTrash); //顯示此玩家丟了什麼牌
	    Tools.delay(1000); //延遲一秒
	}
	return removeTrash; //以牌組的形式傳回被丟棄的牌
    }

    /**
     * 傳回牌是否都丟完了。
     *
     * @return 傳回牌是否都丟完了。用於辨認是否還要繼續抽別的玩家的牌。
     */
    public final boolean isAllDone() {
	return allDone;
    }

    /**
     * 設定牌是否丟完。
     */
    public final void setAllDone() {
	allDone = true;
    }

    /**
     * 從另一位玩家身上取得牌。
     *
     * @param player 傳入另一位玩家
     * @param index 傳入牌的索引
     * @return 傳回取得的牌
     */
    public final Card getAnotherPlayerCard(final Player player, final int index) {
	final Card c = player.getPile().getCard(index); //取得抽到的牌
	getPile().add(c); //放入牌
	player.getPile().remove(index); //移除另一位玩家的牌
	return c;
    }

    // -----抽象方法(需實作)-----
    /**
     * 抽走另一位玩家的一張牌。
     *
     * @param player 傳入另一位玩家
     */
    abstract public void pickAcard(final Player player);

    /**
     * 被另一位玩家抽一張牌。
     *
     * @param index 傳入被挑中的牌序
     * @return 傳回是否成功被抽牌。0為否且可不換；1為否不可不換；2為是。3為直接讓對方抽到鬼牌。
     */
    abstract public int bePicked(final int index);

    /**
     * 輸出語句
     *
     * @param sentence 傳入句子特徵(enum)
     */
    public abstract void talk(final Talk sentence);
}
