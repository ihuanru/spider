package com.su.processor;

import com.su.bo.Category;
import com.su.bo.Chapter;
import com.su.bo.Novel;
import com.su.repository.CategoryRepository;
import com.su.utils.MD5Util;
import com.su.utils.PinyinUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * 笔趣阁的爬虫，还未解决章节排序问题
 * @author suyupeng
 */
@Log4j
@Service
public class BiqugeProcessor implements PageProcessor {

    private CategoryRepository categoryRepository;
    /**
     * 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
      */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private final static String ALL = "小说大全";
    private final static String LIST = "章节列表";
    private final static String NOVEL = "章";
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String judge = html.xpath("//title/text()").toString();
        //全部小说
        if (judge.contains(ALL)) {
            List<String> linksAll = getLinksAll(page);
            if (!isEmpty(linksAll)) {
                page.addTargetRequests(linksAll);
            }
        }else if (judge.contains(LIST)) {
            String linksList = getLinksList(page);
            if (!StringUtils.isEmpty(linksList)) {
                page.addTargetRequest(linksList);
            }
            String categoryName = html.xpath("//div[@class='con_top']/a[2]/text()").toString();
            String website = html.xpath("//div[@class='con_top']/a[1]/text()").toString();
            Novel novel = new Novel();
            novel.setImageUrl(html.xpath("//div[@id='fmimg']/img/@src").toString());
            Selectable info = html.xpath("//div[@id='info']");
            novel.setName(info.xpath("//h1/text()").toString());
            String s = info.xpath("//p[1]/text()").toString();
            String dateStr = info.xpath("//p[3]/text()").toString();
            dateStr = dateStr.substring(dateStr.lastIndexOf("：", dateStr.length() - 1)).replace("：", "");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                novel.setDate(formatter.parse(dateStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            novel.setAuthor(s.substring(s.lastIndexOf("：", s.length() - 1)).replace("：",""));
            if (!categoryRepository.exists(website + categoryName)) {
                categoryRepository.save(new Category("笔趣阁" + categoryName, "笔趣阁", categoryName, "http://www.biquge5200.com/" + PinyinUtil.toPinyin(categoryName) + "/"));
            }
            novel.setCategory(new Category(website + categoryName));
            novel.setIntroduction(html.xpath("//div[@id='intro']/p/text()").toString());
            novel.setUrl(page.getUrl().toString());
            novel.setId(MD5Util.getMD5(novel.getUrl()));
            page.putField("novel", novel);
        } else if (judge.contains(NOVEL)) {
            String name = html.xpath("//div[@class='con_top']/text()").toString().replace(">  >  > ", "").trim();
            Chapter chapter = new Chapter();
            chapter.setUrl(page.getUrl().toString());
            String url = chapter.getUrl();
            url = url.replaceAll("(.*/).*", "$1");
            chapter.setNovel(new Novel(MD5Util.getMD5(url)));
            chapter.setName(name);
            chapter.setContent(html.xpath("//div[@id='content']/text()").toString());
            chapter.setId(MD5Util.getMD5(chapter.getUrl()));
            page.putField("chapter", chapter);
        }
    }

    /**
     * 在小说的章节列表中获取所有的 章节url
     */
    private String getLinksList(Page page) {
        Html html = page.getHtml();
        Selectable xpath = html.xpath("//div[@id='list']");
        log.info("list-size:" + xpath.links().all().size());
        return xpath.links().all().get(xpath.links().all().size() - 1);
    }

    /**
     * 获取所有小说url
     */
    private List<String> getLinksAll(Page page) {
        List<String> list = new ArrayList<>();
        Html html = page.getHtml();
        Selectable all = html.xpath("//div[@class='novellist']");
        log.info("list:" + all.all().size());
        for (String text : all.all()) {
            Html div = new Html(text);
            list.addAll(div.links().all());
        }
        return list;
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
