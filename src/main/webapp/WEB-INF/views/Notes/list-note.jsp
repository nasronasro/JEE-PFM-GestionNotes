
<%@ page import="java.util.List" %>
<%@ page import="dao.Note" %>
<%@ page import="dao.Matiere" %>

<%@ include file="/common/Header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/card-style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sidebar-style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/input-styling.css">
		<%
			List<Note> notes = null;
			List<Matiere> matieres = null;
			if(request.getAttribute("NoteList") != null)
				notes = (List<Note>) request.getAttribute("NoteList");
			if(request.getAttribute("MatiereList") != null)
				matieres = (List<Matiere>) request.getAttribute("MatiereList");
		%>
		<div class="wrapper">
    	<!-- Sidebar -->
	    <div class="sidebar">
	      <h4 class="mb-4">List des categories</h4>
	      <form method="post" action="list-note">	
			<div class="input-group">
			   <input type="text" id="searchBox-matiere" name="searchBox-matiere" class="input-design form-control" placeholder="Chercher produits..." >
			   <button type=submit class="btn btn-outline-primary">Chercher</button>
			</div>
	      </form>
	      <a href="list-note" class="nav-link">Afficher Tous</a>
	      <% for(Matiere matiere : matieres){ %>
	      	<a href="list-note?id_mat=<%= matiere.getId_matiere() %>" class="nav-link"><%= matiere.getLabel() %></a>
	      <%} %>
	    </div>
	    
		<div class="Container content">
		
		<div class="row">
		<% if( request.getAttribute("idError") != null){%>
        		<span class="text-danger"><%= request.getAttribute("idError").toString() %></span>		    	
		<%} %>
		<% if(notes != null){ %>
			<% for(Note note : notes){ %>
			
			<div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-3">
                <div class="card">
                  <div class="card-header"><%= note.getTitre() %></div>
                  <div class="card-body">
                    <h5 class="card-title"><%=  note.getId_note() %></h5>
                    <div class="card-text-container">
                  		<p class="card-text"><%= note.getContenue() %></p>
                  	</div>
                   	<div class="d-flex justify-content-between mt-2">
                        <a class="btn btn-outline-warning btn-sm flex-grow-1 me-2" href="/Web003_tester/modify-note?id_note=<%= note.getId_note() %>">Edit/Show More</a>
                        <a class="btn btn-outline-danger btn-sm flex-grow-1" href="/Web003_tester/delete-note?id_note=<%= note.getId_note() %>">Del</a>
                    </div>
                  </div>
                </div>
              </div>
			<% } %>
			
			<div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-3">
                <div class="card ">
                  <div class="card-header">Add New Card</div>
                  <div class="card-body ">                                    
                	<form class="card-body-add w-100 h-100 d-flex justify-content-center align-items-center">
						<button class="btn btn-outline-success btn-lg" formaction="add-note">Ajouter note</button>
					</form>	
                  </div>
                </div>
              </div>
		</div>
		<% }else{ %>
			<h1>List est vide mintenant.</h1>
		<%}%>
		</div>
	</div>
	 
<%@ include file="/common/Footer.html" %>