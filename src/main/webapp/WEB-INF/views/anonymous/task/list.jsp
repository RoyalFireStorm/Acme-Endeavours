<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.startMoment" path="startMoment" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.endMoment" path="endMoment" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.workload" path="workload" width="20%"/>
<%-- 	<acme:list-column code="anonymous.task.list.label.status" path="status" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.description" path="description" width="60%"/>
	<acme:list-column code="anonymous.task.list.label.link" path="link" width="20%"/> --%>
</acme:list>