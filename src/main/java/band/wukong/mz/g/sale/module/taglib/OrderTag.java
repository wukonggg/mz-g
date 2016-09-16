package band.wukong.mz.g.sale.module.taglib;

import band.wukong.mz.g.sale.service.DiscountRule;
import band.wukong.util.Calculator;

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
     * 计算（预）成交价。
     *
     * @param cateCode cateCode
     * @param sprice   商品零售价
     * @param scale    小数点后保留n位
     * @return 保留n位小数的（预）成交价
     */
    public static double calcDpriceInDouble(String cateCode, double sprice, int scale) {
        return DiscountRule.calcDprice();
    }

    /**
     * 计算（预）成交单价。
     *
     * @param cateCode cateCode
     * @param sprice   商品零售价
     * @param scale    小数点后保留n位
     * @param p        pattern。如按"0.00"的格式，固定保留两位小数
     * @return
     */
//    public static String calcDpriceInString(String cateCode, double sprice, int scale, String p) {
//        double dprice = calcDpriceInDouble(cateCode, sprice, scale);
//        return Calculator.format(dprice, scale, p);
//    }

    /**
     * 计算（预）成交总价。
     *
     * @param cateCode cateCode
     * @param sprice   商品零售价
     * @param dcount   购买数量
     * @param scale    小数点后保留n位
     * @param p        pattern。如按"0.00"的格式，固定保留两位小数
     * @return n.nn格式
     */
//    public static String calcPaymentInString(String cateCode, double sprice, int dcount, int scale, String p) {
//        double dprice = calcDpriceInDouble(cateCode, sprice, scale);
//        double payment = dprice * dcount;
//        return Calculator.format(payment, scale, p);
//    }

    /**
     * 计算某种SKU的（预）预计成交总价。
     *
     * @param sprice   商品零售价
     * @param dcount   购买数量
     * @param scale    小数点后保留n位
     * @param p        pattern。如按"0.00"的格式，固定保留两位小数
     * @return n.nn格式
     */
    public static String calcSkuPaymentInString(double sprice, int dcount, int scale, String p) {
        double payment = sprice * dcount;
        return Calculator.format(payment, scale, p);
    }
}
