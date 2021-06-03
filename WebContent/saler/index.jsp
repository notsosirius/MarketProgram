<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>商品列表</title>
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
				<jsp:include page="/saler/menu.jsp">
					<jsp:param value="ad_index" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong> 商品管理</strong>
					</div>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 60px">商品ID</td>
								<td class="text-left">商品名称</td>
								<td style="width: 80px">类别ID</td>
								<td style="width: 80px" class="text-right">平常售价</td>
								<td style="width: 80px" class="text-right">特殊售价</td>
								<!-- <td style="width: 200px">商品详情</td> -->
								<td style="width: 100px">创建时间</td>
								<td style="width: 80px">销售量</td>
								<td style="width: 80px">库存</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.items}" var="pro">
								<tr>
									<td class="text-center">${pro.id}</td>
									<td class="text-left"><a href="${ctx}/product_detail?id=${pro.id}">${pro.name}</a></td>
									<td class="text-center">${pro.cate_id}</td>
									<td class="text-right"><fmt:formatNumber
											value="${pro.price}" pattern="￥#,##0.00" /></td>
									<td class="text-right"><fmt:formatNumber
											value="${pro.sale_price}" pattern="￥#,##0.00" /></td>
									<td class="text-center"><fmt:formatDate
											value="${pro.create_time}" pattern="yyyy-MM-dd HH:mm" />
									</td>
									<td class="text-center">${pro.sales_volume}</td>
									<td class="text-center">${pro.inventory}</td>
									
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