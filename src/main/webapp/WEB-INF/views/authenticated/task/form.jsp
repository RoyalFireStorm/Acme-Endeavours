<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>	
	<acme:form-moment code="authenticated.task.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="authenticated.task.form.label.endMoment" path="endMoment"/>
	<acme:form-money code="authenticated.task.form.label.workload" path="workload"/>
	<acme:form-select code="authenticated.task.form.label.status" path="status">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${status == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${status == 'PRIVATE'}"/>
	</acme:form-select>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>
	<acme:form-url code="authenticated.task.form.label.link" path="link"/>
<%-- 	<acme:form-submit code="authenticated.task.form.label.create" action="/authenticated/task/create"/> --%>
	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>