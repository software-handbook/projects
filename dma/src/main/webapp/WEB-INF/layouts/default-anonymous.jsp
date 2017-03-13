<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Optional: Include the jQuery library -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script src="${contextApp}/resources/jquery/1.9.1/jquery-1.9.1.min.js"></script>

<!-- KICKSTART -->
<script src="${contextApp}/resources/htmlkickstart/js/kickstart.js"></script> 
<link rel="stylesheet" href="${contextApp}/resources/htmlkickstart/css/kickstart.css" media="all" /> 
<link rel='stylesheet' href='${contextApp}/resources/css/main.css' type='text/css' />

<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="${contextApp}/resources/AdminLTE/css/bootstrap.min.css">

<!-- Optional: Incorporate the Bootstrap JavaScript plugins -->
<!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<script src="${contextApp}/resources/AdminLTE/js/bootstrap.min.js"></script>

<link href="${contextApp}/resources/AdminLTE/css/AdminLTE.css" rel="stylesheet" type="text/css" />

<title><s:message code="Register_account"/></title>


</head>
<body>
<div class="container">
  <%-- header --%>
  <div class="header">
    <tiles:insertAttribute name="register-header" />
  </div>
  
  <!-- Content -->
  <div class="row">
    <%-- Left part --%>
    <div class="col-lg-8">
      <tiles:insertAttribute name="register-body-left" />
    </div>
    
    <%--Right part --%>
    <div class="col-lg-4">
      <tiles:insertAttribute name="register-body-right" />
    </div>
  </div>
  <div class="footer">
    <tiles:insertAttribute name="register-footer" />
  </div>
</div>
</body>
</html>