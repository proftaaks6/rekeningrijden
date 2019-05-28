package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.generator.InvoiceGenerator;
import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.SquareRegion;
import sun.nio.cs.Surrogate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
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

    @Override
    public Invoice addInvoice(Invoice invoice) {
        try {
            em.persist(invoice);
            em.flush();
            return invoice;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Invoice regenerateInvoice(long invoiceid) {

        Invoice invoice = new InvoiceGenerator().regenerateInvoice(em.find(Invoice.class, invoiceid));
        try {
            em.merge(invoice);
            return invoice;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Invoice getInvoiceById(long id) {
        try {
            Invoice invoice = em.createNamedQuery("Invoice.GetById", Invoice.class).setParameter("invoiceId", id).getSingleResult();
            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            return invoiceGenerator.regenerateInvoice(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
