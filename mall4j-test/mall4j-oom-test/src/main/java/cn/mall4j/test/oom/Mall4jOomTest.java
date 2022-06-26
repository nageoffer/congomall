package cn.mall4j.test.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟程序执行抛出 OOM 异常
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public class Mall4jOomTest {
    
    /**
     * 设置 VM 参数：-Xms1m -Xmx1m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:OnOutOfMemoryError=./dev-support/oom.sh
     * <p>
     * -Xms 堆区初始值
     * -Xmx 堆区最大值
     * -XX:+HeapDumpOnOutOfMemoryError 导出堆信息到指定文件，默认关闭
     * -XX:HeapDumpPath 导出堆信息指定路径，配合 -XX:+HeapDumpOnOutOfMemoryError 使用
     * -XX:OnOutOfMemoryError 抛出 OOM 时执行 shell 脚本，可以触发报警或重启应用
     *
     * @param args
     */
    public static void main(String[] args) {
        List<byte[]> oomList = new ArrayList<>();
        while (true) {
            byte[] arr = new byte[10241024];
            oomList.add(arr);
        }
    }
}
