<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Task -->
<div id="browse-task">
		<div>
  		  <H5>${model.request.title}</H5>
		</div>
        <div>
          <label for="status" class="col_2"><s:message code="Status"/>:</label>
          <c:choose>
            <c:when test="${not empty model.request.status}">
                 <label for="status" class='col_2 <s:message code="Format_${model.request.status}"/>'><s:message code="${model.request.status}"/></label>
            </c:when>
            <c:otherwise>
                &nbsp;
            </c:otherwise>
          </c:choose>
         
        </div>

		<div>
			<label for="content" class="col_2 left"><s:message code="Content"/>:</label>
			<label class="col_8">${model.request.content}</label>
		</div>

		<div>
		 	<label for="assigneeUsername" class="col_2"><s:message code="Assignee"/>:</label>
		 	<label class="col_8">${model.request.assigneeUsername}</label>
		</div>
		<div>
            <label for="managerUsername" class="col_2"><s:message code="Manager"/>:</label>
            <label class="col_8">${model.request.managerUsername}</label>
		</div>
		<div>
            <label for="startDate" class="col_2"><s:message code="Start_date"/>:</label>
            <label class="col_2"><fmt:formatDate value="${model.request.startdate}" pattern="${DATE_FORMAT}"/></label>
            
            <label for="endDate" class="col_2"><s:message code="End_date"/>:</label>
            <label class="col_2"><fmt:formatDate value="${model.request.enddate}" pattern="${DATE_FORMAT}"/></label>
		</div>
		<div>
		 	<label for="label" class="col_2"><s:message code="Label"/>:</label>
		 	<label class="col_8">${model.request.label1}</label>
		</div>
		<div>
		 	<label for="duration" class="col_2"><s:message code="Duration"/>:</label>
		 	<label class="col_2">${model.request.duration} <span>${model.durationUnitName}</span></label>
		</div>

		<div id="attachment">
		  <label for="attachment0" class="col_2"><s:message code="Attach"/>:</label>
          <label class="col_8">
            <c:choose>
                <c:when test="${not empty model.request.filename1}">
                  <a href="downloadFile?id=${model.request.id}" target="_blank" title='<s:message code="Download_attachment"/>"'>${model.request.filename1}</a>
                </c:when>
                <c:otherwise>
                  <s:message code="NONE"/>
                </c:otherwise>
            </c:choose>
          </label>
        </div>
<!--         <div> -->
<%--         	<a class="button" href="addComment?id=${model.request.id}">Add Comment</a> --%>
<!--         </div> -->
</div>