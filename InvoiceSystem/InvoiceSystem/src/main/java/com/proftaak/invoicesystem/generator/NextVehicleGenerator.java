package com.proftaak.invoicesystem.generator;

import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Singleton
public class NextVehicleGenerator {

    @PersistenceContext
    private EntityManager em;

    /**
     * Provide the date parameter "before" to indicate which vehicles you want to select.
     * One of the vehicles that are processed before that date will be selected.
     *
     * This method is also threadsafe
     * @param before the date in which the new period is started.
     * @return
     */
    public synchronized String getNextVehicleId(Date before, Date now){

        //acquire a lock in this transaction
        List<VehicleProcessingState> stateList = em.createNamedQuery("VehicleProcessingState.get").setParameter("lastProcessed", before).setLockMode(LockModeType.PESSIMISTIC_WRITE).setMaxResults(1).getResultList();
        if(stateList.size() == 1){
            VehicleProcessingState state = stateList.get(0);
            state.setLastProcessed(now);
            em.merge(state);
            em.flush();
            return state.getVehicleChassis();
        }
        return "";
    }
}
