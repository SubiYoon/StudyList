import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MainTest {

    @Test
    public void testByteArrayToString() {
        byte[] buf = new byte[100];
        String s = "=====Boundary";
//        byte[] bytes = s.getBytes();
//        System.arraycopy(bytes, 0, buf, 0, bytes.length - 1);
        buf[0] = 45;
        buf[1] = 87;
        buf[2] = 101;
        buf[3] = 98;
        buf[4] = 13;
        buf[5] = 10;
        String str = new String(buf);
        System.out.println(str);
    }


}