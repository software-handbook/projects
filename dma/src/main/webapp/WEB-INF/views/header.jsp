﻿<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="DATE_FORMAT" scope="application"><s:message code="DATE_FORMAT"/></c:set>
<c:set var="DATETIME_FORMAT" scope="application"><s:message code="DATETIME_FORMAT"/></c:set>
<c:set var="TIME_FORMAT" scope="application"><s:message code="TIME_FORMAT"/></c:set>

<!-- Menu Horizontal -->
<%--
<script>
$(document).ready(function () {
	$.ajax({
	    url: "response.request.count",
	    dataType: 'json',
	    type: 'GET',
	    success: function (res) {
	    	if (res.countResponseRequest > 0) {
	    		$("#countResponse").html("<a class='button blue small' href='listSendRequest' >" + res.countResponseRequest + " New Response </a>");
	    	}
	    	else {
	    		$("#countResponse").html();
	    	}
	    },
	    fail: function() {
	    	alert("FAIL");
	    }
    });
	
	$.ajax({
	    url: "request.count",
	    dataType: 'json',
	    type: 'GET',
	    success: function (res) {
	    	if (res.countRequest > 0) {
	    		$("#countRequest").html("<a class='button red small' href='listReceiveRequest' >" + res.countRequest + " New Request </a>");
	    	}
	    	else {
	    		$("#countRequest").html("");
	    	}
	    },
	    fail: function() {
	    	alert("FAIL");
	    }
    });
	
	setInterval(function(){ 
		$.ajax({
		    url: "response.request.count",
		    dataType: 'json',
		    type: 'GET',
		    success: function (res) {
		    	if (res.countResponseRequest > 0) {
		    		$("#countResponse").html("<a class='button blue small' href='listSendRequest' >" + res.countResponseRequest + " New Response </a>");
		    	}
		    	else {
		    		$("#countResponse").html();
		    	}
		    },
		    fail: function() {
		    	alert("FAIL");
		    }
	    });
	}, 90000);
	
	setInterval(function(){ 
		$.ajax({
		    url: "response.request.count",
		    dataType: 'json',
		    type: 'GET',
		    success: function (res) {
		    	if (res.countResponseRequest > 0) {
		    		$("#countResponse").html("<a class='button blue small' href='listSendRequest' >" + res.countResponseRequest + " New Response </a>");
		    	}
		    	else {
		    		$("#countResponse").html();
		    	}
		    },
		    fail: function() {
		    	alert("FAIL");
		    }
	    });
	}, 90000);
});
</script>
--%>
<ul class="menu">
	<c:choose>
		<c:when test="${SelectedMenu == 'home'}">
			<li class="current" style="white-space: nowrap;"><a href="home"><i class="icon-home"></i> <s:message code="Home_page"/></a></li>
		</c:when>
		<c:otherwise>
			<li style="white-space: nowrap;"><a href="home"><i class="icon-home"></i> <s:message code="Home_page"/></a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${SelectedMenu == 'listAnnouncement'}">
			<li class="current" style="white-space: nowrap;"><a href="listAnnouncement"><i class="icon-bullhorn"></i> <s:message code="Announcement"/></a></li>
		</c:when>
		<c:otherwise>
			<li style="white-space: nowrap;"><a href="listAnnouncement"><i class="icon-bullhorn"></i> <s:message code="Announcement"/></a></li>
		</c:otherwise>
	</c:choose>
  	<c:choose>
		<c:when test="${SelectedMenu == 'listRule'}">
			<li class="current" style="white-space: nowrap;"><a href="listRule"><i class="icon-legal"></i> <s:message code="Rule"/></a></li>
		</c:when>
		<c:otherwise>
			<li style="white-space: nowrap;"><a href="listRule"><i class="icon-legal"></i> <s:message code="Rule"/></a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${SelectedMenu == 'myListTask'}">
			<li class="current" style="white-space: nowrap;"><a href="myListTask"><i class="icon-tasks"></i> <s:message code="Task"/></a></li>
		</c:when>
		<c:otherwise>
			<li style="white-space: nowrap;"><a href="myListTask"><i class="icon-tasks"></i> <s:message code="Task"/></a></li>
		</c:otherwise>
	</c:choose>
    <c:choose>
        <c:when test="${SelectedMenu == 'myListLeave'}">
            <li class="current" style="white-space: nowrap;"><a href="myListLeave"><i class="icon-beer"></i> <s:message code="Leave"/></a></li>
        </c:when>
        <c:otherwise>
            <li style="white-space: nowrap;"><a href="myListLeave"><i class="icon-beer"></i> <s:message code="Leave"/></a></li>
        </c:otherwise>
    </c:choose>
    <%--
	<c:choose>
		<c:when test="${SelectedMenu == 'commonManagement'}">
			<li class="current" style="display: inline-block;white-space: nowrap;"><a href=""><i class="icon-wrench"></i> <s:message code="Common_management"/></a>
				<ul>
			      <li><a href="createRequest"><i class="icon-magic"></i> <s:message code="Create_request"/></a></li>
			      <li><a href="searchRequest"><i class="icon-search"></i> <s:message code="Search_request"/></a></li>
			  	</ul>
			</li>
		</c:when>
		<c:otherwise>
			<li style="display: inline-block;white-space: nowrap;"><a href=""><i class="icon-wrench"></i> <s:message code="Common_management"/></a>
				<ul>
			      <li><a href="createRequest"><i class="icon-magic"></i> <s:message code="Create_request"/></a></li>
			      <li><a href="searchRequest"><i class="icon-search"></i> <s:message code="Search_request"/></a></li>
			  	</ul>
			</li>
		</c:otherwise>
	</c:choose>
    --%>
    <li>&nbsp;<a href='createRequest' class="button orange small"><i class="icon-magic"></i><s:message code="Create_request"/></a></li>
    <li>&nbsp;<a href='searchRequest' class="button green small"><i class="icon-search"></i><s:message code="Search"/></a></li>
    
	<li style="display: inline-block;" id="countRequest"></li>
	<li style="display: inline-block; margin-left:10px;" id="countResponse"></li>
	<li class="right" style="display: inline-block;"><a href="#"><i class="icon-user"></i> ${pageContext.request.userPrincipal.name}</a>
		<ul>
          <li class="left"><a href="change-password"><i class="icon-reply"></i><s:message code="Change_password"/></a></li>
	      <li class="left"><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i class="icon-coffee"></i> <s:message code="Quit"/></a></li>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
          <li class="left divider"><a href="init-data"><i class="icon-download-alt"></i> <s:message code="Init_data"/></a></li>
          </sec:authorize>
	    </ul>
	</li>

    <%-- For Admin.START --%>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    
      <li class="right" style="display: inline-block;white-space: nowrap;"><a href=""><i class="icon-cog"></i><s:message code="Configuration"/></a>
      <ul>
      <%--
        <li class="left"><a href="master.department"><i class="icon-sitemap"></i><s:message code="Department"/></a></li>
        <li class="left"><a href="master.template"><i class="icon-bookmark-empty"></i><s:message code="Template"/></a></li>
       --%>
        <li class="left divider"><a href="load-account-ldap"><i class="icon-group"></i><s:message code="Load_account_from_ldap"/></a></li>
      </ul>
    </li>
    </sec:authorize>
  <%-- For Admin.END --%>
</ul>

<!-- End #header -->