/**
 * Scripts for Index page
 */

$(document).ready(function () {
	
	
	$("#showGoogleMap").click(function(){
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
		$("#homeTab").removeClass("active");
		$("#userTab").removeClass("active");
		$("#mapTab").addClass("active");
		$("#intro-content").hide();
		$("#googleMapSection").show();
		
//		$("#loadingWindow").modal('show');
//		console.log("Request for GeoFence");
		
		$.ajax({
			type:"GET",
			url:"/LeCampus/BuildingGeoFence",
			success:function(buildingGeoFenceResponse) {
				var buildingGeoFenceData = buildingGeoFenceResponse.buildingGeoFenceList;
				
				console.log("Received Data Type: "+typeof buildingGeoFenceData);
				console.log(buildingGeoFenceResponse.buildingGeoFenceList);
				initGeoFence(buildingGeoFenceData);
				$("#loadingWindow").modal('hide');
				
			}
				
		});
		
	});
	
	
	$("#viewRequestBtn").click(function(){
		$("#loadingWindow").modal('show');
		
		$("#geofenceName").val("");
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
		$.ajax({
			type:"GET",
			url:"/LeCampus/EmergencyRequest",
			success:function(emergencyRequestResponse) {
				
				console.log("Received Ancestor tree");
				var emergencyRequestData = emergencyRequestResponse.emergencyRequestList;
				
				console.log("Type: "+typeof emergencyRequestData);
				console.log(emergencyRequestResponse.emergencyRequestList);
				initEmergencyRequest(emergencyRequestData);
				$("#loadingWindow").modal('hide');
				
			}
				
		});
		
	});
	
	
	$("#viewFootprintBtn").click(function(){
		
		$("#geofenceName").val("");
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
		$.ajax({
			type:"GET",
			url:"/LeCampus/Footprint",
			//data:"key="+selectedFootprint,
			success:function(footprintResponse) {
				
				console.log("Received Descendant tree");
				var footprintData = footprintResponse.footprintList;
				
				console.log("Type: "+typeof footprintData);
				console.log(footprintResponse.footprintList);
				initFootprint(footprintData);
				$("#loadingWindow").modal('hide');
				
			}
				
		});
		
	});
	
	
	$("#refreshMapBtn").click(function(){
		
		//$("#loadingWindow").modal('show');
		$("#geofenceName").val("");
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
		console.log("Request for Refresh Map");
		
		initMap();
		
		$.ajax({
			type:"GET",
			url:"/LeCampus/BuildingGeoFence",
			success:function(buildingGeoFenceResponse) {
				var buildingGeoFenceData = buildingGeoFenceResponse.buildingGeoFenceList;
				
				console.log("Received Data Type: "+typeof buildingGeoFenceData);
				console.log(buildingGeoFenceResponse.buildingGeoFenceList);
				initGeoFence(buildingGeoFenceData);
				$("#loadingWindow").modal('hide');
				
			}
				
		});
		
		
	});
	
	$("#addMultipleGeoFenceLink").click(function(){
		$("#modeToken").val('2');
		$("#geofence-name").val('');
		$("#geofence-address").val('');
		$("#geofence-image-url").val('');
		$("#geofence-category").val('');
		$("#geofence-center").val('');
		$("#geofence-website").val('');
		
		$("#singleAddBody").hide();
		$("#singleAddFooter").hide();
		$("#multiAddBody").show();
		$("#multiAddFooter").show();
		
		$("#singleAddErrorAlert").fadeOut();
		$("#singleAddCompleteAlert").fadeOut();
		$("#singleAddInProgressAlert").fadeOut();
		
	});
	
	$("#addSingleGeoFenceLink").click(function(){
		$("#modeToken").val('1');
		$("#mutipleAddArea").val('');
		
		$("#multiAddBody").hide();
		$("#multiAddFooter").hide();
		$("#singleAddBody").show();
		$("#singleAddFooter").show();
		
		$("#multiAddErrorAlert").fadeOut();
		$("#multiAddCompleteAlert").fadeOut();
		$("#multiAddInProgressAlert").fadeOut();
		
	});
	
	$("#addGeoFenceBtn").click(function(){
		$("#modeToken").val('1');
		$("#multiAddInProgressAlert").fadeOut();
		$("#multiAddErrorAlert").fadeOut();
		$("#multiAddCompleteAlert").fadeOut();
		$("#singleAddInProgressAlert").fadeOut();
		$("#singleAddErrorAlert").fadeOut();
		$("#singleAddCompleteAlert").fadeOut();
		
	});
	
	$("#submit-newSingleGeoFence").click(function(){
		$("#multiAddInProgressAlert").fadeOut();
		$("#multiAddErrorAlert").fadeOut();
		$("#multiAddCompleteAlert").fadeOut();
		$("#singleAddInProgressAlert").fadeOut();
		$("#singleAddErrorAlert").fadeOut();
		$("#singleAddCompleteAlert").fadeOut();
		
		$("#geofenceName").val("");
		
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
//		if ($("#modeToken").val()==1) {
			console.log("Input Mode ["+$("#modeToken").val()+"]");
			
			$("#form-addSingleNew").validate({
				errorClass:'errors1',
				rules: {
					"geofence-name":{
						required:true
					},
					"geofence-address":{
						
					},
					"geofence-image-url":{
						
					},
					"geofence-category":{
						
					},
					"geofence-center":{
						required:true
					},
					"geofence-website":{
						
					}
					
				},
				messages:{
					"geofence-name":{
						required: "This field is required!"
					},
					"geofence-address":{
						
					},
					"geofence-image-url":{
						
					},
					"geofence-category":{
						
					},
					"geofence-center":{
						required: "This field is required!"
					},
					"geofence-website":{
					}
				},
				submitHandler:function(form){
					
					$("#singleAddInProgressInfo").html("<strong>Please wait...</strong> Your request is being processing.");
					$("#singleAddInProgressAlert").fadeIn();
					
					var name = $("#geofence-name").val();
					var address = $("#geofence-address").val();
					
					var imageUrl = $("#geofence-image-url").val();
					if (imageUrl === undefined || imageUrl == "" ) imageUrl=null;
					var category = $("#geofence-category").val();
					if (category === undefined || category == "" ) category=null;
					var center = $("#geofence-center").val();
					if (center === undefined || center == "" ) center=null;
					var website = $("#geofence-website").val();
					if (website === undefined || website == "" ) website=null;
					
					var data = '{"name": "'+name+'", "address": "'+address+'", "imageUrl": "'+imageUrl+'", "category": "'+category+'", "center": "'center+'", "website": "'+website+'"}';
					console.log(data);
					
					var jsonData = JSON.parse(data);
					console.log("Data sent: "+jsonData);
					
					
					$.ajax({
						type: "POST",
						url: "/api/geofence/addJSON",
						contentType: "application/json",
						dataType: 'json',
						data: JSON.stringify(jsonData),
						success:function(response){
							console.log(response.result);
							if(response.result=="false"){
								console.log(response.message);
								$("#singleAddInProgressAlert").toggle();
								$("#singleAddFailedInfo").html("<strong>Failed to add new GeoFence!</strong> "+response.message);
								$("#singleAddErrorAlert").fadeIn(1000);
								
							} else if (response.result=="true") {
								$("#singleAddInProgressAlert").toggle();
								$("#singleAddCompleteInfo").html("<strong>Done!</strong> "+response.message);
								$("#singleAddCompleteAlert").fadeIn();
								completeChange(response.result);
								$("#addGeoFenceModal").modal('hide');
								
							}
							
						}
					
					});
				}
					
			});
			
			
		});
		
		$("#cancelAddMulti").click(function(){
			completeChange("true");
		});
		
		// Multi Add
		$("#submit-newMultiGeoFence").click(function() {
			
			$("#multiAddInProgressAlert").fadeOut();
			$("#multiAddErrorAlert").fadeOut();
			$("#multiAddCompleteAlert").fadeOut();
			$("#singleAddInProgressAlert").fadeOut();
			$("#singleAddErrorAlert").fadeOut();
			$("#singleAddCompleteAlert").fadeOut();
			
			console.log("Input Mode ["+$("#modeToken").val()+"]");
			
			$("#geofenceName").val("");
			
			
			$("#deleteGeoFenceBtn").hide();
			$("#editGeoFenceBtn").hide();
			
			$("#form-addMultiNew").validate({
				errorClass:'errors2',
				rules: {
					"mutipleAddArea":{
						required:true
					}
					
				},
				messages:{
					"mutipleAddArea":{
						required: "This field is required!"
					}
				},
				submitHandler:function(form){
					
					$("#multiAddInProgressInfo").html("<strong>Please wait...</strong> Your request is being processing.");
					$("#multiAddInProgressAlert").fadeIn();
					
					var data = $("#mutipleAddArea").val();
					console.log("Data input: "+data);
					
					try {
						var jsonData = JSON.parse(data);
						console.log("Data sent: "+jsonData);
						
						$.ajax({
							type: "POST",
							url: "/api/geofence/addJSON",
							contentType: "application/json",
							dataType: 'json',
							data: JSON.stringify(jsonData),
							success:function(response){
								console.log(response.result);
								if(response.result=="false"){
									console.log(response.message);
									$("#multiAddInProgressAlert").toggle();
									$("#multiAddFailedInfo").html("<strong>Failed to add new GeoFence!</strong> "+response.message);
									$("#multiAddErrorAlert").fadeIn(1000);
									
								} else if (response.result=="true") {
									$("#multiAddInProgressAlert").toggle();
									$("#multiAddCompleteInfo").html("<strong>Done!</strong> "+response.message);
									$("#multiAddCompleteAlert").fadeIn();
									completeChange(response.result);
									$("#addGeoFenceModal").modal('hide');
									
								}
								
							}
						
						});
						
					} catch (exception) {
						$("#multiAddInProgressAlert").toggle();
						$("#multiAddFailedInfo").html("<strong>JSON Input "+exception.name+":</strong> "+exception.message);
						$("#multiAddErrorAlert").fadeIn(1000);

					}
					
				}
					
			});
		
		
	});
	
	$("#cancel-editedGeoFence").click(function(){
		$("#editErrorAlert").fadeOut();
		$("#editCompleteAlert").fadeOut();
		$("#editInProgressAlert").fadeOut();
		
		$("#geofenceName").val("");
		$("#geofenceAddress").val('');
		$("#geofenceImageUrl").val('');
		$("#geofenceCategory").val('');
		$("#geofenceCenter").val('');
		$("#geofenceWebsite").val('');
		
	});
	
	$("#cancel-editedGeoFenceIcon").click(function(){
		$("#editErrorAlert").fadeOut();
		$("#editCompleteAlert").fadeOut();
		$("#editInProgressAlert").fadeOut();
		
		$("#geofenceName").val("");
		$("#geofenceAddress").val('');
		$("#geofenceImageUrl").val('');
		$("#geofenceCategory").val('');
		$("#geofenceCenter").val('');
		$("#geofenceWebsite").val('');
	});
	
	$("#editGeoFenceBtn").click(function(){
		$("#edit-geofence-name").val($("#geofenceName").val());
		$("#edit-geofence-address").val($("#geofenceAddress").val());
		
		if ($("#geofenceImageUrl").val() !== undefined && $("#geofenceImageUrl").val() != null)
		$("#edit-geofence-image-url").val($("#geofenceImageUrl").val());
		
		if ($("#geofenceCategory").val() !== undefined && $("#geofenceCategory").val() != null)
		$("#edit-geofence-category").val($("#geofenceCategory").val());
		
		if ($("#geofenceCenter").val() !== undefined && $("#geofenceCenter").val() != null)
		$("#edit-geofence-center").val($("#geofenceCenter").val());
		
		if ($("#geofenceWebsite").val() !== undefined && $("#geofenceWebsite").val() != null)
		$("#edit-geofence-website").val($("#geofenceWebsite").val());
		
		$("#editToken").val($("#geofenceName").val());
		
	});
	
	$("#submit-editedGeoFence").click(function(){
		$("#editErrorAlert").fadeOut();
		$("#editCompleteAlert").fadeOut();
		$("#editInProgressAlert").fadeOut();

		$("#geofenceName").val("");
		
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
		$("#form-edit").validate({
			errorClass:'errors',
			rules: {
				"edit-geofence-name":{
					required:true
					
				},
				"edit-geofence-address":{
					
				},
				"edit-geofence-image-url":{
					
				},
				"edit-geofence-category":{
					
				},
				"edit-geofence-center":{
					required:true
				},
				"edit-geofence-website":{
					
				}
				
			},
			messages:{
				"edit-geofence-name":{
					required: "This field is required!"
					
				},
				"edit-geofence-address":{
					
				},
				"edit-geofence-image-url":{
					
				},
				"edit-geofence-category":{
					
				},
				"edit-geofence-center":{
					required: "This field is required!"
				},
				"edit-geofence-website":{
					
				}
			},
			submitHandler:function(form){
				$("#editInProgressInfo").html("");
				$("#editInProgressInfo").html("<strong>Please wait...</strong> Your request is being processing.");
				$("#editInProgressAlert").fadeIn();
				
				var name = $("#edit-geofence-name").val();
				var address = $("#edit-geofence-address").val();
				
				var imageUrl = $("#edit-geofence-image-url").val();
				if (imageUrl === undefined || imageUrl == "" ) imageUrl=null;
				var category = $("#edit-geofence-category").val();
				if (category === undefined || category == "" ) category=null;
				var center = $("#edit-geofence-center").val();
				if (center === undefined || center == "" ) center=null;
				var website = $("#edit-geofence-website").val();
				if (website === undefined || website == "" ) website=null;
				var actualKey = $("#editToken").val();
				
				var data = '{ "name": "'+name+'", "address": "'+address+'", "imageUrl": "'+imageUrl+'", "category": "'+category+'", "center": "'+center+'", "website": "'+website+'", "actualKey": "'+actualKey+'"}';
				console.log(data);
				
				var jsonData = JSON.parse(data);
				console.log("Data sent: "+jsonData);
				
				$.ajax({
					type: "POST",
					url: "/LeCampus/editGeoFenceJSON",
					contentType: "application/json",
					dataType: 'json',
					data: JSON.stringify(jsonData),
					success:function(response){
						console.log(response.result);
						if(response.result=="false"){
							console.log(response.message);
							$("#editInProgressAlert").toggle();
							$("#editFailedInfo").html("");
							$("#editFailedInfo").html("<strong>Update Geofence failed!</strong> "+response.message);
							$("#editErrorAlert").fadeIn(1000);		
							
						} else if (response.result=="true") {
							$("#editInProgressAlert").toggle();
							$("#editCompleteInfo").html("<strong>Done!</strong> "+response.message);
							$("#editCompleteAlert").fadeIn();
							completeChange(response.result);
							$("#editGeoFenceModal").modal('hide');
							
						}
						
					}
				
				});
					
			}

		});
			
	});
	
	$("#confirm-deleteGeoFence").click(function(){
		
		//$("#loadingWindow").modal('show');
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		
		
		var selectedGeoFence = $("#geofenceName").val();
		console.log("Request for delete geofence ["+selectedGeoFence+"]");
		
		$("#geofenceName").val("");
		
		
		$.ajax({
			type:"GET",
			url:"/api/geofence/delete/"+selectedGeoFence,
			success:function(familyTreeResponse) {
				console.log("Refereshing map...");
				completeChange(familyTreeResponse.result)

			}
				
		});
		
		
		$("#deleteGeoFenceModal").modal('hide');
		
	});
	
	
	$("#homeTab").click(function(){
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		$("#homeTab").addClass("active");
		$("#userTab").removeClass("active");
		$("#mapTab").removeClass("active");

		$("#intro-content").show();
		$("#googleMapSection").hide();
	});
	
//	$("#userTab").click(function(){
//		$("#homeTab").removeClass("active");
//		$("#userTab").addClass("active");
//		$("#mapTab").removeClass("active");
//		
//		$("#intro-content").hide();
//		$("#googleMapSection").hide();
//	});
	
	$("#mapTab").click(function(){
		
		
		$("#deleteGeoFenceBtn").hide();
		$("#editGeoFenceBtn").hide();
		completeChange("true");
		$("#homeTab").removeClass("active");
		$("#userTab").removeClass("active");
		$("#mapTab").addClass("active");
		
		$("#intro-content").hide();
		$("#googleMapSection").show();
	});
	
	$("#moreOperationTab").click(function(){
		$("#homeTab").removeClass("active");
		$("#userTab").removeClass("active");
		$("#mapTab").removeClass("active");

	});
	
	
	
});

function completeChange(result){
	
	$("#geofenceName").val("");
	$("#geofenceAddress").val('');
	$("#geofenceImageUrl").val('');
	$("#geofenceCategory").val('');
	$("#geofenceCenter").val('');
	$("#geofenceWebsite").val('');
	
	
	
	$("#deleteGeoFenceBtn").hide();
	$("#editGeoFenceBtn").hide();
	
	$("#editErrorAlert").fadeOut();
	$("#editCompleteAlert").fadeOut();
	$("#editInProgressAlert").fadeOut();
	$("#multiAddInProgressAlert").fadeOut();
	$("#multiAddErrorAlert").fadeOut();
	$("#multiAddCompleteAlert").fadeOut();
	$("#singleAddInProgressAlert").fadeOut();
	$("#singleAddErrorAlert").fadeOut();
	$("#singleAddCompleteAlert").fadeOut();
	
	if (result=="true") {
		//$("#loadingWindow").modal('show');
		$.ajax({
			type:"GET",
			url:"/LeCampus/BuildingGeoFence",
			success:function(buildingGeoFenceResponse) {
				var buildingGeoFenceData = buildingGeoFenceResponse.buildingGeoFenceList;
				
				console.log("Received Data Type: "+typeof buildingGeoFenceData);
				console.log(buildingGeoFenceResponse.buildingGeoFenceList);
				initFamilyTree(buildingGeoFenceData);
				$("#loadingWindow").modal('hide');
				console.log("Map Refereshed");
				
			}
				
		});
	}
	
	
	
}
