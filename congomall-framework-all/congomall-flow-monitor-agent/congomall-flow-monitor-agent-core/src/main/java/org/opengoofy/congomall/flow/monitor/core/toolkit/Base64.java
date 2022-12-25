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

package org.opengoofy.congomall.flow.monitor.core.toolkit;

import java.nio.charset.Charset;

/**
 * Base64解码实现
 *
 * @author looly
 */
public class Base64 {
    
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    private static final byte PADDING = -2;
    
    private static final byte[] DECODE_TABLE = {
            // 0 1 2 3 4 5 6 7 8 9 A B C D E F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00-0f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10-1f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, // 20-2f + - /
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, // 30-3f 0-9，-2的位置是'='
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // 40-4f A-O
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, // 50-5f P-Z _
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, // 60-6f a-o
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 // 70-7a p-z
    };
    
    public static String decodeStr(CharSequence source) {
        return decodeStr(source, DEFAULT_CHARSET);
    }
    
    public static String decodeStr(CharSequence source, Charset charset) {
        return Strings.str(decode(source), charset);
    }
    
    public static byte[] decode(CharSequence source) {
        return decode(Strings.bytes(source, DEFAULT_CHARSET));
    }
    
    public static byte[] decode(byte[] in) {
        return decode(in, 0, in.length);
    }
    
    public static byte[] decode(byte[] in, int pos, int length) {
        final MutableInt offset = new MutableInt(pos);
        byte sestet0;
        byte sestet1;
        byte sestet2;
        byte sestet3;
        int maxPos = pos + length - 1;
        int octetId = 0;
        byte[] octet = new byte[length * 3 / 4];// over-estimated if non-base64 characters present
        while (offset.intValue() <= maxPos) {
            sestet0 = getNextValidDecodeByte(in, offset, maxPos);
            sestet1 = getNextValidDecodeByte(in, offset, maxPos);
            sestet2 = getNextValidDecodeByte(in, offset, maxPos);
            sestet3 = getNextValidDecodeByte(in, offset, maxPos);
            
            if (PADDING != sestet1) {
                octet[octetId++] = (byte) ((sestet0 << 2) | (sestet1 >>> 4));
            }
            if (PADDING != sestet2) {
                octet[octetId++] = (byte) (((sestet1 & 0xf) << 4) | (sestet2 >>> 2));
            }
            if (PADDING != sestet3) {
                octet[octetId++] = (byte) (((sestet2 & 3) << 6) | sestet3);
            }
        }
        
        if (octetId == octet.length) {
            return octet;
        } else {
            return (byte[]) copy(octet, new byte[octetId], octetId);
        }
    }
    
    public static Object copy(Object src, Object dest, int length) {
        System.arraycopy(src, 0, dest, 0, length);
        return dest;
    }
    
    public static boolean isBase64Code(byte octet) {
        return octet == '=' || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1);
    }
    
    private static byte getNextValidDecodeByte(byte[] in, MutableInt pos, int maxPos) {
        byte base64Byte;
        byte decodeByte;
        while (pos.intValue() <= maxPos) {
            base64Byte = in[pos.intValue()];
            pos.increment();
            if (base64Byte > -1) {
                decodeByte = DECODE_TABLE[base64Byte];
                if (decodeByte > -1) {
                    return decodeByte;
                }
            }
        }
        return PADDING;
    }
}
