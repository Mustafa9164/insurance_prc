<%@page import="com.entity.CitizenPlan"%>
<%@page
	import="org.hibernate.type.descriptor.java.MutabilityPlanExposer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	
	<div class="container">

		<div class="">
			<h3 class="pb-3">Report Application</h3>
			<form:form method="POST" action="/search"
				modelAttribute="searchRequest">

				<table class="table table-borderless ">
					<tr>
						<td><form:label path="planName">PlanName : </form:label> <form:select
								path="planName">
								<form:option value="" >-Select-</form:option>
								<form:options items="${planNames }" path="planName" />
							</form:select></td>
						<td><form:label path="planStatus">PlanStatus :</form:label> <form:select
								path="planStatus">
								<form:option value="" >-Select-</form:option>
								<form:options items="${planStatuses }" path="planStatus" />
							</form:select></td>
						<td><form:label path="gender">Gender</form:label> <form:select
								path="gender">
								<form:option value="" >-Select-</form:option>
								<form:option value="male" path="gender"></form:option>
								<form:option value="female" path="gender"></form:option>
							</form:select></td>
					</tr>
					<tr>
						<td><form:label path="planStartDate">Start Date :</form:label>
							<form:input type="date" path="planStartDate" /></td>
						<td><form:label path="planEndDate">End Date :</form:label>
				    	<form:input	type="date" path="planEndDate" /></td>
						<td></td>
					</tr>
					<tr>
					    <td><a href="/welcome" class="btn btn-secondary">Reset</a>
						<input type="submit" value="Search"
							class="btn btn-primary" /></td>

						<td></td>
						<td></td>
					</tr>
				</table>
			</form:form>
		</div>
		<hr>
		<div>
			<h4 class="text-center">Citizens List</h4>

			<table class="table table-borderless">
			<thead>
				<tr class="thead-dark">
				    <th>S.No</th>
					<th>Citizen Name</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Denial Reason</th>
					<th>Termination Date</th>
					<th>Termination Reason</th>
					<th>Gender</th>
					<th>Benefit Amt</th>
				</tr>
			</thead>
			
			 <c:if test="${empty plans}">
        <tr>
            <td colspan="11" style="text-align:center; color:red;">
                No Record Found
            </td>
        </tr>
    </c:if>
				<tbody>
					<c:forEach items="${plans}" var="plan" varStatus="index">
					
					<tr>
						<td>${index.count}</td>
					<td>${plan.citizenName}</td>
					<td>${plan.planName}</td>
					<td>${plan.planStatus}</td>
					<td>${plan.planStartDate }</td>
					<td>${plan.planEndDate }</td>
					<td>${plan.denialReason }</td>
					<td>${plan.terminatedDate }</td>
					<td>${plan.terminationReason }</td>
					<td>${plan.gender}</td>
					<td>${plan.benefitAmount}</td>

					
					</tr>
					
					
					
					
					</c:forEach>
					
				</tbody>
			</table>
			
			Export: <a href="excel">Excel</a><a href="pdf">pdf</a>
		</div>
		</div>
		
		<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
		crossorigin="anonymous"></script>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
	
</body>
</html>