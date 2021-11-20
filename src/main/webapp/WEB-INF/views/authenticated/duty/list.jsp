<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.duty.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.duty.list.label.startMoment" path="startMoment" width="20%"/>
	<acme:list-column code="authenticated.duty.list.label.endMoment" path="endMoment" width="20%"/>
	<acme:list-column code="authenticated.duty.list.label.workload" path="workload" width="20%"/>
</acme:list>