<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" />
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" />
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>	
  	<acme:form-return code="anonymous.shout.form.button.return"/>
  	
</acme:form>