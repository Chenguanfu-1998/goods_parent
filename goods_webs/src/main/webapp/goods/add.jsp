<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>预约详情页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">

<link href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rf.js" charset="UTF-8"></script>

	<script type="text/javascript">
		/* 日期插件 */
		$(function(){
			$('.form_date').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
		})
	</script>
	<script type="text/javascript">
		//查询分类
		$(function () {
			$.ajax({
				method:"get",
				url:"/goods/findByCategory",
				dataType:"json",
				statusCode :{
				    200:function (data) {
				        $(data).each(function (index) {
                            $("#categoryId").append('<option value="'+this.cid+'">'+this.cname+'</option>')
                        })
                    },
					500:function () {
						alert("查询失败!")
                    }
				}
			})
        });
		//校验用户名
		function checkByName(goodsName) {
		    $.ajax({
				method:"post",
				url:"/goods/checkByName",
                data:'{"goods_name":"'+goodsName+'"}',
                contentType:"application/json;charset=utf-8",
				statusCode:{
                    200:function (data) {
                        if(data){
                            $("#checkName").html('<font color="green">商品名称可用</font>')
                            $("#submit").prop("disabled",false);
                        }else{
                            $("#checkName").html('<font color="red">商品名称不可用</font>')
                            $("#submit").prop("disabled",true);
                        }
                    },
                    500:function () {
                        $("#checkName").html('<font color="red">商品名称不可用</font>')
                    }
				}
			})
        };
		//添加
		function submitInsert() {
			$.ajax({
				method:"post",
				url:"/goods/addShopGoods",
				data:JSON.stringify($("#Goods").serializeJson()),
				contentType:"application/json;charset=utf-8",
                dataType:"json",
				statusCode:{
				    201:function (data) {
                            alert("添加成功");
                            location.href='/goods/list.jsp';
                    },
					500:function () {
						alert("添加失败")
                    }
				}
			})
        }
	</script>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>商品添加</h2>
			</div>

			<!-- 商品添加form表单 -->
			<form action="" id="Goods" method="post">
			
				<div class="panel-body">
					<table class="table table-hover">
						<tbody>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="goodsName">商品名称</label> 
										<input type="text" name="goods_name" value="" class="form-control" id="goodsName" placeholder="商品名称" onblur="checkByName(this.value)">
									</div>
									<div id="checkName">
										<!-- 输入数量鼠标离焦后，此处展示商品名称是否可用提示信息
										<font color="green">商品名称可用</font>
										-->
                                    
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="categoryId">商品分类</label> 
										<select name="cid" id="categoryId" class="form-control">
											<option>-- 请选择分类 --</option>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="goodsNumber">商品数量</label>
										<input type="text" name="goods_number" value="" class="form-control" id="goodsNumber" placeholder="商品数量">
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="goodsPrice">商品价格</label> 
										<input type="text" name="goods_price" value="" class="form-control" id="goodsPrice" placeholder="商品价格">
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="control-group">
						                <label class="control-label">保质期</label>
						                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="guarantee_period" data-link-format="yyyy-mm-dd">
						                    <input size="26" type="text" value="" readonly>
						                    <span class="add-on"><i class="icon-remove"></i></span>
											<span class="add-on"><i class="icon-th"></i></span>
						                </div>
						                <!-- 保质期：选择日期会自动赋值给下面输入框value-->
										<input type="hidden" id="guarantee_period" name="guarantee_period" value="" /><br/>
									</div>
								</div>

							</div>
						</tbody>

					</table>
				</div>
				<div class="panel-body text-center">

					<!-- 表单提交按钮 -->
					<button type="submit" class="btn btn-primary btn-lg" id="submit" disabled="disabled" value="添加商品" onclick="submitInsert()">
						<span class="glyphicon glyphicon-copy" aria-hidden="true">
							添加商品
						</span>
					</button>

				</div>
			</form>
		</div>

	</div>


</body>


</html>
