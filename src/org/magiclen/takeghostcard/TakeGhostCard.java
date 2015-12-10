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
 * <p>
 * 抽鬼牌文字版！TakeGhostCard</p>
 * <p>
 * 這是文字版的抽鬼牌遊戲，一開始要先設定玩家人數才能進行遊玩。遊戲模式如下：</p>
 * <ul>
 * <li>
 * 1.人機大戰
 * </li>
 * <li>
 * 2.電腦示範
 * </li>
 * </ul>
 *
 * @see StringClass
 * @see Menu
 * @author Magic Len
 */
public class TakeGhostCard {

    /**
     * 程式進入點。
     *
     * @param args 若有輸入一筆字串資料就直接拿來作為使用者名稱
     */
    public static void main(final String[] args) {
	welcomeMessage();  //顯示歡迎訊息

	/*
	 * 判斷是否有輸入args，若有則跳過讓使用者輸入名稱的步驟，直接作為使用者名稱
	 */
	if (args.length >= 1) { //若args有超過一筆資料
	    StringClass.userName = args[0]; //以第一筆資料當作玩家姓名
	} else {
	    setUserName();
	}
	System.out.printf(StringClass.SHOW_USER_WELCOME, StringClass.userName);

	/*
	 * 顯示清單。程式進入點結束任務了
	 */
	Menu.show();
    }

    /**
     * 設定使用者名稱。(若args沒有輸入則不會被執行)。
     */
    private static void setUserName() {
	final Scanner sc = new Scanner(System.in);

	System.out.print(StringClass.SHOW_TYPE_USER_NAME);
	String userName = sc.nextLine().trim();
	while (userName.equals("")) { //如果沒有輸入
	    System.out.print(StringClass.SHOW_TYPE_USER_NAME_ERROR);
	    userName = sc.nextLine().trim();
	}
	StringClass.userName = userName;
    }

    /**
     * 顯示歡迎訊息。
     */
    private static void welcomeMessage() {
	System.out.print(StringClass.SHOW_WELCOME);
    }
}
