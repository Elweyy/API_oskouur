package org.lpro.boundary;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.lpro.entity.Commande;

@Stateless
@Transactional
public class CommandeRessource 
{
    @PersistenceContext
    EntityManager em;

    public Commande findById(String id) 
    {
        return this.em.find(Commande.class, id);
    }

    public List<Commande> findAll() 
    {
        Query q = this.em.createQuery("SELECT c FROM Commande c");
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Commande save(Commande c) 
    {

        c.setId(UUID.randomUUID().toString());

        return this.em.merge(c);
    }
}
