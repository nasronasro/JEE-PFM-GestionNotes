<%@ page import="java.util.List" %>
<%@ page import="dao.Proffesseur" %>
<%@ include file="../../../common/Header.jsp" %>
<div class="Container">

	<form action="/Web003_tester/modify-matiere" method="post" class="container w-50 p-4 rounded shadow-sm">
		<div class="mb-3">
	        <label for="id" class="form-label">Id:</label>
	        <input type="text" id="id" name="id_matiere" class="form-control input-design" value="<%= request.getAttribute("id_matiere").toString() %>" required>
	         <% if( request.getAttribute("idError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("idError").toString() %></span>		    	
			<%} %>
	    </div>
	    <div class="mb-3">
	        <label for="label" class="form-label">Labelle:</label>
	        <input type="text" id="label" name="label" class="form-control input-design" value="<%= request.getAttribute("label").toString() %>" required>
	        <% if( request.getAttribute("labelError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("labelError").toString() %></span>		    	
			<%} %>
	    </div>
	    <div class="mb-3">
	        <label for="importance" class="form-label">Importance:</label>
	        <select name="importance" class="form-control input-design" value="<%= request.getAttribute("ImpInt").toString() %>">    	
		    	<option value="3" <%= "3".equals(request.getAttribute("ImpInt").toString()) ? "selected" : ""%>>Low</option>
		    	<option value="2" <%= "2".equals(request.getAttribute("ImpInt").toString()) ? "selected" : ""%>>Medium</option>
		    	<option value="1" <%= "1".equals(request.getAttribute("ImpInt").toString()) ? "selected" : ""%>>High</option>
	        </select>
	         <% if( request.getAttribute("importanceError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("importanceError").toString() %></span>		    	
			<%} %>
	    </div>
	    <div class="mb-3">
	        <label for="idProffesseur" class="form-label" >Professor:</label>
	        <select name="proffesseur" class="form-control input-design">
			    <% 
			        List<Proffesseur> listProffesseurs = (List<Proffesseur>) request.getAttribute("ListProfs");
			        String selectedProfesseurId = request.getAttribute("ProfesseurId").toString(); // Get selected ProfesseurId
			        for(Proffesseur prof : listProffesseurs) {
			            // Get the id_proffesseur of the current Professeur
			            String profId = prof.getId_proffesseur().toString();
			    %>
			        <option value="<%= profId %>" 
		            <%= profId.equals(selectedProfesseurId) ? "selected" : "" %> >
		            <%= prof.getNom() + " " + prof.getPrenom() %>
	        		</option>
	   			 <% } %>
			</select>
			<% if( request.getAttribute("proffesseurError") != null){%>
        	<span class="text-danger"><%= request.getAttribute("proffesseurError").toString() %></span>		    	
			<%} %>
	    </div>
	    <button type="submit" class="btn btn-outline-success w-25">Modifier matiere</button>
	    <a href="modify-matiere" class="btn btn-outline-danger">Annuler</a>
	</form>
	
 
</div>
<%@ include file="../../../common/Footer.html" %>