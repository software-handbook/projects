<%--
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<!-- bootstrap 3.0.2 -->
<link href="${contextApp}/resources/AdminLTE/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="${contextApp}/resources/AdminLTE/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="${contextApp}/resources/AdminLTE/css/ionicons.min.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES -->
<link href="${contextApp}/resources/AdminLTE/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<%-- Multi select --%>
<link rel="stylesheet" href="${contextApp}/resources/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css">
<link rel="stylesheet" href="${contextApp}/resources/bootstrap-multiselect/css/prettify.css" type="text/css">

<!-- jQuery -->
<script src="${contextApp}/resources/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<!-- Bootstrap -->
<script src="${contextApp}/resources/AdminLTE/js/bootstrap.min.js" type="text/javascript"></script>
<!-- DATA TABES SCRIPT -->
<script src="${contextApp}/resources/AdminLTE/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
<script src="${contextApp}/resources/AdminLTE/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>

<script type="text/javascript" src="${contextApp}/resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="${contextApp}/resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">


<%-- Process confirmation delete request --%>
<script type="text/javascript" src="${contextApp}/resources/js/confirmFunction.js"></script>
<script type="text/javascript" src="${contextApp}/resources/js/data-table-${pageContext.response.locale}.js"></script>

<jsp:include page="../_common/confirmDeleteRequest.jsp"/>

<%-- Multi select --%>
<script type="text/javascript" src="${contextApp}/resources/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="${contextApp}/resources/bootstrap-multiselect/js/prettify.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
      includeSelectAllOption: true
      $('.multiselect').multiselect({ 
          includeSelectAllOption: true,
          nonSelectedText: '',
          nSelectedText: 'mục chọn',
          selectAllText: 'Chọn tất cả'
      });
  });
</script>

