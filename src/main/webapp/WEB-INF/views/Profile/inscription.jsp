
<%@ include file="/common/Header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/input-styling.css">
	<form action="/Web003_tester/inscription" method="post">
	<div class="form-row container d-flex flex-column align-items-center justify-content-center">
	
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Nom d'etulisateur</label>
	    <input type="text" name="username" class="form-control input-design item" required>
	    <% if( request.getAttribute("usernameError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("usernameError").toString() %></span>		    	
			<%} %>
	  </div>
	  
	  <div class="form-group col-md-6 mb-3"">
	    <label class="form-label">Mot de pass </label>
	    <input type="password" name="mdp" class="form-control input-design item" required >
	    <% if( request.getAttribute("mdpError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("mdpError").toString() %></span>		    	
			<%} %>
	  </div>
	  <div class="form-group col-md-6 mb-3"">
	    <label class="form-label">Confirmer mot de pass </label>
	    <input type="password" name="cmdp" class="form-control input-design item" required >
	    <% if( request.getAttribute("cmdpError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("cmdpError").toString() %></span>		    	
			<%} %>
	  </div>
	  <div class="row col-md-6 md-3">
		  <div class="form-group col-md-6">
		    <label class="form-label">Nom</label>
		    <input type="text" name="nom" class="form-control input-design item" required>
		    <% if( request.getAttribute("nomError") != null){%>
	        	<span class="text-danger"><%= request.getAttribute("nomError").toString() %></span>		    	
				<%} %>
		  </div>
		  <div class="form-group col-md-6">
		    <label class="form-label">Prenom</label>
		    <input type="text" name="prenom" class="form-control input-design item " required>
		    <% if( request.getAttribute("prenomError") != null){%>
	        	<span class="text-danger"><%= request.getAttribute("prenomError").toString() %></span>		    	
				<%} %>
		  </div>
	  </div>
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Email</label>
	    <input type="email" name="email" class="form-control input-design item" required>
	    <% if( request.getAttribute("emailError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("emailError").toString() %></span>		    	
			<%} %>
	  </div>
	  <div class="form-group col-md-6 mb-3">
	    <label class="form-label">Addresse</label>
	    <textarea class="form-control input-design" name="addresse" required></textarea>
	    <% if( request.getAttribute("addresseError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("addresseError").toString() %></span>		    	
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
		  <button type="submit" class="btn btn-outline-primary btn-lg">S'iscrire</button>
	  </div>
	 </div>
	</form>
	
<%@ include file="/common/Footer.html" %>
</body>
</html>