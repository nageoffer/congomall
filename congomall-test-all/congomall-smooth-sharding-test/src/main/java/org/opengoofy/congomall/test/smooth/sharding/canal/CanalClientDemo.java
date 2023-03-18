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

package org.opengoofy.congomall.test.smooth.sharding.canal;

//
// import com.alibaba.otter.canal.client.CanalConnector;
// import com.alibaba.otter.canal.client.CanalConnectors;
// import com.alibaba.otter.canal.common.utils.AddressUtils;
// import com.alibaba.otter.canal.protocol.CanalEntry.*;
// import com.alibaba.otter.canal.protocol.Message;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
//
// import java.net.InetSocketAddress;
// import java.util.List;
//
/// **
// * Canal 客户端测试
// *
// * @author chen.ma
// * @github <a href="https://github.com/opengoofy" />
// * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
// */
// @Slf4j
// @Component
// public class CanalClientDemo implements CommandLineRunner {
//
// @Override
// public void run(String... args) throws Exception {
// CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
// 11111), "example", "", "");
// int batchSize = 1000;
// int emptyCount = 0;
// try {
// connector.connect();
// connector.subscribe("canal_test.pay_info");
// int totalEmptyCount = 120;
// while (emptyCount < totalEmptyCount) {
// Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
// long batchId = message.getId();
// int size = message.getEntries().size();
// if (batchId == -1 || size == 0) {
// emptyCount++;
// System.out.println("empty count: " + emptyCount);
// try {
// Thread.sleep(1000);
// } catch (InterruptedException e) {
// }
// } else {
// emptyCount = 0;
// printEntry(message.getEntries());
// }
// connector.ack(batchId);
// }
// System.out.println("empty too many times, exit");
// } finally {
// connector.disconnect();
// }
// }
//
// private static void printEntry(List<Entry> entrys) {
// for (Entry entry : entrys) {
// if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
// continue;
// }
// RowChange rowChange;
// try {
// rowChange = RowChange.parseFrom(entry.getStoreValue());
// } catch (Exception e) {
// throw new RuntimeException("ERROR ## parser of error change-event has an error, data: " + entry, e);
// }
// EventType eventType = rowChange.getEventType();
// System.out.println(String.format("================> binlog[%s:%s], name[%s,%s], eventType: %s",
// entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
// entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
// eventType));
// for (RowData rowData : rowChange.getRowDatasList()) {
// if (eventType == EventType.DELETE) {
// printColumn(rowData.getBeforeColumnsList());
// } else if (eventType == EventType.INSERT) {
// printColumn(rowData.getAfterColumnsList());
// } else {
// System.out.println("-------> before");
// printColumn(rowData.getBeforeColumnsList());
// System.out.println("-------> after");
// printColumn(rowData.getAfterColumnsList());
// }
// }
// }
// }
//
// private static void printColumn(List<Column> columns) {
// for (Column column : columns) {
// System.out.println(column.getName() + ": " + column.getValue() + " update=" + column.getUpdated());
// }
// }
// }
