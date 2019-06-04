package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.InvoiceProcessingDao;
import com.proftaak.invoicesystem.models.Invoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public List<Invoice> getInvoicesForUser(String unparsedVehicleIds) {
        String[] parsedVehicleIds = unparsedVehicleIds.split(",");
        return processingDao.getInvoicesForUser(parsedVehicleIds);
    }

    public Invoice regenerateInvoice(long invoiceId) {return processingDao.regenerateInvoice(invoiceId);}

    public Invoice addInvoice(Invoice invoice){return processingDao.addInvoice(invoice);}

    public Invoice getInvoiceById(long id){return processingDao.getInvoiceById(id);}
}
