package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.persistence.EntityManager;
import java.util.List;

public interface InvoiceProcessingDao {
    boolean markAsPaid(int invoiceId);
    List<Invoice> getInvoicesForUser(String[] chassisNumbers);
    Invoice addInvoice(Invoice invoice);
    Invoice regenerateInvoice(long invoiceId);
    Invoice getInvoiceById(long id);
    void setEm(EntityManager em);
    EntityManager getEm();
}
