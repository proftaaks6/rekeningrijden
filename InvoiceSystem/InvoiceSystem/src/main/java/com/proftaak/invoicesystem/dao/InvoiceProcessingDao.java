package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.VehicleProcessingState;

import java.util.List;

public interface InvoiceProcessingDao {
    boolean markAsPaid(int invoiceId);
    List<Invoice> getInvoicesForUser(String unparsedVehicleIds);
}
