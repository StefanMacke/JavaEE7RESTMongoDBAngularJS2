package it.macke.ee7.minimal.persistence;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.macke.ee7.minimal.domain.Talk;

@RequestScoped
public class TalkRepository
{
	@PersistenceContext
	private EntityManager em;

	public Iterable<Talk> findAll()
	{
		return em
				.createQuery("SELECT t FROM Talk t ORDER BY t.title", Talk.class)
				.getResultList();
	}
}
