package leCampusServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import leCampusServer.domain.BuildingGeoFence;
import leCampusServer.domain.Footprint;
import leCampusServer.domain.JSONOrderedObject;
import leCampusServer.domain.JSONResponse;
import leCampusServer.domain.Member;
import leCampusServer.domain.User;
import leCampusServer.repository.BuildingGeoFenceRepository;
import leCampusServer.repository.CrimeGeoFenceRepository;
import leCampusServer.repository.FootprintRepository;
import leCampusServer.repository.MemberRepository;
import leCampusServer.repository.PublicEventRepository;
import leCampusServer.repository.UserRepository;
import leCampusServer.service.BuildingGeoFenceService;
import leCampusServer.service.CrimeGeoFenceService;
import leCampusServer.service.FootprintService;
import leCampusServer.service.MemberService;
import leCampusServer.service.PublicEventService;
import leCampusServer.service.UserService;
import leCampusServer.utility.JSONValidator;
import leCampusServer.utility.TextValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Controller
@RequestMapping("/")
public class RESTfulController {
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Autowired BuildingGeoFenceService buildingGeoFenceService;
	@Autowired CrimeGeoFenceService crimeGeoFenceService;
	@Autowired FootprintService footprintService;
	@Autowired PublicEventService publicEventService;
	@Autowired UserService userService;
	
	@Autowired BuildingGeoFenceRepository buildingGeoFenceRepository;
	@Autowired CrimeGeoFenceRepository crimeGeoFenceRepository;
	@Autowired FootprintRepository footprintRepository;
	@Autowired PublicEventRepository publicEventRepository;
	@Autowired UserRepository userRepository;
	
	JSONValidator jsonValidator = new JSONValidator();
	TextValidator textValidator = new TextValidator();
	InputChecker inputChecker = new InputChecker();
	Gson gson = new Gson();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	JSONObject jsonResponse = new JSONObject();

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		System.out.println("IndexPage");
		return "IndexPage";
	}

	

	// Add a GeoFence by Modal for single
	@RequestMapping(value = "/api/geofence/add", method = RequestMethod.GET)
	@ResponseBody
	public Object addMember(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "imageUrl", required = false) String imageUrl,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "center", required = false) String center,
			@RequestParam(value = "website", required = false) String website) {
		
		JSONObject jsonResponseAdd = new JSONObject();
		
		BuildingGeoFence buildingGeoFence = new BuildingGeoFence();
		buildingGeoFence.setBuilding(name);
		buildingGeoFence.setAddress(address);
		buildingGeoFence.setImageURL(imageUrl);
		buildingGeoFence.setCollegeName(category);
		buildingGeoFence.setDirectionPoint(center);
		buildingGeoFence.setWebsiteURL(website);
		
		buildingGeoFenceService.saveBuildingGeoFence(buildingGeoFence);

		System.out.println("SAVED: " + buildingGeoFence.toString());
		System.out.println("SUCCESS: The person input have been saved!");
		jsonResponseAdd.put("result", "true");
		return jsonResponseAdd.toMap();
	}

	// Add GeoFences by Modal for JSON - could be mutiple
	@RequestMapping(value = "/api/geofence/addJSON", method = RequestMethod.POST)
	@ResponseBody
	public Object addMemberJSON(@RequestBody String memberInput) {
		JSONObject jsonResponseAdd = new JSONObject();
		

		return jsonResponseAdd.toMap();
	}

	// Delete a GeoFence
	@RequestMapping(value = "/api/geofence/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteGeoFence(@PathVariable Integer id) {
		JSONObject jsonResponseDelete = new JSONObject();
		Member member = memberService.findById(id);
		
		
		return jsonResponseDelete.toMap();

	}

	
	// Add/update footprint - JSONArray
	@RequestMapping(value="/api/footprint", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse changeFootprintJSON(@RequestBody String footprintJSONString) {
		JSONResponse jsonResponse = new JSONResponse();
		JSONArray jsonFootprintArray;
		//JSONObject jsonFootprint;
		
		if (textValidator.isEmptyString(footprintJSONString)) {
			System.out.println("String entered is blank");
			jsonResponse.setResult("false");
			jsonResponse.setMessage("No footprint in String");
			
		} else if (jsonValidator.isJSONObject(footprintJSONString)) {
			jsonFootprintArray = new JSONArray(footprintJSONString);
			int updated = 0;
			int added = 0;
			
			for (int i=0; i<jsonFootprintArray.length();i++) {
				JSONObject jsonFootprint = new JSONObject(jsonFootprintArray.get(i));
				
				Integer serverId = jsonFootprint.getInt("serverId");
				String title = jsonFootprint.getString("title");
				String description = jsonFootprint.getString("description");
				String nodeList = jsonFootprint.getString("nodeList");
				String createTime = jsonFootprint.getString("createTime");
				String creator = jsonFootprint.getString("creator");
				String length = jsonFootprint.getString("length");
				String type = jsonFootprint.getString("type");
				Long totalTime = jsonFootprint.getLong("totalTime");
				Integer privacy = jsonFootprint.getInt("privacy");
				
				if (footprintService.findByServerId(serverId) != null) {
					Footprint existingFootprint = footprintService.findByServerId(serverId);
					existingFootprint.setTitle(title);
					existingFootprint.setDescription(description);
					existingFootprint.setNodeList(nodeList);
					existingFootprint.setCreateTime(createTime);
					existingFootprint.setCreator(creator);
					existingFootprint.setLength(length);
					existingFootprint.setType(type);
					existingFootprint.setTotalTime(totalTime);
					existingFootprint.setPrivacy(privacy);
					footprintService.saveFootprint(existingFootprint);
					updated++;
					
				} else {
					Footprint newFootprint = new Footprint();
					newFootprint.setTitle(title);
					newFootprint.setDescription(description);
					newFootprint.setNodeList(nodeList);
					newFootprint.setCreateTime(createTime);
					newFootprint.setCreator(creator);
					newFootprint.setLength(length);
					newFootprint.setType(type);
					newFootprint.setTotalTime(totalTime);
					newFootprint.setPrivacy(privacy);
					footprintService.saveFootprint(newFootprint);
					added++;
				}
				
			}	
			jsonResponse.setResult("true");
			jsonResponse.setMessage("Footprints added: "+added+"Updated: "+updated);
				
			
		} else {
			jsonResponse.setResult("false");
			jsonResponse.setMessage("Invalid user JSON");
		}
		return jsonResponse;
	}
	
	// Add/update user to SQL by RESTful API
	@RequestMapping(value="/api/user", method = RequestMethod.POST)
	@ResponseBody
	public Object changeUserJSON(@RequestBody String userJSONString) {
		JSONObject jsonResponse = new JSONObject();
		JSONObject jsonUser;
		
		if (textValidator.isEmptyString(userJSONString)) {
			System.out.println("String entered is blank");
			jsonResponse.put("result", "false");
			jsonResponse.put("message", "User entered is blank");
			
			
		} else if (jsonValidator.isJSONObject(userJSONString)) {
			jsonUser = new JSONObject(userJSONString);
			Integer localId = jsonUser.getInt("localId");
			String studentNumber = jsonUser.getString("studentNumber");
			String realName = jsonUser.getString("realName");
			String uolEmail = jsonUser.getString("uolEmail");
			
			if (userService.findByStudentNumber(studentNumber) != null) {
				
				User existingUser = userService.findByStudentNumber(studentNumber);
				existingUser.setLocalId(localId);
				existingUser.setStudentNumber(studentNumber);
				existingUser.setRealname(realName);
				existingUser.setUolEmail(uolEmail);
				
				userService.saveUser(existingUser);				
				jsonResponse.put("result", "true");
				jsonResponse.put("message", "User info updated");
				
			} else {
				User newUser = new User();
				newUser.setLocalId(localId);
				newUser.setStudentNumber(studentNumber);
				newUser.setRealname(realName);
				newUser.setUolEmail(uolEmail);
				
				userService.saveUser(newUser);				
				jsonResponse.put("result", "true");
				jsonResponse.put("message", "New user registered");
				
			}
			
		} else {
			jsonResponse.put("result", "false");
			jsonResponse.put("message", "Invalid user JSON");
		}
		return jsonResponse;
		
	}
		
	// Add/update eventList to SQL by RESTful API
	@RequestMapping(value="/api/event", method = RequestMethod.POST)
	@ResponseBody
	public void changeEventJSON(@RequestBody String eventJSONString) {
		JSONObject jsonResponse = new JSONObject();
		JSONArray jsonEventList;
		JSONObject jsonEvent;
		
		if (textValidator.isEmptyString(eventJSONString)) {
			System.out.println("String entered is blank");
			jsonResponse.put("result", "false");
			jsonResponse.put("message", "Event entered is blank");
			
		} else if (jsonValidator.isJSONArray(eventJSONString)) {
			jsonEventList = new JSONArray(eventJSONString);
			
			for (int i=0; i<jsonEventList.length(); i++) {
				jsonEvent = jsonEventList.getJSONObject(i);
				
				Integer localId = jsonEvent.getInt("localId");
				String holdBy = jsonEvent.getString("holdBy");
				String eventType = jsonEvent.getString("eventType");
				String eventTitle = jsonEvent.getString("eventTitle");
				String eventDesc = jsonEvent.getString("eventDesc");
				String eventCode = jsonEvent.getString("eventCode");
				String location = jsonEvent.getString("location");
				String address = jsonEvent.getString("address");
				String postCode = jsonEvent.getString("postCode");
				String city = jsonEvent.getString("city");
				String region = jsonEvent.getString("region");
				String country = jsonEvent.getString("country");
				String lat = jsonEvent.getString("lat");
				String lon = jsonEvent.getString("lon");
				String startTime = jsonEvent.getString("startTime");
				String endTime = jsonEvent.getString("endTime");
				String duration = jsonEvent.getString("duration");
				String weekDay = jsonEvent.getString("weekDay");
				String isAllDay = jsonEvent.getString("isAllDay");
				String isDisabled = jsonEvent.getString("isDisabled");
				String host = jsonEvent.getString("host");
				String email = jsonEvent.getString("email");
				String detailUrl = jsonEvent.getString("detailUrl");
				String imageUrl = jsonEvent.getString("imageUrl");
				String offers = jsonEvent.getString("offers");
				
//					if (publicEventService.findRepeatEvent(eventTitle, location, address, startTime) != null) {
//						// Do nothing
//						
//					} else {
//						// Create new
//						
//					}
			}
		}
		
	}

	// Convert Iterable of all person in repository to List
	public static <E> Collection<E> makeCollection(Iterable<E> iterable) {
		Collection<E> list = new ArrayList<E>();
		for (E item : iterable) {
			list.add(item);
		}
		return list;
	}

}
