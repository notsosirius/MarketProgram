<%@ page pageEncoding="UTF-8"%>
<nav class="menu" id="mymenu">
	<ul class="nav nav-primary">
		<li class="nav-parent show"><a href="javascript:;"> 商品管理</a>
			<ul class="nav">
				<li ${param.tag == 'ad_index' ? "class='active'" : ""}><a
					href="${ctx}/administrator/products"><i class="icon-shopping-cart"></i>
						商品列表<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'add' ? "class='active'" : ""}><a
					href="${ctx}/administrator/product/add.jsp"><i class="icon-plus-sign"></i>
						添加商品<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'delete' ? "class='active'" : ""}><a
					href="${ctx}/administrator/product/delete.jsp"><i class="icon-minus-sign"></i>
						删除商品<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'alter' ? "class='active'" : ""}><a
					href="${ctx}/administrator/product/alter.jsp"><i class="icon-edit"></i>
						修改商品<i class="icon-chevron-right"></i></a></li>
			</ul>
		</li>
		<li class="nav-parent show"><a href="javascript:;"> 人员管理</a>
			<ul class="nav">
				<li ${param.tag == 'addSaler' ? "class='active' " : ""}><a
					href="${ctx}/administrator/manage/addSaler.jsp"><i class="icon-plus-sign"></i>
					 添加销售员<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'deleteSaler' ? "class='active' " : ""}><a
					href="${ctx}/administrator/manage/deleteSaler.jsp"><i class="icon-minus-sign"></i>
						删除销售员<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'alterSaler' ? "class='active'" : ""}><a
					href="${ctx}/administrator/manage/alterSaler.jsp"><i class="icon-edit"></i>
						修改销售员<i class="icon-chevron-right"></i></a></li>
			</ul>
		</li>
		<li class="nav-parent show"><a href="javascript:;"> 销售管理</a>
			<ul class="nav">
				<li ${param.tag == 'report' ? "class='active' " : ""}><a
					href="${ctx}/AdministratorReportFormServlet?action=showall"><i class="icon-table"></i>
					 统计报表<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'order' ? "class='active' " : ""}><a
					href="${ctx}/administrator/sale/order"><i class="icon-dollar"></i>
						 订单管理<i class="icon-chevron-right"></i></a></li>
			</ul>
		</li>
		
		<li class="nav-parent show"><a href="javascript:;"> 访问日志</a>
			<ul class="nav">
				<li ${param.tag == 'loginout' ? "class='active' " : ""}><a
					href="${ctx}/LoginoutServlet?action=showall"><i class="icon-group"></i>
					 登入登出<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'browse' ? "class='active' " : ""}><a
					href="${ctx}/BrowseServlet?action=showall"><i class="icon-eye-open"></i>
						 浏览记录<i class="icon-chevron-right"></i></a></li>
			</ul>
		</li>
	</ul>
</nav>