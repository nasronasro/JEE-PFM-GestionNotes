
<%@ include file="/common/Header.jsp" %>
	<form action="/Web003_tester/login" method="post">
	<div class="form-row container d-flex flex-column align-items-center justify-content-center ">
	<h1>Login form</h1>
	  <div class="form-outline form-group col-md-6 mb-3">
	   <label class="form-label" for="username">Username</label>
	    <input type="text" id="username" class="form-control input-design" name="username" required >
	    <% if( request.getAttribute("usernameError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("usernameError").toString() %></span>		    	
		<%} %>
	  </div>
	  
	  <div class="form-outline form-group col-md-6 mb-3">
	    <label class="form-label" for="password" >Mot de pass </label>
	    <input type="password" id="password" class="form-control input-design" name="password" required >
	    <% if( request.getAttribute("mdpError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("mdpError").toString() %></span>		    	
		<%} %>
	  </div>
	  <div class="form-group mb-3">
		  <button type="submit" class="btn btn-outline-primary btn-lg">Login</button>
	  </div>
	 </div>
	</form>
	
<%@ include file="/common/Footer.html" %>