package com.su;

import com.su.pipeline.BiqugePipeline;
import com.su.processor.BiqugeCategoryProcessor;
import com.su.processor.BiqugeProcessor;
import com.su.repository.CategoryRepository;
import com.su.utils.ContextUtil;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author suyupeng
 */
public class Run {
    public static void main(String[] args) {
//        CategoryRepository categoryRepository = ContextUtil.getBean(CategoryRepository.class);
//        categoryRepository.save(BiqugeCategoryProcessor.LIST);
//        ScheduledExecutorService service = Executors
//                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
//        service.scheduleAtFixedRate(()->{
            Spider.create(ContextUtil.getBean(BiqugeProcessor.class))
//                    .addUrl("http://www.biquge5200.com/13_13599/")
//                .addUrl("http://www.biquge5200.com/13_13599/5995571.html")
                .addUrl("http://www.biquge5200.com/xiaoshuodaquan/")
                    //开启5个线程抓取
//                    .thread(5)
                    .addPipeline(ContextUtil.getBean(BiqugePipeline.class))
                    .setScheduler(new FileCacheQueueScheduler(new File("").getAbsolutePath()))
                    //启动爬虫
                    .run();
//        }, 0, 1, TimeUnit.DAYS);
    }
}
