<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>订单详情</title>
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
						<strong> 订单管理</strong>
					</div>
					<div class="col-xs-4" id="search">
						<table class="table table-hover table-striped tablesorter">
							<thead>
								<tr>
									<td class="text-left">商品列表</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-left">
										<c:forEach items="${ord.items}" var="item" varStatus="vs">
											<c:if test="${vs.index >0}">
												<br />
											</c:if>
												<a href="${ctx}/product_detail?id=${item.product.id}">${item.product.name}</a> x ${item.amount}
										</c:forEach>
									</td>
								</tr>
							</tbody>
						</table>
						<table class="table table-hover table-striped tablesorter">
							<thead>
								<tr><td class="text-left">买家留言</td></tr>
								<tr><td class="text-center">${ord.remark}</td></tr>
							</thead>
						</table>
						<table class="table table-hover table-striped tablesorter">
							<thead>
								<tr><td class="text-left">收货人</td></tr>
								<tr><td class="text-center">${ord.contact}</td></tr>
							</thead>
						</table>
						<table class="table table-hover table-striped tablesorter">
							<thead>
								<tr><td class="text-left">收货人电话</td></tr>
								<tr><td class="text-center">${ord.mobile}</td></tr>
							</thead>
						</table>
						<table class="table table-hover table-striped tablesorter">
							<thead>
								<tr><td class="text-left">收货地址</td></tr>
								<tr><td class="text-center">${ord.street}</td></tr>
							</thead>
						</table>
					</div>
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