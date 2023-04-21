package top.testeru;

import org.junit.jupiter.api.Test;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject interview-code
 * @Description
 * @createTime 2023年04月19日 16:47:00
 */
public class OneTest {
    @Test
    public void a(){
        String a = "* LEGB法则，";
        if(a.contains("* ")){
            a = a.replace("* ","");
        }
        System.out.println(a);
    }
    @Test
    public void b(){
        String a = "1.自我介绍";
        if(a.contains(".")){
            String[] bits = a.split("\\.");

            a = bits[bits.length-1];
        }
        System.out.println(a);
    }
}
