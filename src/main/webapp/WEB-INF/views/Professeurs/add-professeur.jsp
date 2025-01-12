
<%@ include file="/common/Header.jsp" %>

	<form action="/Web003_tester/add-professeur" method="post">
	<div class="form-row container d-flex flex-column align-items-center justify-content-center">
	  
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Nom</label>
	    <input type="text" name="nom" class="form-control input-design" required>
	    <% if( request.getAttribute("nomError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("nomError").toString() %></span>		    	
		    <%} %>
	  </div>
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Prenom</label>
	    <input type="text" name="prenom" class="form-control input-design" required>
	    <% if( request.getAttribute("prenomError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("prenomError").toString() %></span>		    	
		    <%} %>
	  </div>
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Email</label>
	    <input type="email" name="email" class="form-control input-design" required>
	    <% if( request.getAttribute("emailError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("emailError").toString() %></span>		    	
		    <%} %>
	  </div>
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Telephone</label>
	    <input type="text" name="telephone" class="form-control input-design" required >
	    <% if( request.getAttribute("telephoneError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("telephoneError").toString() %></span>		    	
		    <%} %>
	  </div>

	  
	  <div class="form-group mb-3">
		  <button type="submit" class="btn btn-outline-success btn-lg">Ajouter professeur</button>
		  <a href="list-professeur" class="btn btn-outline-danger" >Annuller</a>
	  </div>
	 </div>
	</form>
<%@ include file="/common/Footer.html" %>
