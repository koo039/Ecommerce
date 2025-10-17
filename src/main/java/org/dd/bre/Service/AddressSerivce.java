package org.dd.bre.Service;

import jakarta.validation.Valid;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressSerivce {
    private final AddressRepo addressRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    public List<AddressResponse> getAdresses(Long userId){

        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));

        List<Address> addresses = addressRepo.findAllByUserId(user.getId());

        if(addresses.isEmpty()){
            return new ArrayList<>();
        }

        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteAddress(Long userId,Long addressId){

        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));

        Address address = addressRepo.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("This address does not belong to the user");
        }

        addressRepo.delete(address);
    }
    public AddressResponse addAddress(Long userId,AddressRequest addressRequest){

        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
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

    public AddressResponse updateAddress(Long userId,Long addressId,AddressRequest addressRequest){

        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));

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
