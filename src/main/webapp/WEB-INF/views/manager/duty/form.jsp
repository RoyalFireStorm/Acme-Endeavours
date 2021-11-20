<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="manager.duty.form.label.title" path="title"/>	
	<acme:form-moment code="manager.duty.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="manager.duty.form.label.endMoment" path="endMoment"/>
	<acme:form-textbox code="manager.duty.form.label.workload" path="workload"/>
	<acme:form-select code="manager.duty.form.label.status" path="status">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${status == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${status == 'PRIVATE'}"/>
	</acme:form-select>
	<acme:form-textarea code="manager.duty.form.label.description" path="description"/>
	<acme:form-url code="manager.duty.form.label.link" path="link"/>
	
 	<acme:form-submit test="${command == 'create'}" code="manager.duty.form.button.create" action="/manager/duty/create"/> 

 	<acme:form-submit test="${command == 'show'}" code="manager.duty.form.button.update" action="/manager/duty/update"/> 
 	<acme:form-submit test="${command == 'show'}" code="manager.duty.form.button.delete" action="/manager/duty/delete"/> 
	<acme:form-submit test="${command == 'update'}" code="manager.duty.form.button.update" action="/manager/duty/update"/> 
 	<acme:form-submit test="${command == 'delete'}" code="manager.duty.form.button.delete" action="/manager/duty/delete"/>

	<acme:form-return code="manager.duty.form.button.return"/>
</acme:form>