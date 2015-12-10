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

import java.util.ArrayList;
import java.util.Collections;

/**
 * 撲克牌牌組類別，用來表示一堆(疊、組、副)撲克牌。
 *
 * @see Card
 * @see CardSort
 * @author Magic Len
 */
public class Pile {

    // -----類別方法-----
    /**
     * 建立整附牌組(可決定是否要放入鬼牌)。
     *
     * @param hasGhostCard true，牌組中放入鬼牌；false，牌組中不放入鬼牌。
     * @return 建立完成的牌組。
     */
    public static Pile createFullPile(final boolean hasGhostCard) {
	final Pile p = new Pile(); //建立一副新牌組
	for (int i = 1; i <= 4; i++) { //所有花色範圍
	    for (int j = 1; j <= 13; j++) { //所有排值範圍
		p.add(new Card(i, j)); //加到牌組中
	    }
	}
	if (hasGhostCard) { //若要放入鬼牌
	    p.add(new Card()); //將鬼牌加到牌組中
	}
	return p; //傳回處理好的牌組
    }

    // -----物件常數-----
    private final ArrayList<Card> al = new ArrayList<>(); //建立動態陣列清單物件，用以儲存Card(牌)

    // -----物件方法-----
    /**
     * 添加牌至此牌組中。
     *
     * @param card 傳入一張要增加的牌。
     * @return 如果牌有增加成功，傳回true；否則傳回false。
     */
    public boolean add(final Card card) {
	return al.add(card); //如果牌有增加成功，傳回true。
    }

    /**
     * 從此牌組中移除牌(有多載)。傳入要移除的Card(牌)。
     *
     * @param card 傳入一張要移除的牌。
     * @return 如果牌有移除成功，傳回true；否則傳回false。
     */
    public boolean remove(final Card card) {
	return al.remove(card); //如果牌有移除成功，傳回true。
    }

    /**
     * 從此牌組中移除牌(有多載)。傳入要移除的Card(牌)的索引值。
     *
     * @param index 傳入一個要移除之牌位於此牌組的索引值。
     * @return 如果牌有移除成功，傳回true；否則傳回false。
     */
    public boolean remove(final int index) {
	if (index >= 0) { //如果索引大於零
	    return al.remove(index) != null; //如果牌有移除成功，傳回true。
	} else {
	    return false; //錯誤的索引值，傳回false。
	}
    }

    /**
     * 從此牌組中搜尋指定的牌(有多載)。傳入要搜尋的Card(牌)。
     *
     * @param card 傳入要搜尋的牌。
     * @return 如果存在(同張牌、同花色、同數值)，傳回它的位置；若不存在，傳回-1。
     */
    public int isExist(final Card card) {
	return al.indexOf(card); //找牌並回傳結果。
    }

    /**
     * 從此牌組中搜尋指定的牌(有多載)。傳入要搜尋的牌值。
     *
     * @param cardNumber 傳入要搜尋的牌值。
     * @param leftLimit 要從哪開始搜尋？傳入搜尋的左界線。
     * @return 如果存在(同數值)，傳回它的位置；若不存在，傳回-1。
     */
    public int isExist(final int cardNumber, final int leftLimit) {
	for (int i = leftLimit; i < size(); i++) { //從左界線開始搜尋至右邊最大值。
	    Card c = (Card) al.get(i); //取得索引所指的Card
	    if (c.getNumber() == cardNumber) { //如果牌值相同
		return i; //傳回索引
	    }
	}
	//若找不到相同牌值的牌
	return -1; //傳回-1
    }

    /**
     * 從此牌組中搜尋指定花色的牌。傳入要搜尋的牌值。
     *
     * @param cardPattern 傳入要搜尋的花色值。
     * @param leftLimit 要從哪開始搜尋？傳入搜尋的左界線。
     * @return 如果存在(同花色)，傳回它的位置；若不存在，傳回-1。
     */
    public int isPatternExist(final int cardPattern, final int leftLimit) { //判斷牌是否存在(不一定同張牌，但同花色)。如果存在，傳回它的第一個位置；若不存在，傳回-1。
	for (int i = leftLimit; i < size(); i++) {  //從左界線開始搜尋至右邊最大值。
	    Card c = (Card) al.get(i); //取得索引所指的Card
	    if (c.getPattern() == cardPattern) {  //如果花色值相同
		return i; //傳回索引
	    }
	}
	//若找不到相同花色值的牌
	return -1; //傳回-1
    }

    /**
     * 從此牌組中拿出最上層的一張牌。(索引值最大的牌)
     *
     * @return 傳回取得的Card(牌)，並從牌組中移除該張牌。
     */
    public Card takeTopCard() { //拿出牌(從第一張)
	if (!isEmpty()) { //如果牌組不是空的
	    Card c = getCard(size() - 1); //取得索引值最大的牌
	    remove(size() - 1); //移除該牌
	    return c; //傳回該牌
	} else { //如果牌組已經是空的
	    return null; //傳回null
	}
    }

    /**
     * 從此牌組中取得指定的一張牌。
     *
     * @param index 傳入要取得的牌的索引值。
     * @return 傳回取得的Card(牌)。
     */
    public Card getCard(final int index) {
	return al.get(index); //傳回利用索引值所取得的al物件中的Card(牌)
    }

    /**
     * 由小到大，排序牌組。
     */
    public void sort() {
	Collections.sort(al, new CardSort()); //利用CardSort類別提供的排序演算法進行排序。
    }

    /**
     * 顯示牌組。直接print在螢幕上。
     */
    public void show() {
	System.out.println(getString(al)); //直接print在螢幕上。
    }

    /**
     * 將牌組轉為字串(有多載)。取得此牌組中所包含的所有Card(牌)。
     *
     * @return 傳回此牌組中所含的Card(牌)。
     */
    public String getString() {
	return getString(al); //呼叫多載方法，傳回字串。
    }

    /**
     * 將牌組轉為字串。取得此牌組中所包含的所有Card(牌)，並自動排序。
     *
     * @return 傳回此牌組中所含的Card(牌)。
     */
    public String getStringWithSort() {
	final ArrayList<Card> cards = (ArrayList<Card>) al.clone(); //建立一個新的ArrayList物件來儲存現有的ArrayList，以免更動到資料。
	Collections.sort(cards, new CardSort()); //進行排序
	return getString(cards);
    }

    /**
     * 將牌組轉為字串(有多載)。取得ArrayList陣列中所包含的所有Card(牌)。
     *
     * @see cards 傳入存放之物件為Card的ArrayList。
     * @return 傳回ArrayList中所含的Card(牌)。
     */
    private String getString(final ArrayList<Card> cards) {
	final StringBuilder sb = new StringBuilder(); //建立StringBuilder物件，用來儲存須更變多次的字串。
	for (int i = 0; i < cards.size(); i++) { //搜尋傳入的ArrayList之所有範圍
	    sb.append(cards.get(i)); //將牌的名稱加到字串內
	    if (i != cards.size() - 1) { //如果還沒到結尾
		sb.append(" "); //加上空格
	    }
	}
	return sb.toString(); //傳回串接好的字串
    }

    /**
     * 顯示由小到大已經排序好的牌組，直接print在螢幕上。(但事實上牌組並未排序)
     */
    public void showWithSort() { //顯示排序後的牌組(實際尚未排序)
	System.out.println(getStringWithSort()); //將結果直接print在螢幕上。
    }

    /**
     * 取得牌組大小，即所含Card(牌)的數量。
     *
     * @return 傳回牌組中Card(牌)的數量。
     */
    public int size() {
	return al.size(); //傳回數量
    }

    /**
     * 是不是空牌組，即沒有任何Card(牌)在這個牌組中。
     *
     * @return 若牌組是空的，傳回true；否則傳回false。
     */
    public boolean isEmpty() {
	return al.isEmpty(); //傳回al物件是否是空的
    }

    /**
     * 洗牌。將此牌組中的所有牌順序進行亂數隨機交換。
     */
    public void random() {
	for (int i = 0; i < size() * 100; i++) { //洗牌次數為牌組大小的100倍次
	    final int rnd = (int) (Math.random() * size()); //隨機產生要移動的牌之索引值
	    Card temp = al.get(rnd); //取得索引值對應的牌
	    //將該牌從最後面移到最前面
	    al.remove(rnd);
	    al.add(temp);
	}
    }

    /**
     * 牌組中有沒有鬼牌？
     *
     * @return 若牌組中有鬼牌，傳回true；否則傳回false。
     */
    public boolean hasGhostCard() { //有沒有鬼牌
	return (isExist(new Card()) != -1); //傳回鬼牌是否存在
    }

    //-------------------------覆寫方法-------------------------
    /**
     * 將牌組轉為字串。取得此牌組中所包含的所有Card(牌)。
     *
     * @return 傳回此牌組中所含的Card(牌)。
     */
    @Override
    public String toString() { //傳回字串
	return getString(); //呼叫getString方法
    }
}
