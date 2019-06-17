package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.InvoiceProcessingDao;
import com.proftaak.invoicesystem.models.Invoice;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Default
public class InvoiceProcessingService {

    @Inject
    private InvoiceProcessingDao processingDao;

    public boolean markAsPaid(int invoiceId){
        return processingDao.markAsPaid(invoiceId);
    }

    public List<Invoice> getInvoicesForUser(long userId, String unparsedVehicleIds) {
        return processingDao.getInvoicesForUser(userId, convertUnparsedVehicleIds(unparsedVehicleIds));
    }

    public boolean markForGeneration(String unparsedVehicleIds) {
        return processingDao.markForGeneration(convertUnparsedVehicleIds(unparsedVehicleIds));
    }

    private String[] convertUnparsedVehicleIds(String unparsedVehicleIds) {
        String[] parsedVehicleIds = unparsedVehicleIds.split(",");
        String[] vehicleIds = new String[parsedVehicleIds.length];
        for (int i = 0; i < parsedVehicleIds.length; i++) {
            String id = parsedVehicleIds[i];
            vehicleIds[i] = id;
        }

        return vehicleIds;
    }

    public Invoice regenerateInvoice(long invoiceId) {return processingDao.regenerateInvoice(invoiceId);}

    public Invoice addInvoice(Invoice invoice){return processingDao.addInvoice(invoice);}

    public void addInvoices(List<Invoice> invoices)
    {
        for (Invoice invoice : invoices)
        {
            processingDao.addInvoice(invoice);
        }
    }

    public Invoice getInvoiceById(long id){return processingDao.getInvoiceById(id);}
}
