/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.congomall.test.h2;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * H2 数据库测试
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class H2Test {
    
    @Test
    @SneakyThrows
    public void h2Test() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        // 如果要测试 h2 持久化存储，请替换存储目录 jdbc:h2:file:/Users/single/Desktop/temp/h2_test_file;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MYSQL
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:config;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MYSQL");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        try (
                Connection connection = hikariDataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `repository`(id varchar(36) PRIMARY KEY, `key` TEXT, `value` TEXT, parent TEXT)");
            ResultSet resultSet = statement.executeQuery("select id from `repository` where id = '1'");
            if (resultSet.next()) {
                System.out.println(String.format("H2 数据库已存在值: %s", resultSet.getString("id")));
                resultSet.close();
                statement.executeUpdate("update `repository` set `key` = '3', `value` = '4', `parent` = '5' where id = '1'");
                return;
            }
            statement.executeUpdate("insert into `repository` (`id`, `key`, `value`, `parent`) values ('1', '2', '3', '4')");
        }
    }
}
