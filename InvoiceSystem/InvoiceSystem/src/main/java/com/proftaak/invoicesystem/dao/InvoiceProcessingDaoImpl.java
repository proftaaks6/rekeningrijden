package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.generator.InvoiceGenerator;
import com.proftaak.invoicesystem.models.Invoice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class InvoiceProcessingDaoImpl implements InvoiceProcessingDao{

    @PersistenceContext
    private EntityManager em;

    @Inject
    private InvoiceGenerator invoiceGenerator;

    @Override
    public boolean markAsPaid(int invoiceId) {
        try {
            Invoice i = em.createNamedQuery("Invoice.GetById", Invoice.class).setParameter("invoiceId", invoiceId).getSingleResult();
            i.setPaid(true);

            em.merge(i);

            return true;
        } catch (Exception e) {


            return false;
        }
    }

    @Override
    public List<Invoice> getInvoicesForUser(long userId, String[] chassisNumbers) {
        List<Invoice> invoices = new ArrayList<>();

        for (String chassis : chassisNumbers) {
            try {
                List<Invoice> invoicesForVehicle = em
                        .createNamedQuery("Invoice.GetByVehicleChassisAndUser", Invoice.class)
                        .setParameter("chassis", chassis)
                        .setParameter("userId", userId)
                        .getResultList();
                invoices.addAll(invoicesForVehicle);
            } catch (Exception e) {
               return new ArrayList<>();
            }
        }

        return invoices;
    }

    @Override
    public boolean markForGeneration(String[] chassisNumbers) {
        for (String chassis : chassisNumbers) {
            try {
                Query q = em.createNativeQuery("UPDATE tbl_vehicleproccessing SET LASTPROCESSED = DATE_SUB(curdate(), INTERVAL 1 MONTH) WHERE VEHICLECHASSIS = ?1");
                q.setParameter(1, chassis);
                q.executeUpdate();
            } catch (Exception e) {

                return false;
            }
        }

        return true;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        try {
            em.persist(invoice);
            em.flush();
            return invoice;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public Invoice regenerateInvoice(long invoiceid) {

        Invoice invoice = invoiceGenerator.regenerateInvoice(em.find(Invoice.class, invoiceid));
        try {
            em.merge(invoice);
            return invoice;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Invoice getInvoiceById(long id) {
        try {
            return em.createNamedQuery("Invoice.GetById", Invoice.class).setParameter("invoiceId", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
