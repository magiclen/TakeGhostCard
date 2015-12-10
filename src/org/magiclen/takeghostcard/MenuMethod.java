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
 * 程式功能選單(Menu)所搭配連結的方法
 *
 * @see StringClass
 * @see Menu
 * @author Magic Len
 */
public class MenuMethod {

    // -----類別方法-----
    /**
     * 將從清單(Menu)傳來的選項值利用switch關鍵字套用至對應的方法。
     *
     * @param index 輸入清單選項值
     * @return 傳回0表示方法的結束
     * @see Player
     * @see ComputerPlayer
     * @see UserPlayer
     * @see Stage
     * @see StringClass
     * @see Menu
     * @see Tools
     */
    public static int methods(final int index) {
	/**
	 * 選擇要執行的方法
	 */
	switch (index) {
	    case 1: //人機大戰
	    case 2: //電腦示範
		final int number = setPlayerNumber(); //取得玩家數量
		final Player[] p = new Player[number]; //建立玩家
		do {
		    int n = number;
		    if (index == 1) { //人機大戰
			n--; //電腦玩家人數-1，這樣才能塞使用者玩家進去。
			p[n] = new UserPlayer(StringClass.userName);
		    }
		    final String[] name = ComputerPlayer.createComputerPlayerNames(n); //創造電腦玩家的名字
		    for (int i = 0; i < n; i++) { //創立電腦玩家的實體
			p[i] = new ComputerPlayer(name[i]);
		    }
		} while (Stage.play(p)); //建立無窮迴圈舞台
		break;
	    case 3: //抽鬼牌玩法說明
		Explain.show();
		break;
	    case 4: //離開選單
		System.out.print(StringClass.SHOW_EXIT_MENU);
		if (Tools.typeYorN()) { //若輸入y
		    System.out.printf(StringClass.SHOW_EXIT, StringClass.userName); //顯示結束訊息
		    Tools.delay(1000); //延遲一秒
		    return 0; //結束方法
		} else { //若輸入n
		    System.out.println(StringClass.SHOW_WELCOME_BACK);
		}
	}
	Menu.show(); //顯示選單
	return 0; //結束方法
    }

    /**
     * 設定玩家數量。
     *
     * @return 傳回正確的玩家數量
     */
    private static int setPlayerNumber() {
	final Scanner sc = new Scanner(System.in);  //建立Scanner物件
	System.out.print(StringClass.SHOW_TYPE_PLAYERS_NUMBER);
	String inputString = sc.nextLine(); //儲存使用者輸入的資料
	while (inputString.length() != 1 || inputString.charAt(0) < '3' || inputString.charAt(0) > '9') { //如果輸入有誤
	    System.out.print(StringClass.SHOW_TYPE_PLAYERS_NUMBER_ERROR);
	    inputString = sc.nextLine();
	}
	try {
	    return Integer.parseInt(inputString); //將字串轉為整數傳回
	} catch (final Exception e) { //如果字串轉為整數的過程出現錯誤
	    System.err.println(e.getMessage()); //顯示錯誤訊息
	    return 3; //自動以3人的方式進行遊戲
	}
    }
}
