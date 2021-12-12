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
			<acme:message code="administrator.dashboard.form.label.total-number-duty-public"/>
		</th>
		<td>
			<acme:print value="${totalNumberDutyPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-duty-private"/>
		</th>
		<td>
			<acme:print value="${totalNumberDutyPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-duty-finished"/>
		</th>
		<td>
			<acme:print value="${totalNumberDutyFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-duty-no-finished"/>
		</th>
		<td>
			<acme:print value="${totalNumberDutyNoFinished}"/>
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
			<acme:message code="administrator.dashboard.form.label.average-duty-workload"/>
		</th>
		<td>
			<acme:print value="${averageDutyWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-duty-workload"/>
		</th>
		<td>
			<acme:print value="${deviationDutyWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-duty-workload"/>
		</th>
		<td>
			<acme:print value="${maximumDutyWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-duty-workload"/>
		</th>
		<td>
			<acme:print value="${minimumDutyWorkload}"/>
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
			<acme:message code="administrator.dashboard.form.label.average-duty-execution-period"/>
		</th>
		<td>
			<acme:print value="${averageDutyExtPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-duty-execution-period"/>
		</th>
		<td>
			<acme:print value="${deviationDutyExtPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-duty-execution-period"/>
		</th>
		<td>
			<acme:print value="${maximumDutyExtPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-duty-execution-period"/>
		</th>
		<td>
			<acme:print value="${minimumDutyExtPeriod}"/>
		</td>
	</tr>
</table>


