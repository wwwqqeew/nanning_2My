package com.ritu.nanning.utils.speechProduction.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ritu.nanning.utils.o;

public class Demo {

	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;

	public static void main(String[] args) {
		sql = "CREATE TABLE `personnel`(`id` int(11) NOT NULL ,PRIMARY KEY (`id`) , `name` varchar(233) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL , `character` datetime NULL DEFAULT NULL , `Age` varchar(233) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=COMPACT;";//SQL语句
		createTable(sql);
	}

	public static void createTable(String sql) {
		db1 = new DBHelper(sql);//创建DBHelper对象

		try {
			db1.pst.execute(sql);
			o.o("创建表结束");
			db1.close();//关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
