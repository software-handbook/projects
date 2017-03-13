<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
/**
 * 
 */
function showInfoDialogRegisterSuccess() {
  $("#dialog-info-register-success").dialog({
      resizable: false,
      height:190,
      modal: true,
      buttons: {
          '<s:message code="Close"/>': function() {
              $(this).dialog("close");
  
              // Redirect to login screen
              window.location.href = "login";
          }
      }
  });
}

/**
 * 
 */
$(function() {
	  $("#dialog-info-register-success").dialog({
	      resizable: false,
	      height:190,
	      modal: true,
	      buttons: {
	          '<s:message code="Close"/>': function() {
	              $(this).dialog("close");
	  
	              // Redirect to login screen
	              window.location.href = "login";
	          }
	      }
	  });

});
</script>
<div id="dialog-info-register-success" title='<s:message code="Information"/>?' style="display: NONE">
    <p><span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span><s:message code="Information_register_success"/>?</p>
</div>
