<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<script src="${contextApp}/resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="${contextApp}/resources/handsontable/jquery.handsontable.full.js"></script>
<script src="${contextApp}/resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="${contextApp}/resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="${contextApp}/resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">
<p>
${rule.content}
</p>
