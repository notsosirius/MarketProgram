<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>订单列表</title>
<jsp:include page="/link.jsp" />
</head>
<body>
	<jsp:include page="/top.jsp"></jsp:include>

	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 530px">

		<div class="row" style="padding: 20px 0px;">
			<!-- /左边 -->
			<div class="col-xs-2">
				<%-- JSP中的include中page路径里/代表的是项目的根目录 --%>
				<jsp:include page="/administrator/menu.jsp">
					<jsp:param value="order" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong><i class="icon-dollar"> </i>订单列表</strong>
					</div>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 60px">ID</td>
								<!-- <td class="text-left">商品信息</td> -->
								<!-- <td style="width: 80px">数量</td> -->
								<td style="width: 80px">金额</td>
								<td style="width: 200px">订单跟踪</td>
								<td style="width: 60px">状态</td>
								<!-- <td style="width: 100px">买家留言</td> -->
								<td style="width: 100px">买家账号</td>
								<!-- <td style="width: 100px">收货人</td>
								<td style="width: 100px">收货电话</td>
								<td style="width: 100px">收货地址</td> -->
								<td style="width: 120px">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.items}" var="ord">
								<tr>
									<td class="text-center">
										<a href="${ctx}/OrderDetailServlet?id=${ord.id}">${ord.id}</a>
									</td>
									<%-- <td class="text-left">
										<c:forEach items="${ord.items}"
											var="item" varStatus="vs">
											<c:if test="${vs.index >0}">
												<br />
											</c:if>
											<a href="${ctx}/product_detail?id=${item.product.id}">${item.product.name}</a> x ${item.amount}
										</c:forEach>
									</td> --%>
									<%-- <td class="text-center">${ord.total_amount}</td> --%>
									<td class="text-center"><fmt:formatNumber
											value="${ord.payment_price}" pattern="￥#,##0.00" /></td>
									<td class="text-center">下单时间：<fmt:formatDate
											value="${ord.create_time}" pattern="yyyy-MM-dd HH:mm" /> <c:if
											test="${!empty ord.delivery_time}">
											<br />发货时间：<fmt:formatDate value="${ord.delivery_time}"
												pattern="yyyy-MM-dd HH:mm" />
										</c:if> <c:if test="${!empty ord.end_time}">
											<br />完成时间：<fmt:formatDate value="${ord.end_time}"
												pattern="yyyy-MM-dd HH:mm" />
										</c:if>
									</td>
									<td class="text-center">
										<!-- 2已付款,3待发货,4已发货,5己收货,6已完成,-1已取消 --> <c:choose>
											<c:when test="${ord.status==2}">已付款</c:when>
											<c:when test="${ord.status==3}">待发货</c:when>
											<c:when test="${ord.status==4}">已发货</c:when>
											<c:when test="${ord.status==5}">己收货</c:when>
											<c:when test="${ord.status==6}">已完成</c:when>
											<c:when test="${ord.status==-1}">已取消</c:when>
										</c:choose>
									</td>
									<%-- <td class="text-center">${ord.remark}</td> --%>
									<td class="text-center">${ord.buyer_id}</td>
									<%-- <td class="text-right">${ord.contact}</td>
									<td class="text-right">${ord.mobile}</td>
									<td class="text-right">${ord.street}</td> --%>
									<td class="text-center">
										<c:if test="${ord.status==2}" var="flag">
											<a href="${ctx}/administrator/sale/order/deliver?id=${ord.id}">发货</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8"><q:pager
										totalElements="${page.totalElements}" number="${page.number}" />
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- /右边 -->
		</div>
	</div>
	<!-- /主内容 -->

	<jsp:include page="/bottom.jsp"></jsp:include>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
</body>
</html>