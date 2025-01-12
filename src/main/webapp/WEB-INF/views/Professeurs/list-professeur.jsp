<%@ page import="java.util.List" %>
<%@ page import="dao.Proffesseur" %>
<%@ include file="/common/Header.jsp" %>
	<% 
			List<Proffesseur> profs = null;
			if(request.getAttribute("profList") != null){
				profs = (List<Proffesseur>)request.getAttribute("profList");
			}
		%>
	<div class="Container">
		<% if(profs != null){ %>
		<form>
			<button class="btn btn-outline-success" formaction="add-professeur">Ajouter professeur</button>
		</form>
		<table class="table table-c text-white">
			<thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Nom</th>
			      <th scope="col">Prenom</th>
			      <th scope="col">Email</th>
			      <th scope="col">Telephone</th>
			      <th scope="col">Actions</th>
			    </tr>
 			 </thead>
 			 <tbody>
			<% for(Proffesseur prof : profs){ %>
				<tr>
					<td><%= prof.getId_proffesseur() %></td>
					<td><%= prof.getNom() %></td>
					<td><%= prof.getPrenom() %></td>
					<td><%= prof.getEmail() %></td>
					<td><%= prof.getTelephone() %></td>
					<td>
						<form>
							<a class="btn btn-outline-warning btn-sm" href="/Web003_tester/modify-professeur?id_professeur=<%= prof.getId_proffesseur() %>">Modifier</a>
							<a class="btn btn-outline-danger btn-sm" href="/Web003_tester/delete-professeur?id_professeur=<%= prof.getId_proffesseur() %>">Supprimer</a>
						</form>
					</td>
				</tr>	
			<% } %>
 			 </tbody>
		</table>
		<% }else{ %>
			<h1>List est vide mintenant.</h1>
			<%			
		}
			%>
			
	</div>
<%@ include file="/common/Footer.html" %>
</body>
</html>