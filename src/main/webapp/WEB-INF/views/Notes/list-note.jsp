
<%@ page import="java.util.List" %>
<%@ page import="dao.Note" %>

<%@ include file="/common/Header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/card-style.css">
		<% 
			List<Note> notes = null;
			if(request.getAttribute("NoteList") != null){
				notes = (List<Note>)request.getAttribute("NoteList");
			}
		%>
	<div class="Container">
		<form class="mb-3">
			<button class="btn btn-outline-success btn" formaction="add-note">Ajouter note</button>
		</form>
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
		</div>
		<% }else{ %>
			<h1>List est vide mintenant.</h1>
		<%			
		}
		%>
			
	</div>
<%@ include file="/common/Footer.html" %>