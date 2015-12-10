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
 * 字串常數集。
 *
 * @author Magic Len
 */
public class StringClass {

    /**
     * 儲存玩家名稱(此類別唯一的變數)。
     */
    public static String userName = "玩家"; //變數
    /**
     * 提示輸入使用者名稱。
     */
    public static final String SHOW_TYPE_USER_NAME = "請先輸入您的大名：";
    /**
     * 提示輸入的使用者名稱有誤。
     */
    public static final String SHOW_TYPE_USER_NAME_ERROR = "未輸入大名，請重新輸入：";
    /**
     * 歡迎玩猜數字遊戲的訊息。
     */
    public static final String SHOW_WELCOME = "\n\t歡迎來玩Java抽鬼詐牌遊戲\n\n";
    /**
     * 歡迎使用者的訊息。
     */
    public static final String SHOW_USER_WELCOME = "%s 您好！\n"; //printf
    /**
     * 主選單的頂端。
     */
    public static final String SHOW_MENU_TOP = "\n********************主選單********************\n";
    /**
     * 主選單項目的顯示格式。
     */
    public static final String SHOW_MENU_TYPE = "\t%d.%s\n"; //printf
    /**
     * 主選單中的項目。
     */
    public static final String SHOW_MENU_ITEMS[] = {"人機大戰", "電腦示範", "玩法說明", "離開選單"};
    /**
     * 主選單的底端。
     */
    public static final String SHOW_MENU_END = "*********************************************\n";
    /**
     * 提示輸入主選單的項目值。
     */
    public static final String SHOW_TYPE_MENU_ITEM = "請輸入您要的功能[1-" + SHOW_MENU_ITEMS.length + "]：";
    /**
     * 提示輸入的主選單項目值有誤。
     */
    public static final String SHOW_TYPE_ITEM_ERROR = "輸入有誤，請重新輸入[%d-%d]："; //printf
    /**
     * 是否關閉程式的訊息。
     */
    public static final String SHOW_EXIT_MENU = "離開選單等於關閉程式，您確定嗎？[Y/N]：";
    /**
     * 取消關閉程式的訊息。
     */
    public static final String SHOW_WELCOME_BACK = "歡迎回來！";
    /**
     * 關閉程式的訊息。
     */
    public static final String SHOW_EXIT = "掰掰 %s ，歡迎下次再來挑戰！\n"; //printf
    /**
     * Y或N輸入錯誤的訊息。
     */
    public static final String SHOW_EXIT_ERROR = "輸入有誤，請重新輸入[Y/N]：";
    /**
     * Enter鍵訊息。
     */
    public static final String SHOW_TYPE_ENTER = "(按下Enter繼續...) ";
    /**
     * 提示輸入玩家數量。
     */
    public static final String SHOW_TYPE_PLAYERS_NUMBER = "請輸入進行抽鬼牌遊戲的玩家數量[3-9]：";
    /**
     * 提示輸入玩家數量的值有誤。
     */
    public static final String SHOW_TYPE_PLAYERS_NUMBER_ERROR = "數量輸入有誤，請重新輸入[3-9]：";
    /**
     * 電腦名稱。
     */
    public static final String[] COMPUTER_NAMES = {"「狂獅」鐵戰", "「血手」杜殺", "「不男不女」屠嬌嬌", "「笑裡藏刀」哈哈兒", "「不吃人頭」李大嘴", "「惡賭鬼」軒轅三光", "「迷死人不賠命」蕭咪咪", "「半人半鬼」陰九幽", "「損人不利己」白開心", "「寧死不吃虧、拚命佔便宜」歐陽丁當", "「程式作者」幻嵐", "「詛咒纏身」祿語海", "「霹靂第一」襲滅天來", "「機器貓」小叮噹", "「網球王子」越前龍馬", "「海賊王」哥爾羅傑", "「海賊王的男人」魯夫", "「史上最強弟子」兼一", "「通靈王」麻倉葉", "「遊戲王」武藤遊戲", "「烘焙王」東河馬", "「東邪」黃藥師", "「西毒」歐陽鋒", "「南僧」一燈大師", "「北丐」洪七公", "「中神通」王重陽", "「西狂」楊過", "「北俠」郭靖", "「中頑童」周伯通", "「花果山水濂洞」孫悟空", "「名偵探」柯南"};
    /**
     * 開局訊息。
     */
    public static final String START_GAME = "\n本局玩家有：";
    public static final String START_GAME_Player = "。共%d人。\n\n"; //printf
    /**
     * 玩家名稱：說話語句。
     */
    public static final String SHOW_TALK_NAME_SENTENCE = "%s：%s\n";
    /**
     * 發牌。
     */
    public static final String[] COMPUTER_DEAL_CARD = {"看你們都沒有人想發牌，那就由我來發吧！", "我來發牌好了。", "我要發牌，怕你們作弊。", "我發牌，絕對公正。", "我就辛苦點，幫你們發牌吧。", "你們這些懶惰鬼，都不想動牌就是了。我來發牌，不要有異議！", "我發牌很快，我來發吧！", "給我1秒，我就能發完牌。", "只要ㄅㄧㄤˋ ㄅㄧㄤˋ聲，我很快地就能把牌發完。", "發牌的事情就交給我吧。", "我這個人最喜歡發牌了，嘻嘻...", "啊是沒人想發牌喔？我來發好了。", "這疊俗稱「荷蘭牌」的撲克牌，就由我來發吧！", "我要發牌。"};
    public static final String SHOW_COMPUTER_DEALING_CARD = "(發牌中...)\n";
    public static final String[] COMPUTER_DEAL_CARD_FINISH = {"牌發完了，我先來抽吧！", "牌發完了，很快吧～我先抽牌。", "應該沒有發錯，那就先從我開始抽牌。", "終於發完了，手好痠，讓我先抽一張牌吧。", "牌發完啦！", "我發牌還真的挺快的。"};
    /**
     * 剛丟完牌。
     */
    public static final String[] PILE_EMPTY = {"我沒牌啦！", "沒牌了。", "沒牌了，跳過我吧。", "我沒牌了。", "我結束了。", "沒牌啦！", "幸好這次輸家不是我。", "好家在，牌都沒了。", "看來幸運女神站在我這邊。"};
    /**
     * 抽牌。
     */
    public static final String[] GET_A_CARD = {"嗯...就抽這張牌！", "我要抽這張牌。", "我要抽你這張。", "就抽你這張！", "你這張牌就交給我吧。", "給抽嗎？"};
    /**
     * 被抽牌。
     */
    public static final String[] GETTED_A_CARD = {"拿去。", "Here you are.", "拿去吧。", "儘管抽。", "送你啦！", "隨便你抽。", "快點抽走吧。", "快抽吧。", "快點抽。", "快抽啦！", "抽吧。", "真愛抽。", "送你的。", "拿去哺啦。"};
    /**
     * 拒絕被抽(心機)。
     */
    public static final String[] TAKE_RESIST = {"你確定要抽這張？", "嘿嘿...你確定要抽這張嗎？", "嘻嘻，你摸到的這張不錯。", "嘿嘿，這張好啊！", "建議你換抽別張牌。", "我好心提醒你，最好別抽這張牌。", "你確定要抽這張牌嗎？", "Are you sure?", "呵呵，快點拿走它吧。", "快抽快抽！哈哈哈，不要後悔就好了。", "你確定要抽這張，不會後悔？別說我沒提醒你。", "兄弟，我對你最好了，聽我的，別抽這張。"};
    /**
     * 答應換牌。
     */
    public static final String[] AGREE_CHANGE = {"感覺有點奇怪，我換一張好了。", "那我換一張牌。", "那我換牌好了。", "我知道你在打什麼主意，騙不了我也嚇不了我。我要換抽別張，以免中你計。", "不用你說，我早就想換抽別張牌了。", "我要換抽別張。"};
    /**
     * 不答應換牌。
     */
    public static final String[] DISAGREE_CHANGE = {"我就是要這張。", "我確定要抽這張，別想動搖我。", "啦啦啦，不想聽你說話。", "當我白癡嗎？", "我知道你在打什麼主意，騙不了我也嚇不了我。我要抽這張就是這張。", "我相信我的第六感。", "我的直覺最準。", "我相信我的直覺。", "我相信我的第六感。"};
    /**
     * 抽到鬼牌。
     */
    public static final String[] PICK_GHOST_CARD = {"可惡！", "運氣真差！", "該死。", "居然抽到鬼...呼，差點說溜嘴。", "鬼牌！"};
    /**
     * 鬼牌被抽走。
     */
    public static final String[] BE_PICK_GHOST_CARD = {"哈哈！", "呵呵！", "恭喜你抽到好牌。", "嘻嘻。", "嘿嘿...", "哇哈哈！"};
    /**
     * 要求洗牌。
     */
    public static final String[] ASK_RANDOM = {"等一下！先讓我洗個牌。", "我想先洗牌。", "不行！讓我先洗牌。", "別急，讓我洗個牌。"};
    /**
     * 洗牌重抽。
     */
    public static final String[] PICK_ASK_RANDOM = {"洗什麼牌，真賊。那我抽這張。", "不管你怎麼洗牌，結果都是一樣的。我要抽這張。", "你洗牌！真卑鄙。我要抽這張牌。", "好個洗牌招，讓我不知道抽哪張了。算了，隨便抽一個。"};
    /**
     * 強迫送給對方鬼牌。
     */
    public static final String[] FORCE_GHOSTCARD = {"這張給你比較快！", "這張直接給你，不用說謝謝了。", "鬼牌直接送你啦！不用還我。", "你要這張嗎？好，給你。", "你好像很想要這張？", "我知道你很需要這張牌。"};
    /**
     * 抽牌訊息。
     */
    public static final String SHOW_PLAYING_PLAYER = "\n(%s 抽 %s)\n"; //printf
    /**
     * 回合訊息。
     */
    public static final String SHOW_GAME_STAGE = "\n\n------第%d回合------\n"; //printf
    /**
     * 牌局結束。
     */
    public static final String SHOW_GAME_OVER = "\n\n-------------------\n\n經過%d個回合的大戰之後，輸家是......\n"; //printf
    public static final String SHOW_GAME_OVER_CONTINUE = "%s！！\n\n"; //printf
    /**
     * 輸家遺言。
     */
    public static final String[] LOSER_TALK = {"啊啊啊！輸了！。", "我居然...輸了。", "真難以置信。", "果然玩不過這些心機重的人。", "只是運氣不好。", "運氣不好罷了。"};
    /**
     * 遊戲結束訊息。
     */
    public static final String SHOW_GAME_EXIT = "\n遊戲已結束，要繼續嗎？[Y/N]：";
    /**
     * 電腦玩家的掰掰語句。
     */
    public static final String[] COMPUTER_BYE = {"掰掰！", "88", "86", "下次再來。", "886", "881", "再見啦！", "再會啦！", "有緣再見。"};
    /**
     * 抽對象的哪張牌。
     */
    public static final String TARGET_CARD = "%s 共有%d張牌，您要抽第幾張？[1-%d]："; //printf
    /**
     * User抽牌被駁回。
     */
    public static final String WANTCHANGE = "對方這樣說，其中必有詐，您要換抽別張牌嗎？[Y/N]：";
    /**
     * User抽牌被洗牌。
     */
    public static final String RANDOMCHANGE = "對方居然洗了牌。您要抽第幾張？[1-%d]："; //printf
    /**
     * 顯示抽到的牌。
     */
    public static final String SHOW_GET_WHAT_CARD = "您抽到%s。\n"; //printf
    /**
     * 顯示玩家擁有的牌。
     */
    public static final String SHOW_USER_PILE = "\n您有%d張牌，分別是：%s\n\n"; //printf
    /**
     * 顯示對方即將要抽到的牌。
     */
    public static final String SHOW_COMPUTER_GET = "他想抽您的%s。"; //printf
    /**
     * 只剩一張牌，無法耍詐。
     */
    public static final String USER_ONLY_LEAST_ONE = "您只剩一張牌，無法耍詐。\n";
    /**
     * 詢問耍詐。
     */
    public static final String USER_TREAK = "要耍詐嗎？[Y/N]：";
    /**
     * 耍詐選單。
     */
    public static final String USER_TREAK_MENU = "----------------\n1.勸說對方換張牌抽(剩餘%d次)\n2.強制洗牌讓對方重抽(剩餘%d次)\n3.直接送對方一張鬼牌(剩餘%d次)\n----------------\n您想用哪種詐術？[1-3]："; //printf
    /**
     * 耍詐次數用完。
     */
    public static final String USER_TREAK_OVER = "耍詐次數已用盡，無法再做什麼小動作。\n";
    /**
     * 耍詐次數用完、牌也只剩一張。
     */
    public static final String USER_TREAK_OVER_ONLY = "\n";
    /**
     * 耍詐次數用完、牌也只剩一張。
     */
    public static final String SHOW_COMPUTER_GETTED = "他抽了您的%s。\n"; //printf
    /**
     * 耍詐次數用完提示。
     */
    public static final String SHOW_TREAK_LEAK = "此詐術次數已用盡，您已被盯上，只能乖乖給別人抽。\n";
    /**
     * 顯示移除掉的牌。
     */
    public static final String SHOW_CARD_REMOVED = "\n%s 丟出：%s。\n"; //printf
    /**
     * 沒有鬼牌。
     */
    public static final String SHOW_NO_GHOST_CARD = "您沒有鬼牌。";
}
