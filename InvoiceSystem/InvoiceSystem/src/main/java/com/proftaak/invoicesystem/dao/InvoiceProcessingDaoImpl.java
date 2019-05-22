package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.SquareRegion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class InvoiceProcessingDaoImpl implements InvoiceProcessingDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean markAsPaid(int invoiceId) {
        try {
            Invoice i = em.createNamedQuery("Invoice.GetById", Invoice.class).setParameter("invoiceId", invoiceId).getSingleResult();
            i.setPaid(true);

            em.merge(i);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Invoice> getInvoicesForUser(String unparsedVehicleIds) {
        String[] vehicleIds = unparsedVehicleIds.split(",");
        List<Invoice> invoices = new ArrayList<>();

        for (String s : vehicleIds) {
            try {
                invoices.add(em.createNamedQuery("Invoice.GetByVehicleId", Invoice.class).setParameter("vehicleId", s).getSingleResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return invoices;
    }
}
