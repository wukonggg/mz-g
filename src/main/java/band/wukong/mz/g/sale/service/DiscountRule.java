package band.wukong.mz.g.sale.service;

import band.wukong.mz.g.category.SimpleCateConst;

/**
 * 优惠规则
 *
 * @author wukong(wukonggg@139.com)
 */
public class DiscountRule {

    /* ********** M...为人民币 ********** */
    public static final double M0 = 0.00;
    public static final double M100 = 100.00;
    public static final double M200 = 200.00;
    public static final double M300 = 300.00;
    public static final double M400 = 400.00;
    public static final double M500 = 500.00;

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
     * @return 保留n位小数的（预）成交价
     */
    public static double calcDprice() {
        return 0L;
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
            return discount4Clothing(fee);
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
     * @param payment4Clothing 历史购买服装类商品支付的总金额
     * @return 返回折扣
     */
    private static double discount4Clothing(double payment4Clothing) {
        if (payment4Clothing >= M500) {
            return D80;
        } else if (payment4Clothing >= M400) {
            return D85;
        } else if (payment4Clothing >= M300) {
            return D88;
        } else if (payment4Clothing >= M200) {
            return D90;
        } else if (payment4Clothing >= M100) {
            return D95;
        } else {
            return D100;
        }
    }

    /**
     * 服装类商品的优惠折扣
     *
     * @param fee 历史购买服装类商品支付的总金额
     * @return 返回折扣文本
     */
    private static String discountClothingInTextCN(double fee) {

        if (fee >= M500) {
            return "八折";
        } else if (fee >= M400) {
            return "八五折";
        } else if (fee >= M300) {
            return "八八折";
        } else if (fee >= M200) {
            return "九折";
        } else if (fee >= M100) {
            return "九五折";
        } else {
            return "无折扣";
        }
    }

}
