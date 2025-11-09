package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.AddressRequest;
import org.dd.bre.Dto.PurchaseReq;
import org.dd.bre.Repo.AddressRepo;
import org.dd.bre.Repo.ShippingAddressRepo;
import org.dd.bre.model.Address;
import org.dd.bre.model.ShippingAddress;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {
    private final ShippingAddressRepo shippingAddressRepo;
    private final AddressRepo addressRepo;

    protected ShippingAddress getShippingAddress(PurchaseReq purchaseReq) {
        Address address = addressRepo.findById(purchaseReq.getAddressId()).orElseThrow(()->new RuntimeException("Address not found"));

        Optional<ShippingAddress> existing = shippingAddressRepo.findMatchingAddress(
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry(),
                address.getLabel(),
                address.getPhoneNumber()
        );
        if (existing.isPresent()) {
            return existing.get();
        }
        ShippingAddress shippingAddress = getShippingAddress(address);

        return shippingAddressRepo.save(shippingAddress);
    }

    private static ShippingAddress getShippingAddress(Address address) {
        ShippingAddress shippingAddress = new ShippingAddress();

        shippingAddress.setCity(address.getCity());
        shippingAddress.setCountry(address.getCountry());
        shippingAddress.setPostalCode(address.getPostalCode());
        shippingAddress.setAddressLine1(address.getAddressLine1());
        shippingAddress.setAddressLine2(address.getAddressLine2());
        shippingAddress.setLabel(address.getLabel());
        shippingAddress.setState(address.getState());
        shippingAddress.setPhoneNumber(address.getPhoneNumber());
        return shippingAddress;
    }
}
