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
 * 抽鬼牌玩法說明。
 *
 * @author Magic Len
 */
public class Explain {

    // -----類別方法-----
    /**
     * 顯示抽鬼牌的玩法說明。
     */
    public static void show() {
	System.out.println("　　抽鬼牌是一種挑戰運氣和心機的遊戲，一局牌局中的玩家至少要有三人，且要有一張鬼牌。");
	type_Enter();
	System.out.println("　　規則就是每位玩家坐成一圈，以順時針或逆時針的方式進行我抽你、你抽他、他抽我牌的遊戲。當玩家手上組合出一對數值(點數)相同的牌時(如：紅心2對黑桃2)，即可拋棄這對牌。遊戲輪到最後，會由某位玩家擁有剩下來的鬼牌，該玩家就是輸家。");
	type_Enter();
	System.out.println("\n本抽鬼牌遊戲程式提供了「人機大戰」、「電腦示範」的遊戲模式。如果您覺得這個遊戲不錯，歡迎造訪我的網站—MagicLen");
	type_Enter();
	System.out.println("http://magiclen.org/");
	type_Enter();
    }

    /**
     * 按下Enter繼續閱讀。
     */
    private static void type_Enter() {
	final Scanner sc = new Scanner(System.in); //建立Scanner物件
	System.out.printf(StringClass.SHOW_TYPE_ENTER); //顯示讓使用者按下Enter繼續的訊息
	sc.nextLine(); //讀取Enter
	System.out.println(); //換行
    }
}
