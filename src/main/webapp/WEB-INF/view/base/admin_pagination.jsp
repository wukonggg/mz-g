<%@ page import="band.wukong.mz.g.AppConst" %>
<%--
  Desc: 分页
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    //TODO -OPT 完善分页。增加除了当前页之外的前面、后面几页
    //TODO -OPT 完善分页。共几条记录和分页没对齐

    String pageSize = request.getParameter("pageSize");
    pageSize = null == pageSize ? String.valueOf(AppConst.PAGE_SIZE) : pageSize;
    System.out.println("pageSize = " + pageSize);
%>
<div class="am-cf mz-sticky-br">
<ul class="am-pagination am-pagination-right">
    <li id="li-page-pre"><a href="#" class="page-pre">&laquo;</a></li>
    <li class="am-active"><a href="#">${pager.pageNumber} / ${pager.pageCount}</a></li>
    <%--<li><a href="#">2</a></li>--%>
    <%--<li><a href="#">3</a></li>--%>
    <%--<li><a href="#">4</a></li>--%>
    <%--<li><a href="#">5</a></li>--%>
    <li id="li-page-next"><a href="#" class="page-next">&raquo;</a></li>
</ul>

<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNumber}">
<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize%>">
<input type="hidden" id="recordCount" name="recordCount" value="${pager.recordCount}">
</div>

<%--${param.pageSize == null ? }
<div class="am-cf">
    <div class="am-fl"></div>
    <div class="am-fr">
        <ul class="am-pagination">
            <li id="li-page-pre"><a href="#" class="page-pre">«</a></li>
            <li class="am-active"><a href="#">${pager.pageNumber}</a></li>
            &lt;%&ndash;<li><a href="#">2</a></li>&ndash;%&gt;
            &lt;%&ndash;<li><a href="#">3</a></li>&ndash;%&gt;
            &lt;%&ndash;<li><a href="#">4</a></li>&ndash;%&gt;
            &lt;%&ndash;<li><a href="#">5</a></li>&ndash;%&gt;
            <li id="li-page-next"><a href="#" class="page-next">»</a></li>
        </ul>
    </div>
</div>
--%>
