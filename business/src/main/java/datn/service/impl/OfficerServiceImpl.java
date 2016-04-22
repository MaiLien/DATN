package datn.service.impl;

import datn.dao.entity.Officer;
import datn.dao.repository.OfficerRepository;
import datn.interfaces.request.OfficerRequest;
import datn.interfaces.response.OfficerResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.util.DateFormatUtil;
import datn.service.IOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OfficerServiceImpl implements IOfficerService{

    @Autowired
    OfficerRepository officerRepository;

    public RestApiResponse<ArrayList<OfficerResponse>> getOfficers() {
        return null;
    }

    public RestApiResponse<OfficerResponse> getOfficer(String id) {
        Officer officerEntity = officerRepository.findOne(id);
        OfficerResponse officerResponse = convertOfficerEntityToOfficerResponse(officerEntity);
        RestApiResponse<OfficerResponse> officerResponseRestApiResponse = new RestApiResponse<OfficerResponse>(officerResponse);

        return officerResponseRestApiResponse;
    }

    public RestApiResponse<OfficerResponse> addOfficer(OfficerRequest officerRequest) {
        return null;
    }

    public RestApiResponse<OfficerResponse> deleteOfficer(OfficerRequest officerRequest) {
        return null;
    }

    public RestApiResponse<OfficerResponse> updateOfficer(OfficerRequest officerRequest) {
        return null;
    }

    private OfficerResponse convertOfficerEntityToOfficerResponse(Officer officerEntity){
        OfficerResponse officerResponse = new OfficerResponse();
        officerResponse.setId(officerEntity.getId());
        officerResponse.setBirthday(DateFormatUtil.convertDateToString(officerEntity.getBirthday()));
        officerResponse.setDescription(officerEntity.getDescription());
        officerResponse.setEmail(officerEntity.getEmail());
        officerResponse.setGender(officerEntity.getGender().getValue());
        officerResponse.setName(officerEntity.getName());
        officerResponse.setPhoneNumber(officerEntity.getPhoneNumber());
        officerResponse.setStatus(officerEntity.getStatus());
        officerResponse.setTypeOfUser(officerEntity.getTypeOfUser().getValue());

        return officerResponse;
    }

}
