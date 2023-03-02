package com.travel.b1910025.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.b1910025.models.Address;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.models.City;
import com.travel.b1910025.models.District;
import com.travel.b1910025.models.Firm;
import com.travel.b1910025.models.Hotel;
import com.travel.b1910025.models.Place;
import com.travel.b1910025.models.Restau;
import com.travel.b1910025.models.User;
import com.travel.b1910025.models.Ward;
import com.travel.b1910025.payload.request.AddressRequest;
import com.travel.b1910025.payload.request.AddressSecondRequest;
//import com.travel.b1910025.payload.request.AddressRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.repository.AddressRepository;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.CityRepository;
import com.travel.b1910025.repository.DistrictRepository;
import com.travel.b1910025.repository.FirmRepository;
import com.travel.b1910025.repository.HotelRepository;
import com.travel.b1910025.repository.PlaceRepository;
import com.travel.b1910025.repository.RestauRepository;
import com.travel.b1910025.repository.UserRepository;
import com.travel.b1910025.repository.WardRepository;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/addressSecond")
public class AddressSecondController {
    @Autowired
    AddressSecondRepository address2Repo;
    @Autowired
    CityRepository cityRepo;
    @Autowired
    DistrictRepository districtRepo;
    @Autowired
    WardRepository wardRepo;

    @GetMapping("/city")
    public ResponseEntity<List<City>> getCities() {
        try {
            List<City> cities = cityRepo.findAll();
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/district")
    public ResponseEntity<List<District>> getDistricts(@RequestParam Optional<Long> city_id) {
        try {
            List<District> districts;
            if (city_id.isPresent()) {
                districts = districtRepo.findAllByCity_Id(city_id.get());
            } else
                districts = districtRepo.findAll();

            return new ResponseEntity<>(districts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ward")
    public ResponseEntity<List<Ward>> getWards(@RequestParam Optional<Long> district_id) {
        try {
            List<Ward> wards;
            if (district_id.isPresent()) {
                wards = wardRepo.findAllByDistrict_Id(district_id.get());
            } else
                wards = wardRepo.findAll();

            return new ResponseEntity<>(wards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<List<Address>> getAddresses(@PathVariable("id") Long
    // user_id) {
    // try {
    // List<AddressS> addresses = address2Repo.findByUser_Id(user_id);
    // return new ResponseEntity<>(addresses, HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PostMapping(value = "/create", consumes = { "*/*" })
    public ResponseEntity<AddressSecond> createAddressSecond(@Valid @RequestBody AddressSecondRequest address2) {
        try {
            Ward ward = wardRepo.findById(address2.getWard_id()).get();

            AddressSecond a = new AddressSecond(address2.getAddressSe(), address2.getPhone(), ward);
            return new ResponseEntity<>(address2Repo.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tên cũ: createAddress chưa sửa trong ReactJs
    @PutMapping(value = "/{id}", consumes = { "*/*" })
    public ResponseEntity<AddressSecond> updateAddressSecond(
            @Valid @RequestBody AddressSecondRequest addressSecondRequest,
            @PathVariable("id") Long id) {
        try {
            Optional<AddressSecond> a = address2Repo.findById(id);

            if (a.isPresent()) {
                Ward w = wardRepo.findById(addressSecondRequest.getWard_id()).get();

                a.get().setWard(w);
                a.get().setPhone(addressSecondRequest.getPhone());
                a.get().setAddress2(addressSecondRequest.getAddressSe());

                return new ResponseEntity<>(address2Repo.save(a.get()), HttpStatus.OK);

            } else {
                throw new InvalidConfigurationPropertyValueException("address2Id", id, "Not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", consumes = { "*/*" })
    public ResponseEntity<String> deleteAddressSecond(@PathVariable("id") Long id) {
        try {
            if (address2Repo.findById(id).get().getId().equals(id)) {
                address2Repo.deleteById(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else
                throw new InvalidConfigurationPropertyValueException("id", id, "Not found");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
