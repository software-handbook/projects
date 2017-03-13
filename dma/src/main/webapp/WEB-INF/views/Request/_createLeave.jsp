<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!--    Leave -->
<div id="make-leave">
  <form:form id="createLeave" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
    <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Leave"/>
    <form:hidden path="request.id"/>
        <div>
          <label for="title" class="col_2"><s:message code="Title"/><span class="required">*</span></label>
          <form:input path="request.title" type="text" required="required" class="col_8"/>
          <form:errors path="request.title" class="error"/>
        </div>
        <div>
            <label for="content" class="col_2 left"><s:message code="Reason"/></label>
            <form:textarea path="request.content" id="content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="leaveContent" rows="15" placeholder="Mô tả chi tiết lý do và sắp xếp công việc đảm bảo không ảnh hưởng"></form:textarea>
        </div>
        <%-- Default assignee of leave --%>
        <div>
          <c:choose>
            <c:when test="${not empty request.assigneeUsername}">
                <form:input path="request.assigneeUsername" class="col_8"/>
            </c:when>
            <c:otherwise>
                <input name="request.assigneeUsername" type="hidden" class="col_8" value="${pageContext.request.userPrincipal.name}"/>
            </c:otherwise>
          </c:choose>
          
        </div>	
        <div>
		 	<label for="managerUsername" class="col_2"><s:message code="Manager"/></label>
		 	<form:select path="request.managerUsername" class="col_3"  title="Người sẽ nhận đơn và duyệt nghỉ phép">
         		<option value=""></option>
         		<c:forEach var="user" items="${listUser}">
                  <c:choose>
                    <c:when test="${model.request.managerUsername == user.username}">
                        <option value="${user.username}" selected="selected">${user.username}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${user.username}">${user.username}</option>
                    </c:otherwise>
                  </c:choose>
         			
         		</c:forEach>
	  		</form:select>        
		</div>
        <div>
            <label for="startDate" class="col_2">Ngày bắt đầu</label>
            <form:input path="request.startdate" id="request_startdate_leave" class="col_2"/>
        </div>
        <div>
            <label for="endDate" class="col_2">Ngày kết thúc</label>
            <form:input path="request.enddate" id="request_enddate_leave" class="col_2"/>
            <form:errors path="request.startdate" class="error"/>
        </div>
        <div>
            <label for="label" class="col_2">Nhãn</label>
            <form:input path="request.label1" type="text" class="col_8"/>
        </div>
        
        <jsp:include page="_createRequest_Attach.jsp">
            <jsp:param name="requestType" value="Leave"/>
        </jsp:include>
        
      	<div>
        <input type="submit" value='<s:message code="Save"/>' class="button"/>
        <c:choose>
            <c:when test="${not empty model.request.id}">
                <a href="browseRequest?id=${model.request.id}" title='' class="button"><s:message code="Back"/></a>
            </c:when>
            <c:otherwise>
                <input type="submit" disabled="disabled" value='<s:message code="Back"/>' class="button"/>
            </c:otherwise>
        </c:choose>
      	</div>
  </form:form>
</div>