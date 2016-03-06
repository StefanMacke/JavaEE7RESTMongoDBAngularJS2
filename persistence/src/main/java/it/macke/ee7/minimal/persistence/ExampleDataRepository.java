package it.macke.ee7.minimal.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.macke.ee7.minimal.domain.Attendee;
import it.macke.ee7.minimal.domain.Talk;

@ApplicationScoped
public class ExampleDataRepository
{
	@PersistenceContext
	private EntityManager em;

	@Transactional
			void addExampleTalks(@Observes @Initialized(ApplicationScoped.class) final Object event)
	{
		try
		{
			if (em.createQuery("SELECT COUNT(t) FROM Talk t", Long.class).getSingleResult() == 0)
			{
				em.persist(new Talk("Java EE 7", "Fun with Java EE 7"));
				em.persist(new Talk("More Java EE 7", "More Fun with Java EE 7"));
				em.persist(new Talk("Even More Java EE 7", "Even More Fun with Java EE 7"));
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	@Transactional
			void addExampleAttendees(@Observes @Initialized(ApplicationScoped.class) final Object event)
	{
		try
		{
			if (em.createQuery("SELECT COUNT(a) FROM Attendee a", Long.class).getSingleResult() == 0)
			{
				em.persist(new Attendee("Stefan", "Macke"));
				em.persist(new Attendee("Adam", "Bien"));
				em.persist(new Attendee("Arun", "Gupta"));
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}
}
