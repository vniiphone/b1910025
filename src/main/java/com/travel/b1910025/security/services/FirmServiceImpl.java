package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.travel.b1910025.models.Address;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.models.Firm;
// import com.travel.b1910025.models.Category;
import com.travel.b1910025.models.Tour;
import com.travel.b1910025.payload.request.FirmRequest;
import com.travel.b1910025.payload.request.TourRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.FirmRepository;
// import com.travel.b1910025.repository.CategoryRepository;
import com.travel.b1910025.repository.TourRepository;

@Service
public class FirmServiceImpl implements FirmService {

	@Autowired
	FirmRepository firmRepo;
	@Autowired
	TourRepository tourRepo;
	@Autowired
	AddressSecondRepository addressSecondRepo;

	@Override
	public Firm createFirm(FirmRequest firmRequest) {
		// Product product =
		// productRepo.findById(brandRequest.getProduct_id()).orElseThrow();
		AddressSecond addressSecond = addressSecondRepo.findById(firmRequest.getAddressSecond_id()).orElseThrow();
		Firm firm = new Firm(firmRequest.getName(), addressSecond);
		return firmRepo.save(firm);
	}

	@Override
	public Optional<Firm> updateFirm(Long firmId, FirmRequest firmRequest) {
		// TODO Auto-generated method stub
		Optional<Firm> firm = firmRepo.findById(firmId);
		if (firm.isPresent()) {

			firm.get().setName(firmRequest.getName());

			firmRepo.save(firm.get());
			return firm;
		} else {
			throw new InvalidConfigurationPropertyValueException("firmId", firmId, "firmId");
		}

	}

	private List<Tour> getAllTours() {
		return tourRepo.findAll();
	}

	@Override
	public void deleteFirm(Long firmId) {

		boolean hasExist = firmRepo.existsById(firmId);

		// if (brandRepo.findById(brandId).get().getId().equals(brandId))
		if (hasExist) {
			firmRepo.deleteById(firmId);
		} else
			throw new InvalidConfigurationPropertyValueException("firmId", firmId, "Not Found");

	}

	@Override
	public Firm getASingleFirm(Long firmId) {
		// TODO Auto-generated method stub
		return firmRepo.findById(firmId)
				.orElseThrow(() -> new InvalidConfigurationPropertyValueException("firmId", firmId, "Not found"));
	}

	@Override
	public List<Firm> getAllFirms() {
		// TODO Auto-generated method stub
		return firmRepo.findAll();
	}

}