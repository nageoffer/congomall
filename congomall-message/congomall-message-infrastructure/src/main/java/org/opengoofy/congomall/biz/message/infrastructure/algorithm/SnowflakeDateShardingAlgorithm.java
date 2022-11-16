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

package org.opengoofy.congomall.biz.message.infrastructure.algorithm;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Range;
import lombok.Getter;
import org.apache.shardingsphere.infra.config.exception.ShardingSphereConfigurationException;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.opengoofy.congomall.springboot.starter.distributedid.SnowflakeIdUtil;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * 自定义分片算法
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class SnowflakeDateShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    
    private static final String DATE_TIME_LOWER_KEY = "datetime-lower";
    
    private static final String DATE_TIME_UPPER_KEY = "datetime-upper";
    
    private static final String SHARDING_SECONDS_KEY = "sharding-seconds";
    
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Getter
    private Properties props;
    
    private LocalDateTime dateTimeLower;
    
    private long shardingSeconds;
    
    @Getter
    private int autoTablesAmount;
    
    private final String messageSendId = "message_send_id";
    
    private final String sendTime = "send_time";
    
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        Map<String, Range<Comparable<?>>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        if (CollUtil.isNotEmpty(columnNameAndShardingValuesMap)) {
            Collection<Comparable<?>> sendTimeCollection = columnNameAndShardingValuesMap.get(sendTime);
            if (CollUtil.isNotEmpty(sendTimeCollection)) {
                Comparable<?> comparable = sendTimeCollection.stream().findFirst().get();
                String tableNameSuffix = String.valueOf(this.doSharding(this.parseDate(comparable)));
                result.add(shardingValue.getLogicTableName() + "_" + tableNameSuffix);
            } else {
                Collection<Comparable<?>> messageSendIdCollection = columnNameAndShardingValuesMap.get(messageSendId);
                Comparable<?> comparable = messageSendIdCollection.stream().findFirst().get();
                long parseDate = this.parseDate(DateUtil.format(new Date(SnowflakeIdUtil.parseSnowflakeId(((Long) comparable)).getTimestamp()), DatePattern.NORM_DATETIME_PATTERN));
                String tableNameSuffix = String.valueOf(this.doSharding(parseDate));
                result.add(shardingValue.getLogicTableName() + "_" + tableNameSuffix);
            }
        } else {
            Range<Comparable<?>> sendTimeRange = columnNameAndRangeValuesMap.get(sendTime);
            if (sendTimeRange != null) {
                int firstPartition = getFirstPartition(sendTimeRange);
                int lastPartition = getLastPartition(sendTimeRange);
                for (int i = firstPartition; i <= lastPartition; i++) {
                    String suffix = String.valueOf(i);
                    result.add(shardingValue.getLogicTableName() + "_" + suffix);
                }
            } else {
                result.addAll(availableTargetNames);
            }
        }
        return result;
    }
    
    @Override
    public Properties getProps() {
        return null;
    }
    
    @Override
    public void init(final Properties properties) {
        this.props = properties;
        dateTimeLower = getDateTime(props);
        shardingSeconds = getShardingSeconds(props);
        autoTablesAmount = (int) (Math.ceil((double) (parseDate(props.getProperty(DATE_TIME_UPPER_KEY)) / shardingSeconds)) + 2);
    }
    
    private LocalDateTime getDateTime(final Properties props) {
        String value = props.getProperty(DATE_TIME_LOWER_KEY);
        Preconditions.checkNotNull(value, "%s cannot be null.", DATE_TIME_LOWER_KEY);
        try {
            return LocalDateTime.parse(value, DATE_TIME_FORMAT);
        } catch (final DateTimeParseException ex) {
            throw new ShardingSphereConfigurationException("Invalid %s, datetime pattern should be `yyyy-MM-dd HH:mm:ss`, value is `%s`", DATE_TIME_LOWER_KEY, value);
        }
    }
    
    private long getShardingSeconds(final Properties props) {
        Preconditions.checkArgument(props.containsKey(SHARDING_SECONDS_KEY), "%s cannot be null.", SHARDING_SECONDS_KEY);
        return Long.parseLong(props.getProperty(SHARDING_SECONDS_KEY));
    }
    
    private int getFirstPartition(final Range<Comparable<?>> valueRange) {
        return valueRange.hasLowerBound() ? doSharding(parseDate(valueRange.lowerEndpoint())) : 0;
    }
    
    private int getLastPartition(final Range<Comparable<?>> valueRange) {
        return valueRange.hasUpperBound() ? doSharding(parseDate(valueRange.upperEndpoint())) : autoTablesAmount - 1;
    }
    
    private int doSharding(long shardingValue) {
        String position = (new DecimalFormat("0.00")).format((double) shardingValue / (double) this.shardingSeconds);
        return Math.min(Math.max(0, (int) Math.ceil(Double.parseDouble(position))), this.autoTablesAmount - 1);
    }
    
    private long parseDate(final Comparable<?> shardingValue) {
        LocalDateTime dateValue = LocalDateTime.from(DATE_TIME_FORMAT.parse(shardingValue.toString(), new ParsePosition(0)));
        return Duration.between(dateTimeLower, dateValue).toMillis() / 1000;
    }
    
    public Optional<String> findMatchedTargetName(final Collection<String> availableTargetNames, final String suffix, final DataNodeInfo dataNodeInfo) {
        String targetName = dataNodeInfo.getPrefix() + Strings.padStart(suffix, dataNodeInfo.getSuffixMinLength(), dataNodeInfo.getPaddingChar());
        if (availableTargetNames.contains(targetName)) {
            return Optional.of(targetName);
        }
        return Optional.empty();
    }
}
