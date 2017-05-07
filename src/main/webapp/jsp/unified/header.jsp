<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <header class="logo-env">
					
		<!-- logo -->
		<div class="logo" >
			<a href="javascript:void(0)" class="logo-expanded">
				<div style="width:100%;">
					<div style="width:20%;float:left;margin-right:15px;">
						<%--<img src="<%=request.getContextPath() %>/static/img/favicon.ico" />--%>
					</div>
					<div style="float:left;line-height:30px;white-space:nowrap;">
						<h1 style="color: white;font-size:26px;">实验教学平台</h1>
					</div>
				</div>
			</a>
			
			<a href="javascript:void(0)" class="logo-collapsed">
				<span style="color: white;">
					<%--<img src="<%=request.getContextPath() %>/static/img/favicon.ico" />--%>
				</span>
			</a>
		</div>
		
		<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
		<div class="mobile-menu-toggle visible-xs">
			<a href="#" data-toggle="user-info-menu">
				<i class="fa-bell-o"></i>
				<span class="badge badge-success">7</span>
			</a>
			
			<a href="#" data-toggle="mobile-menu">
				<i class="fa-bars"></i>
			</a>
		</div>
		
	</header>