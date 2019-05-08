/**
 * 
 */

$(document).ready(function () {
	
	/**
	 * Add single building geofence
	 */
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
				
				var data = 
					'{"properties":{"building": "'+name+
					'", "address": "'+address+
					'", "photo": "'+imageUrl+
					'", "collegeCorporate": "'+category+
					'", "directionsButton": "'+center+
					'", "urlOne": "'+website+'"}}';
				console.log(data);
				
				var jsonData = JSON.parse(data);
				console.log("Data sent: "+jsonData);
				
				$.ajax({
					type: "POST",
					url: "/LeCampusServer/Building",
					contentType: "application/json",
					dataType: 'json',
					data: '['+JSON.stringify(jsonData)+']',
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
	
	/**
	 * Add multiple building Geofences
	 */
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
						url: "/LeCampusServer/Building",
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
								$("#mutipleAddArea").val('');
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
	
	/**
	 * Edit Building GeoFence
	 */
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
				
				var data = 
					'{ "name": "'+name+
					'", "address": "'+address+
					'", "imageUrl": "'+imageUrl+
					'", "category": "'+category+
					'", "center": "'+center+
					'", "website": "'+website+
					'", "actualKey": "'+actualKey+'"}';
				
				console.log(data);
				
				var jsonData = JSON.parse(data);
				console.log("Data sent: "+jsonData);
				
				$.ajax({
					type: "POST",
					url: "/LeCampusServer/editGeoFenceJSON",
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
	
	/**
	 * Import crime data
	 */
	$("#submit-newMultiCrimeData").click(function() {
		
		$("#multiCrimeDataAddInProgressAlert").fadeOut();
		$("#multiCrimeDataAddErrorAlert").fadeOut();
		$("#multiCrimeDataAddCompleteAlert").fadeOut();
		
		$("#form-addMultiNewCrimeData").validate({
			errorClass:'errors3',
			rules: {
				"mutipleAddCrimeDataArea":{
					required:true,
					maxlength: 3000000
				}
				
			},
			messages:{
				"mutipleAddCrimeDataArea":{
					required: "This field is required!",
					maxlength: "Max data length is 3,000,000 characters"
				}
			},
			submitHandler:function(form){
				
				$("#multiCrimeDataAddInProgressInfo").html("<strong>Please wait...</strong> Your request is being processing.");
				$("#multiCrimeDataAddInProgressAlert").fadeIn();
				
				var data = $("#mutipleAddCrimeDataArea").val();
				console.log("Data input: "+data);
				
				try {
					var jsonData = JSON.parse(data);
					console.log("Data sent: "+jsonData);				
					$.ajax({
						type: "POST",
						url: "/LeCampusServer/Crime",
						contentType: "application/json",
						dataType: 'json',
						data: JSON.stringify(jsonData),
						success:function(response){
							console.log(response.result);
							if(response.result=="false"){
								console.log(response.message);
								$("#multiCrimeDataAddInProgressAlert").toggle();
								$("#multiCrimeDataAddFailedInfo").html("<strong>Failed to import Crime Data!</strong> "+response.message);
								$("#multiCrimeDataAddErrorAlert").fadeIn(1000);
								
							} else if (response.result=="true") {
								$("#multiCrimeDataAddInProgressAlert").toggle();
								$("#multiCrimeDataAddCompleteInfo").html("<strong>Done!</strong> "+response.message);
								$("#multiCrimeDataAddCompleteAlert").fadeIn();
								completeChange(response.result);
								$("#mutipleAddCrimeDataArea").val('');
								$("#addCrimeDataModal").modal('hide');
								$("#multiCrimeDataAddCompleteAlert").toggle();
							}					
						}			
					});					
				} catch (exception) {
					$("#multiCrimeDataAddInProgressAlert").toggle();
					$("#multiCrimeDataAddFailedInfo").html("<strong>JSON Input "+exception.name+":</strong> "+exception.message);
					$("#multiCrimeDataAddErrorAlert").fadeIn(1000);
				}
				
			}
				
		});
		
	});
		
	/**
	 * Import event data
	 */
	$("#submit-newMultiPublicEvent").click(function() {
		
		$("#multiPublicEventAddInProgressAlert").fadeOut();
		$("#multiPublicEventAddErrorAlert").fadeOut();
		$("#multiPublicEventAddCompleteAlert").fadeOut();
		
		$("#form-addMultiNewPublicEvent").validate({
			errorClass:'errors4',
			rules: {
				"mutipleAddPublicEventArea":{
					required:true
				}
				
			},
			messages:{
				"mutipleAddPublicEventArea":{
					required: "This field is required!"
				}
			},
			submitHandler:function(form){
				
				$("#multiPublicEventAddInProgressInfo").html("<strong>Please wait...</strong> Your request is being processing.");
				$("#multiPublicEventAddInProgressAlert").fadeIn();
				
				var data = $("#mutipleAddPublicEventArea").val();
				console.log("Data input: "+data);
				
				try {
					var jsonData = JSON.parse(data);
					console.log("Data sent: "+jsonData);
					
					$.ajax({
						type: "POST",
						url: "/LeCampusServer/PublicEvent",
						contentType: "application/json",
						dataType: 'json',
						data: JSON.stringify(jsonData),
						success:function(response){
							console.log(response.result);
							if(response.result=="false"){
								console.log(response.message);
								$("#multiPublicEventAddInProgressAlert").toggle();
								$("#multiPublicEventAddFailedInfo").html("<strong>Failed to import Public Event!</strong> "+response.message);
								$("#multiPublicEventAddErrorAlert").fadeIn(1000);
								
							} else if (response.result=="true") {
								$("#multiPublicEventAddInProgressAlert").toggle();
								$("#multiPublicEventAddCompleteInfo").html("<strong>Done!</strong> "+response.message);
								$("#multiPublicEventAddCompleteAlert").fadeIn();
								completeChange(response.result);
								$("#mutipleAddPublicEventArea").val('');
								$("#addPublicEventModal").modal('hide');
								$("#multiPublicEventAddCompleteAlert").toggle();
							}	
						}
					});				
				} catch (exception) {
					$("#multiPublicEventAddInProgressAlert").toggle();
					$("#multiPublicEventAddFailedInfo").html("<strong>JSON Input "+exception.name+":</strong> "+exception.message);
					$("#multiPublicEventAddErrorAlert").fadeIn(1000);
				}
			}
		});
	});
});