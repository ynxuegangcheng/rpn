import com.xgc.rpn.RpnCalculator;

import java.util.Scanner;

/**
 * 测试类
 *
 * @author xgc
 * @Date 2020/8/13
 */
public class Test {
    public static void main(String[] args) {
        RpnCalculator rpnCalculator = new RpnCalculator();
        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String expression = scanner.nextLine();
                rpnCalculator.doCalculate(expression);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
