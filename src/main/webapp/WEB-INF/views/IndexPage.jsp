<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <meta name="description" content="A index page for Le Campus Admin Portal">
    <meta name="author" content="Yifan Tang (yt120)">
    
	<title>Le Campus Admin Portal</title>
	
	<!-- JQuery scripts -->
	<script src="../scripts/jquery.331.min.js"></script>
	<script src="../scripts/jquery.validate.min.js"></script>
	
	<!-- Bootstrap scripts -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Bootstrap css -->
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	
	<!-- Google Maps scripts -->
	<script src="../scripts/googleMaps.js" type="text/javascript"></script>

	<!-- Custom scripts -->
	<script src="../scripts/interactiveMap.js" type="text/javascript"></script>
	<script src="../scripts/IndexPage.js" type="text/javascript"></script>
	
	<!-- Custom css -->
	<link href="resources/css/index.css" rel="stylesheet" >
	
	<link rel="icon" type="image/x-icon" href="/resources/favicon.ico">
		
</head>

<body>

	<!-- [Section 1]: Nav-bar -->
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
		
			<div class="navbar-header">
			
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				  <span class="sr-only">Toggle navigation</span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="#">Admin Portal</a>
				
			</div>
		  
			<div id="navbar" class="navbar-collapse collapse">
			
				<ul class="nav navbar-nav">
				
					<li class="active" id="homeTab"><a href="#">Home</a></li>
					<!-- li id="userTab" ><a href="#"></a></li -->
					<li id="mapTab"><a href="#">Map</a></li>
					<li class="dropdown">
					
						<a href="#" id="moreOperationTab" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">More Options <span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li><a href="#">Settings</a></li>
							<li><a href="#">About</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">Manage Data</li>
							<li><a href="#">Import from JSON</a></li>
							<li><a href="#">Clear all</a></li>
						</ul>
					  
					</li>
					
				</ul>
				
			</div>
		  
		</div>
	</nav>

	<!-- [Section 2]: Main Part below the Nav-bar -->
	<div class="container">
	
		<div class="jumbotron" id="intro-content">
	
			<h1>Welcome to Admin Portal</h1>
			<p>Le Campus Admin Tool is an online tool for managing the data in application background and syncing them with application's local SQLite database.</p>
			<p>This Portal Website consists of two parts - RESTful API Service (Back end) and Web Interface (Front end).</p>
			<p>
			  <a class="btn btn-lg btn-primary" id="showGoogleMap" role="button">Check it out &raquo;</a>
			</p>
	
		</div>
	
		<div class="jumbotron" id="googleMapSection">
			<div id="operation-buttons">
				<p>
					<button class="btn btn-sm btn-info" id="refreshMapBtn" data-toggle="modal" data-target="#refreshMap"><span class="glyphicon glyphicon-refresh"></span> Refresh Map</button>
					<button class="btn btn-sm btn-primary" id="addGeoFenceBtn"data-toggle="modal" data-target="#addGeoFenceModal"><span class="glyphicon glyphicon-plus"></span> Add GeoFence</button>
					<button class="btn btn-sm btn-primary" id="editGeoFenceBtn"data-toggle="modal" data-target="#editGeoFenceModal"><span class="glyphicon glyphicon-edit"></span> Edit GeoFence</button>
					<button class="btn btn-sm btn-danger" id="deleteGeoFenceBtn"data-toggle="modal" data-target="#deleteGeoFenceModal"><span class="glyphicon glyphicon-trash"></span> Delete GeoFence</button>
					<button class="btn btn-sm btn-primary" id="viewRequestBtn" data-toggle="modal" data-target="#viewRequest"><span class="glyphicon glyphicon-circle-arrow-up"></span> View Request</button>
					<button class="btn btn-sm btn-primary" id="viewFootprintBtn" data-toggle="modal" data-target="#viewFootprint"><span class="glyphicon glyphicon-circle-arrow-down"></span> View Footprint</button>
				</p>
	
			</div>
			
			<div id="googleMapDiv">	
				<div id="googleMap" style="border: solid 1px black; width:100%; height:600px"></div>
				
				<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBrGwHcTQnP_PC_NXstZIYhR7VQFQ1sjSE&callback=initMap&language=en-UK" async defer></script>
				
				<div hidden>
					<input id="geofenceName" ></input>
					<input id="geofenceAddress" ></input>
					<input id="geofenceImageUrl" ></input>
					<input id="geofenceCategory" ></input>
					<input id="geofenceCenter" ></input>
					<input id="geofenceWebsite" ></input>
				</div>
			</div>
		</div>
	
		

	</div>
	
	<!-- [Section 3]: Modal windows -->
	<!-- Modal 01 - Add New GeoFence -->
	<div class="modal fade" id="addGeoFenceModal" tabindex="-1" role="dialog" aria-labelledby="addGeoFenceLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="addGeoFenceLabel">Add New GeoFence</h4>
	                <div hidden><input id="modeToken"></input></div>
	            </div>
	            <form class="form-group" id="form-addSingleNew">
		            <div class="modal-body" id="singleAddBody">
		            	<div id="addSingleGeoFence">
			           		<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-asterisk"></i> GeoFence Name</span>
		        				<input id="geofence-name"	name="geofence-name" class="form-control" placeholder="New GeoFence">
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i> Address</span>
		        				<input id="geofence-address"		name="geofence-address" class="form-control" placeholder="University Road">
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i> Image URL</span>
		        				<input id="geofence-image-url"	name="geofence-image-url" class="form-control" placeholder="Image URL" >
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-heart-empty"></i> Category</span>
		        				<input id="geofence-category"		name="geofence-category" class="form-control" placeholder="Department of Informatics" >
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i> Coordinate</span>
		        				<input id="geofence-center"	name="geofence-center" class="form-control" placeholder="52.62179, -1.123867" >
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i> Website</span>
		        				<input id="geofence-website"	name="geofence-website" class="form-control" placeholder="Website URL" >
	        				</div>
						
							<div id="addFenceSingle"> </div>
							<br>
							<p><a href="#" id="addMultipleGeoFenceLink">Add Multiple/Polygon GeoFences (JSON)</a></p>
							<div class="alert alert-info fade in out" id="singleAddInProgressAlert" role="alert"><span id="singleAddInProgressInfo"></span></div>
							<div class="alert alert-danger fade in out" id="singleAddErrorAlert" role="alert"><span id="singleAddFailedInfo"></span></div>
							<div class="alert alert-success fade in out" id="singleAddCompleteAlert" role="alert"><span id="singleAddCompleteInfo"></span></div>
						</div>
					</div>	
					
					<div class="modal-footer" id="singleAddFooter">
		                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		                <button type="submit" class="btn btn-primary" id="submit-newSingleGeoFence">Submit</button>
		            </div>	
				</form>
				<form class="form-group" id="form-addMultiNew">		
					<div class="modal-body" id="multiAddBody">	
						<div id="addMultipleGeoFence">
    						<label for="mutipleAddArea">Add Multiple GeoFences (JSON)</label>
							<textarea class="form-control" id="mutipleAddArea" name="mutipleAddArea" rows="10"></textarea>
							<div id="addErrorInfoMulti" role="alert"> </div>
							<br>
							<p><a href="#" id="addSingleGeoFenceLink">Add Single GeoFence</a></p>
							<div class="alert alert-info fade in out" id="multiAddInProgressAlert" role="alert"><span id="multiAddInProgressInfo"></span></div>
							<div class="alert alert-danger fade in out" id="multiAddErrorAlert" role="alert"><span id="multiAddFailedInfo"></span></div>
							<div class="alert alert-success fade in out" id="multiAddCompleteAlert" role="alert"><span id="multiAddCompleteInfo"></span></div>
						</div>
		            </div>

		            <div class="modal-footer" id="multiAddFooter">
		                <button type="button" class="btn btn-default" id="cancelAddMulti" data-dismiss="modal">Cancel</button>
		                <button type="submit" class="btn btn-primary" id="submit-newMultiGeoFence">Submit</button>
		            </div>
	            </form>
	            
	        </div>
	    </div>
	</div>
	
	<!-- Modal 02 - Edit Current GeoFence -->
	<div class="modal fade" id="editGeoFenceModal" tabindex="-1" role="dialog" aria-labelledby="editGeoFenceLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" id="cancel-editedGeoFenceIcon" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="editGeoFenceLabel">Edit GeoFence</h4>
	            </div>
	            
	            <form class="form-group" id="form-edit">
		            <div class="modal-body">
	            		<div id="editSinglePerson">
	            			<div hidden><input id="editToken"></input></div>
			           		<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-asterisk"></i> GeoFence Name</span>
		        				<input id="edit-geofence-name"	name="edit-geofence-name" class="form-control" placeholder="New GeoFence">
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i> Address</span>
		        				<input id="edit-geofence-address"		name="edit-geofence-address" class="form-control" placeholder="University Road">
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i> Image URL</span>
		        				<input id="edit-geofence-image-url"	name="edit-geofence-image-url" class="form-control" placeholder="Image URL" >
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-heart-empty"></i> Category</span>
		        				<input id="edit-geofence-category"		name="edit-geofence-category" class="form-control" placeholder="Department of Informatics" >
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i> Coordinate</span>
		        				<input id="edit-geofence-center"	name="edit-geofence-center" class="form-control" placeholder="52.62179, -1.123867" >
	        				</div>
	        				<div class="input-group">
		      					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i> Website</span>
		        				<input id="edit-geofence-website"	name="edit-geofence-website" class="form-control" placeholder="Website URL" >
	        				</div>
	        				<br>
	        				<div class="alert alert-info fade in out" id="editInProgressAlert" role="alert"><span id="editInProgressInfo"></span></div>
							<div class="alert alert-danger fade in out" id="editErrorAlert" role="alert"><span id="editFailedInfo"></span></div>
							<div class="alert alert-success fade in out" id="editCompleteAlert" role="alert"><span id="editCompleteInfo"></span></div>
							
						</div>
		            </div>
		            
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" id="cancel-editedGeoFence" data-dismiss="modal">Cancel</button>
		                <button type="submit" class="btn btn-primary" id="submit-editedGeoFence">Submit</button>
		            </div>
	            </form>
	            
	        </div>
	    </div>
	</div>
	
	<!-- Modal 03 - Delete Current GeoFence -->
	<div class="modal fade" id="deleteGeoFenceModal" tabindex="-1" role="dialog" aria-labelledby="deleteGeoFenceLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="deleteGeoFenceLabel">Delete Person</h4>
	            </div>
	            
	            <div class="modal-body">
	            	<p>Are you sure you want to delete this person?</p>
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	                <button type="button" class="btn btn-danger" id="confirm-deleteGeoFence">Delete</button>
	            </div>
	            
	        </div>
	    </div>
	</div>
	
	<!-- Modal 04 - Tips for editing GeoFence -->
	<div class="modal fade" id="editGeoFenceTips" tabindex="-1" role="dialog" aria-labelledby="editGeoFenceTipsLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="editGeoFenceTipsLabel">Edit GeoFence Tips</h4>
	            </div>
	            
	            <div class="modal-body">
	            	<p>Please double-click a person to edit!</p>
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
	            </div>
	            
	        </div>
	    </div>
	</div>
	
	<!-- Modal 05 - Loading window -->
	<div class="modal fade" id="loadingWindow" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="loadingWindowLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        	<div class="modal-body">
		        	<div>
		        		<p><img id="loaderImg" src="resources/loader.gif" />   Processing your request, please wait...</p>
					</div>
				</div>
	        </div>
	    </div>
	</div>


</body>
</html>