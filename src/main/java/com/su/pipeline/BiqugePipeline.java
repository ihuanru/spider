package com.su.pipeline;

import com.su.bo.Chapter;
import com.su.bo.Novel;
import com.su.repository.ChapterRepository;
import com.su.repository.NovelRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author suyupeng
 */
@Service
@Log4j
public class BiqugePipeline implements Pipeline{

    private ChapterRepository chapterRepository;
    private NovelRepository novelRepository;
    @Override
    public void process(ResultItems resultItems, Task task) {
        Novel novel = resultItems.get("novel");
        Chapter chapter = resultItems.get("chapter");
        if (novel != null) {
            novelRepository.save(novel);
            log.info("save novel:" + novel.getName());
        }
        if (chapter != null) {
            chapterRepository.save(chapter);
            log.info("save chapter:" + chapter.getName());
        }
    }


    @Autowired
    public void setChapterRepository(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }
    @Autowired
    public void setNovelRepository(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }
}
