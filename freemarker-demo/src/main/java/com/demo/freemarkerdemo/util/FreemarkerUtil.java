package com.demo.freemarkerdemo.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;



public class FreemarkerUtil {

    static String ftlPath = "src/main/java/com/demo/freemarkerdemo/ftl/";

    static Template temp;

    // 使用freemarker生成，只需要生成一些共用的代码就好了，每个模块自己需要的代码我们就后期自己补充上去

    // 读模板
    public static void initConfig(String ftlName) throws IOException {
        // 初始化，首先进行一些常见的配置如版本设置，然后加载模板文件ftl
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        // 加载ftl文件所在目录
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_31));
        // 指定特定的ftl文件，需要传入
        temp = cfg.getTemplate(ftlName);
    }

    // 根据模板，生成文件
    public static void generator(String fileName, Map<String, Object> map) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(map, bw);
        bw.flush();
        fw.close();
    }

}