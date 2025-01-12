<%@ page import="java.util.List" %>
<%@ page import="dao.Matiere" %>
<%@ include file="/common/Header.jsp" %>
<div class="Container">
	<form action="/Web003_tester/modify-note" method="post">
	<div class="form-row container d-flex flex-column align-items-center justify-content-center ">
		<h1>Modifier une  note</h1>
		<div class="form-group col-md-6 mb-3">
		    <input type="text" name="id_note" class="form-control input-design" value="<%= request.getAttribute("id_note").toString() %>" hidden required>
		    <% if( request.getAttribute("idError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("idError").toString() %></span>		    	
		    <%} %>
		    <% if( request.getAttribute("idErrorSize") != null){%>
        		<span class="text-danger"><%= request.getAttribute("idErrorSize").toString() %></span>		    	
		    <%} %>
		  </div>
		  <div class="form-group col-md-6 mb-3">
		    <label class="form-label">Titre</label>
		    <input type="text" name="title" class="form-control input-design" value="<%= request.getAttribute("title").toString() %>" required >
		    <% if( request.getAttribute("titreError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("titreError").toString() %></span>		    	
		    <%} %>
		  </div>
		  <div class="form-group col-md-6 mb-3">
		    <label class="form-label">Contenue</label>
		    <textarea rows="5" name="content" class="form-control input-design"><%= request.getAttribute("content").toString() %></textarea>
		  </div>
		  
		  <div class="form-group col-md-6 mb-3"">
		    <label class="form-label">Matiere</label>
		    <select name="matiere" class="form-control input-design">
		    	 <% 
		    	List<Matiere> matieres =(List<Matiere>) request.getAttribute("ListMatieres");
		    	 String selectedMatiereId = request.getAttribute("id_matiere").toString();
		    	for(Matiere mat : matieres) {	
			    %>
			    	<option value="<%= mat.getId_matiere() %>"
			    	<%= mat.getId_matiere().equals(selectedMatiereId) ? "selected" : "" %> ><%= mat.getLabel() %></option>
			    <%}%>
		    </select>
		    <% if( request.getAttribute("matiereError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("matiereError").toString() %></span>		    	
		    <%} %>
		  </div>
		  
		  <div class="form-group mb-3">
			  <button type="submit" class="btn btn-outline-success btn-lg">Modifier</button>
			  <a href="list-note" class="btn btn-outline-danger" >Annuller</a>
		  </div>
	 </div>
	</form>
</div>
<%@ include file="/common/Footer.html" %>