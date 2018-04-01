package me.uac.utils;

import java.util.Random;

/**
 * <p>Title: RandomUtil. </p>
 * <p>Description 随机数工具类 </p>
 * @author dragon
 * @date 2018年03月30日22:30:01
 */
public class RandomUtil {
    /**
     * 生成一个随机验证码
     */
    public static String createComplexCode(int length) {
        int maxLength = 47;
        if (length > maxLength) {
            length = maxLength;
        }
        Random random = new Random();
        String code = "";
        while (true) {
            if (code.length() == length) {
                break;
            }
            int tmp = random.nextInt(127);
            if (tmp < 33 || tmp == 92 || tmp == 47 || tmp == 34) {
                continue;
            }
            char x = (char) (tmp);
            if (code.indexOf(x) > 0) {
                continue;
            }
            code += x;
        }
        return code;
    }
}
