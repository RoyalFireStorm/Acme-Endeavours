<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<br/>
<acme:list>
	<acme:list-column code="administrator.spam.list.label.spamWords" path="spamWords" width="20%"/>
</acme:list>

<acme:form-submit code="administrator.spam.form.button.threshold-show" action="/administrator/spam/threshold/show" method="get"/>