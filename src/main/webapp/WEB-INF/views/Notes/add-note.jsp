<%@ page import="java.util.List" %>
<%@ page import="dao.Matiere" %>
<%@ include file="/common/Header.jsp" %>
<div class="Container">
	<form action="/Web003_tester/add-note" method="post">
	<div class="form-row container d-flex flex-column align-items-center justify-content-center ">
		  <div class="form-group col-md-6 mb-3">
		    <label class="form-label">Titre</label>
		    <input type="text" name="titre" class="form-control input-design" required >
		    <% if( request.getAttribute("titreError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("titreError").toString() %></span>		    	
		    <%} %>
    	
		  </div>
		  
		  <div class="form-group col-md-6 mb-3">
		    <label class="form-label">Contenue</label>
		    <textarea rows="5" name="contenue" class="form-control input-design" ></textarea>
		  </div>
		  <div class="form-group col-md-6 mb-3">
		    <label class="form-label">Matiere</label>
		    <select name="matiere" class="form-control input-design">
		    <% 
		    if(request.getAttribute("ListMatieres") != null){
		    	
		    	List<Matiere> matieres = (List<Matiere>) request.getAttribute("ListMatieres");
		    	for(Matiere mat : matieres) {	
		    %>
		    		<option value="<%= mat.getId_matiere() %>"><%= mat.getLabel() %></option>
		    <%	}
		    }
		    %>
		    </select>
		    <% if( request.getAttribute("matiereError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("matiereError").toString() %></span>		    	
		    <%} %>
		  </div>
		  
		  <div class="form-group mb-3">
			  <button type="submit" class="btn btn-outline-success btn-lg">Ajouter</button>
			  <a href="list-note" class="btn btn-outline-danger" >Annuller</a>
		  </div>
	 </div>
	</form>
</div>
<%@ include file="/common/Footer.html" %>