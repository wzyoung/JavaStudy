package SubStringLeakProblem;

/**
 * java 6 String.substring(int start, int end) 方法会导致泄漏,因为内部实现会引用原来的string value (char[]) 如果是个超大字符串的话
 *
 * 某些场景会导致内存泄漏
 *
 *
 * 看源码:
 *
 01.    public String substring(int beginIndex, int endIndex) {
 02.    if (beginIndex < 0) {
 03.        throw new StringIndexOutOfBoundsException(beginIndex);
 04.    }
 05.    if (endIndex > count) {
 06.        throw new StringIndexOutOfBoundsException(endIndex);
 07.    }
 08.    if (beginIndex > endIndex) {
 09.        throw new StringIndexOutOfBoundsException(endIndex - beginIndex);
 10.    }
 11.    return ((beginIndex == 0) && (endIndex == count)) ? this :
 12.        new String(offset + beginIndex, endIndex - beginIndex, value); //使用的是和父字符串同一个char数组value
 13.    }


        String(int offset, int count, char value[]) {
            this.value = value;
            this.offset = offset;
            this.count = count;
        }

 *
 *
 * 解决方法有两个:
 *
 * 第一个是用 new String(x.substring(start,end))
 *
 * 这样会生成新字符串,原来的超大char[]会被回收掉,就避免了内存泄漏
 *
 * 第二个是用 x.substring(start,end).intern();
 *
 *
 * java 7以上版本已经重构了 String 类,去掉了 offset 与 count参数,并且substring方法会重新生成一个char[]数组,长度为截取的长度
 *
 * Created by wzyoung on 2015/2/6.
 */
public class SubstringTest {
}
