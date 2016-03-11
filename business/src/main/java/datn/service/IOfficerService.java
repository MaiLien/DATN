package datn.service;

import datn.interfaces.request.OfficerRequest;
import datn.interfaces.response.OfficerResponse;
import datn.interfaces.response.RestApiResponse;

import java.util.ArrayList;

public interface IOfficerService {

    public RestApiResponse<ArrayList<OfficerResponse>> getOfficers();

    public RestApiResponse<OfficerResponse> getOfficer(String id);

    public  RestApiResponse<OfficerResponse> addOfficer(OfficerRequest officerRequest);

    public RestApiResponse<OfficerResponse> deleteOfficer(OfficerRequest officerRequest);

    public RestApiResponse<OfficerResponse> updateOfficer(OfficerRequest officerRequest);

}
