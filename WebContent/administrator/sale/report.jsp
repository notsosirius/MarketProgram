<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>统计报表</title>
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
					<jsp:param value="report" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong> 统计报表</strong>
					</div>
					<form action="${ctx}/AdministratorReportFormServlet" method="post">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						类别:&nbsp;&nbsp;
						<select name="category">
							<c:forEach items="${cateList}" var="category">
								<option value="${category.name}">${category.name}</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						时间:&nbsp;&nbsp;从
						<input type="datetime-local" step="01" id="startTime" name="startTime" />
						&nbsp;到
						<input type="datetime-local" step="01" id="endTime" name="endTime" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="btn btn-primary" type="submit">
							<i class="icon icon-search"></i>
						</button>
					</form>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 60px">类别ID</td>
								<td style="width: 60px">类别名称</td>
								<td style="width: 100px">营收额</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.items}" var="revenue">
								<tr>
									<td class="text-center">${revenue.cateId}</td>
									<td class="text-center">${revenue.cateName}</td>
									<td class="text-center">${revenue.cateWhole}</td><!-- 这里应该要算总额 -->
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