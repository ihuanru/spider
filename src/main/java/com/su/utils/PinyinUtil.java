package com.su.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author suyupeng
 */
public class PinyinUtil {
    /**
     * 汉字转为拼音
     */
    public static String toPinyin(String chinese) {
        try {
            if (!isNotEmpty(chinese)) {
                return null;
            }
            StringBuilder pinyinStr = new StringBuilder("");
            char[] newChar = chinese.toCharArray();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            for (char c : newChar) {
                if (c > 128) {
                    pinyinStr.append(PinyinHelper.toHanyuPinyinStringArray(c,defaultFormat)[0]);
                }else {
                    pinyinStr.append(c);
                }
            }
            return pinyinStr.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
