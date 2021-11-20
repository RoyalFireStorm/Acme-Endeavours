<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.duty.form.label.title" path="title"/>	
	<acme:form-moment code="anonymous.duty.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="anonymous.duty.form.label.endMoment" path="endMoment"/>
	<acme:form-money code="anonymous.duty.form.label.workload" path="workload"/>
	<acme:form-select code="anonymous.duty.form.label.status" path="status">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${status == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${status == 'PRIVATE'}"/>
	</acme:form-select>
	<acme:form-textarea code="anonymous.duty.form.label.description" path="description"/>
	<acme:form-url code="anonymous.duty.form.label.link" path="link"/>
	<acme:form-return code="anonymous.duty.form.button.return"/>
</acme:form>