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

import java.util.Comparator;

/**
 * 撲克牌單張類別，實作Comparable介面，使得牌可以被比較大小，令其可被用於排序法。
 *
 * @author Magic Len
 */
public class Card implements Comparable<Card> {

    // -----物件變數-----
    private int pattern; //牌的花色。0為鬼牌；1為黑桃；2為紅心；3為梅花；4為方塊。
    private int number; //牌的數字(牌值)。範圍是1~13。1、11~13分別代表A,J,Q,K。
    public String tag; //作為特殊標記使用。

    // -----建構子-----
    /**
     * 建構子(有多載)。無傳入任何引數，直接建立出鬼牌(花色值為0)。
     */
    public Card() {
	try {
	    setPattern(0);  //設定牌的花色
	} catch (final Exception e) { //如果設定時發生錯誤
	    System.err.println(e.getMessage());  //顯示錯誤訊息
	}
    }

    /**
     * 建構子(有多載)。傳入牌的花色和數值。
     *
     * @param cardPattern 牌的花色
     * @param cardNumber 牌的數值
     */
    public Card(final int cardPattern, final int cardNumber) {
	try {
	    setPattern(cardPattern); //設定牌的花色
	    setNumber(cardNumber); //設定牌的數值
	} catch (final Exception e) { //如果設定時發生錯誤
	    System.err.println(e.getMessage()); //顯示錯誤訊息
	}
    }

    //-------------------------物件方法-------------------------
    /**
     * 判斷這張牌是不是鬼牌。
     *
     * @return 如果是鬼牌，傳回true；否則傳回false。
     */
    public boolean isGhostCard() {
	return (pattern == 0); //若花色值等於0，表示為鬼牌。
    }

    /**
     * 判斷這張牌的顏色是不是紅色的愛心或是方塊。
     *
     * @return 如果是，傳回true；否則傳回false。
     */
    public boolean isRed() {
	return (!isGhostCard() && getPattern() % 2 == 0); //不是鬼牌，且花色是紅色的(花色值為偶數)。
    }

    /**
     * 判斷這張牌的顏色是不是黑色的愛心或是方塊。
     *
     * @return 如果是，傳回true；否則傳回false。
     */
    public boolean isBlack() {
	return (!isGhostCard() && getPattern() % 1 == 0); //不是鬼牌，且花色是黑色的(花色值為奇數)。
    }

    /**
     * 判斷這張牌的值是不是數字，而非A、J、Q、K等文字，更不是鬼牌。
     *
     * @return 如果是，傳回true；否則傳回false。
     */
    public boolean isNumber() {
	return (!isGhostCard() && getNumber() >= 2 && getNumber() <= 10); //不是鬼牌，且數值是數字(介於2~10之間)。
    }

    /**
     * 判斷這張牌的值是不是A、J、Q、K等文字，但不是鬼牌。
     *
     * @return 如果是，傳回true；否則傳回false。
     */
    public boolean isCharacter() {
	return (!isGhostCard() && (getNumber() == 1 || (getNumber() >= 11 && getNumber() >= 13))); //不是鬼牌，且數值是文字(1或介於11~13之間)。
    }

    /**
     * 設定牌的花色。
     *
     * @exception Exception 如果設定範圍有誤，則丟出含有錯誤範圍訊息的例外。
     * @param cardPattern 牌的花色
     */
    private void setPattern(final int cardPattern) throws Exception {
	if (cardPattern >= 0 && cardPattern <= 4) { //花色值必須介於0~4之間。0為鬼牌；1為黑桃；2為紅心；3為梅花；4為方塊。
	    pattern = cardPattern; //將參數值儲存至資料成員。
	} else { //若輸入範圍有誤
	    throw new Exception(String.format("%d 不是一個正確的撲克牌花色", cardPattern)); //拋出例外
	}
    }

    /**
     * 設定牌的數值。
     *
     * @exception Exception 如果設定範圍有誤，則丟出含有錯誤範圍訊息的例外。
     * @param cardNumber 牌的數值
     */
    private void setNumber(final int cardNumber) throws Exception { //設定數值
	if (cardNumber >= 1 && cardNumber <= 13) { //牌值必須介於1~13之間。1、11~13分別代表A,J,Q,K。
	    number = cardNumber; //將參數值儲存至資料成員。
	} else { //若輸入範圍有誤
	    throw new Exception(String.format("%d 不是一個正確的撲克牌數值", cardNumber)); //拋出例外
	}
    }

    /**
     * 取得牌的花色。
     *
     * @return 傳回牌的花色值。0為鬼牌；1為黑桃；2為紅心；3為梅花；4為方塊。
     */
    public int getPattern() {
	return pattern; //傳回牌的花色值。
    }

    /**
     * 取得牌值。
     *
     * @return 傳回牌值。1、11~13分別代表A,J,Q,K，其餘值為原數字。鬼牌之牌值為0。
     */
    public int getNumber() {
	if (!isGhostCard()) { //如果這張牌不是鬼牌
	    return number; //傳回牌值
	} else { //如果這張牌是鬼牌
	    return 0; //傳回0
	}
    }

    /**
     * 取得花色值所代表的字串。
     *
     * @return 傳回花色值所代表的字串。花色值等於0的話為"鬼牌"；1為"黑桃"；2為"紅心"；3為"梅花"；4為"方塊"。
     */
    public String getPatternString() {
	if (!isGhostCard()) { //如果這張牌不是鬼牌
	    switch (getPattern()) { //判斷花色值
		case 1:
		    return ("黑桃");
		case 2:
		    return ("紅心");
		case 3:
		    return ("梅花");
		case 4:
		    return ("方塊");
		default:
		    return ("未知");
	    }
	} else { //如果這張牌是鬼牌
	    return ("鬼牌");
	}
    }

    /**
     * 取得牌值所代表的字串。
     *
     * @return 傳回牌值所代表的字串。牌值為1或11~13的話分別代表"A","J","Q","K"，其餘值為原數字。
     */
    public String getNumberString() {
	if (!isGhostCard()) { //如果這張牌不是鬼牌
	    switch (getNumber()) { //判斷牌值
		case 1:
		    return "A";
		case 11:
		    return "J";
		case 12:
		    return "Q";
		case 13:
		    return "K";
		default: //若牌值非上述的文字牌
		    return String.valueOf(getNumber()); //直接將數值轉成字串傳回
	    }
	} else {  //如果這張牌是鬼牌
	    return "0";
	}
    }

    /**
     * 轉成字串，取得牌名。
     *
     * @return 傳回牌名。鬼牌或是花色+牌值。
     */
    @Override
    public String toString() {
	if (!isGhostCard()) { //如果這張牌不是鬼牌
	    return getPatternString() + getNumberString(); //傳回花色+牌值
	} else { //如果這張牌是鬼牌
	    return getPatternString(); //傳回鬼牌
	}
    }

    /**
     * 比較兩張牌是否相同。
     *
     * @param object 傳入一個物件。
     * @return 傳回兩張牌是否相同。若花色和牌值均相等，判斷為相同牌。
     */
    @Override
    public boolean equals(final Object object) {
	if (object instanceof Card) { //如果obj物件是一張牌
	    final Card c = (Card) object; //將obj物件轉成c牌
	    return ((this.isGhostCard() && c.isGhostCard()) || this.hashCode() == c.hashCode()); //如果都為鬼牌或是花色和牌值相同，傳回true；否則傳回false。
	} else {  //如果obj物件不是一張牌
	    return false; //直接傳回false
	}
    }

    /**
     * 雜驟值。
     *
     * @return 傳回雜湊值
     */
    @Override
    public int hashCode() {
	return this.getPattern() * 100 + this.getNumber();
    }

    /**
     * 比較兩張牌的大小。
     *
     * @param card 傳入一張牌。
     * @return 傳回兩張牌值的差距。若這張牌加權牌值大於目標牌加權牌值，傳回值為正；相同，傳回零；小於，傳回負數。
     */
    @Override //覆寫
    public int compareTo(final Card card) {
	//加權牌值計算公式 = 花色值*100 + 原牌值。
	final int this_score = this.getPattern() * 100 + this.getNumber(); //取得這張牌加權之後的牌值。
	final int score = card.getPattern() * 100 + card.getNumber(); //取得目標牌加權之後的牌值。
	return this_score - score; //若這張牌加權牌值大於目標牌加權牌值，傳回值為正；相同，傳回零；小於，傳回負數。
    }
}

/**
 * 撲克牌排序法類別，實作Comparator介面。
 *
 * @author Magic Len
 */
class CardSort implements Comparator<Card> {

    /**
     * 比較兩張牌的大小。
     *
     * @param card1 傳入一張牌。
     * @param card2 傳入另一張牌。
     * @return 傳回兩張牌值的差距。若o1加權牌值大於o2加權牌值，傳回值為正；相同，傳回零；小於，傳回負數。
     */
    @Override //覆寫
    public int compare(final Card card1, final Card card2) {
	return card1.compareTo(card2);
    }
}
