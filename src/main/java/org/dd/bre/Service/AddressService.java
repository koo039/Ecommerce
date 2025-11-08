package org.dd.bre.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.AddressRequest;
import org.dd.bre.Dto.AddressResponse;
import org.dd.bre.Exception.AddressNotFoundException;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Repo.AddressRepo;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.model.Address;
import org.dd.bre.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepo addressRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    public List<AddressResponse> getAddresses(UserDetails userDetails){

        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        List<Address> addresses = addressRepo.findAllByUserId(user.getId());

        if(addresses.isEmpty()){
            return new ArrayList<>();
        }

        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddress(UserDetails userDetails,Long addressId){

        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Address address = addressRepo.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("This address does not belong to the user");
        }

        addressRepo.delete(address);
    }

    @Transactional
    public AddressResponse addAddress(UserDetails userDetails,AddressRequest addressRequest){

        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Address address = new  Address();
        address.setUser(user);
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setLabel(addressRequest.getLabel());

        addressRepo.save(address);
        return modelMapper.map(address, AddressResponse.class);
    }

    @Transactional
    public AddressResponse updateAddress(UserDetails userDetails, Long addressId, AddressRequest addressRequest){

        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Address address = addressRepo.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("This address does not belong to the user");
        }
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setLabel(addressRequest.getLabel());

        addressRepo.save(address);
        return modelMapper.map(address, AddressResponse.class);
    }
}
