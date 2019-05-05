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
			url:"/LeCampusServer/BuildingGeoFence",
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
			url:"/LeCampusServer/EmergencyRequest",
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
			url:"/LeCampusServer/Footprint",
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
			url:"/LeCampusServer/BuildingGeoFence",
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
		
	$("#cancelAddMulti").click(function(){
		completeChange("true");
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
			url:"/LeCampusServer/BuildingGeoFence",
			success:function(buildingGeoFenceResponse) {
				var buildingGeoFenceData = buildingGeoFenceResponse.buildingGeoFenceList;
				
				console.log("Received Data Type: "+typeof buildingGeoFenceData);
				console.log(buildingGeoFenceResponse.buildingGeoFenceList);
				initMap(buildingGeoFenceData);
				$("#loadingWindow").modal('hide');
				console.log("Map Refereshed");
				
			}
				
		});
	}
	
	
	
}
