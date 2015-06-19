package band.wukong.mz.g.sale;

import band.wukong.mz.g.sale.service.DiscountRule;
import band.wukong.mz.util.Calculator;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderTag {

    /**
     * 计算折扣。目前只有服装类才有优惠
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @return double型数字
     */
    public static double discountInDouble(String cateCode, double fee) {
        return DiscountRule.discount(cateCode, fee);
    }

    /**
     * 计算折扣。目前只有服装类才有优惠
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @return 按"0.00"的格式，固定保留两位小数
     */
    public static String discountInString(String cateCode, double fee) {
        double d = discountInDouble(cateCode, fee);
        return Calculator.format(d, 2, Calculator.FMT_PATTERN_FD2);
    }

    /**
     * 计算折扣。目前只有服装类才有优惠
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @return 返回折扣文本
     */
    public static String discountInText(String cateCode, double fee) {
        return DiscountRule.discountInText(cateCode, fee);
    }

    /**
     * 计算（预）成交价。
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @param sprice   商品零售价
     * @param scale    小数点后保留n位
     * @return 保留n位小数的（预）成交价
     */
    public static double calcDpriceInDouble(String cateCode, double fee, double sprice, int scale) {
        return DiscountRule.calcDprice(cateCode, fee, sprice, scale);
    }

    /**
     * 计算（预）成交价。
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @param sprice   商品零售价
     * @param scale    小数点后保留n位
     * @param p        pattern。如按"0.00"的格式，固定保留两位小数
     * @return
     */
    public static String calcDpriceInString(String cateCode, double fee, double sprice, int scale, String p) {
        double dprice = calcDpriceInDouble(cateCode, fee, sprice, scale);
        return Calculator.format(dprice, scale, p);
    }
}
