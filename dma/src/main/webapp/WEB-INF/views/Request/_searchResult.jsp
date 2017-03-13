<%--
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="box-body table-responsive">
  <table id="searchResult" class="table table-bordered table-hover">
    <thead>
      <tr>
        <th width="5px" title="Phân Loại">L</th>
        <th width="200px" nowrap="nowrap">Tiêu đề</th>
        <th width="50px" nowrap="nowrap">Người thực hiện</th>
        <th width="50px" nowrap="nowrap">Người quản lý</th>
        <th width="50px" nowrap="nowrap">Trạng thái</th>
        <th width="40px" nowrap="nowrap">Ngày tạo</th>
        <th width="40px" nowrap="nowrap">Ngày cập nhật</th>
        <th width="40px" nowrap="nowrap">Ngày kết thúc</th>
        <th width="5px" title="Thao tác">T</th>
      </tr>
    </thead>
    <c:forEach var="request" items="${requests}" varStatus="status">
      <tr>
        <td>
          <c:if test='${request.requesttypeCd == "Task"}'><i class="icon-magic"></i></c:if>
          <c:if test='${request.requesttypeCd == "Rule"}'><i class="icon-reorder"></i></c:if>
          <c:if test='${request.requesttypeCd == "Leave"}'><i class="icon-plane"></i></c:if>
          <c:if test='${request.requesttypeCd == "Announcement"}'><i class="icon-bullhorn"></i></c:if>
        </td>
        <td>
          <c:choose>
            <c:when test="${not empty request.filename1}">
                <a href="downloadFile?id=${request.id}" target="_blank" title="Tài liệu đính kèm: ${request.filename1}"><span class="glyphicon glyphicon-paperclip"></span></a>
            </c:when>
            <c:otherwise>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </c:otherwise>
          </c:choose>
          
          
          <a href="browseRequest?id=${request.id}" title='<s:message code="View_Details"/>'>${request.title}</a>
        </td>
        <td>${request.assigneeUsername}</td>
        <td>${request.managerUsername}</td>
        <td>
            <c:choose>
                <c:when test="${not empty request.status}"><s:message code="${request.status}"/></c:when>
                <c:otherwise>&nbsp;</c:otherwise>
            </c:choose>
            
          
        </td>
        <td><fmt:formatDate value="${request.created}" pattern="${DATE_FORMAT}"/></td>
        <td><fmt:formatDate value="${request.lastmodified}" pattern="${DATE_FORMAT}"/></td>
        <td><fmt:formatDate value="${request.enddate}" pattern="${DATE_FORMAT}"/></td>
        <td style="width: 10px; padding-top: 0px; padding-bottom: 0px; padding-left: 2px; padding-right: 0px;">
            <div class="input-group-btn">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="border: NONE" title="Thao tác"><i class="icon-cog"></i><span class="fa fa-caret-down"></span></button>
                <ul class="dropdown-menu">
                  <c:choose>
                    <c:when test="${(request.createdbyUsername == pageContext.request.userPrincipal.name) || (request.managerUsername == pageContext.request.userPrincipal.name)}">
                      <li><a href="#" onclick='showConfirmDialog("${request.id}", "${request.title}")' title='<s:message code="Delete"/>'><s:message code="Delete"/></a></li>
                    </c:when>
                   
                    <c:otherwise>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                          <li><a href="#" onclick='showConfirmDialog("${request.id}", "${request.title}")' title='<s:message code="Delete"/>'><s:message code="Delete"/></a></li>
                        </sec:authorize>
                    </c:otherwise>
                  </c:choose>
                    
<%--                   <li><a href="#"><s:message code="View_Details"/></a></li> --%>
<!--                   <li class="divider"></li> -->
<!--                   <li><a href="#">Comment</a></li> -->
                </ul>
            </div><!-- /btn-group -->
        </td>
      </tr>
    </c:forEach>
  </table>
</div>