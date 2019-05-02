package leCampusServer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import leCampusServer.domain.BuildingGeoFence;
import leCampusServer.domain.EmergencyRequest;
import leCampusServer.domain.Footprint;
import leCampusServer.domain.JSONResponse;
import leCampusServer.domain.JSONResponse;
import leCampusServer.domain.Member;
import leCampusServer.repository.BuildingGeoFenceRepository;
import leCampusServer.repository.CrimeGeoFenceRepository;
import leCampusServer.repository.EmergencyRequestRepository;
import leCampusServer.repository.FootprintRepository;
import leCampusServer.repository.MemberRepository;
import leCampusServer.repository.PublicEventRepository;
import leCampusServer.repository.UserRepository;
import leCampusServer.service.BuildingGeoFenceService;
import leCampusServer.service.CrimeGeoFenceService;
import leCampusServer.service.EmergencyRequestService;
import leCampusServer.service.FootprintService;
import leCampusServer.service.MemberService;
import leCampusServer.service.PublicEventService;
import leCampusServer.service.UserService;

@Controller
public class InterfaceController {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Autowired BuildingGeoFenceService buildingGeoFenceService;
	@Autowired CrimeGeoFenceService crimeGeoFenceService;
	@Autowired FootprintService footprintService;
	@Autowired PublicEventService publicEventService;
	@Autowired UserService userService;
	@Autowired EmergencyRequestService emergencyRequestService;
	
	@Autowired BuildingGeoFenceRepository buildingGeoFenceRepository;
	@Autowired CrimeGeoFenceRepository crimeGeoFenceRepository;
	@Autowired FootprintRepository footprintRepository;
	@Autowired PublicEventRepository publicEventRepository;
	@Autowired UserRepository userRepository;
	@Autowired EmergencyRequestRepository emergencyRequestRepository;
	
	
	RESTfulController restController;
	InputChecker inputChecker = new InputChecker();
	JSONObject jsonResponse = new JSONObject();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/LeCampus/BuildingGeoFence", method=RequestMethod.GET)
	public @ResponseBody JSONResponse showBuildingGeoFence() {	
		JSONResponse buildingGeoFenceResponse = new JSONResponse();
		List<BuildingGeoFence> buildingGeoFenceList = new ArrayList<>();
		
		System.out.println("Retrieving building geofence, please wait...");
		buildingGeoFenceList = (List<BuildingGeoFence>) buildingGeoFenceService.findAllBuildingGeoFences();
		
		buildingGeoFenceResponse.setBuildingGeoFenceList(buildingGeoFenceList);
		return buildingGeoFenceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/LeCampus/EmergencyRequest", method=RequestMethod.GET)
	@ResponseBody
	public JSONResponse showEmergencyRequest() {
		JSONResponse emergencyRequestResponse = new JSONResponse();
		List<EmergencyRequest> emergencyRequestList = new ArrayList<>();
		
		emergencyRequestList = (List<EmergencyRequest>) emergencyRequestService.findAllEmergencyRequests();
		
		emergencyRequestResponse.setEmergencyRequestList(emergencyRequestList);
		return emergencyRequestResponse;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/LeCampus/Footprint", method=RequestMethod.GET)
	@ResponseBody
	public JSONResponse showFootprint() {
		JSONResponse footprintResponse = new JSONResponse();
		List<Footprint> footprintList = new ArrayList<>();
		
		footprintList = (List<Footprint>) footprintService.findAllFootprints();
		
		footprintResponse.setFootprintList(footprintList);
		return footprintResponse;
		
	}
	
	
	@RequestMapping(value="/LeCampus/editGeoFenceJSON", method=RequestMethod.POST)
	@ResponseBody
	public Object editGeoFence(@RequestBody String geoFence) throws ParseException {
		System.out.println("Received request for editing GeoFence :"+geoFence);
		final Gson gson = new Gson();
		JSONParser jsonParser = new JSONParser();
		JSONResponse jsonEditResponse = new JSONResponse();
		
		if (inputChecker.isValidJSON(geoFence, gson)) {
			
		} else {
			jsonEditResponse.setResult("false");
			jsonEditResponse.setMessage("Invalid input format!");
			return jsonEditResponse;
		}
		
		JSONObject jsonObject = (JSONObject) jsonParser.parse(geoFence);
		
		Integer serverId = Integer.valueOf((String) jsonObject.get("serverId"));
		
		//TODO finish if possible
		
		jsonEditResponse.setResult("true");
		jsonEditResponse.setMessage("GeoFence info has been updated.");
		return jsonEditResponse;
		
	}

}
