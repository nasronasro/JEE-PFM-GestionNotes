
<%@ page import="java.util.List" %>
<%@ page import="dao.Matiere" %>

<%@ include file="../../../common/Header.jsp" %>

		<%  List<Matiere> matieres = (List<Matiere>)request.getAttribute("MatiereList");%>
	<div class="Container">
	<form>
		<button class="btn btn-outline-success btn-lg" formaction="add-matiere">Ajouter Matiere</button>
	</form>
		<% if(matieres != null || !matieres.isEmpty()){ %>
		<table class="table table-c text-white">
			<thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Libelle</th>
			      <th scope="col">Importance</th>
			      <th scope="col">Proffesseur</th>
			      <th scope="col">Actions</th>
			    </tr>
 			 </thead>
 			 <tbody>
			<% for(Matiere mate : matieres){ %>
				<tr>
					<td><%= mate.getId_matiere()%></td>
					<td><%= mate.getLabel() %></td>
					<td><%= mate.getImportance() %></td>
					<td><%= mate.getProffesseur().getNom() + " " + mate.getProffesseur().getPrenom() %></td>
					<td>
						<form>
							<a class="btn btn-outline-warning btn-sm" href="/Web003_tester/modify-matiere?id_matiere=<%= mate.getId_matiere() %>">Modifier</a>
							<a class="btn btn-outline-danger btn-sm" href="/Web003_tester/delete-matiere?id_matiere=<%= mate.getId_matiere() %>">Supprimer</a>
						</form>
					</td>
				</tr>	
			<% } %>
 			 </tbody>
		</table>
		
		<% }else{ %>
			<h1>List est vide mintenant.</h1>
			<%}%>
			
	</div>
<%@ include file="../../../common/Footer.html" %>