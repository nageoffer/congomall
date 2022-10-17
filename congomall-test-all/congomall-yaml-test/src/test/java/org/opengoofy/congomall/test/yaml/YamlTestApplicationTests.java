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

package org.opengoofy.congomall.test.yaml;

import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.Collections;

public class YamlTestApplicationTests {
    
    @Test
    public void assertYaml() {
        Plate plate = new Plate(new Apple("1", "苹果"), new Banana("2", "香蕉"));
        CustomerRepresenter customerRepresenter = new CustomerRepresenter();
        customerRepresenter.addClassTag(plate.getClass(), Tag.MAP);
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
        String yaml = new Yaml(customerRepresenter, dumperOptions).dumpAs(Collections.singletonList(plate), null, DumperOptions.FlowStyle.BLOCK);
        System.out.println(yaml);
    }
}
