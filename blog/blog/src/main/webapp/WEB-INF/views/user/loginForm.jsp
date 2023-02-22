<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Juyeon's Blog</title>
<meta charset="utf-8">

<%@include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">User name:</label> <input type=""text""
				class="form-control" placeholder="Enter username" id=""username"">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group form-check">
			<label class="form-check-label"> <input
				class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		
		<button type="로그인" class="btn btn-primary">Submit</button>
	</form>
</div>

<%@include file="../layout/footer.jsp"%>
