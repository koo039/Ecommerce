package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.AddressRequest;
import org.dd.bre.Dto.PurchaseReq;
import org.dd.bre.Repo.ShippingAddressRepo;
import org.dd.bre.model.ShippingAddress;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {
    private final ShippingAddressRepo shippingAddressRepo;

    protected ShippingAddress getShippingAddress(PurchaseReq purchaseReq) {
        AddressRequest address = purchaseReq.getAddress();

        if(address == null)
            throw new IllegalArgumentException("Address is required.");

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

    private static ShippingAddress getShippingAddress(AddressRequest address) {
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
