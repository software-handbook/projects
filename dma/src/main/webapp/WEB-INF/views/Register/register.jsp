<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">

<div>
  <div class="box-group">
    <H4><s:message code="Register_account"/></H4>
    <form:form action="doRegister" modelAttribute="model" enctype="multipart/form-data" method="POST">
      <div class="form-group">
        <label for="mail"><s:message code="Your_email"/>(*)</label>
        <form:input path="mail" required="required" class="form-control"/>
        <form:errors path="mail" class="error"/>
      </div>
      <div class="form-group">
        <label for="password"><s:message code="Password"/>(*)</label>
        <form:password path="password" required="required" class="form-control"/>
        <form:errors path="password" class="error"/>
      </div>
      <div class="form-group">
        <label for="retypePassword"><s:message code="Retype_password"/>(*)</label>
        <form:password path="retypePassword" required="required" class="form-control"/>
        <form:errors path="retypePassword" class="error"/>
        <form:errors path="matchedPassword" class="error"/>
      </div>
      <div class="form-group">
        <label for="company"><s:message code="Company_code"/>(*)</label>
        <form:input path="company" class="form-control"/>
        <form:errors path="company" class="error"/>
        <small><s:message code="Company_link_app" arguments="6"/></small>
      </div>
      <div>
        <label></label>
        <input type="submit" value='<s:message code="Register"/>' class="button"/>
      </div>
    </form:form>
  </div>
</div>


<c:if test='${not empty SAVE_STATUS}'>
    <c:choose>
        <c:when test='${SAVE_STATUS == "SUCCESS"}'>
          <div class="notice success"><i class="icon-ok icon-large"></i>
            <s:message code="Information_register_success"></s:message>
          </div>
        </c:when>
        <c:otherwise>
          <div class="notice error"><i class="icon-remove-sign icon-large"></i> 
              <br/><s:message code="Information_register_fail"></s:message>
              <c:if test='${not empty MESSAGE_CODE}'>
              <s:message code="${MESSAGE_CODE}"></s:message>
              </c:if>
          </div>
        </c:otherwise>
    </c:choose>
</c:if>

<%-- <%@ include file="_infoRegisterSuccess.jsp" %> --%>
