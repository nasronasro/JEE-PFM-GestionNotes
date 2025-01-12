<%@ page import="java.util.List" %>
<%@ page import="dao.Proffesseur" %>
<%@ include file="../../../common/Header.jsp" %>
<div class="Container">

	<form action="/Web003_tester/add-matiere" method="post" class="container w-50 p-4 rounded shadow-sm">
		<legend>Ajouter Matiere</legend>
	    <div class="mb-3">
	        <label for="label" class="form-label">Label:</label>
	        <input type="text" id="label" name="label" class="form-control input-design" required>
	        <% if( request.getAttribute("labelError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("labelError").toString() %></span>		    	
			<%} %>
	    </div>
	    <div class="mb-3">
	        <label for="importance" class="form-label">Importance:</label>
	        <select name="importance" class="form-control input-design">    	
		    	<option value="3">Low</option>
		    	<option value="2">Medium</option>
		    	<option value="1">High</option>
	        </select>
	        <% if( request.getAttribute("importanceError") != null){%>
        	<span class="text-danger input-design"><%= request.getAttribute("importanceError").toString() %></span>		    	
			<%} %>
	    </div>
	    <div class="mb-3">
	        <label for="idProffesseur" class="form-label">Professor:</label>
	        <select name="proffesseur" class="form-control input-design">
	       
	       <% 
		    	List<Proffesseur> listProffesseurs = (List<Proffesseur>) request.getAttribute("ListProfs");
		    	for(Proffesseur prof : listProffesseurs) {	
		    %>
		    	<option value="<%= prof.getId_proffesseur() %>"><%= prof.getNom() + " " + prof.getPrenom() %></option>
		    <%}%>
	        </select>
	        <% if( request.getAttribute("proffesseurError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("proffesseurError").toString() %></span>		    	
			<%} %>
	    </div>
	    <button type="submit" class="btn btn-outline-success btn-lg">Ajouter matiere</button>
	    <a href="modify-matiere" class="btn btn-outline-danger">Annuler</a>
	</form>
</div>
<%@ include file="../../../common/Footer.html" %>