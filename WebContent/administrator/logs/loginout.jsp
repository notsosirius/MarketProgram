<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>登入登出</title>
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
					<jsp:param value="loginout" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong> 访问日志</strong>
					</div>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 60px">用户类型</td>
								<td style="width: 60px">用户id</td>
								<td style="width: 100px">邮箱</td>
								<td style="width: 100px">手机号</td>
								<td style="width: 100px">ip地址</td>
								<td style="width: 60px">操作</td>
								<td style="width: 100px">时间</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.items}" var="session">
								<tr>
									<c:if test="${session.user_cate eq 0}">
										<td class="text-center">顾客</td>
									</c:if>
									<c:if test="${session.user_cate eq 1}">
										<td class="text-center">管理员</td>
									</c:if>
									<c:if test="${session.user_cate eq 2}">
										<td class="text-center">销售员</td>
									</c:if>
									<td class="text-center">${session.user_id}</td>
									<td class="text-center">${session.email}</td>
									<td class="text-center">${session.mobile}</td>
									<td class="text-center">${session.ip}</td>
									<td class="text-center">${session.operation}</td>
									<td class="text-center">${session.time}</td>
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