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
 * 常用的方法，獨立出一個類別來存放。
 *
 * @author Magic Len
 */
public class Tools {

    // -----類別方法-----
    /**
     * 時間延遲。
     *
     * @param time 輸入延遲時間(單位：毫秒)
     */
    public static void delay(final int time) {
	try {
	    Thread.sleep(time); //延遲
	} catch (final Exception e) {
	}
    }

    /**
     * 只能輸入Y或N，其餘免談，會要求重新輸入。
     *
     * @see StringClass
     * @return 輸入Y傳回true，輸入N傳回false
     */
    public static boolean typeYorN() {
	final Scanner sc = new Scanner(System.in);
	String inputEnd = sc.nextLine().trim(); //儲存使用者輸入的資料
	while (inputEnd.length() != 1 || (!inputEnd.equalsIgnoreCase("Y") && !inputEnd.equalsIgnoreCase("N"))) { //如果輸入有誤
	    System.out.print(StringClass.SHOW_EXIT_ERROR);
	    inputEnd = sc.nextLine().trim();
	}
	return inputEnd.equalsIgnoreCase("Y");
    }

    /**
     * 用亂數的方式隨機取得字串陣列的項目。
     *
     * @param array 傳入字串陣列
     * @return 傳回隨機取得的字串項目
     */
    public static String getStringArrayRandom(final String[] array) {
	return array[getRandom(0, array.length)];
    }

    /**
     * 用亂數的方式隨機取得某範圍中的整數。
     *
     * @param a 範圍下限(包含)
     * @param b 範圍上限(不包含)
     * @return 傳回隨機取得的字串項目
     */
    public static int getRandom(final int a, final int b) {
	return (int) (Math.random() * (b - a) + a);
    }

    /**
     * 輸入某範圍中的整數(有多載)。
     *
     * @param a 範圍下限(包含)
     * @param b 範圍上限(不包含)
     * @return 傳回輸入的正確範圍整數
     */
    public static int typeInteger(final int a, final int b) {
	final Scanner sc = new Scanner(System.in);
	String inputString = sc.nextLine().trim(); //儲存使用者輸入的資料
	while (true) {
	    try {
		int num = Integer.parseInt(inputString);
		if (num >= 1 && num <= b) {
		    return num;
		} else {
		    throw new Exception("範圍錯誤");
		}
	    } catch (final Exception e) {
		System.out.printf(StringClass.SHOW_TYPE_ITEM_ERROR, a, b);
		inputString = sc.nextLine().trim();
	    }
	}
    }

    /**
     * 輸入某範圍中的整數(有多載)，c除外。
     *
     * @param a 範圍下限(包含)
     * @param b 範圍上限(不包含)
     * @param c 例外
     * @return 傳回輸入的正確範圍整數
     */
    public static int typeInteger(final int a, final int b, final int c) {
	int n;
	try {
	    n = typeInteger(a, b);
	    if (n == c) {
		throw new Exception("不能輸入" + c + "！");
	    }
	    return n;
	} catch (final Exception e) {
	    System.out.printf(StringClass.SHOW_TYPE_ITEM_ERROR, a, b);
	    return typeInteger(a, b, c);
	}
    }
}
