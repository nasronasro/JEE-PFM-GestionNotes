<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Note Application</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link href="<%= request.getContextPath() %>/css/input-styling.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/navbar-style.css" rel="stylesheet">
</head>
<body class="bg-dark text-white min-vh-100 d-flex flex-column">
	<main class="flex-grow-1">
	<nav class="navbar navbar-dark text-light navbar-expand-lg ">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">note app</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <form>
	    	
	     <ul class="navbar-nav me-auto mb-2 mb-lg-0">

    <li class="nav-item">
        <a href="/Web003_tester/list-note" class="nav-link active" aria-current="page">Home</a>
    </li>

    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle btn btn-outline-dark me-2" href="#" id="navbarDropdownNotes" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Notes
        </a>
        <ul class="dropdown-menu dropdown-menu-dark bg-dark text-white" aria-labelledby="navbarDropdownNotes">
            <li><a class="dropdown-item text-white" href="/Web003_tester/list-note">List Notes</a></li>
            <li><a class="dropdown-item text-white" href="/Web003_tester/add-note">Add New Note</a></li>
        </ul>
	    </li>
	
	    <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle btn btn-outline-dark me-2" href="#" id="navbarDropdownMatieres" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Matieres
	        </a>
	        <ul class="dropdown-menu dropdown-menu-dark bg-dark text-white" aria-labelledby="navbarDropdownMatieres">
	            <li><a class="dropdown-item text-white" href="/Web003_tester/list-matiere">List Matieres</a></li>
	            <li><a class="dropdown-item text-white" href="/Web003_tester/add-matiere">Add New Matiere</a></li>
	        </ul>
	    </li>
	
	    <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle btn btn-outline-dark me-2" href="#" id="navbarDropdownProfesseurs" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Professeurs
	        </a>
	        <ul class="dropdown-menu dropdown-menu-dark bg-dark text-white" aria-labelledby="navbarDropdownProfesseurs">
	            <li><a class="dropdown-item text-white" href="/Web003_tester/list-professeur">List Professeurs</a></li>
	            <li><a class="dropdown-item text-white" href="/Web003_tester/add-professeur">Add New Professeur</a></li>
	        </ul>
	    </li>
	</ul>
	      </form>
	      <form class="d-flex ms-auto">	
			<% boolean isLoggedIn = (boolean) request.getAttribute("login");
			if(isLoggedIn) {
			%>
			 	<a href="/Web003_tester/login" class="btn btn-outline-primary me-2" aria-current="page" >Login</a>
			 	<a href="/Web003_tester/inscription" class="btn btn-outline-primary" aria-current="page">Inscritre</a>
			<%
			} else{
				
			%>
				<a href="/Web003_tester/logout" class="btn btn-outline-danger" aria-current="page" >Logout</a>
			<%
			}
			%>
	    </form>
	   </div>
	  </div>
	</nav>
