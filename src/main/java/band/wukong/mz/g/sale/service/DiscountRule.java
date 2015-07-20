package band.wukong.mz.g.sale.service;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.util.Calculator;

/**
 * 优惠规则
 *
 * @author wukong(wukonggg@139.com)
 */
public class DiscountRule {

    /* ********** M...为人民币 ********** */
    public static final double M100 = 100;
    public static final double M1000 = 1000;
    public static final double M3000 = 3000;
    public static final double M5000 = 5000;
    public static final double M10000 = 10000;

    /* ********** D...为折扣 ********** */
    private static final double D100 = 1;
    private static final double D95 = 0.95;
    private static final double D90 = 0.9;
    private static final double D88 = 0.88;
    private static final double D85 = 0.85;
    private static final double D80 = 0.8;

    /**
     * 计算（预）成交价。
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @param sprice   商品零售价
     * @param scale    小数点后保留n位
     * @return 保留n位小数的（预）成交价
     */
    public static double calcDprice(String cateCode, double fee, double sprice, int scale) {
        double discount = discount(cateCode, fee);
        double dprice = Calculator.mul(sprice, discount);
        return Calculator.round(dprice, scale);
    }

    /**
     * 优惠规则。目前只有服装类才有优惠
     *
     * @param cateCode cateCode
     * @param fee      历史购买某类商品支付的合计金额，目前只有服装类。fee就代表服装类
     * @return double
     */
    public static double discount(String cateCode, double fee) {
        if (hasClothingDiscount(cateCode)) {
            return discountClothing(fee);
        } else {
            return 1;
        }
    }

    /**
     * 优惠规则。目前只有服装类才有优惠
     *
     * @param cateCode cateCode
     * @param fee      历史购买服装类商品支付的总金额
     * @return 文本
     */
    public static String discountInText(String cateCode, double fee) {
        if (hasClothingDiscount(cateCode)) {
            return discountClothingInTextCN(fee);
        } else {
            return "";
        }
    }

    /**
     * 判断是否有服装类商品的折扣
     *
     * @param cateCode cateCode
     * @return
     */
    public static boolean hasClothingDiscount(String cateCode) {
        return SimpleCateConst.CATE_CODE_A_SYTZ.equals(cateCode)
                || SimpleCateConst.CATE_CODE_B_KZ.equals(cateCode)
                || SimpleCateConst.CATE_CODE_C_NY.equals(cateCode)
                || SimpleCateConst.CATE_CODE_D_XZ.equals(cateCode);
    }

    /**
     * 服装类商品的优惠折扣
     *
     * @param paymentClothing 历史购买服装类商品支付的总金额
     * @return 返回折扣
     */
    private static double discountClothing(double paymentClothing) {
        if (paymentClothing >= 10000) {
            return D80;
        } else if (paymentClothing >= 5000) {
            return D85;
        } else if (paymentClothing >= 3000) {
            return D88;
        } else if (paymentClothing >= 1000) {
            return D90;
        } else if (paymentClothing >= 100) {
            return D95;
        } else {
            return D100;
        }
        /*
        服装类：
        历史消费             优惠                 会员等级
        =============       =============       =============
        100                 95%                 V1-普通
        1000                90%                 V2-中级
        3000                88%                 V3-高级
        5000                85%                 V4-资深
        10000               80%                 V5-传说
         */
    }

    /**
     * 服装类商品的优惠折扣
     *
     * @param fee 历史购买服装类商品支付的总金额
     * @return 返回折扣文本
     */
    private static String discountClothingInTextCN(double fee) {

        if (fee >= 10000) {
            return "八折";
        } else if (fee >= 5000) {
            return "八五折";
        } else if (fee >= 3000) {
            return "八八折";
        } else if (fee >= 1000) {
            return "九折";
        } else if (fee >= 100) {
            return "九五折";
        } else {
            return "";
        }
    }

}
