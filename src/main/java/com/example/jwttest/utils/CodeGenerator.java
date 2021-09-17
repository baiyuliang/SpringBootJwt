package com.example.jwttest.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.SimpleAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;

import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        SimpleAutoGenerator simpleAutoGenerator = new SimpleAutoGenerator() {
            @Override
            public IConfigBuilder<DataSourceConfig> dataSourceConfigBuilder() {
                return new DataSourceConfig
                        .Builder("jdbc:mysql://localhost:3306/jwttest?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Hongkong",
                        "root", "root");
            }

            @Override
            public IConfigBuilder<GlobalConfig> globalConfigBuilder() {
                return new GlobalConfig.Builder()
                        .author("白玉梁")
                        .openDir(false)
                        .outputDir(System.getProperty("user.dir") + "/src/main/java");
            }

            @Override
            public IConfigBuilder<PackageConfig> packageConfigBuilder() {
                return new PackageConfig.Builder("com.example.jwttest", scanner("模块名"));
            }

            @Override
            public IConfigBuilder<InjectionConfig> injectionConfigBuilder() {
                return super.injectionConfigBuilder();
            }

            @Override
            public IConfigBuilder<TemplateConfig> templateConfigBuilder() {
                return super.templateConfigBuilder();
            }

            @Override
            public IConfigBuilder<StrategyConfig> strategyConfigBuilder() {
                return super.strategyConfigBuilder();
            }

        };
        simpleAutoGenerator.execute();
    }

}
