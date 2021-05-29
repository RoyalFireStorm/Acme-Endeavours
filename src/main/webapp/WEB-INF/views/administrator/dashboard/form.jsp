<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" id="general_indicators">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-task-public"/>
		</th>
		<td>
			<acme:print value="${totalNumberTaskPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-task-private"/>
		</th>
		<td>
			<acme:print value="${totalNumberTaskPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-task-finished"/>
		</th>
		<td>
			<acme:print value="${totalNumberTaskFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-task-no-finished"/>
		</th>
		<td>
			<acme:print value="${totalNumberTaskNoFinished}"/>
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.workload-statistics"/>
</h2>

<table class="table table-sm" id="workload-statistics">
	<caption>
		<acme:message code="administrator.dashboard.form.title.workload-statistics"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-task-workload"/>
		</th>
		<td>
			<acme:print value="${averageTaskWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-task-workload"/>
		</th>
		<td>
			<acme:print value="${deviationTaskWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-task-workload"/>
		</th>
		<td>
			<acme:print value="${maximumTaskWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-task-workload"/>
		</th>
		<td>
			<acme:print value="${minimumTaskWorkload}"/>
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.execution-period-statistics"/>
</h2>

<table class="table table-sm" id="execution-period-statistics">
	<caption>
		<acme:message code="administrator.dashboard.form.title.execution-period-statistics"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${averageTaskExtPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${deviationTaskExtPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${maximumTaskExtPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${minimumTaskExtPeriod}"/>
		</td>
	</tr>
</table>


