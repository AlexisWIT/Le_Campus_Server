/**
 * 
 */
function confirmSelect(geoFenceData) {
	
	$("#geofenceName").val(geoFenceData.name);
	$("#geofenceAddress").val(geoFenceData.address);
	$("#geofenceImageUrl").val(geoFenceData.imageUrl);
	$("#geofenceCategory").val(geoFenceData.category);
	$("#geofenceCenter").val(geoFenceData.center);
	$("#geofenceWebsite").val(geoFenceData.website);
	
	$("#editGeoFenceBtn").show();
	$("#deleteGeoFenceBtn").show();
	$("#viewRequestBtn").show();
	$("#viewFootprintBtn").show();
	
}

function editGeoFenceByClickObject(geoFenceData) {
	
	$("#editErrorAlert").fadeOut();
	$("#editCompleteAlert").fadeOut();
	$("#editInProgressAlert").fadeOut();
	
	// Initialise edit inputs
	$("#editToken").val('');
	$("#edit-geofence-image-url").val('');
	$("#edit-geofence-category").val('');
	$("#edit-geofence-center").val('');
	$("#edit-geofence-website").val('');
	
	console.log("Start editing GeoFence ["+geoFenceData.name+"]");
	$("#edit-geofence-name").val(geoFenceData.name);
	$("#edit-geofence-address").val(geoFenceData.address);
	if (geoFenceData.imageUrl !== undefined && geoFenceData.imageUrl != null)
	$("#edit-geofence-image-url").val(geoFenceData.imageUrl);
	if (geoFenceData.category !== undefined && geoFenceData.category != null)
	$("#edit-geofence-category").val(geoFenceData.category);
	if (geoFenceData.center !== undefined && geoFenceData.center != null)
	$("#edit-geofence-center").val(geoFenceData.center);
	if (geoFenceData.website !== undefined && geoFenceData.website != null)
	$("#edit-geofence-website").val(geoFenceData.website);
	//$("#editToken").val(geoFenceData.id);
	
	$("#editGeoFenceModal").modal("show");
}

function cancelSelect() {
	$("#geofenceName").val('');
	$("#geofenceAddress").val('');
	$("#geofenceImageUrl").val('');
	$("#geofenceCategory").val('');
	$("#geofenceCenter").val('');
	$("#geofenceWebsite").val('');
	
	$("#viewFootprintBtn").hide();
	$("#viewRequestBtn").hide();
	$("#deleteGeoFenceBtn").hide();
	$("#editGeoFenceBtn").hide();
}
