<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>修改商品</title>
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
					<jsp:param value="alter" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong> 商品管理</strong>
					</div>
					<div class="col-xs-4" id="search">
						<form id="userAction_save_do" action="${ctx}/administrator/alterOne?id=${product.id}&thumbnail=${product.thumbnail}&create_time=${product.create_time}&sales_volume=${product.sales_volume}" method="post" enctype="multipart/form-data" name="Form1">
							&nbsp;
							<table cellSpacing="1" cellPadding="5" width="100%" align="center">
								<tr>
									<td width="30%" align="center">商品名称</td>
									<td>
										<input type="text" name="name" value="${product.name}" id="userAction_save_do_name"/>
									</td>
									<td width="30%" align="center">类别编号</td>
									<td>
										<input type="text" name="cate_id" value="${product.cate_id}" id="userAction_save_do_cateID"/>
									</td>
								</tr>
								<tr>
									<td width="30%" align="center">起售价</td>
									<td>
										<input type="text" name="price" value="${product.price}" id="userAction_save_do_price"/>
									</td>
									<td width="30%" align="center">特价</td>
									<td>
										<input type="text" name="sale_price" value="${product.sale_price}" id="userAction_save_do_salePrice"/>
									</td>
								</tr>
								<tr>
									<td width="30%" align="center">库存</td>
									<td>
										<input type="text" name="inventory" value="${product.inventory}" id="userAction_save_do_inventory"/>
									</td>
									<!-- <td width="30%" align="center">特价时间</td>
									<td>
										<input type="text" name="sale_time" value="" id="userAction_save_do_saleTime"/>
									</td> -->
								</tr>
								<tr>
									<td width="30%" align="center">图片</td>
									<td>
										<input type="file" name="thumbnail" value="${product.thumbnail}" id="userAction_save_do_image"/>
									</td>
								</tr>
								<tr>
									<td width="30%" align="center">商品描述</td>
									<td colspan="3">
										<textarea name="detail_description" rows="5" cols="30">${product.detail_description}</textarea>
									</td>
								</tr>
								<tr>
									<td style="width:100%" align="center" colspan="4">
										<button type="submit" is="userActoin_save_do_submit" value="确定" class="btn btn_primary">
											&#30830;&#30830;
										</button>
										<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
										<button type="reset" value="重置" class="btn btn_primary">
											&#37325;&#32622;
										</button>
									</td>
								</tr>
							</table>
							
						</form>
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