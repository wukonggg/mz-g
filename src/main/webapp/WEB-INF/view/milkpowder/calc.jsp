<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Desc:
  User: wukong(wukonggg@139.com)
--%>

<html>
<head>
    <%@ include file="../base/head.jsp" %>
    <%--<link rel="stylesheet" href="${base}/assets/css/fixed.css">--%>
</head>
<body>
<%@ include file="../base/b_ie_lte_ie9.jsp" %>

<%@ include file="../base/admin_topbar.jsp" %>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <%@ include file="../base/admin_sidebar.jsp" %>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <jsp:include page="../base/admin_breadcrumb.jsp">
            <jsp:param name="info1" value="奶粉"/>
            <jsp:param name="info2" value="算算更健康！"/>
        </jsp:include>

        <div class="am-g">
            <div class="am-u-sm-4 am-cf">
                <div class="am-fl am-cf">
                    <div class="am-btn-toolbar am-fl">
                        <div class="am-btn-group am-btn-group-xs">
                            <button type="button" id="btnAdd" name="btnAdd" class="am-btn am-btn-default">
                                <span class="am-icon-plus"></span> 新增
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-u-md-3 am-cf">
                <div class="am-fr">
                    <div class="am-input-group am-input-group-sm">
                        <input type="text" id="sweight" class="am-form-field" placeholder="输入一罐的重量" value="800"><br>
                        <input type="text" id="sprice" class="am-form-field" placeholder="输入一罐的价格" value="180">
                        <span class="am-input-group-btn">
                          <button id="btnCalc" class="am-btn am-btn-default" type="button">算算算</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                        <th>4</th>
                        <th>5</th>
                        <th>6</th>
                        <th>7</th>
                        <th>8</th>
                        <th>9</th>
                        <th>10</th>
                        <th>11</th>
                        <th>12</th>
                        <th>13</th>
                        <th>14</th>
                        <th>15</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td id="p1"></td>
                        <td id="p2"></td>
                        <td id="p3"></td>
                        <td id="p4"></td>
                        <td id="p5"></td>
                        <td id="p6"></td>
                        <td id="p7"></td>
                        <td id="p8"></td>
                        <td id="p9"></td>
                        <td id="p10"></td>
                        <td id="p11"></td>
                        <td id="p12"></td>
                        <td id="p13"></td>
                        <td id="p14"></td>
                        <td id="p15"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${base}/component/amazeui/2.1.0/assets/js/polyfill/rem.min.js"></script>
<script src="${base}/component/amazeui/2.1.0/assets/js/polyfill/respond.min.js"></script>
<script src="${base}/component/amazeui/2.1.0/assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${base}/component/amazeui/2.1.0/assets/js/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.1.0/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="${base}/component/amazeui/2.1.0/assets/js/app.js"></script>

<script src="${base}/component/raw/raw.lang.js"></script>
<script>
    $(function () {
        $("#btnCalc").click(function() {
            var sweight = $("#sweight").val();
            var sprice = $("#sprice").val();
            for(var i = 1; i <= 15; i++) {
                $("#p" + i).text(Math.round(parseInt(sprice) + calcFee(sweight, i)));
            }
        });
    });

    /**
     * 计算单罐运费
     * @weight 单罐重量
     * @num 罐数
     */
    function calcFee(weight, num) {
        var m = genFeeMap();
        for(var key in m.keys) {
            var k = m.keys[key];
            if(weight * num + 300  < k) {
                return m.get(k) / num;
            }
        }
    }

    /**
    * 生成运费map
    */
    function genFeeMap() {
        var m = new raw.lang.map();
        m.put("1000", "167");
        m.put("2000", "177");
        m.put("3000", "197");
        m.put("4000", "207");
        m.put("5000", "227");
        m.put("6000", "243");
        m.put("7000", "257");
        m.put("8000", "277");
        m.put("9000", "297");
        m.put("10000", "327");
        m.put("15000", "397");
        m.put("20000", "477");
        m.put("25000", "567");
        m.put("30000", "680");
        return m;
    }

</script>
</body>
</html>
