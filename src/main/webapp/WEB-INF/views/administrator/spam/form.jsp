<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spam.form.label.spamWords" path="spamWords"/>
		
	<acme:form-submit code="administrator.spam.form.button.update" action="/administrator/spam/update"/>
	
  	<acme:form-return code="administrator.spam.form.button.return"/>
</acme:form>