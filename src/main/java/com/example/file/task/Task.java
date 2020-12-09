package com.example.file.task;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.NumberUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class Task {

    @Scheduled(fixedDelay = 3000)
    public void sys(){
        int initStart = getInitStart();
        System.out.println(initStart);
        FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + File.separator + "1.txt");
        fileWriter.write(String.valueOf(initStart+5));
    }

    private static int getInitStart() {
        File file = new File(System.getProperty("user.dir") + File.separator + "1.txt");
        int offset;
        if(file.exists() && file.isFile()){
            FileReader fileReader = new FileReader(file);
            List<String> strings = fileReader.readLines();
            if (CollUtil.isNotEmpty(strings)) {
                String value = strings.get(0);
                if (NumberUtil.isInteger(value.trim())) {
                    offset = Integer.valueOf(value.trim());
                }else {
                    throw new IllegalArgumentException("文件被改动,请检查内容是否正确,数字前不要出现空行");
                }
            }else {
                System.out.println("我要关闭了");
                System.exit(0);
                throw new IllegalArgumentException("存在文件,但是不存在内容");
            }
        }else {
            // 第一次,直接读取
            offset = 0;
        }
        return offset;
    }
}
