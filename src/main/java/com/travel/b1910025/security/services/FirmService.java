package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

// import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.travel.b1910025.models.Firm;
import com.travel.b1910025.payload.request.FirmRequest;

@Component
public interface FirmService {

    Firm createFirm(FirmRequest firmRequest);

    Optional<Firm> updateFirm(Long firmId, FirmRequest firmRequest);

    void deleteFirm(Long firmdId);

    Firm getASingleFirm(Long firmdId);

    List<Firm> getAllFirms();

    
    

   
    
}