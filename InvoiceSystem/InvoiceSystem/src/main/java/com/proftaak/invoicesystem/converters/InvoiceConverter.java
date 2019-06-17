package com.proftaak.invoicesystem.converters;

import com.proftaak.invoicesystem.shared.Invoice;

import java.util.stream.Collectors;

public class InvoiceConverter {
    public static Invoice fromEntity(com.proftaak.invoicesystem.models.Invoice entity){
        Invoice invoice = new Invoice();
        invoice.setDate(entity.getDate());
        invoice.setId(entity.getId());
        invoice.setPaid(entity.isPaid());
        invoice.setTotalDistance(entity.getTotalDistance());
        invoice.setTotalPrice(entity.getTotalPrice());
        invoice.setVehicleChassis(entity.getVehicleChassis());
        invoice.setPriceRowList(entity.getPriceRowList().stream().map(PriceRowConverter::fromEntity).collect(Collectors.toList()));
        return invoice;
    }
}
