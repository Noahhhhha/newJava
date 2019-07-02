package nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer） ： 在javaNIO中负责数据的存储，缓冲区就是数组。用于存储不同数据类型的数据
 * 根据不同的数据类型（boolean除外），都有对应的缓冲区：
 *  ByteBuffer ~ Double Buffer
 *  各种数据类型的缓冲区管理方法几乎一致，通过allocate()获取缓冲区
 *
 * 二、两个核心方法
 *  get()-获取数据
 *  put()-存入数据
 * 三、四个核心属性
 *  capacity-最大存储容量，一旦声明，不能改变
 *  limit-界限，缓冲区中可以操作数据的大小（limit后的数据不能进行读写）
 *  position-位置，表示缓冲区正在执行操作数据的位置
 *
 *      position <= limit <= capacity
 *
 *
 */
public class TestBuffer {
    String str = "wdnmd";

    @Test
    public void testMarkAndReset(){ //测试mark：记录position位置 reset:position恢复到mark位置
        ByteBuffer bf = ByteBuffer.allocate(1024);
        bf.put(str.getBytes());
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());
        System.out.println("+++++++++++++++++++");
        bf.mark();
        bf.getChar();
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());
        System.out.println("+++++++++++++++++++");
        bf.reset();
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());
        System.out.println("+++++++++++++++++++");



    }


    @Test
    public void test1(){
        //分配一个指定大小的字节缓冲区 —— allocate()
        System.out.println("----------------allocate()----------------");
        ByteBuffer bf = ByteBuffer.allocate(1024);
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());

        //利用put() 存入
        //存入的时候 position 会发生变化
        System.out.println("----------------put()----------------");
        bf.put(str.getBytes());
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());

        //切换到读模式 position 和 limit发生变化
        System.out.println("----------------flip()----------------");
        bf.flip();
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());

        //进行读
        System.out.println("----------------get()----------------");
        byte[] bytes = new byte[bf.limit()];
        bf.get(bytes);

        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());

        System.out.println(new String(bytes,0,bytes.length)); // 读完以后bytes中就会存值，从第0位读到末位

        //反复读，相当于再flip()
        System.out.println("----------------rewind()----------------");
        bf.rewind();
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());

        //清空缓冲区操作记录，回归开始 (数据进入“被遗忘”状态)
        System.out.println("----------------clear()----------------");
        bf.clear();
        System.out.println(bf.position());
        System.out.println(bf.limit());
        System.out.println(bf.capacity());
    }
}
