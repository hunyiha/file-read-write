package com.example.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @program: file-read-write->Main
 * @description:
 * @author: hunyiha
 * @create: 2020-12-09 21:03
 **/
public class Main {
    public static void main(String[] args) {
        //FileWriter fileWriter = new FileWriter("C:\\Users\\admin\\Desktop\\1.txt");
        int initStart = getInitStart();
        System.out.println(initStart);
        FileWriter fileWriter = new FileWriter("C:\\Users\\admin\\Desktop\\1.txt");
        fileWriter.write(String.valueOf(initStart+5));

    }

    private static int getInitStart() {
        File file = new File("C:\\Users\\admin\\Desktop\\1.txt");
        int offset;
        if(file.exists() && file.isFile()){
            FileReader fileReader = new FileReader("C:\\Users\\admin\\Desktop\\1.txt");
            List<String> strings = fileReader.readLines();
            if (CollUtil.isNotEmpty(strings)) {
                String value = strings.get(0);
                if (NumberUtil.isInteger(value.trim())) {
                    offset = Integer.valueOf(value.trim());
                }else {
                    throw new IllegalArgumentException("文件里面的数字不正确");
                }
            }else {
                throw new IllegalArgumentException("存在文件,但是文件不存在内容");
            }
        }else {
            // 第一次,直接读取
            offset = 0;
        }
        return offset;
    }
}
