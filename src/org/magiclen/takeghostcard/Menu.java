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
 * 程式功能選單。
 *
 * @see StringClass
 * @see MenuMethod
 * @author Magic Len
 */
public class Menu {

    // -----類別方法-----
    /**
     * 顯示遊戲選單。
     */
    public static void show() {
	//顯示清單項目
	System.out.print(StringClass.SHOW_MENU_TOP);
	for (int i = 0; i < StringClass.SHOW_MENU_ITEMS.length; i++) {
	    System.out.printf(StringClass.SHOW_MENU_TYPE, i + 1, StringClass.SHOW_MENU_ITEMS[i]);
	}
	System.out.print(StringClass.SHOW_MENU_END);

	typeData(); //由使用者輸入資料
    }

    /**
     * 由使用者輸入資料(清單上面的號碼)。
     */
    private static void typeData() {
	System.out.print(StringClass.SHOW_TYPE_MENU_ITEM);
	MenuMethod.methods(Tools.typeInteger(1, StringClass.SHOW_MENU_ITEMS.length)); //呼叫Menu選單的方法，傳入引數為選單的選項值
    }
}
