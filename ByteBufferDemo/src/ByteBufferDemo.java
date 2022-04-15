import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: netty-demo
 * @description:
 * @author: YzChen
 * @create: 2022-04-15 10:14
 **/
@Slf4j(topic = "byteBuffer")
public class ByteBufferDemo {
    public static void main(String[] args) {
        try {
            FileChannel channel = new FileInputStream("/Users/yzchen/IdeaProjects/netty-demo/images/i2.png").getChannel();
            // 准备缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(20);
            while (true) {
                // 从channel中读取
                int read = channel.read(allocate);
                log.info("读取到的字节数：{}", read);
                if (read == -1) {
                    break;
                }
                // 默认buffer是写模式，要切换模式
                allocate.flip();
                // 打印剩余的数据
                while (allocate.hasRemaining()) {
                    log.info("读取到的数据:{}", allocate.get());
                }
                // 清空缓冲，避免阻塞(会切换到写模式)
//                allocate.compact()
                allocate.clear();
            }
        } catch (IOException e) {
           log.info(e.getMessage());
        }
    }
}

