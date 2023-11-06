package com.cp.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;

/**
 * MyBatis-Plus 代码生成类
 */
public class UcenterCodeGenerator {


	private static final String SERVICE_NAME = "content.service";

	//dataSource config
	private static final String DATA_SOURCE_USER_NAME  = "root";
	private static final String DATA_SOURCE_PASSWORD  = "1234";
	//package config
	private static final String PARENT_PACK_NAME = "com.cp";
	private static final String XML_PACK_NAME = "mapper";
	private static final String SERVICE_IMPL_PACK_NAME = "service.impl";
	private static final String ENTITY_PACK_NAME = "model.po";
	//create schema
	private static final String[] TABLE_NAMES = new String[]{
//			"mq_message",
//			"mq_message_history"
			"course_base",
			"course_market",
			"course_teacher",
			"course_category",
			"teachplan",
			"teachplan_media",
			"course_publish",
			"course_publish_pre"

	};
	private static final String DATA_SOURCE_URL = "jdbc:mysql://127.0.0.1:3306/xc166_content";
	public static void main(String[] args) {
		FastAutoGenerator.create( new DataSourceConfig.Builder(DATA_SOURCE_URL, DATA_SOURCE_USER_NAME, DATA_SOURCE_PASSWORD))
				.globalConfig(builder -> {
					builder.author("paul")
							.outputDir(System.getProperty("user.dir")+"/cp-generator/src/main/java");
				})
				.packageConfig(builder -> {
					builder.parent(PARENT_PACK_NAME)
							.xml(XML_PACK_NAME)
							.moduleName(SERVICE_NAME)
							.serviceImpl(SERVICE_IMPL_PACK_NAME)
							.entity(ENTITY_PACK_NAME);
				})
				.strategyConfig(builder -> {
					builder.addTablePrefix("" + "_")
							.addInclude(TABLE_NAMES)
							//entity builder
							.entityBuilder()
							.naming(NamingStrategy.underline_to_camel)
							.columnNaming(NamingStrategy.underline_to_camel)
							.enableFileOverride()
							.enableLombok()
							.enableRemoveIsPrefix()
							.addTableFills(Arrays.asList(
									new Column("create_date", FieldFill.INSERT),
									new Column("change_date", FieldFill.UPDATE),
									new Column("modify_date", FieldFill.UPDATE)
							))
							//controller builder
							.controllerBuilder()
							.enableRestStyle()
							.enableHyphenStyle()
							//service builder
							.serviceBuilder()
							.enableFileOverride()
							.formatServiceFileName("%sService")
							.formatServiceImplFileName("%sServiceImpl")
							//mapper builder
							.mapperBuilder()
							.enableBaseColumnList()
							.enableBaseResultMap()
							.build();
				})
				.templateEngine(new FreemarkerTemplateEngine())
				.execute();
	}

}
