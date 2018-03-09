package com.su.processor;

import com.su.bo.Category;
import com.su.utils.MD5Util;
import lombok.extern.log4j.Log4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suyupeng
 */
@Log4j
public class BiqugeCategoryProcessor implements PageProcessor {
    private static Map<String, String> map = new HashMap<>();
    public static List<Category> LIST = new ArrayList<>();
    static {
//        map.put("玄幻小说", "http://www.biquge5200.com/xuanhuanxiaoshuo/");
//        map.put("修真小说", "http://www.biquge5200.com/xiuzhenxiaoshuo/");
//        map.put("都市小说", "http://www.biquge5200.com/dushixiaoshuo/");
//        map.put("穿越小说", "http://www.biquge5200.com/chuanyuexiaoshuo/");
//        map.put("网游小说", "http://www.biquge5200.com/wangyouxiaoshuo/");
//        map.put("科幻小说", "http://www.biquge5200.com/kehuanxiaoshuo/");
//        map.put("排行榜单", "http://www.biquge5200.com/paihangbang/");
        Category c = new Category("笔趣阁" + "玄幻小说", "笔趣阁", "玄幻小说", "http://www.biquge5200.com/xuanhuanxiaoshuo/");
        Category c1 = new Category("笔趣阁" + "修真小说", "笔趣阁", "修真小说", "http://www.biquge5200.com/xiuzhenxiaoshuo/");
        Category c2 = new Category("笔趣阁" + "都市小说", "笔趣阁", "都市小说", "http://www.biquge5200.com/dushixiaoshuo/");
        Category c3 = new Category("笔趣阁" + "穿越小说", "笔趣阁", "穿越小说", "http://www.biquge5200.com/chuanyuexiaoshuo/");
        Category c4 = new Category("笔趣阁" + "网游小说", "笔趣阁", "网游小说", "http://www.biquge5200.com/wangyouxiaoshuo/");
        Category c5 = new Category("笔趣阁" + "科幻小说", "笔趣阁", "科幻小说", "http://www.biquge5200.com/kehuanxiaoshuo/");
        Category c6 = new Category("笔趣阁" + "排行榜单", "笔趣阁", "排行榜单", "http://www.biquge5200.com/paihangbang/");
        LIST.add(c);
        LIST.add(c1);
        LIST.add(c2);
        LIST.add(c3);
        LIST.add(c4);
        LIST.add(c5);
        LIST.add(c6);
    }
    @Override
    public void process(Page page) {
        Html html = page.getHtml();

    }

    @Override
    public Site getSite() {
        return null;
    }
}
