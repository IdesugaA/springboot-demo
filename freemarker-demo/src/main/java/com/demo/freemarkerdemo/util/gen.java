package com.demo.freemarkerdemo.util;


import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class gen {

    public static void main(String[] args) throws Exception {
        // 读取generator配置文件，和mybatis生成插件共用一个配置文件，这样就不需要额外再多整一个
        Document document = new SAXReader().read("src\\main\\resources\\generator.xml");
        System.out.println(document.getXMLEncoding());
        // 为DbUtil设置数据源
        Node connectionURL = document.selectSingleNode("//@connectionURL");
        Node userId = document.selectSingleNode("//@userId");
        Node password = document.selectSingleNode("//@password");
        System.out.println("url: " + connectionURL.getText());
        System.out.println("user: " + userId.getText());
        System.out.println("password: " + password.getText());
        DbUtil.url = connectionURL.getText();
        DbUtil.user = userId.getText();
        DbUtil.password = password.getText();

        List<Node> tables = document.selectNodes("//table");

        for (int i = 0 ; i < tables.size() ; i++) {
            Node table = tables.get(i);
            // selectSingleNode(String xpath)通过xpath来返回第一个匹配到的节点
            // @用来匹配标签上的属性。generator上面有关于数据库的信息，如url，表名的
            // 同时要注意，xpath的使用需要jaxen依赖
            Node tableName = table.selectSingleNode("@tableName");
            Node domainObjectName = table.selectSingleNode("@domainObjectName");
            System.out.println(tableName.getText() + "/" + domainObjectName);

            // 开头字母大写形式
            String Domain = domainObjectName.getText();
            // 开头字母小写形式
            String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
            // 下划线形式
            String do_main = tableName.getText().replaceAll("_", "-");
            // 表中文名
            String tableNameCn = DbUtil.getTableComment(tableName.getText());
            List<Field> fieldList = DbUtil.getColumnByTableName(tableName.getText());
            for(int j = 0 ; j < fieldList.size() ; j++) {
                System.out.println("eee: "+ fieldList.get(j).toString());
            }
            Set<String> typeSet = getJavaTypes(fieldList);

            // 组装参数
            Map<String, Object> param = new HashMap<>();
            param.put("Domain", Domain);
            param.put("domain", domain);
            param.put("do_main", do_main);
            param.put("tableNameCn", tableNameCn);
            param.put("fieldList", fieldList);
            param.put("typeSet", typeSet);
            System.out.println("组装参数：" + param);

            gen(Domain, param, "service", "service");
            gen(Domain, param, "controller", "controller");
            gen(Domain, param, "dto", "saveDto");
            gen(Domain, param, "dto", "deleteDto");
            gen(Domain, param, "dto", "queryDto");
            gen(Domain, param, "vo", "queryVo");

        }
    }

    private static void gen(String Domain, Map<String, Object> param, String packageName, String target) throws IOException, TemplateException {
        FreemarkerUtil.initConfig(target + ".ftl");
        // 设置生成出的位置，packageName，是指dto，controller，service这些包名
        String toPath = "src/main/java/" + "com/demo/freemarkerdemo/" +packageName + "/";
        new File(toPath).mkdirs();
        String Target = target.substring(0, 1).toUpperCase() + target.substring(1);
        // 最终生成  路径名+实体名+特定文件如Req或Resp
        String fileName = toPath + Domain + Target + ".java";
        System.out.println("开始生成：" + fileName);
        // param是拿来套进模板文件里面的模块值的
        FreemarkerUtil.generator(fileName, param);
    }

    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0 ; i < fieldList.size() ; i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }

}